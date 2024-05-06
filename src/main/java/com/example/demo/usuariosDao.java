package com.example.demo;

import java.util.List;

import com.example.models.usuarios;

public interface usuariosDao {

	List<usuarios> getUsuario(String id);
	List<usuarios> getUsuarioNames(String id);
}
