package com.tiendaonline.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tiendaonline.entity.Productos;
import com.tiendaonline.entity.UnidadesCarrito;
import com.tiendaonline.service.ProductosService;
import com.tiendaonline.service.UnidadesCarritoService;

@Controller
@RequestMapping("/productos")
public class ProductosController {

	@Autowired
	ProductosService productoS;
	
	UnidadesCarritoService uCarritoS = new UnidadesCarritoService();
		
	
	@GetMapping("/addCarrito")
	public String addProductCarrito(HttpSession session, Model model, @RequestParam Integer id) {

		//ProductosService productoS = new ProductosService();
		
		//UnidadesCarritoService uCarritoS = new UnidadesCarritoService();
		
		//Productos producto = new Productos();
		
		UnidadesCarrito linea = new UnidadesCarrito();
		
		ArrayList<UnidadesCarrito> carrito = (ArrayList<UnidadesCarrito>) session.getAttribute("carrito");

		Productos producto = productoS.getProductoById(id);

		if (producto != null) {

			linea = uCarritoS.getLineaByProduct(carrito, producto);

			if (linea != null) {
				
				linea.setUnidades(linea.getUnidades()+1);
				
			} else {
				
				linea = uCarritoS.crearLineaCarrito(producto);
				carrito.add(linea);
			}
		} 
		
		session.setAttribute("totalCarrito", uCarritoS.getTotalCarrito(carrito));

		return "redirect:/index";
	} 

	@GetMapping("/pago")
	public String pagarCarrito(HttpSession sesion, Model model, @RequestParam String cart) {

		ArrayList<UnidadesCarrito> carrito = (ArrayList<UnidadesCarrito>) sesion.getAttribute("carrito");

		return "pago";
	}

	@GetMapping("/detalle")
	public String verDetalle(@RequestParam Integer id, Model model) {

		Productos producto = new Productos();
		
		producto = productoS.getProductoById(id);

		model.addAttribute("producto" , producto);

		return "verProducto";
	}



}
