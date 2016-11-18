package devweb.user;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="usuarioBean")
@RequestScoped
public class User {
	public String getNome() {
		return "Eae galera";
	}
}
