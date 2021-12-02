package com.tiendaonline.service;

import java.util.ArrayList;

import com.tiendaonline.entities.Productos;

public class Carrito {

	//private Double total;

	public Double getTotal(ArrayList<Productos> carrito) {
		
		Double total = (double) 0;
		
		for (Productos producto : carrito) {
			total+= producto.getPrecio();
		}

		return total;
	} 



}
