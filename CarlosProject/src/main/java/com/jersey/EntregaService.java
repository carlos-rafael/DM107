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
	//o m�todo ir� retornar json
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
				
				//resultado que ser� enviado ao cliente, envia status ok porque foi poss�vel encontrar os contatos, entity(contatos)-> retorna os contatos
				return Response.status(Response.Status.OK).entity(entregas).build();				
			}
		}
		//caso n�o consiga conectar � base de dados
		catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	*/
	
	//m�todo que retorna uma entrega com base no n�mero do pedido enviado na requisi��o
	@GET
	@Path("/entregaId/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	//o m�todo recebe um par�metro de entrada, que representa o n�mero do pedido da entrega
	public Response listContatoById(@PathParam("id") int entregaId) {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		EntregaModel entrega = null;
		try { 
			Connection conn = connectionFactory.getConnection();
			EntregaDAO entregasDAO = new EntregaDAO();
			//objeto entrega receber� o retorno do m�todo listById, presente na classe entregaDAO
			entrega = entregasDAO.listById(conn, entregaId);
			//caso a entrega exista, o cliente receber� o status ok, assim como o objeto da entrega
			return Response.status(Response.Status.OK).entity(entrega).build();
		} catch (Exception e) {
			//caso contr�rio, o cliente receber� um erro 500 (internal server error)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	//m�todo que cria uma nova entrega no banco
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create( EntregaModel entrega){
		ConnectionFactory connectionFactory = new ConnectionFactory();
		try {
			Connection conn = connectionFactory.getConnection();
			EntregaDAO entregasDAO = new EntregaDAO();
			//m�todo insert da classe EntregaDAO
			entregasDAO.insert(conn, entrega);
			//caso a opera��o seja bem-sucedida, o cliente recebe resposta 200 (sucesso) 
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			//caso contr�rio, o cliente recebe um erro 500 (internal server error)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}	
	
}
