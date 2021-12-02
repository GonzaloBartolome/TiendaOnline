package curso.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import curso.spring.model.Categorias;
import curso.spring.model.Productos;
import curso.spring.model.UnidadesCarrito;
import curso.spring.model.Usuarios;
import curso.spring.service.CategoriasService;
import curso.spring.service.ProductosService;
import curso.spring.service.UsuariosService;

@Controller
@RequestMapping("")
public class MainController {

	@Autowired
	ProductosService productosService;
	
	@Autowired
	CategoriasService catServ;
	
	@Autowired
	UsuariosService usuariosService;
	
	@GetMapping("")
	public String inicial(Model model, HttpSession sesion) {
		
		if (sesion.getAttribute("carrito") == null) {
			ArrayList<UnidadesCarrito> carrito = new ArrayList<UnidadesCarrito>();
			sesion.setAttribute("carrito", carrito);
		}
	
		return "redirect:/index";
	} 
	
	
	@GetMapping("/index")
	public String index (Model model, HttpSession sesion) {
		
		List<Productos> productList = productosService.getAllProducts();
		
		model.addAttribute("productList", productList);
		 
		return "index";
	}
	
	@GetMapping("/login")
	public String loginForm(Model model) {
		model.addAttribute("usuario", new Usuarios());
		return "access/login";
	}
	
	@PostMapping("/login/submit")
	public String logar(HttpSession sesion , Model model, @ModelAttribute Usuarios usuario) {

		if(usuariosService.checkUserLogin(usuario)) { 
			sesion.setAttribute("nombre", usuario.getNombre());
			sesion.setAttribute("usuario", usuario);
			return "redirect:/index";
		}
		else {
			model.addAttribute("mensaje", "El usuario y/o la contraseña no son correctos.");
			model.addAttribute("usuario", new Usuarios());
			return "access/login";
		}
	}
	
	@GetMapping("/signup")
	public String signUpForm(Model model) {
		model.addAttribute("usuario", new Usuarios());
		return "access/signup";
	}
	
	@PostMapping("/signup/submit")
	public String signUp(HttpSession sesion , Model model, @ModelAttribute Usuarios usuario) {
		
		boolean user = usuariosService.checkUserExist(usuario.getEmail());
		
		if(user) { 
			model.addAttribute("mensaje", "El usuario ya tiene una cuenta. Por favor entre en su cuenta");
			return "access/login";
		}
		else {
			
			return "redirect:/index";
		}
	}
	
	

}
