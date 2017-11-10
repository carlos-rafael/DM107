<?php 
use \Psr\Http\Message\ServerRequestInterface as Request; 
use \Psr\Http\Message\ResponseInterface as Response;
use slim\Middleware\HttpBasicAuthentication\PdoAuthenticator;

require '../vendor/autoload.php';

//Configuração necessária para receber os parâmetros enviados pelo postman ao utilizar basic authentication, através da super global $_SERVER
$username = $_SERVER["PHP_AUTH_USER"];
$password = $_SERVER["PHP_AUTH_PW"];

//Configurações básicas para aplicação, o user e password do banco na minha máquina é root
$config['displayErrorDetails'] = true; 
$config['addContentLengthHeader'] = false;
$config['db']['host'] = "localhost"; 
$config['db']['user'] = "root"; 
$config['db']['pass'] = "root"; 
//nome do banco onde as tabelas devem existir
$config['db']['dbname'] = "entrega";

//criação de uma instância que representa a aplicação 
$app = new \Slim\App(["config" => $config]);
$container = $app->getContainer();

//configuração do banco através de PDO
$container['db'] = function ($c) { 

    $dbConfig = $c['config']['db']; 
    $pdo = new PDO("mysql:host=" . $dbConfig['host'] . ";dbname=" . $dbConfig['dbname'], $dbConfig['user'], $dbConfig['pass']); 
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION); 
    $pdo->setAttribute(PDO::ATTR_DEFAULT_FETCH_MODE, PDO::FETCH_ASSOC); 
    $db = new NotORM($pdo); 
    return $db; 

};

//Query para seleção de registros na tabela user 
$usersInfo = $container['db']->user->where("userInfo = ?", $username)
->where("passwordInfo = ?", $password);
//passa um array que contenha todos os usuários e passwords, no nosso caso, a tabela só possui um registro: nome (admin2), senha (admin2)
$res = array();
$res["userInfo"] = "";
$res["passwordInfo"] = "";
//faz o mapeamento dos valores presentes no retorno do método get na tabela user, para um array auxiliar res
foreach ($usersInfo as $userInfo) {
$res["userInfo"] = $userInfo["userInfo"];
$res["passwordInfo"] = $userInfo["passwordInfo"];
}

//autenticaçao da aplicaçao utilizando basic authentication
$app->add(new Tuupola\Middleware\HttpBasicAuthentication([
    "users" => [
        $res["userInfo"] => $res["passwordInfo"]
    ],
    "error" => function ($request, $response, $arguments) {
        $data["status"] = "error";
        $data["message"] = $arguments["message"];
        return $response
            ->withHeader("Content-Type", "application/json")
            ->write(json_encode($data, JSON_UNESCAPED_SLASHES | JSON_PRETTY_PRINT));
    }
]));

//método get, criado para facilitar os testes na validação do escopo do projeto
$app->get('/api/entregas/{id}', function(Request $request, Response $response){ 
    //Retorna a entrega referente ao id inserido pelo usuário na url 
    $idEntrega = $request->getAttribute("id");
    $entrega = $this->db->entregas("id = ?", $idEntrega)->fetch();
    //retorna a entrega em formato json
    return $response->withJson($entrega);
});

//método para deletar uma entrega
$app->delete('/api/entrega/{id}', function(Request $request, Response $response){
    //Retorna a entrega referente ao id inserido pelo usuário na url   
    $idEntrega = $request->getAttribute("id");
    $entrega = $this->db->entregas("id = ?", $idEntrega)->fetch();
    //se a entrega existe deleta a mesma
    if ($entrega->fetch()) {
    $deleted = $entrega->delete();
    } 
    return $entrega;
});

//método para atualizar uma entrega
$app->put('/api/entrega/{id}', function(Request $request, Response $response){
    //Retorna a entrega referente ao id inserido pelo usuário na url   
    $idEntrega = $request->getAttribute("id");
    $entrega = $this->db->entregas("id = ?", $idEntrega)->fetch();
    
    //recebe o body da requisição, ou seja, as novas informações da entrega a ser atualizada
    /*Utilizar este modelo no postman para testar este método(atentar-se ao formato dos dados (principalmente o da data)):
    {
    "id": "16",
    "numPedido": "10",
    "idCliente": "3",
    "nomeRecebedor": "Aluno",
    "cpfRecebedor": "12341234",
    "dataEntrega": "2017-10-10",
    "horaEntrega": "16:00"  
    }*/
    $json = $request->getParsedBody();
    
    $updated = null;
    if ($entrega->fetch()) {
    //$tarefaUpdate=($json); //um array com os dados da tarefa
    $updated = $entrega->update($json);
    }
    return $updated;
});

$app->run(); 

?>

