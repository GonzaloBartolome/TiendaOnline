package com.tiendaonline.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tiendaonline.entities.Productos;
import com.tiendaonline.service.ProductosService;

@Controller
@RequestMapping("")
public class InicialController {
	
	@GetMapping("")
	public String inicial(Model model, HttpSession sesion) {
		
		ArrayList<Productos> carrito = new ArrayList<Productos> ();
		sesion.setAttribute("carrito", carrito);
		
		ProductosService productosService = new ProductosService();
		ArrayList<Productos> productList = productosService.getAllProducts();
		
		model.addAttribute("productList", productList);
	
		return "index";
	} 
}
