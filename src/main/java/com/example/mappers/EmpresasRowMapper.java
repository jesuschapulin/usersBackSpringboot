package com.example.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.models.empresas;

public class EmpresasRowMapper implements RowMapper<empresas> {
	   @Override
	   public empresas mapRow(ResultSet rs, int rowNum) throws SQLException {
	       return new empresas(
	    		   rs.getInt("v_id_empresa"),
	    		   rs.getString("v_nombre_resultado")
	       );
	   }

}
