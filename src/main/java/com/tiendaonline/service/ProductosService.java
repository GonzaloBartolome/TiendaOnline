package com.tiendaonline.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiendaonline.entity.Productos;
import com.tiendaonline.entity.UnidadesCarrito;
import com.tiendaonline.repository.IProductosRepository;


public class ProductosService {
	
	
	IProductosRepository productRepo;

	public void addProducto(Productos producto) {
		// TODO Auto-generated method stub
		
	}

	
	public boolean checkProduct(String nameProduct) {
		// TODO Auto-generated method stub
		return false;
	}


	
	public List<Productos> getAllProductos(){
		
		List<Productos> listProducts = productRepo.getAllProducts();
		
		return listProducts;
	}
	
	// A piñon 
	public ArrayList<Productos> getAllProducts() {
		// TODO Cambiar por Database Products
		Timestamp ts = Timestamp.from(Instant.now());
		
		ArrayList<Productos> listaProductos = new ArrayList<Productos>();
		
		Productos producto1 = new Productos(1, 1, "El Picaro 2020", "Vino Tinta de Toro", 7.50, 23, ts, null, (float) 21.0, null);
		Productos producto2 = new Productos(2, 1, "Madre Mia 2020", "Vino Tinta de Toro", 8.50, 17, ts, null, (float) 21.0, null);
		Productos producto3 = new Productos(3, 2, "Pingus 2018", "Vino Tinta de Toro", 1249.00, 6, ts, null, (float) 21.0, null);
		Productos producto4 = new Productos(4, 3, "Bigardo 2018", "Vino Tinta de Toro", 13.50, 20, ts, null, (float) 21.0, null);
		Productos producto5 = new Productos(5, 2, "Loquillo 2019", "Vino Tinta de Toro", 6.30, 14, ts, null, (float) 21.0, null);

		listaProductos.add(producto1);
		listaProductos.add(producto2);
		listaProductos.add(producto3);
		listaProductos.add(producto4);
		listaProductos.add(producto5);

		
		return listaProductos;
	}

	public Productos getProductoById(Integer id) {
		// TODO Auto-generated method stub
		
		ArrayList<Productos> listaProductos = getAllProducts();
		
		for (Productos producto : listaProductos) {
			if (producto.getId().equals(id)) {
				return producto;
			}
		}
		
		return null;
	}
}
