package my.jpa;

//import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="FAVORITES")
//@NamedQuery(name="Favorite.findAll", query="SELECT f FROM Favorite f")
public class Favorite {//implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ICE_CREAM")
	private String iceCream;

	@Id
	@Column(name="NAME")
	private String name;

	public Favorite( ) {
		super();
	}

	public String getIceCream() {
		return this.iceCream;
	}

	public void setIceCream(String iceCream) {
		this.iceCream = iceCream;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Favorite [iceCream=" + iceCream + ", name=" + name + "]";
	}

}