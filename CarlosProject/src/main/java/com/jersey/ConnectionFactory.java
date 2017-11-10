package com.jersey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection getConnection() throws SQLException {
			
		//for�a reconhecer onde est� o pacote do driver
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		//retorna uma inst�ncia de conex�o ao banco de dados, com as respectivas informa��es de acesso
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/dm107", "root", "root");
		
	}
}
