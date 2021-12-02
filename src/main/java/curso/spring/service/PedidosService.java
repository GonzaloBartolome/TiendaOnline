package curso.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.spring.model.Pedidos;
import curso.spring.repository.PedidosRepository;

@Service
public class PedidosService {
	
	@Autowired
	private PedidosRepository pedidosRepo;


	public void editPedido(Pedidos pedido) {

		pedidosRepo.save(pedido);
	}

	public void deletePedido(Integer id) {

		Pedidos p = pedidosRepo.getById(id);
		pedidosRepo.delete(p);
	}

	public void addPedido(Pedidos pedido) {

		pedidosRepo.save(pedido);
	}

	public List<Pedidos> getAllPedidos(){

		List<Pedidos> listPedidos = pedidosRepo.findAll();

		return listPedidos;
	}


	public Pedidos getPedidoById(Integer id) {

		return pedidosRepo.getById(id);

	}

	public List<Pedidos> getPedidosByUsuario(Integer id_pedido) {

		List<Pedidos> listPedidos = pedidosRepo.getPedidoByUser(id_pedido);
		
		return listPedidos;

	}

}
