package devweb.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import devweb.model.Cliente;

@RequestScoped
@ManagedBean(name="cliente")
public class ClienteDAO extends DAO{
	private String filtro = "";
	public List<Cliente> listar() throws ClassNotFoundException, SQLException {
		if (this.filtro.length() == 0) {
			return this._listar("select * from cliente;");
		}
		return this.listar(this.filtro);
		
	}
	public List<Cliente> listar(String nome) throws ClassNotFoundException, SQLException {
		return this._listar("select * from cliente where nome like '%" + nome + "%'");
	}
	
	private List<Cliente> _listar(String query) throws ClassNotFoundException, SQLException {
		List<Cliente> clientes = new ArrayList<Cliente>();
		this.open();
		Statement stmt = this.conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		while(rs.next()) {
			Cliente c = new Cliente(
					rs.getInt("id"),
					rs.getString("nome"),
			    	rs.getString("endereco"),
			  		rs.getString("bairro"),
			  		rs.getString("cep"),
			  		rs.getString("cidade"),
			  		rs.getString("estado"),
			  		rs.getString("telefone")
			);
			clientes.add(c);
		}

		this.close();
		return clientes;
	}
	public String getFiltro() {
		return filtro;
	}
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	
}
