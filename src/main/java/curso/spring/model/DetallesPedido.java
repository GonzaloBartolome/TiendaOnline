package curso.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DetallesPedido {
	
	@Id @GeneratedValue
	private Integer id;
	private Integer id_pedido;
	private Integer id_producto;
	private Double precioUnidad;
	private Integer unidades;
	private Float impuesto;
	private Double total;

	public DetallesPedido() {
	}

	public DetallesPedido(Integer id, Integer id_pedido, Integer id_producto, Double precioUnidad, Integer unidades,
			Float impuesto, Double total) {
		super();
		this.id = id;
		this.id_pedido = id_pedido;
		this.id_producto = id_producto;
		this.precioUnidad = precioUnidad;
		this.unidades = unidades;
		this.impuesto = impuesto;
		this.total = total;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(Integer id_pedido) {
		this.id_pedido = id_pedido;
	}

	public Integer getId_producto() {
		return id_producto;
	}

	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
	}

	public Double getPrecioUnidad() {
		return precioUnidad;
	}

	public void setPrecioUnidad(Double precioUnidad) {
		this.precioUnidad = precioUnidad;
	}

	public Integer getUnidades() {
		return unidades;
	}

	public void setUnidades(Integer unidades) {
		this.unidades = unidades;
	}

	public Float getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(Float impuesto) {
		this.impuesto = impuesto;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}


	
}
