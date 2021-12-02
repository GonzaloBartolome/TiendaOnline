package curso.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Valoraciones {
	
	@Id @GeneratedValue
	private Integer id;
	private Integer id_producto;
	private Integer id_usuario;
	private Integer valoracion;
	private String comentario;

	public Valoraciones() {
	}

	public Valoraciones(Integer id, Integer id_producto, Integer id_usuario, Integer valoracion, String comentario) {
		super();
		this.id = id;
		this.id_producto = id_producto;
		this.id_usuario = id_usuario;
		this.valoracion = valoracion;
		this.comentario = comentario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_producto() {
		return id_producto;
	}

	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

	public Integer getValoracion() {
		return valoracion;
	}

	public void setValoracion(Integer valoracion) {
		this.valoracion = valoracion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	

}
