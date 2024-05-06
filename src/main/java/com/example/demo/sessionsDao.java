package com.example.demo;

import java.util.List;

import com.example.models.sesiones;

public interface sessionsDao {

	String InsertaSesion(String id);
	String actualizaSesion(String id);
	List<sesiones> getSesionVigenteNormal(String id);

}
