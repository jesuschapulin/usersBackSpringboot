package com.example.models;

public class empresas {
	private int id_empresa;
	private String nombre;
	
	public empresas() {
		
	}

	public int getId_empresa() {
		return id_empresa;
	}

	public void setId_empresa(int id_empresa) {
		this.id_empresa = id_empresa;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public empresas(int id_empresa, String nombre) {
		super();
		this.id_empresa = id_empresa;
		this.nombre = nombre;
	}
	
}
