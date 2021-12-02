package curso.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Configuracion {

	@Id @GeneratedValue
	private Integer id;
	private String clave;
	private String valor;
	private String tipo;

	public Configuracion() {
	}

	public Configuracion(String clave, String valor, String tipo) {
		this.clave = clave;
		this.valor = valor;
		this.tipo = tipo;
	}

	@Id @GeneratedValue
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
