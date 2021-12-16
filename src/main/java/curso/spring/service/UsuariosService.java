package curso.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import curso.spring.model.Usuarios;
import curso.spring.repository.UsuariosRepository;

@Service
public class UsuariosService {

	@Autowired
	UsuariosRepository userRepo;

	public boolean checkUserLogin(Usuarios usuario) {

		boolean login = false;
		
		List<Usuarios> user = userRepo.findUsuarioLogin(usuario.getEmail(), usuario.getClave());

		if (!user.isEmpty()) {
			login = true;
		}

		return login;
	}


	public List<Usuarios> getListaUsuarios() {
		
		return userRepo.findAll();
	}
	
	public void addUsuario(Usuarios usuario) {
		userRepo.save(usuario);
	}
	
	public void delUsuario(int id) {
		Usuarios u = userRepo.getById(id);
		userRepo.delete(u);
	}
	
	public void editUsuario(Usuarios usuario) {
		userRepo.save(usuario);		
	}

	public Usuarios getUsuarioById(int id) {
		Usuarios u = userRepo.getById(id);
		return u;
	}
	
	public Integer getIdUsuarioByEmail(String email) {
		Usuarios u = userRepo.findByEmail(email);
		return u.getId();
		
	}
	
	public boolean checkUserExist(String email) {
		
		if (userRepo.findByEmail(email) != null) {
			return true;
		}
				
		return false;	
	}

}
