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

import curso.spring.model.Productos;
import curso.spring.model.UnidadesCarrito;
import curso.spring.service.ProductosService;
import curso.spring.service.UnidadesCarritoService;


@Controller
@RequestMapping("/productos")
public class ProductosController {


	@Autowired
	ProductosService productoS;

	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable int id) {
		
		productoS.deleteProduct(id);
		
		return "redirect:/producto/listar";
	}
	
	@GetMapping("/edit/{id}")
	public String actualizacionForm(@PathVariable int id, Model model) {
		
		Productos p = productoS.getProductoById(id);
		model.addAttribute("productos", p);
		
		return "productos/edit";
	}
	
	@PostMapping("/edit/submit")
	public String actualizar(@ModelAttribute Productos producto) {
		productoS.editProduct(producto);
		return "redirect:/productos/list";
		
	}
	
	@GetMapping("/add")
	public String addProduct(Model model) {

		model.addAttribute("producto", new Productos());

		return "productos/new";
	}

	@PostMapping("/add/submit")
	public String submitProduct(@ModelAttribute Productos producto) {

		productoS.addProduct(producto);

		return "redirect:/productos/list";	
	}	

	@GetMapping("/list")
	public String listProducts(Model model) {

		List<Productos> productList = productoS.getAllProducts();

		model.addAttribute("productList", productList);

		return "productos/list";
	}

	@GetMapping("/addCarrito/{id}")
	public String addProductCarrito(HttpSession session, Model model, @PathVariable int id) {

		UnidadesCarritoService uCarritoS = new UnidadesCarritoService();

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

	@GetMapping("/detail/{id}")
	public String verDetalle(@PathVariable int id, Model model) {

		Productos producto = new Productos();

		producto = productoS.getProductoById(id);

		model.addAttribute("producto" , producto);

		return "productos/detail";
	}
}
