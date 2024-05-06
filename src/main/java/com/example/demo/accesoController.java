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


@Repository
public class accesoController implements AccesoDao{
	 private final JdbcTemplate jdbcTemplate;
	 @Autowired
	 public accesoController(JdbcTemplate jdbcTemplate) {
	     this.jdbcTemplate = jdbcTemplate;
	 }


	@Override
	       public String InsertaAcceso(String id) {
	         String respuesta="";
	           SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
	                    .withProcedureName("insertar_acceso")
	                    .declareParameters(
	                            new SqlParameter("i_id_acceso", Types.INTEGER),
	                            new SqlParameter("i_id_usuario", Types.INTEGER), // Agrega el nuevo parámetro aquí
	                            new SqlOutParameter("o_resultado", Types.INTEGER)
	                    );

	           Map<String, Object> inParams = new HashMap<>();
	           inParams.put("i_id_acceso", 1);
	           inParams.put("i_id_usuario", 1);
	           Map<String, Object> outParams = jdbcCall.execute(inParams);

	            int o_resultado = (int) outParams.get("o_resultado");
	            
	            System.out.println("estado de insert: " + o_resultado);
	            return respuesta;
	       }
}
