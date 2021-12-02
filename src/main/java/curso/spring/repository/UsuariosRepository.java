package curso.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import curso.spring.model.Usuarios;


public interface UsuariosRepository extends JpaRepository<Usuarios, Integer>{

	
	@Query(value="select * from usuarios where email=?1", nativeQuery=true)
	Usuarios findByEmail(String email);
	
	
	@Query(value="select * from usuarios where email=?1 and clave=?2", nativeQuery=true)
	List<Usuarios> findUsuarioLogin(String email, String clave);
	
}
