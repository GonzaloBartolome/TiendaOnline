package com.tiendaonline.repository;

import java.util.List;

import com.tiendaonline.entities.Productos;

public interface IProductosRepository {

	void addProducto(Productos producto);

	boolean checkProduct(String nameProduct);

	List<Productos> getAllProducts();

	Productos getProductoById(Integer id);

}
