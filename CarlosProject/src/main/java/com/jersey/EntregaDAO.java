package com.jersey;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//esta classe trata as operações com o banco de dados
public class EntregaDAO {
	
	//método para inserção de uma entrega no banco
	public void insert(Connection conn, EntregaModel entrega) throws SQLException{
		//query sql que será utilizada para inserir a nova entrega no banco de nome "entregas"
		String sql = "insert into entregas (numPedido, idCliente, nomeRecebedor, cpfRecebedor, dataEntrega, horaEntrega) values (?,?,?,?,?,?);";
		PreparedStatement pstm;
		pstm = conn.prepareStatement(sql);
		//a query sql recebe os parâmetros presentes no objeto entrega recebido como parâmetro no início da função
		pstm.setInt(1, entrega.getNumPedido());
		pstm.setInt(2, entrega.getIdCliente());
		pstm.setString(3, entrega.getNomeRecebedor());
		pstm.setInt(4, entrega.getCpfRecebedor());
		pstm.setDate(5, entrega.getDataEntrega());
		pstm.setString(6, entrega.getHoraEntrega());
		//execução da query
		pstm.execute();
	}
	
	/*Este método foi criado para fins de debug
	 * 
	 * 
	public List<EntregaModel> list(Connection conn) throws SQLException{
		//Statement quando não é passado nenhum parâmetro na query
		String sql = "select * from entregas";
		Statement stm = conn.createStatement();
		//objeto que possui todos os dados capturados na base de dados
		ResultSet rs = stm.executeQuery(sql);
		EntregaModel entrega;
		List<EntregaModel> entregas = new ArrayList<>();
		while (rs.next())
		{
			System.out.println("entrou no while ");

			int id = rs.getInt("id");
			System.out.println("id "+ id);
			int numPedido = rs.getInt("numPedido");
			System.out.println("numPedido "+ numPedido);
			int idCliente = rs.getInt("idCliente");
			System.out.println("idCliente "+ idCliente);
			String nomeRecebedor = rs.getString("nomeRecebedor");
			System.out.println("nomeRecebedor "+ nomeRecebedor);
			int cpfRecebedor = rs.getInt("cpfRecebedor");
			System.out.println("cpfRecebedor "+ cpfRecebedor);
			Date dataEntrega = rs.getDate("dataEntrega");
			System.out.println("dataEntrega "+ dataEntrega);
			String horaEntrega = rs.getString("horaEntrega");
			System.out.println("horaEntrega "+ horaEntrega);
			

			
			entrega = new EntregaModel(id, numPedido, idCliente, nomeRecebedor, cpfRecebedor, dataEntrega, horaEntrega);
			entregas.add(entrega);

		}
		System.out.println("saiu do while, entregas, "+entregas);
		return entregas;
	}*/
	
	//método que retorna uma entrega com base no número do pedido enviado como parâmetro de entrada na função
	public EntregaModel listById(Connection conn, int numBusca) throws SQLException{
		//query sql que será utilizada para inserir a nova entrega no banco de nome "entregas"
		String sql = "select * from entregas where numPedido = ?";
		PreparedStatement pstm;
		pstm = conn.prepareStatement(sql);
		pstm.setInt(1, numBusca);
		//query é executada
		ResultSet rs = pstm.executeQuery();
		//um novo objeto entrega é instanciado 
		EntregaModel entrega = new EntregaModel();
		while (rs.next())
		{
			int id = rs.getInt("id");
			System.out.println("id "+ id);
			int numPedido = rs.getInt("numPedido");
			System.out.println("numPedido "+ numPedido);
			int idCliente = rs.getInt("idCliente");
			System.out.println("idCliente "+ idCliente);
			String nomeRecebedor = rs.getString("nomeRecebedor");
			System.out.println("nomeRecebedor "+ nomeRecebedor);
			int cpfRecebedor = rs.getInt("cpfRecebedor");
			System.out.println("cpfRecebedor "+ cpfRecebedor);
			Date dataEntrega = rs.getDate("dataEntrega");
			System.out.println("dataEntrega "+ dataEntrega);
			String horaEntrega = rs.getString("horaEntrega");
			System.out.println("horaEntrega "+ horaEntrega);
			
			//objeto entrega recebe as informações da entrega retornada pelo banco
			entrega = new EntregaModel(id, numPedido, idCliente, nomeRecebedor, cpfRecebedor, dataEntrega, horaEntrega);
		}
		//objeto entrega é retornado
		return entrega;
	}
}
