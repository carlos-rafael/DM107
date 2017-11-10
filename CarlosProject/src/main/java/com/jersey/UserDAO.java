package com.jersey;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class UserDAO {


	//m�todo que retorna uma entrega com base no n�mero do pedido enviado como par�metro de entrada na fun��o
		public static boolean validUser(Connection conn, String user, String password) throws SQLException{
			boolean flag = false;
			
			System.out.println("user and pasword: "+ user + " "+password );
			//query sql que ser� utilizada para inserir a nova entrega no banco de nome "entregas"
			String sql = "select userInfo, passwordInfo from user;";
//			Statement pstm = null;
//			ResultSet results = pstm.executeQuery(sql);
			
			PreparedStatement pstm1;
			pstm1 = conn.prepareStatement(sql);
			
			//query � executada
			ResultSet results = pstm1.executeQuery();
			//um novo objeto entrega � instanciado 

			 while (results.next()) {
				 
			        String usernameContent = results.getString("userInfo");
			        String passwordContent =  results.getString("passwordInfo");
			        System.out.println("entrou, "+usernameContent+" "+passwordContent);
			           if ((user.equals(usernameContent)) && (password.equals(passwordContent))) {
			              flag = true;
			              return flag;
			        } 
			        results.close();
			        if(!flag){
			               return false;
			        }
			 
			 }
			 return flag;
			 }


		//m�todo que retorna uma entrega com base no n�mero do pedido enviado como par�metro de entrada na fun��o
			public static boolean validUser1(Connection conn, String user, String password) throws SQLException{
				boolean flag = false;
				
				System.out.println("user and pasword: "+ user + " "+password );
				//query sql que ser� utilizada para inserir a nova entrega no banco de nome "entregas"
				String sql = "select * from user where userInfo=? and passwordInfo=?;";
//				Statement pstm = null;
//				ResultSet results = pstm.executeQuery(sql);
				
				PreparedStatement pstm1;
				pstm1 = conn.prepareStatement(sql);

				pstm1.setString(1, user);
				pstm1.setString(2, password);
				
				//query � executada
				ResultSet results = pstm1.executeQuery();
				//um novo objeto entrega � instanciado 

				 while (results.next()) {
					 
				        String usernameContent = results.getString("userInfo");
				        String passwordContent =  results.getString("passwordInfo");
				        System.out.println("entrou, "+usernameContent+" "+passwordContent);
				           if ((user.equals(usernameContent)) && (password.equals(passwordContent))) {
				              flag = true;
				              return flag;
				        } 
				        results.close();
				        if(!flag){
				               return false;
				        }
				 
				 }
				 return flag;
				 }


}

	
