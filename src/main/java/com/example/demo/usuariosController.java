package com.example.demo;

import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.example.models.sesiones;
import com.example.models.usuarios;
@Repository
public class usuariosController implements usuariosDao{
	 private final JdbcTemplate jdbcTemplate;
	 @Autowired
	 public usuariosController(JdbcTemplate jdbcTemplate) {
	     this.jdbcTemplate = jdbcTemplate;
	 }
	 @Override
	   public List<usuarios> getUsuario(String id) {
		    List<usuarios> lista = new ArrayList<usuarios>();
		    SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
	                .withProcedureName("GET_USUARIO")
	                .declareParameters(
	                        new SqlParameter("i_id_usuario", Types.INTEGER),
	                        new SqlParameter("i_email", Types.VARCHAR), // Agrega el nuevo parámetro aquí
	                        new SqlParameter("i_password", Types.VARCHAR), // Agrega el nuevo parámetro aquí
	                        new SqlOutParameter("o_id_usuario", Types.INTEGER),
	                        new SqlOutParameter("o_nombre", Types.VARCHAR),
	                        new SqlOutParameter("o_apellido_paterno", Types.VARCHAR),
	                        new SqlOutParameter("o_apellido_materno", Types.VARCHAR),
	                        new SqlOutParameter("o_email", Types.VARCHAR),
	                        new SqlOutParameter("o_password", Types.VARCHAR),
	                        new SqlOutParameter("o_activo", Types.VARCHAR),
	                        new SqlOutParameter("o_crear_usuarios", Types.VARCHAR),
	                        new SqlOutParameter("o_fecha_registro", Types.VARCHAR),
	                        new SqlOutParameter("o_fecha_actualizacion", Types.VARCHAR),
	                        new SqlOutParameter("o_perfil", Types.VARCHAR),
	                        new SqlOutParameter("o_id_persona", Types.INTEGER),
	                        new SqlOutParameter("o_telefono", Types.VARCHAR)
	                );

	       Map<String, Object> inParams = new HashMap<>();
	       inParams.put("i_id_usuario", 1);
	       inParams.put("i_email", "");
	       inParams.put("i_password", "");
	       ///inParams.put("i_otro_parametro", 123); // Reemplaza con el valor deseado
	        Map<String, Object> outParams = jdbcCall.execute(inParams);

	        int id_usuario = (int) outParams.get("o_id_usuario");
	        String nombre = (String) outParams.get("o_nombre");
	        String ap = (String) outParams.get("o_apellido_paterno");
	        String am = (String) outParams.get("o_apellido_materno");
	        String email = (String) outParams.get("o_email");
	        String password = (String) outParams.get("o_password");
	        
	        String o_activo = (String) outParams.get("o_activo");
	        String o_crear_usuarios = (String) outParams.get("o_crear_usuarios");
	        String o_fecha_registro = (String) outParams.get("o_fecha_registro");
	        String o_fecha_actualizacion = (String) outParams.get("o_fecha_actualizacion");
	        String o_perfil = (String) outParams.get("o_perfil");
	        int o_id_persona = (int) outParams.get("o_id_persona");
	        String o_telefono = (String) outParams.get("o_telefono");
	        
	        usuarios em = new usuarios();
	        em.setId_usuario(""+id_usuario);
	        em.setNombre(nombre);
	        em.setApellido_paterno(ap);
	        em.setApellido_materno(am);
	        em.setEmail(email);
	        em.setPassword(password);
	        em.setActivo(""+o_activo.trim());
	        em.setCrear_usuarios(""+o_crear_usuarios);
	        em.setFecha_registro(o_fecha_registro);
	        em.setFecha_actualizacion(o_fecha_actualizacion);
	        em.setPerfil(""+o_perfil.trim());
	        em.setId_persona(""+o_id_persona);
	        em.setTelefono(""+o_telefono);
	        lista.add(em);
	        System.out.println("ID usuario: " + id_usuario + ", crear: "+o_crear_usuarios );
	        return lista;
	   }
	   @Override
	   public List<usuarios> getUsuarioNames(String id) {
		    List<usuarios> lista = new ArrayList<usuarios>();
		    SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
	                .withProcedureName("get_usuario_names")
	                .declareParameters(
	                        new SqlParameter("i_id_usuario", Types.INTEGER),
	                        new SqlParameter("i_email", Types.VARCHAR), // Agrega el nuevo parámetro aquí
	                        new SqlParameter("i_password", Types.VARCHAR), // Agrega el nuevo parámetro aquí
	                        new SqlOutParameter("o_id_usuario", Types.INTEGER),
	                        new SqlOutParameter("o_nombre", Types.VARCHAR),
	                        new SqlOutParameter("o_apellido_paterno", Types.VARCHAR),
	                        new SqlOutParameter("o_apellido_materno", Types.VARCHAR),
	                        new SqlOutParameter("o_telefono", Types.VARCHAR)
	                );

	       Map<String, Object> inParams = new HashMap<>();
	       inParams.put("i_id_usuario", 1);
	       inParams.put("i_email", "");
	       inParams.put("i_password", "");
	       ///inParams.put("i_otro_parametro", 123); // Reemplaza con el valor deseado
	        Map<String, Object> outParams = jdbcCall.execute(inParams);

	        int id_usuario = (int) outParams.get("o_id_usuario");
	        String nombre = (String) outParams.get("o_nombre");
	        String ap = (String) outParams.get("o_apellido_paterno");
	        String am = (String) outParams.get("o_apellido_materno");
	        String o_telefono = (String) outParams.get("o_telefono");
	        
	        usuarios em = new usuarios();
	        em.setId_usuario(""+id_usuario);
	        em.setNombre(nombre);
//	        em.setApellido_paterno(ap);
//	        em.setApellido_materno(am);
	        em.setTelefono(""+o_telefono);
	        
	        lista.add(em);
	        System.out.println("ID usuario: " + id_usuario + ", Nombre: " + nombre);
	        return lista;
	   }
}
