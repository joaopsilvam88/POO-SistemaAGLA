package br.com.agla.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Herco
 * Classe para criar uma conexao com o banco de dados que est� na pasta bd
 * 
 * */
public class ConnectionFactory {
	
	/**
	 * M�todo usado para criar a conexao
	 * 
	 * */
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:sqlite:bd\\agla.db");
	}
	
}
