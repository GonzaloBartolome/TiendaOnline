package curso.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import curso.spring.model.Pedidos;

public interface PedidosRepository extends JpaRepository<Pedidos, Integer>{

	@Query(value="select * from pedidos where id_usuario=?1", nativeQuery=true)
	List<Pedidos> getPedidoByUser(Integer id_usuario);

}
