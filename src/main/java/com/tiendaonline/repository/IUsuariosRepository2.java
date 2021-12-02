package com.tiendaonline.repository;

import java.util.List;

import com.tiendaonline.entity.Usuarios;


public interface IUsuariosRepository2 {

	public List<Usuarios> getAllUsuarios();
	
	public boolean checkUserLogin (String uname,String pass);
	
	public Usuarios userLogin (String uname , String pass);
	
	public Usuarios addUsuario(Usuarios usuario);
	
	public Usuarios getUsuarioByEmail(String email);

	
	boolean checkUsuario(String name);
	
	boolean checkUsuario(Integer id);



	
}
