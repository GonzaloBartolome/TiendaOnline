package com.tiendaonline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiendaonline.entity.Productos;

@Repository
public interface IProductosRepository extends JpaRepository<Productos, Integer>{

	void addProducto(Productos producto);

	boolean checkProduct(String nameProduct);

	List<Productos> getAllProducts();

	Productos getProductoById(Integer id);

}
