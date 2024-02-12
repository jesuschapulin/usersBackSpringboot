package com.example.demo;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/service")
public class Service {
	@Autowired
	
	
	private final UserDao userDao;

	
	public Service(UserDao userDao) {
	       this.userDao = userDao;
	}
	@GetMapping("/hello")
	public String hello() {
		return "Hello chaps from springboot";
	}
	@GetMapping("/getUsersADEA")
	public List<User> findAll(){
	   return userDao.findAll();
	}
	@GetMapping("/getUserADEAByLogin/{login}")
	public List<User> findByLogin(@PathVariable("login") String login){
	   return userDao.findByLogin(login);
	}
	@GetMapping("/getUsersADEAByName/{words}")
	public List<User> getUsersADEAByName(@PathVariable("words") String words){
	   return userDao.findByName(words);
	}
	@GetMapping("/getUsersADEAByActive/{status}")
	public List<User> getUsersADEAByActive(@PathVariable("status") String status){
	   return userDao.findByStatus(status);
	}
	@GetMapping("/getUsersADEAByDate/{date1}/{date2}")
	public List<User> getUsersADEAByDate(@PathVariable("date1") String date1,@PathVariable("date2") String date2){
	   return userDao.findByDate(date1,date2);
	}
	@GetMapping("/getOneUserADEA/{login}/{secret}")
	public List<User> AccessByLogin(@PathVariable("login") String login,@PathVariable("secret") String secret){
	   return userDao.AccessByLogin(login,secret);
	}
	@PostMapping("/setOneUserADEA/{login}/{secret}/{nombre}/{apaterno}/{amaterno}/{cliente}/{dateVigencia}")
	public String setNewUser(@PathVariable("login") String login,@PathVariable("secret") String secret,
            @PathVariable("nombre") String nombre ,@PathVariable("apaterno") String apaterno,@PathVariable("amaterno") String amaterno,
            @PathVariable("cliente") String cliente,@PathVariable("dateVigencia") String dateVigencia){
		User us=new User();
		us.setLOGIN(login);
		us.setNOMBRE(nombre);
		us.setPASSWORD(secret);
		us.setAPELLIDO_PATERNO(apaterno);
		us.setAPELLIDO_MATERNO(amaterno);
		us.setCLIENTE(Float.parseFloat(cliente));
		us.setFECHA_VIGENCIA(dateVigencia);
		int state=0;
		String res="";
		try{
			state=userDao.setNewUser(us);
			JSONArray array = new JSONArray();
        	System.out.println("elemento de base creado ::::::::: ");
            JSONObject item = new JSONObject();
            item.put("messaje","creado");
            item.put("error","xxxxx");
            array.put(item);
            res = array.toString();
		}catch(Exception e) {
			System.out.println("Fallo el registro ::::::::: ");
			JSONArray arraye = new JSONArray();
			JSONObject iteme = new JSONObject();
            iteme.put("messaje", "fallo");
            iteme.put("error", "Error al registrar usuario puede que ya exista el usuario");
            arraye.put(iteme);
            res = arraye.toString();
		}
	   return res;
	}
	@PostMapping("/alterUserADEA/{login}/{nombre}/{cliente}/{apaterno}/{amaterno}/{estado}/{dateVigencia}")
	public String alterUserADEA(@PathVariable("login") String login,
            @PathVariable("nombre") String nombre,
            @PathVariable("cliente") String cliente,@PathVariable("apaterno") String apaterno,@PathVariable("amaterno") String amaterno,
            @PathVariable("estado") String estado,@PathVariable("dateVigencia") String dateVigencia){
		User us=new User();
		us.setLOGIN(login);
		us.setNOMBRE(nombre);
		us.setAPELLIDO_PATERNO(apaterno);
		us.setAPELLIDO_MATERNO(amaterno);
		us.setCLIENTE(Float.parseFloat(cliente));
		us.setSTATUS(estado);
		us.setFECHA_VIGENCIA(dateVigencia);
		int state=0;
		String res="";
		try{
			state=userDao.updateUser(us);
			JSONArray array = new JSONArray();
        	System.out.println("elemento de base modificado ::::::::: ");
            JSONObject item = new JSONObject();
            item.put("messaje","modificado");
            item.put("error","xxxxx");
            array.put(item);
            res = array.toString();
		}catch(Exception e) {
			System.out.println("Fallo la modificacion ::::::::: ");
			JSONArray arraye = new JSONArray();
			JSONObject iteme = new JSONObject();
            iteme.put("messaje", "fallo");
            iteme.put("error", "Error al modificar el usuario");
            arraye.put(iteme);
            res = arraye.toString();
		}
	   return res;
	}
	@PostMapping("/deleteUserADEA/{login}/{secret}")
	public String deleteUserADEA(@PathVariable("login") String login,@PathVariable("secret") String secret){

		int state=0;
		String res="";
		try{
			state=userDao.deleteUser(login);
			JSONArray array = new JSONArray();
        	System.out.println("elemento de base borrado ::::::::: "+state);
            JSONObject item = new JSONObject();
            if(state==1) {
            	item.put("messaje","borrado");
                item.put("error","xxxxx");
            }else {
            	item.put("messaje", "fallo");
                item.put("error", "Error al borrar el usuario");
            }
            array.put(item);
            res = array.toString();
		}catch(Exception e) {
			System.out.println("Fallo el borrado de base ::::::::: ");
			JSONArray arraye = new JSONArray();
			JSONObject iteme = new JSONObject();
            iteme.put("messaje", "fallo");
            iteme.put("error", "Error al borrar el usuario");
            arraye.put(iteme);
            res = arraye.toString();
		}
	   return res;
	}
	@PostMapping("/inactiveUserADEA/{login}/{nombre}")
	public String inactiveUserADEA(@PathVariable("login") String login,@PathVariable("nombre") String nombre){

		int state=0;
		String res="";
		try{
			state=userDao.inactiveUser(login);
			JSONArray array = new JSONArray();
        	System.out.println("elemento de base inactivado ::::::::: "+state);
            JSONObject item = new JSONObject();
            if(state==1) {
            	item.put("messaje","inactivado");
                item.put("error","xxxxx");
            }else {
            	item.put("messaje", "fallo");
                item.put("error", "Error al borrar el usuario");
            }
            array.put(item);
            res = array.toString();
		}catch(Exception e) {
			System.out.println("Fallo el borrado de base ::::::::: ");
			JSONArray arraye = new JSONArray();
			JSONObject iteme = new JSONObject();
            iteme.put("messaje", "fallo");
            iteme.put("error", "Error al borrar el usuario");
            arraye.put(iteme);
            res = arraye.toString();
		}
	   return res;
	}
}