package com.tiendaonline.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tiendaonline.entities.Productos;
import com.tiendaonline.service.Carrito;
import com.tiendaonline.service.ProductosService;

@Controller
@RequestMapping("/productos")
public class ProductosController {

	@GetMapping("/addCarrito")
	public String addProductCarrito(HttpSession sesion, Model model, @RequestParam Integer id) {

		ProductosService productosService = new ProductosService();

		Productos producto = productosService.getProductoById(id);

		ArrayList<Productos> carrito = (ArrayList<Productos>) sesion.getAttribute("carrito");

		if (producto!= null) {

			carrito.add(producto);

		} else {

			return "index";
		}

		sesion.setAttribute("carrito", carrito);

		Carrito totalCarrito = new Carrito();

		Double total = totalCarrito.getTotal(carrito);

		model.addAttribute("total", total);

		ProductosService productosService2 = new ProductosService();
		ArrayList<Productos> productList = productosService2.getAllProducts();

		model.addAttribute("productList", productList);

		return "index";
	} 

	@GetMapping("/pago")
	public String pagarCarrito(HttpSession sesion, Model model, @RequestParam String cart) {

		ArrayList<Productos> carrito = (ArrayList<Productos>) sesion.getAttribute("carrito");

		Carrito totalCarrito = new Carrito();

		Double total = totalCarrito.getTotal(carrito);
		
		model.addAttribute("total", total);
		
		return "pago";
	}
	
	@GetMapping("/detalle")
	public String verDetalle(@RequestParam Integer id, Model model) {
		
		ProductosService productosService = new ProductosService();

		Productos producto = productosService.getProductoById(id);
		
		model.addAttribute("producto" , producto);
		
		return "verProducto";
	}
			
}
