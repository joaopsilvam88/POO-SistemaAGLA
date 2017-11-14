package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Herco
 * Classe para criar uma conexao com o banco de dados que está na pasta bd
 * 
 * */
public class ConnectionFactory {
	
	/**
	 * Método usado para criar a conexao
	 * 
	 * */
	public Connection getConnection() throws SQLException {
		
		if(System.getProperty("os.name").equalsIgnoreCase("linux"))
			return DriverManager.getConnection("jdbc:sqlite:bd//agla.db");
			
		return DriverManager.getConnection("jdbc:sqlite:bd\\agla.db");
	}
	
}
