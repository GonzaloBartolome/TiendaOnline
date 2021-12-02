package com.tiendaonline.entities;

public class UnidadesCarrito {
	
	Productos producto; 
	
	Integer unidades;
	

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
	

}
