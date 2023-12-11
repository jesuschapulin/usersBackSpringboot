package com.example.demo;

import com.example.demo.User;
import com.example.demo.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao{

   private final JdbcTemplate jdbcTemplate;

   @Autowired
   public UserDaoImpl(JdbcTemplate jdbcTemplate) {
       this.jdbcTemplate = jdbcTemplate;
   }
   @Override
   public List<User> findAll() {
      String sql = ""+
              "SELECT * "+
              "FROM usuariosadea "+
              ""+
              "";
      return jdbcTemplate.query(sql,new UserRowMapper());
   }
   
   @Override
   public int setNewUser(User user) {
	   System.out.println("preparando para agregar con springboot :::"+user.getLOGIN());
	   System.out.println("preparando para agregar con springboot :::"+user.getPASSWORD());
	   String encodedUrl = Base64.getUrlEncoder().encodeToString(user.getPASSWORD().getBytes());
       System.out.println("agregando con springboot :::"+encodedUrl);
       String sql = "INSERT INTO usuariosadea VALUES (\n" +
              "'"+user.getLOGIN()+"',\n" +
              " '"+encodedUrl+"',\n" +
              " '"+user.getNOMBRE()+"',\n" +
              " "+user.getCLIENTE()+",\n" +
              " '"+user.getNOMBRE()+"@"+user.getNOMBRE()+"',\n" +
              " '"+user.getFECHAALTA()+"',\n" +
              " sysdate,\n" +
              "'"+(user.getSTATUS().equals("A") ? 'A' : user.getSTATUS().equals("B") ? 'B' : user.getSTATUS().equals("R") ? 'R' : '0' )+"',\n" +
              " 0,\n" +
              " sysdate,\n" +
              " sysdate,\n" +
              " 1,\n" +
              " '"+user.getAPELLIDO_PATERNO()+"',\n" +
              " '"+user.getAPELLIDO_MATERNO()+"',\n" +
              " 1,\n" +
              " sysdate \n" +
              " )";
       System.out.println("query para agregar con springboot :::"+sql);
      return jdbcTemplate.update(
    		  sql);
   }
   @Override
   public List<User> findByLogin(String login) {
	  System.out.println("login :::"+login);
      String sql = "select "+
    		  		"LOGIN,"
    		  		+ "PASSWORD,"
    		  		+ "NOMBRE,"
    		  		+ "CLIENTE,"
    		  		+ "EMAIL,"
    		  		+ "FECHAALTA,"
    		  		+ "FECHABAJA,"
    		  		+ "STATUS,"
    		  		+ "INTENTOS,"
    		  		+ "FECHAREVOCADO,"
    		  		+ "FECHA_VIGENCIA,"
    		  		+ "NO_ACCESO,"
    		  		+ "APELLIDO_PATERNO,"
    		  		+ "APELLIDO_MATERNO,"
    		  		+ "AREA,"
    		  		+ "FECHAMODIFICACION "+
    		  		"from usuariosadea where LOGIN='"+login+"'";
      System.out.println("sql by login :::"+sql);
      return jdbcTemplate.query(sql,new UserRowMapper());
   }
   @Override
   public int updateUser(User user) {
      String sql = "UPDATE usuariosadea\n" +
              "SET \n" +
              "NOMBRE='"+user.getNOMBRE()+"',\n" +
              "CLIENTE="+user.getCLIENTE()+",\n" +
              "EMAIL='"+user.getNOMBRE()+"@"+user.getNOMBRE()+"',\n" +
              "FECHAALTA='"+user.getFECHAALTA()+"',\n" +
              "FECHABAJA=sysdate,\n" +
              "STATUS='"+(user.getSTATUS().equals("A") ? 'A' : user.getSTATUS().equals("B") ? 'B' : user.getSTATUS().equals("R") ? 'R' : '0' )+"',\n" +
              "INTENTOS=0,\n" +
              "FECHAREVOCADO=sysdate,\n" +
              "FECHA_VIGENCIA=sysdate,\n" +
              "NO_ACCESO=1,\n" +
              "APELLIDO_PATERNO='"+user.getAPELLIDO_PATERNO()+"',\n" +
              "APELLIDO_MATERNO='"+user.getAPELLIDO_MATERNO()+"',\n" +
              "AREA=1,\n" +
              "FECHAMODIFICACION=sysdate\n" +
              "where LOGIN='"+user.getLOGIN()+"'\n" +
              "";
      return jdbcTemplate.update(sql);
   }
   @Override
   public int deleteUser(String login) {
		System.out.println("login to delete:::" + login);
		String sql = "delete from usuariosadea where LOGIN='" + login + "'";
		System.out.println("sql by delete :::" + sql);
		return jdbcTemplate.update(sql);
   }
   @Override
   public List<User> AccessByLogin(String login,String pass) {
		System.out.println("login to access:::" + login);
		String encodedUrl = Base64.getUrlEncoder().encodeToString(pass.getBytes());
		System.out.println("contra :::" + encodedUrl);
		String sql = "select * from usuariosadea where LOGIN='" + login + "' and PASSWORD='" + encodedUrl + "'";
		System.out.println("sql by delete :::" + sql);
		return jdbcTemplate.query(sql, new UserRowMapper());
   }
   @Override
   public List<User> findByName(String words) {
		System.out.println("search by name::: " + words);
		String sql = "select * from usuariosadea where NOMBRE like'%" + words + "%'";
		System.out.println("sql by search :::" + sql);
		return jdbcTemplate.query(sql, new UserRowMapper());
   }
   @Override
   public List<User> findByStatus(String status) {
		System.out.println("search by status::: " + status);
		String sql = "select * from usuariosadea where STATUS like'%" + status + "%'";
		System.out.println("sql by search :::" + sql);
		return jdbcTemplate.query(sql, new UserRowMapper());
   }
   @Override
   public List<User> findByDate(String date1,String date2) {
		System.out.println("search by date::: " + date1);
		String sql = "select * from usuariosadea where FECHAALTA BETWEEN to_date('"+date1+"') and to_date('"+date2+"')";
		System.out.println("sql by search :::" + sql);
		return jdbcTemplate.query(sql, new UserRowMapper());
   }
}