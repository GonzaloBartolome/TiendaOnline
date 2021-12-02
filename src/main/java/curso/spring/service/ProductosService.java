package curso.spring.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.spring.model.Productos;
import curso.spring.repository.ProductosRepository;
import curso.spring.repository.UsuariosRepository;

@Service
public class ProductosService {

	@Autowired
	private ProductosRepository productRepo;


	public void editProduct(Productos producto) {

		productRepo.save(producto);
	}

	public void deleteProduct(Integer id) {

		Productos p = productRepo.getById(id);
		productRepo.delete(p);
	}

	public void addProduct(Productos producto) {

		productRepo.save(producto);
	}

	public List<Productos> getAllProducts(){

		List<Productos> listProducts = productRepo.findAll();

		return listProducts;
	}


	public Productos getProductoById(Integer id) {

		return productRepo.getById(id);

	}

	public List<Productos> getProductsByCat(Integer id_categoria) {

		List<Productos> listProducts = productRepo.getProductsByCategoria(id_categoria);
		
		return listProducts;

	}
}
