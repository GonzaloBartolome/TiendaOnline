package curso.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import curso.spring.model.Usuarios;
import curso.spring.service.UsuariosService;



@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	@Autowired
	UsuariosService us;
	
	@GetMapping("/list")
	public String listarUsuarios(Model model) {
		model.addAttribute("lista", us.getListaUsuarios());
		return "usuarios/list";
	}
	
	@GetMapping("/add")
	public String nuevoForm(Model model) {
		model.addAttribute("usuario", new Usuarios());
		return "usuarios/new";
	}
	
	@PostMapping("/add/submit")
	public String crear(@ModelAttribute Usuarios usuario) {
		us.addUsuario(usuario);
		return "redirect:/usuarios/list";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable int id) {
		us.delUsuario(id);
		return "redirect:/usuarios/list";
	}
	
	@GetMapping("/edit/{id}")
	public String actualizacionForm(@PathVariable int id, Model model) {
		Usuarios u = us.getUsuarioById(id);
		model.addAttribute("usuario", u);
		return "usuarios/edit";
	}
	
	@PostMapping("/edit/submit")
	public String actualizar(@ModelAttribute Usuarios usuario) {
		us.editUsuario(usuario);
		return "redirect:/usuarios/list";

	}
	
}
