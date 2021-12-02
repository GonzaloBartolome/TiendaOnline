package com.tiendaonline.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tiendaonline.entity.Usuarios;
import com.tiendaonline.service.UsuariosService;

@Controller
@RequestMapping("/usuario")
public class UsuariosController {
	
	@Autowired
	UsuariosService usuariosS;
	
	Usuarios usuario = new Usuarios();
	
	
	@GetMapping("/login")
	public String checkLogin(HttpSession sesion, Model model, @RequestParam String email, @RequestParam String pass) {
		
		
		//TODO: Checkear el login
		usuario = usuariosS.checkUsuarioByEmail(email);
		
		sesion.setAttribute("nombre", usuario.getNombre());
		
		
		return "redirect:/index";
		
		
	}

}
