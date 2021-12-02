package curso.spring.model;


public class UnidadesCarrito {

	Productos producto; 

	Integer unidades;

	Double total;


	public UnidadesCarrito() {
		super();
	}

	public UnidadesCarrito(Productos producto, Integer unidades) {
		super();
		this.producto = producto;
		this.unidades = unidades;
	}

	public Productos getProducto() {
		return producto;
	}

	public Integer getUnidades() {
		return unidades;
	}

	public void setProducto(Productos producto) {
		this.producto = producto;
	}

	public void setUnidades(Integer unidades) {
		this.unidades = unidades;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	} 
}
