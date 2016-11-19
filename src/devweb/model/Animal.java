package devweb.model;

public class Animal {
	protected String id;
	protected String nome;
	protected Cliente cliente;
	protected Especie especie;
	
	public Animal(String id, String nome, Cliente cliente, Especie especie) {
		super();
		this.id = id;
		this.nome = nome;
		this.cliente = cliente;
		this.especie = especie;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Especie getEspecie() {
		return especie;
	}

	public void setEspecie(Especie especie) {
		this.especie = especie;
	}
	
}
