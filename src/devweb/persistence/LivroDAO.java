package devweb.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Categoria;
import model.Livro;
import persistence.DAO;

public class LivroDAO extends DAO{
	public Livro incluir(Livro livro) throws ClassNotFoundException, SQLException {
		this.open();
		PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO livro(nome, isbn, categoria, id) VALUES(?, ?, ?, ?);");
		stmt.setString(1, livro.nome);
		stmt.setString(2, livro.isbn);
		stmt.setInt(3, livro.categoria);
		stmt.setInt(4, livro.id);
		stmt.execute();
		this.close();
		return livro;
	}
	
	public List<Livro> listar() throws ClassNotFoundException, SQLException {
		List<Livro> livros = new ArrayList<Livro>();
		this.open();
		Statement stmt = this.conn.createStatement();
		ResultSet rs = stmt.executeQuery("select l.*, c.descricao as categorianome from livro l inner join categoria c on l.categoria = c.id;");
		while(rs.next()) {
			Categoria c = new Categoria(rs.getString("categorianome"), rs.getInt("categoria"));
			livros.add(
				new Livro(rs.getString("nome"), rs.getString("isbn"), c, rs.getInt("id"))
			);
		}
		this.close();
		return livros;
	}
	
	public List<Livro> consulta(String nome) throws ClassNotFoundException, SQLException {
		List<Livro> livros = new ArrayList<Livro>();
		this.open();
		Statement stmt = this.conn.createStatement();
		ResultSet rs = stmt.executeQuery("select l.*, c.descricao as categorianome from livro l inner join categoria c on l.categoria = c.id and l.nome like '%" + nome.toString() +"%';");
		while(rs.next()) {
			Categoria c = new Categoria(rs.getString("categorianome"), rs.getInt("categoria"));
			livros.add(
				new Livro(rs.getString("nome"), rs.getString("isbn"), c, rs.getInt("id"))
			);
		}
		this.close();
		return livros;
	}
	public Livro consulta(int id) throws ClassNotFoundException, SQLException {
		this.open();
		Statement stmt = this.conn.createStatement();
		ResultSet rs = stmt.executeQuery("select l.*, c.descricao as categorianome from livro l inner join categoria c on l.categoria = c.id and l.id = " + id + "LIMIT 1;");
		while(rs.next()) {
			Categoria c = new Categoria(rs.getString("categorianome"), rs.getInt("categoria"));
			Livro l = new Livro(rs.getString("nome"), rs.getString("isbn"), c, rs.getInt("id"));
			this.close();
			return l;
		}
		return null;
	}
}
