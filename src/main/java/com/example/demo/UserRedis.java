
package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRedis {
	private String  LOGIN;
	private String  PASSWORD;
	private String  NOMBRE;
	private String CLIENTE;
	private String  EMAIL;
	private String FECHAALTA;
	private String FECHABAJA;
	private String STATUS;
	private String INTENTOS;
	private String FECHAREVOCADO;
	private String FECHA_VIGENCIA;
	private String NO_ACCESO;
	private String  APELLIDO_PATERNO;
	private String  APELLIDO_MATERNO;
	private String AREA;
	private String FECHAMODIFICACION;

	protected UserRedis( String  LOGIN,
					String  PASSWORD,
					String  NOMBRE,
					String CLIENTE,
					String  EMAIL,
					String FECHAALTA,
					String FECHABAJA,
					String STATUS,
					String INTENTOS,
					String FECHAREVOCADO,
					String FECHA_VIGENCIA,
					String NO_ACCESO,
					String  APELLIDO_PATERNO,
					String  APELLIDO_MATERNO,
					String AREA,
					String FECHAMODIFICACION
	) {
		this.LOGIN=LOGIN;
		this.PASSWORD=PASSWORD;
		this.NOMBRE=NOMBRE;
		this.CLIENTE=CLIENTE;
		this.EMAIL=EMAIL;
		this.FECHAALTA=FECHAALTA;
		this.FECHABAJA=FECHABAJA;
		this.STATUS=STATUS;
		this.INTENTOS=INTENTOS;
		this.FECHAREVOCADO=FECHAREVOCADO;
		this.FECHA_VIGENCIA=FECHA_VIGENCIA;
		this.NO_ACCESO=NO_ACCESO;
		this.APELLIDO_PATERNO=APELLIDO_PATERNO;
		this.APELLIDO_MATERNO=APELLIDO_MATERNO;
		this.AREA=AREA;
		this.FECHAMODIFICACION=FECHAMODIFICACION;
	}

	protected UserRedis() {
	}

	public String getLOGIN() {
		return LOGIN;
	}

	public void setLOGIN(String LOGIN) {
		this.LOGIN = LOGIN;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String PASSWORD) {
		this.PASSWORD = PASSWORD;
	}

	public String getNOMBRE() {
		return NOMBRE;
	}

	public void setNOMBRE(String NOMBRE) {
		this.NOMBRE = NOMBRE;
	}

	public String getCLIENTE() {
		return CLIENTE;
	}

	public void setCLIENTE(String string) {
		this.CLIENTE = string;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String EMAIL) {
		this.EMAIL = EMAIL;
	}

	public String getFECHAALTA() {
		return FECHAALTA;
	}

	public void setFECHAALTA(String FECHAALTA) {
		this.FECHAALTA = FECHAALTA;
	}

	public String getFECHABAJA() {
		return FECHABAJA;
	}

	public void setFECHABAJA(String FECHABAJA) {
		this.FECHABAJA = FECHABAJA;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String STATUS) {
		this.STATUS = STATUS;
	}

	public String getINTENTOS() {
		return INTENTOS;
	}

	public void setINTENTOS(String INTENTOS) {
		this.INTENTOS = INTENTOS;
	}

	public String getFECHAREVOCADO() {
		return FECHAREVOCADO;
	}

	public void setFECHAREVOCADO(String FECHAREVOCADO) {
		this.FECHAREVOCADO = FECHAREVOCADO;
	}

	public String getFECHA_VIGENCIA() {
		return FECHA_VIGENCIA;
	}

	public void setFECHA_VIGENCIA(String FECHA_VIGENCIA) {
		this.FECHA_VIGENCIA = FECHA_VIGENCIA;
	}

	public String getNO_ACCESO() {
		return NO_ACCESO;
	}

	public void setNO_ACCESO(String NO_ACCESO) {
		this.NO_ACCESO = NO_ACCESO;
	}

	public String getAPELLIDO_PATERNO() {
		return APELLIDO_PATERNO;
	}

	public void setAPELLIDO_PATERNO(String APELLIDO_PATERNO) {
		this.APELLIDO_PATERNO = APELLIDO_PATERNO;
	}

	public String getAPELLIDO_MATERNO() {
		return APELLIDO_MATERNO;
	}

	public void setAPELLIDO_MATERNO(String APELLIDO_MATERNO) {
		this.APELLIDO_MATERNO = APELLIDO_MATERNO;
	}

	public String getAREA() {
		return AREA;
	}

	public void setAREA(String AREA) {
		this.AREA = AREA;
	}

	public String getFECHAMODIFICACION() {
		return FECHAMODIFICACION;
	}

	public void setFECHAMODIFICACION(String FECHAMODIFICACION) {
		this.FECHAMODIFICACION = FECHAMODIFICACION;
	}
	
}



