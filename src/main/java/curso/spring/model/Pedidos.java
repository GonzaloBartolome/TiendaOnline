package curso.spring.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pedidos {

	@Id @GeneratedValue
	private Integer id;
	private Integer id_usuario;
	private Date fecha;
	private String metodoPago;
	private String estado;
	private String numFactura;
	private Double total;

	public Pedidos() {
	}

	public Pedidos(Integer id, Integer id_usuario, Date fecha, String metodoPago, String estado, String numFactura,
			Double total) {
		super();
		this.id = id;
		this.id_usuario = id_usuario;
		this.fecha = fecha;
		this.metodoPago = metodoPago;
		this.estado = estado;
		this.numFactura = numFactura;
		this.total = total;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNumFactura() {
		return numFactura;
	}

	public void setNumFactura(String numFactura) {
		this.numFactura = numFactura;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	
}
