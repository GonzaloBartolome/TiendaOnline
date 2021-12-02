package com.tiendaonline.controller;

import java.util.ArrayList;
import java.util.List;

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

@Controller
@RequestMapping("")
public class InicialController {
	
	//@Autowired
	ProductosService productosService = new ProductosService();
	
	@GetMapping("")
	public String inicial(Model model, HttpSession sesion) {
		
		ArrayList<UnidadesCarrito> carrito = new ArrayList<UnidadesCarrito>();
		sesion.setAttribute("carrito", carrito);
	
		return "redirect:/index";
	} 
	
	
	@GetMapping("/index")
	public String index (Model model, HttpSession sesion) {
		
		//ProductosService productosService = new ProductosService();
		List<Productos> productList = productosService.getAllProductos();
		
		model.addAttribute("productList", productList);
		
		return "index";
	}
	
	
}
