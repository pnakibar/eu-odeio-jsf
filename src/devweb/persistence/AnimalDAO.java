package devweb.persistence;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import devweb.model.Animal;
import devweb.model.Cliente;
import devweb.model.Especie;

@RequestScoped
@ManagedBean(name="animal")
public class AnimalDAO extends DAO{
	private String filtro = "";

	public List<Animal> listar() throws ClassNotFoundException, SQLException {
		if (this.filtro.length() == 0) {
			return this._listar(
					"SELECT " +
	  				"	a.id, a.nome, a.idade, a.id_especie, a.id_cliente, " +
	  				"	e.descricao as e_descricao, " +
	  				"	c.nome as c_nome, c.endereco as c_endereco, c.bairro as c_bairro, c.cep as c_cep, c.cidade as c_cidade, c.estado as c_estado, c.telefone as c_telefone " +
					"FROM animal a " +
					"INNER JOIN especie e ON e.id = a.id_especie " +
					"INNER JOIN cliente c ON c.id = a.id_cliente;"
		);
		}
		return this._listar(
			    "SELECT " +
			    "	a.id, a.nome, a.idade, a.id_especie, a.id_cliente, " +
			    "	e.descricao as e_descricao, " +
			    "	c.nome as c_nome, c.endereco as c_endereco, c.bairro as c_bairro, c.cep as c_cep, c.cidade as c_cidade, c.estado as c_estado, c.telefone as c_telefone " +
			    "FROM animal a " +
			    "INNER JOIN especie e ON e.id = a.id_especie " +
			    "INNER JOIN cliente c ON c.id = a.id_cliente " +
				  "WHERE a.nome LIKE '%" + this.filtro + "%';"
				);

	}

	private List<Animal> _listar(String query) throws ClassNotFoundException, SQLException {
		List<Animal> animais = new ArrayList<Animal>();
		this.open();
		Statement stmt = this.conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next()) {
			Cliente c = new Cliente(
					rs.getString("id_cliente"),
					rs.getString("c_nome"),
			    	rs.getString("c_endereco"),
			  		rs.getString("c_bairro"),
			  		rs.getString("c_cep"),
			  		rs.getString("c_cidade"),
			  		rs.getString("c_estado"),
			  		rs.getString("c_telefone")
			);
			Especie e = new Especie(
					rs.getString("id_especie"),
					rs.getString("e_descricao")
			);
			Animal a = new Animal(
					rs.getString("id"),
					rs.getString("nome"),
					c,
					e
			);

			animais.add(a);
		}

		this.close();
		return animais;
	}

	public String getFiltro() {
		return this.filtro;
	}
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
}
