package com.tiendaonline.repository;

import java.util.List;

import com.tiendaonline.entities.Usuarios;


public interface IUsuariosRepository {

	public List<Usuarios> getAllUsuarios();
	
	public boolean checkUserLogin (String uname,String pass);
	
	public Usuarios userLogin (String uname,String pass);
	
	public Usuarios addUsuario(Usuarios usuario);
	
	boolean checkUsuario(String name);
	
	boolean checkUsuario(Integer id);



	
}
