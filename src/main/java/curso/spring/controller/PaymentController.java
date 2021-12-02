package curso.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import curso.spring.model.MetodosPago;
import curso.spring.model.Pedidos;
import curso.spring.model.UnidadesCarrito;
import curso.spring.model.Usuarios;
import curso.spring.service.MetodosPagoService;
import curso.spring.service.PaymentService;

@Controller
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	MetodosPagoService metodoS;
	
	@Autowired
	PaymentService payS;
	
	@GetMapping("")
	public String irCarrito(HttpSession sesion) {

		ArrayList<UnidadesCarrito> carrito = (ArrayList<UnidadesCarrito>) sesion.getAttribute("carrito");

		return "payment/cart";
	}
	
	
	@GetMapping("/metodo")
	public String selectMetodo(Model model) {
		List<MetodosPago> listMetodos = metodoS.getAllMetodosPago();
		model.addAttribute("listMetodos", listMetodos);
		return "payment/metodo";
	}
	

	@GetMapping("/submit/{id}")
	public String createFactura(@PathVariable int id, HttpSession sesion, Model model) {
		
		Usuarios usuario = (Usuarios) sesion.getAttribute("usuario");
		
		Double total = (Double) sesion.getAttribute("totalCarrito");
		
		ArrayList<UnidadesCarrito> carrito = (ArrayList<UnidadesCarrito>) sesion.getAttribute("carrito");
		
		Pedidos pedido = payS.addPedido(usuario, id, "numFactura", total , "pagado");
		
		payS.addDetallesPedido(carrito, pedido);
		
		model.addAttribute("pedido", pedido);
		
		return "payment/factura";
	}
	
	@GetMapping("/end")
	public String createFactura2(HttpSession sesion) {
		
		
		sesion.setAttribute("carrito", null);
		sesion.setAttribute("totalCarrito", null);
		
		return "redirect:/";
	}
	

}
