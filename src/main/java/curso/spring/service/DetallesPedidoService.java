package curso.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.spring.model.DetallesPedido;
import curso.spring.model.Productos;
import curso.spring.repository.DetallesPedidoRepository;
import curso.spring.repository.ProductosRepository;

@Service
public class DetallesPedidoService {

	@Autowired
	private DetallesPedidoRepository detallesRepo;


	public void editDetallePedido(DetallesPedido detallesP) {

		detallesRepo.save(detallesP);
	}

	public void deleteDetallePedido(Integer id) {

		DetallesPedido p = detallesRepo.getById(id);
		detallesRepo.delete(p);
	}

	public void addDetallePedido(DetallesPedido detallesP) {

		detallesRepo.save(detallesP);
	}

	public List<DetallesPedido> getAllDetallesPedido(){

		List<DetallesPedido> listDetallesP = detallesRepo.findAll();

		return listDetallesP;
	}


	public DetallesPedido getDetallePedidoById(Integer id) {

		return detallesRepo.getById(id);

	}

	public List<DetallesPedido> getDetallesByPedido(Integer id_pedido) {

		List<DetallesPedido> listDetallesP = detallesRepo.getDetailsByPedido(id_pedido);
		
		return listDetallesP;

	}
	
}
