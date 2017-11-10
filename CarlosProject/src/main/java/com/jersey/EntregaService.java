package com.jersey;

import java.sql.Connection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//valor do recurso deve ser um substantivo
@Path("/apiEntregas")
public class EntregaService {
   
	/* Utilizado somente para debug
	@GET
	//adiciona no recurso esse valor
	@Path("/entregas")
	//o método irá retornar json
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAllContatos() {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		List<EntregaModel> entregas = null;
		try {
			Connection conn = connectionFactory.getConnection();
			EntregaDAO EntregasDAO = new EntregaDAO();
			entregas = EntregasDAO.list(conn);
			
			if (entregas == null){
				return Response.status(Response.Status.NOT_FOUND).build();
			}else{
				
				//resultado que será enviado ao cliente, envia status ok porque foi possível encontrar os contatos, entity(contatos)-> retorna os contatos
				return Response.status(Response.Status.OK).entity(entregas).build();				
			}
		}
		//caso não consiga conectar à base de dados
		catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	*/
	
	//método que retorna uma entrega com base no número do pedido enviado na requisição
	@GET
	@Path("/entregaId/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	//o método recebe um parâmetro de entrada, que representa o número do pedido da entrega
	public Response listContatoById(@PathParam("id") int entregaId) {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		EntregaModel entrega = null;
		try { 
			Connection conn = connectionFactory.getConnection();
			EntregaDAO entregasDAO = new EntregaDAO();
			//objeto entrega receberá o retorno do método listById, presente na classe entregaDAO
			entrega = entregasDAO.listById(conn, entregaId);
			//caso a entrega exista, o cliente receberá o status ok, assim como o objeto da entrega
			return Response.status(Response.Status.OK).entity(entrega).build();
		} catch (Exception e) {
			//caso contrário, o cliente receberá um erro 500 (internal server error)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	//método que cria uma nova entrega no banco
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create( EntregaModel entrega){
		ConnectionFactory connectionFactory = new ConnectionFactory();
		try {
			Connection conn = connectionFactory.getConnection();
			EntregaDAO entregasDAO = new EntregaDAO();
			//método insert da classe EntregaDAO
			entregasDAO.insert(conn, entrega);
			//caso a operação seja bem-sucedida, o cliente recebe resposta 200 (sucesso) 
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			//caso contrário, o cliente recebe um erro 500 (internal server error)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}	
	
}
