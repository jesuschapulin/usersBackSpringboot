package com.example.demo;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
   @Override
   public User mapRow(ResultSet rs, int rowNum) throws SQLException {
       return new User(
    		   rs.getString("LOGIN"),
    		   rs.getString("PASSWORD"),
    		   rs.getString("NOMBRE"),
    		   rs.getFloat("CLIENTE"),
    		   rs.getString("EMAIL"),
    		   rs.getString("FECHAALTA"),
    		   rs.getString("FECHABAJA"),
    		   rs.getString("STATUS"),
    		   rs.getFloat("INTENTOS"),
    		   rs.getString("FECHAREVOCADO"),
    		   rs.getString("FECHA_VIGENCIA"),
    		   rs.getInt("NO_ACCESO"),
    		   rs.getString("APELLIDO_PATERNO"),
    		   rs.getString("APELLIDO_MATERNO"),
    		   rs.getInt("AREA"),
    		   rs.getString("FECHAMODIFICACION")
       );
   }
}