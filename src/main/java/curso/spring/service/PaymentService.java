package curso.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import curso.spring.model.Configuracion;
import curso.spring.model.DetallesPedido;
import curso.spring.model.MetodosPago;
import curso.spring.model.Pedidos;
import curso.spring.model.UnidadesCarrito;
import curso.spring.model.Usuarios;
import curso.spring.repository.ConfiguracionRepository;

@Service
public class PaymentService {
	
	@Autowired
	ConfiguracionService configS;
	
	@Autowired
	PedidosService pedidoS;
	
	@Autowired
	DetallesPedidoService detalleS;
	
	@Autowired
	MetodosPagoService metodoS;
	
	@Autowired
	UsuariosService userS;
	
	
	public String crearNumFactura(String clave) {
		
		List<Configuracion> config = configS.getConfigByClave(clave);

		if (config == null) {
			
			return "que cojones";
		}
		
		for (Configuracion configuracion : config) {
			
			String valor = configuracion.getValor();
			
			int contador = Integer.parseInt(configuracion.getValor()) + 1;
			
			configuracion.setValor(Integer.toString(contador));
			
			configS.editConfiguracion(configuracion);
			
			
			return valor;
		}
		
		return null;	
	}
	
	public String getMetodoPagoById(Integer id) {
		
		MetodosPago metodo = metodoS.getMetodosPagoById(id);
		
		return metodo.getMetodoPago();
			
	}
	
	
	public Pedidos addPedido(Usuarios usuario, Integer id, String clave, Double total, String estado) {
		
		Pedidos pedido = new Pedidos();
		
		pedido.setMetodoPago(getMetodoPagoById(id));
		pedido.setId_usuario(userS.getIdUsuarioByEmail(usuario.getEmail()));
		pedido.setNumFactura(crearNumFactura(clave));
		pedido.setEstado(estado);
		pedido.setTotal(total);
		
		pedidoS.addPedido(pedido);
		
		return pedido;
			
	}
	
	public void addDetallesPedido(ArrayList<UnidadesCarrito> carrito, Pedidos pedido) {
		

		for (UnidadesCarrito unidades : carrito) {
			
			DetallesPedido detalles = new DetallesPedido();
			
			detalles.setId_pedido(pedido.getId());	
			detalles.setId_producto(unidades.getProducto().getId());
			detalles.setImpuesto(unidades.getProducto().getImpuesto());
			detalles.setPrecioUnidad(unidades.getProducto().getPrecio());
			detalles.setUnidades(unidades.getUnidades());
			detalles.setTotal(unidades.getUnidades() * unidades.getProducto().getPrecio());
			
			detalleS.addDetallePedido(detalles);

		}
		
		
	}
	

}
