package com.tiendaonline.service;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiendaonline.entity.Productos;
import com.tiendaonline.entity.UnidadesCarrito;



@Service
public class UnidadesCarritoService {
	
	
	// Añadir linea de pedido al carrito
	public UnidadesCarrito crearLineaCarrito(Productos producto) {
		UnidadesCarrito linea = new UnidadesCarrito();
		linea.setProducto(producto);
		linea.setUnidades(1);
		linea.setTotal(producto.getPrecio());
	
		return linea;
	}
	
	public Double calcularTotal(UnidadesCarrito linea) {
		
		Double total = linea.getProducto().getPrecio() * linea.getUnidades();
		return total;
	}
	
	
	//---------- Carrito
	//Get total de carrito y de pedido
	public Double getTotalCarrito(ArrayList<UnidadesCarrito> carrito) {
		
		Double total = (double) 0;
		
		for (UnidadesCarrito linea : carrito) {
			total += linea.getUnidades() * linea.getProducto().getPrecio();
		}	
		return total;
	} 
	
	//Get Linea de Pedido por producto
	public UnidadesCarrito getLineaByProduct(ArrayList<UnidadesCarrito> carrito, Productos producto) {
		for (UnidadesCarrito linea : carrito) {
			if (linea.getProducto().getId() == producto.getId()) {
				return linea;
			}
		}
		return null;
	}
}
