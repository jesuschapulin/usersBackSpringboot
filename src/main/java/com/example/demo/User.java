package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

	@JsonProperty("LOGIN")
	private String  LOGIN;
	
	@JsonProperty("PASSWORD")
	private String  PASSWORD;
	
	@JsonProperty("NOMBRE")
	private String  NOMBRE;
	
	@JsonProperty("CLIENTE")
	private float CLIENTE;
	
	@JsonProperty("EMAIL")
	private String  EMAIL;
	
	@JsonProperty("FECHAALTA")
	private String FECHAALTA;
	
	@JsonProperty("FECHABAJA")
	private String FECHABAJA;
	
	@JsonProperty("STATUS")
	private String STATUS;
	
	@JsonProperty("INTENTOS")
	private float INTENTOS;
	
	@JsonProperty("FECHAREVOCADO")
	private String FECHAREVOCADO;
	
	@JsonProperty("FECHA_VIGENCIA")
	private String FECHA_VIGENCIA;
	
	@JsonProperty("NO_ACCESO")
	private int NO_ACCESO;
	
	@JsonProperty("APELLIDO_PATERNO")
	private String  APELLIDO_PATERNO;
	
	@JsonProperty("APELLIDO_MATERNO")
	private String  APELLIDO_MATERNO;
	
	@JsonProperty("AREA")
	private int AREA;
	
	@JsonProperty("FECHAMODIFICACION")
	private String FECHAMODIFICACION;

	protected User( String  LOGIN,
					String  PASSWORD,
					String  NOMBRE,
					float CLIENTE,
					String  EMAIL,
					String FECHAALTA,
					String FECHABAJA,
					String STATUS,
					float INTENTOS,
					String FECHAREVOCADO,
					String FECHA_VIGENCIA,
					int NO_ACCESO,
					String  APELLIDO_PATERNO,
					String  APELLIDO_MATERNO,
					int AREA,
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

	protected User() {
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

	public float getCLIENTE() {
		return CLIENTE;
	}

	public void setCLIENTE(float CLIENTE) {
		this.CLIENTE = CLIENTE;
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

	public float getINTENTOS() {
		return INTENTOS;
	}

	public void setINTENTOS(float INTENTOS) {
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

	public int getNO_ACCESO() {
		return NO_ACCESO;
	}

	public void setNO_ACCESO(int NO_ACCESO) {
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

	public int getAREA() {
		return AREA;
	}

	public void setAREA(int AREA) {
		this.AREA = AREA;
	}

	public String getFECHAMODIFICACION() {
		return FECHAMODIFICACION;
	}

	public void setFECHAMODIFICACION(String FECHAMODIFICACION) {
		this.FECHAMODIFICACION = FECHAMODIFICACION;
	}
	
}



