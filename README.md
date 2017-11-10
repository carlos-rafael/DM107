Projeto Final DM107

Notas PHP
- Para realização do projeto realizei o download do arquivo https://getcomposer.org/download/1.5.1/composer.phar e o copiei na pasta src.
- Para realizar o download do Slim, utilizei o seguinte comando dentro da pasta src: "C:\xampp\php\php" composer.phar require slim/slim
- Para manipular dados do bancoo de dados, foi utilizada a bilioteca NotORM, inserido ao projeto com o comando: "C:\xampp\php\php" composer.phar require vrana/notorm:dev-master.
- Para instalar o middleware de autenticação, foi utilizado o seguinte comando: "C:\xampp\php\php" composer.phar require tuupola/slim-basic-auth:3.0.0-rc.2

Conteúdo presente no arquivo .htaccess:
RewriteEngine On
RewriteCond %{REQUEST_FILENAME} !-f
RewriteRule ^ index.php [QSA,L]

