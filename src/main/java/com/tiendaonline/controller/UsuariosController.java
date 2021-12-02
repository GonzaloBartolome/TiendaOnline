package com.tiendaonline.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tiendaonline.entities.Productos;
import com.tiendaonline.service.Carrito;
import com.tiendaonline.service.ProductosService;

@Controller
@RequestMapping("/usuario")
public class UsuariosController {
	
	@GetMapping("/login")
	public String addProductCarrito(HttpSession sesion, Model model, @RequestParam String email, @RequestParam String pass) {
		
		sesion.setAttribute("email", email);
		
		ProductosService productosService = new ProductosService();
		
		ArrayList<Productos> productList = productosService.getAllProducts();
		
		model.addAttribute("productList", productList);
		
		ArrayList<Productos> carrito = (ArrayList<Productos>) sesion.getAttribute("carrito");

		Carrito totalCarrito = new Carrito();

		Double total = totalCarrito.getTotal(carrito);

		model.addAttribute("total", total);
		
		return "catalog";
		
		
	}

}
