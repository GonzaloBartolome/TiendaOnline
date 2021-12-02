package curso.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.spring.model.Valoraciones;
import curso.spring.repository.ValoracionesRepository;

@Service
public class ValoracionesService {
	
	
	@Autowired
	ValoracionesRepository valRepo; 

	public void editValoracion(Valoraciones valoraciones) {

		valRepo.save(valoraciones);
	}

	public void deleteValoracion(Integer id) {

		Valoraciones c = valRepo.getById(id);
		valRepo.delete(c);
	}

	public void addValoracion(Valoraciones valoraciones) {

		valRepo.save(valoraciones);
	}

	public List<Valoraciones> getAllValoracion(){

		List<Valoraciones> listVal = valRepo.findAll();

		return listVal;
	}
	
	public Valoraciones getValoracionById(Integer id) {

		return valRepo.getById(id);

	}

}
