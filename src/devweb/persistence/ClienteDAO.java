package devweb.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import devweb.model.Cliente;

@RequestScoped
@ManagedBean(name="cliente")
public class ClienteDAO extends DAO{
	private String filtro = "";
	private String novonome = "";
	private String novoendereco = "";
	private String novobairro = "";
	private String novocep = "";
	private String novocidade = "";
	private String novoestado = "";
	private String novotelefone = "";
	private String novoid = "";
	private String msg;

	public String getNovoid() {
		return novoid;
	}
	public void setNovoid(String novoid) {
		this.novoid = novoid;
	}
	public List<Cliente> listar() throws ClassNotFoundException, SQLException {
		if (this.filtro.length() == 0) {
			return this._listar("select * from cliente;");
		}
		return this._listar("select * from cliente where nome like '%" + this.filtro + "%'");

	}

	private List<Cliente> _listar(String query) throws ClassNotFoundException, SQLException {
		List<Cliente> clientes = new ArrayList<Cliente>();
		this.open();
		Statement stmt = this.conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		while(rs.next()) {
			Cliente c = new Cliente(
					rs.getString("id"),
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

	public String inserir() throws ClassNotFoundException, SQLException {
	    Cliente novoCliente = new Cliente(
		    this.novoid,
		    this.novonome,
		    this.novoendereco,
		    this.novobairro,
		    this.novocep,
		    this.novocidade,
		    this.novoestado,
		    this.novotelefone
	    );
	    
		this.open();
		PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO public.cliente(nome, endereco, bairro, cep, cidade, estado, telefone) VALUES (?, ?, ?, ?, ?, ?, ?);");
	    stmt.setString(1, this.novonome);
	    stmt.setString(2, this.novoendereco);
	    stmt.setString(3, this.novobairro);
	    stmt.setString(4, this.novocep);
	    stmt.setString(5, this.novocidade);
	    stmt.setString(6, this.novoestado);
	    stmt.setString(7, this.novotelefone);
  		stmt.execute();
  		this.close();

  		this.novoid = "";
	    this.novonome = "";
	    this.novoendereco = "";
	    this.novobairro = "";
	    this.novocep = "";
	    this.novocidade = "";
	    this.novoestado = "";
	    this.novotelefone = "";
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Inserido com sucesso", ""));
		return "criarCliente";
	}
	
	public String getNovonome() {
		return novonome;
	}
	public void setNovonome(String novonome) {
		this.novonome = novonome;
	}
	public String getNovoendereco() {
		return novoendereco;
	}
	public void setNovoendereco(String novoendereco) {
		this.novoendereco = novoendereco;
	}
	public String getNovobairro() {
		return novobairro;
	}
	public void setNovobairro(String novobairro) {
		this.novobairro = novobairro;
	}
	public String getNovocep() {
		return novocep;
	}
	public void setNovocep(String novocep) {
		this.novocep = novocep;
	}
	public String getNovocidade() {
		return novocidade;
	}
	public void setNovocidade(String novocidade) {
		this.novocidade = novocidade;
	}
	public String getNovoestado() {
		return novoestado;
	}
	public void setNovoestado(String novoestado) {
		this.novoestado = novoestado;
	}
	public String getNovotelefone() {
		return novotelefone;
	}
	public void setNovotelefone(String novotelefone) {
		this.novotelefone = novotelefone;
	}
	public String getFiltro() {
		return filtro;
	}
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	


}
