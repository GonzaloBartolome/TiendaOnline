package curso.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import curso.spring.model.Categorias;
import curso.spring.model.Productos;
import curso.spring.model.Usuarios;
import curso.spring.service.CategoriasService;

@Controller
@RequestMapping("/categorias")
public class CategoriasController {
	
	@Autowired
	CategoriasService catS;
	

	@GetMapping("/list")
	public String listCategorias(Model model) {
		model.addAttribute("list", catS.getAllCategorias());
		return "categorias/list";
	}
	
	@GetMapping("/add")
	public String addcat(Model model) {
		model.addAttribute("categoria", new Categorias());
		return "categorias/new";
	}
	
	@PostMapping("/add/submit")
	public String createCat(@ModelAttribute Categorias categoria) {
		catS.addCategoria(categoria);
		return "redirect:/categorias/list";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteCat(@PathVariable int id) {
		catS.deleteCategoria(id);
		return "redirect:/categorias/list";
	}
	
	@GetMapping("/edit/{id}")
	public String actualizacionForm(@PathVariable int id, Model model) {
		Categorias c = catS.getCategoriaById(id);
		model.addAttribute("categoria", c);
		return "categorias/edit";
	}
	
	@PostMapping("/edit/submit")
	public String actualizar(@ModelAttribute Categorias categoria) {
		catS.editCategoria(categoria);
		return "redirect:/categorias/list";

	}


}
