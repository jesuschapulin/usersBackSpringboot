package com.example.demo;



import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.example.models.sesiones;

@Repository
public class sesionesController implements sessionsDao{
	 private final JdbcTemplate jdbcTemplate;
	 @Autowired
	 public sesionesController(JdbcTemplate jdbcTemplate) {
	     this.jdbcTemplate = jdbcTemplate;
	 }
	 @Override
	   public String InsertaSesion(String id) {
		 String respuesta="";
	       SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
	                .withProcedureName("insertar_sesion")
	                .declareParameters(
	                        new SqlParameter("i_id_sesion", Types.INTEGER),
	                        new SqlParameter("i_sesion", Types.VARCHAR), // Agrega el nuevo parámetro aquí
	                        new SqlParameter("i_fecha_registro", Types.VARCHAR), // Agrega el nuevo parámetro aquí
	                        new SqlParameter("i_fecha_valida", Types.VARCHAR),
	                        new SqlParameter("i_estado", Types.VARCHAR), // Agrega el nuevo parámetro aquí
	                        new SqlParameter("i_id_usuario", Types.INTEGER), // Agrega el nuevo parámetro aquí
	                        new SqlOutParameter("o_resultado", Types.INTEGER)
	                );

	       Map<String, Object> inParams = new HashMap<>();
	       inParams.put("i_id_sesion", 1);
	       inParams.put("i_sesion", "insertado desde spring con sp");
	       inParams.put("i_fecha_registro", "sysdate");
	       inParams.put("i_fecha_valida", "");
	       inParams.put("i_estado", "S");
	       inParams.put("i_id_usuario", 1);
	       Map<String, Object> outParams = jdbcCall.execute(inParams);

	        int o_resultado = (int) outParams.get("o_resultado");
	        
	        System.out.println("estado de insert: " + o_resultado);
	        return respuesta;
	   }
	 @Override
	   public String actualizaSesion(String id) {
		 String respuesta="";
	       SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
	                .withProcedureName("actualizar_sesion")
	                .declareParameters(
	                        new SqlParameter("i_id_sesion", Types.INTEGER),
	                        new SqlParameter("i_accion", Types.VARCHAR),
	                        new SqlOutParameter("o_resultado", Types.INTEGER)
	                );

	       Map<String, Object> inParams = new HashMap<>();
	       inParams.put("i_id_sesion", 5);
	       inParams.put("i_accion", "confirmar");
	       Map<String, Object> outParams = jdbcCall.execute(inParams);

	        int o_resultado = (int) outParams.get("o_resultado");
	        
	        System.out.println("estado de insert: " + o_resultado);
	        return respuesta;
	   }
	   @Override
	   public List<sesiones> getSesionVigenteNormal(String id) {
		   List<sesiones> lista = new ArrayList<sesiones>();
	       SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
	                .withProcedureName("GET_SESIONES")
	                .declareParameters(
	                        new SqlParameter("i_id_usuario", Types.INTEGER),
	                        new SqlParameter("i_sesion", Types.VARCHAR), // Agrega el nuevo parámetro aquí
	                        new SqlParameter("i_accion", Types.VARCHAR), // Agrega el nuevo parámetro aquí
	                        new SqlOutParameter("o_id_sesion", Types.INTEGER),
	                        new SqlOutParameter("o_sesion", Types.VARCHAR),
	                        new SqlOutParameter("o_fecha_registro", Types.VARCHAR),
	                        new SqlOutParameter("o_fecha_valida", Types.VARCHAR)
	                );

	       Map<String, Object> inParams = new HashMap<>();
	       inParams.put("i_id_usuario", 1);
	       inParams.put("i_sesion", "");
	       inParams.put("i_accion", "vigente");
	       ///inParams.put("i_otro_parametro", 123); // Reemplaza con el valor deseado
	        Map<String, Object> outParams = jdbcCall.execute(inParams);

	        int id_Sesion = (int) outParams.get("o_id_sesion");
	        String sesion = (String) outParams.get("o_sesion");
	        String fr = (String) outParams.get("o_fecha_registro");
	        String fv = (String) outParams.get("o_fecha_valida");
	        
	        sesiones em = new sesiones();
	        em.setId_sesion(id_Sesion);
	        em.setSesion(sesion);
	        em.setFecha_registro(fr);
	        em.setFecha_valida(fv);
	        
	        lista.add(em);
	        System.out.println("ID Empresa: " + id_Sesion + ", Nombre: " + sesion);
	        return lista;
	   }

}
