package curso.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import curso.spring.model.Productos;

@Repository
public interface ProductosRepository extends JpaRepository<Productos, Integer> {
	
	@Query(value="select * from productos where id_categoria=?1", nativeQuery=true)
	List<Productos> getProductsByCategoria(Integer id_categoria);

}
