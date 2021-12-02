package curso.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import curso.spring.model.Categorias;
import curso.spring.model.Productos;
import curso.spring.service.CategoriasService;
import curso.spring.service.ProductosService;

@Controller
@RequestMapping("/buscar")
public class BusquedasController {

	@Autowired
	CategoriasService catS;

	@Autowired
	ProductosService productoS;

	
	@GetMapping("/cat")
	public String buscarCategorias(Model model) {


		List<Categorias> catList = catS.getAllCategorias();
		
		model.addAttribute("catList", catList);
		
		model.addAttribute("categoria", new Categorias());
		
		List<Productos> productList = productoS.getAllProducts();

		model.addAttribute("productList", productList);

		return "search/index";
	}

	@GetMapping("/cat/{id}")
	public String buscarByCategoria(Model model , @PathVariable int id) {


		List<Productos> productList = productoS.getProductsByCat(id);

		model.addAttribute("productList", productList);


		return "search/cat";
	}

}
