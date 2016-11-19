package devweb.model;

public class Especie {
	protected String id;
	protected String descricao;
	public Especie(String id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
