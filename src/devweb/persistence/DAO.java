package devweb.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAO {
	protected Connection conn;
	protected PreparedStatement stmt;
	protected Connection open() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver"); 
		this.conn = DriverManager.getConnection(
				"jdbc:postgresql://127.0.0.1:5432/postgres",
				"postgres",
				"myverysecretpassword");
		return this.conn;
	}
	
	protected Connection close() throws SQLException {
		this.conn.close();
		return this.conn;
	}

}
