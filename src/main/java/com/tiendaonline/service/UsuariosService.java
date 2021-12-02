package com.tiendaonline.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tiendaonline.entity.Usuarios;
import com.tiendaonline.repository.IUsuariosRepository;


@Service
public class UsuariosService {

	public ArrayList<Usuarios> getAllUsuarios() {

		ArrayList<Usuarios> listaUsuarios = new ArrayList<Usuarios>();

		Usuarios usuario1 = new Usuarios(1, 1, "usuario1@mail.com", "pass1", "Usuario1", "Apellido1User1", "Apellido2User1", "Calle Usuario1", "Provincia1", "Ciudad Usuario1", "TLF0001", "DNI001");
		Usuarios usuario2 = new Usuarios(2, 2, "usuario2@mail.com", "pass2", "Usuario1", "Apellido1User2", "Apellido2User2", "Calle Usuario2", "Provincia2", "Ciudad Usuario1", "TLF0002", "DNI002");
		Usuarios usuario3 = new Usuarios(3, 2, "usuario3@mail.com", "pass3", "Usuario1", "Apellido1User3", "Apellido2User3", "Calle Usuario3", "Provincia3","Ciudad Usuario1", "TLF0003", "DNI003");
		Usuarios usuario4 = new Usuarios(4, 1, "usuario4@mail.com", "pass4", "Usuario1", "Apellido1User4", "Apellido2User4", "Calle Usuario4", "Provincia4","Ciudad Usuario1", "TLF0004", "DNI004");

		listaUsuarios.add(usuario1);
		listaUsuarios.add(usuario2);
		listaUsuarios.add(usuario3);
		listaUsuarios.add(usuario4);


		return listaUsuarios;
	}

	public boolean checkUserLogin(String uname, String pass) {

		boolean login = false;

		ArrayList<Usuarios> listaUsuarios = getAllUsuarios();

		for (Usuarios usuario : listaUsuarios) {
			if (usuario.getEmail().equals(uname) && usuario.getClave().equals(pass)) {
				login=true;
			}
		}

		return login;
	}

	public Usuarios checkUsuarioByEmail(String email) {

		ArrayList<Usuarios> listUsuarios = getAllUsuarios();

		for (Usuarios usuario : listUsuarios) {
			if (usuario.getEmail().equals(email)) {
				return usuario;
			}
		}
		return null;
	}




}
