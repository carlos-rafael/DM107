package com.jersey;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.IOException;
import java.sql.Connection;
import java.util.Base64;
import java.util.StringTokenizer;

public class AuthenticationService {

	public boolean authenticate(String authCredentials) {
		if (null == authCredentials)
		
			return false;
			// header value format will be "Basic encodedstring" for Basic
			// authentication. Example "Basic YWRtaW46YWRtaW4="
			//rrealizou replace do base 64 por vazio, a string removida não interessa para nossa operação, 
			//por isso decodificamos a credencial passada e removemos 
			final String encodedUserPassword = authCredentials.replaceFirst("Basic"+ " ", "");
			String usernameAndPassword = null;
			try {
				byte[] decodedBytes = Base64.getDecoder().decode(encodedUserPassword);
				usernameAndPassword = new String(decodedBytes, "UTF-8");
			} catch (IOException e) {
				e.printStackTrace();
			}
			final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
			final String username = tokenizer.nextToken();
			final String password = tokenizer.nextToken();
			// we have fixed the userid and password as admin
			// call some UserService/LDAP here
			//boolean authenticationStatus = "admin".equals(username) && "admin".equals(password);
			ConnectionFactory connectionFactory = new ConnectionFactory();
			Connection conn;
			try {
				conn = connectionFactory.getConnection();
				boolean authenticationStatus = UserDAO.validUser1(conn, username,password);
				return authenticationStatus;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;



		}
		
}
