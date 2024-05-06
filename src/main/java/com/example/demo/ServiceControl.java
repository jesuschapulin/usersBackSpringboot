package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.empresas;
import com.example.models.sesiones;
import com.example.models.usuarios;
@RestController
@CrossOrigin
@RequestMapping("/control")
public class ServiceControl {
	@Autowired
	
	private final sessionsDao sesionesDao;
	private final EmpresasDao EmpresasDao;
	private final usuariosDao usuariosDao;
	private final AccesoDao accesoDao;
	
	public ServiceControl(sessionsDao sesionesDao,
			EmpresasDao EmpresasDao, usuariosDao usuariosDao, AccesoDao accesoDao) {
		this.sesionesDao = sesionesDao;
		this.EmpresasDao = EmpresasDao;
		this.usuariosDao = usuariosDao;
		this.accesoDao = accesoDao;
	}
	@GetMapping("/hello")
	public String hello() {
		return "Hello chaps from springboot";
	}
	@GetMapping("/empresas/busqueda")
	public List<empresas>getPruebas(){
	   return EmpresasDao.getPrueba();
	}
	@GetMapping("/empresas/busqueda/{email}/{secret}")
	public List<empresas>getPruebaEmpresaByCredentials(@PathVariable("email") String email,@PathVariable("secret") String secret){
	   return EmpresasDao.getPruebaEmpresaByCredentials(email,secret);
	}
	@GetMapping("/empresas")
	public List<empresas>getEmpresas(){
	   return EmpresasDao.buscarEmpresas();
	}
	@GetMapping("/sesiones/vigente/{id}")
	public List<sesiones>getSesionVigente(@PathVariable("id") String id){
	   return sesionesDao.getSesionVigenteNormal(id);
	}
	@GetMapping("/usuariosNames/{id}")
	public List<usuarios>getUsuarioNames(@PathVariable("id") String id){
	   return usuariosDao.getUsuarioNames(id);
	}
	@GetMapping("/usuarios/{id}")
	public List<usuarios>getUsuario(@PathVariable("id") String id){
	   return usuariosDao.getUsuario(id);
	}
	@PostMapping("/sesiones/{id}")
	public String InsertaSesion(@PathVariable("id") String id){
	   return sesionesDao.InsertaSesion(id);
	}
	@PutMapping("/sesiones/{id}")
	public String actualizaSesion(@PathVariable("id") String id){
	   return sesionesDao.actualizaSesion(id);
	}
	@PostMapping("/accesos/{id}")
	public String InsertaAcceso(@PathVariable("id") String id){
	   return accesoDao.InsertaAcceso(id);
	}
}
