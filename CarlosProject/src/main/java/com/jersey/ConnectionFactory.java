package com.jersey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection getConnection() throws SQLException {
			
		//força reconhecer onde está o pacote do driver
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		//retorna uma instância de conexão ao banco de dados, com as respectivas informações de acesso
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/dm107", "root", "root");
		
	}
}
