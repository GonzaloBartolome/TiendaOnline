package curso.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MetodosPago {
	
	@Id @GeneratedValue
	private Integer id;
	private String metodoPago;

	public MetodosPago() {
	}

	public MetodosPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMetodoPago() {
		return this.metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}


}
