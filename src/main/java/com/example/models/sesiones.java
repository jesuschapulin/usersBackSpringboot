package com.example.models;

public class sesiones {
	private int id_sesion;
	private String sesion;
	private String fecha_registro;
	private String fecha_valida;
	private String estado;
	
	public sesiones() {
		
	}

	public int getId_sesion() {
		return id_sesion;
	}

	public void setId_sesion(int id_sesion) {
		this.id_sesion = id_sesion;
	}

	public String getSesion() {
		return sesion;
	}

	public void setSesion(String sesion) {
		this.sesion = sesion;
	}

	public String getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(String fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public String getFecha_valida() {
		return fecha_valida;
	}

	public void setFecha_valida(String fecha_valida) {
		this.fecha_valida = fecha_valida;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
