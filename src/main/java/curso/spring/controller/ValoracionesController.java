package curso.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import curso.spring.model.Configuracion;
import curso.spring.model.Valoraciones;
import curso.spring.service.ConfiguracionService;
import curso.spring.service.ValoracionesService;

@Controller
@RequestMapping("/valoraciones")
public class ValoracionesController {
	

	@Autowired
	ValoracionesService valS;
	
	@GetMapping("/list")
	public String listValoraciones(Model model) {
		model.addAttribute("list", valS.getAllValoracion());
		return "valoraciones/list";
	}
	
	@GetMapping("/add")
	public String addValoracion(Model model) {
		model.addAttribute("valoracion", new Valoraciones());
		return "valoraciones/new";
	}
	
	@PostMapping("/add/submit")
	public String createValoracion(@ModelAttribute Valoraciones valoracion) {
		valS.addValoracion(valoracion);
		return "redirect:/valoraciones/list";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteValoracion(@PathVariable int id) {
		valS.deleteValoracion(id);
		return "redirect:/valoraciones/list";
	}
	
	@GetMapping("/edit/{id}")
	public String actualizacionForm(@PathVariable int id, Model model) {
		Valoraciones v = valS.getValoracionById(id);
		model.addAttribute("valoracion", v);
		return "valoraciones/edit";
	}
	
	@PostMapping("/edit/submit")
	public String actualizar(@ModelAttribute Valoraciones valoracion) {
		valS.editValoracion(valoracion);
		

		return "redirect:/valoraciones/list";

	}

}
