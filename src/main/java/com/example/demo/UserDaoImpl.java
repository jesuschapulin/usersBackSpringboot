package com.example.demo;

import com.example.demo.User;
import com.example.demo.UserRowMapper;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


import com.google.gson.Gson;
import java.util.Map;


import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



@Repository
public class UserDaoImpl implements UserDao{

   private final JdbcTemplate jdbcTemplate;
   private RedisTemplate<String, Object> redisTemplate;
   
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
      this.sendcorreo();
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
              " sysdate,\n" +
              " '',\n" +
              "'A',\n" +
              " 0,\n" +
              " '',\n" +
              " '"+user.getFECHA_VIGENCIA()+"',\n" +
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
              "STATUS='"+(user.getSTATUS().equals("A") ? 'A' : user.getSTATUS().equals("B") ? 'B' : user.getSTATUS().equals("R") ? 'R' : '0' )+"',\n" +
              "FECHA_VIGENCIA='"+user.getFECHA_VIGENCIA()+"',\n" +
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
   public int inactiveUser(String login) {
		System.out.println("login to inactive:::" + login);
		String sql = "update usuariosadea set STATUS='B' where LOGIN='" + login + "'";
		System.out.println("sql by inactive :::" + sql);
		return jdbcTemplate.update(sql);
   }
   @Override
   public List<User> AccessByLogin(String login,String pass) {
		System.out.println("login to access::: " + login);
		String encodedUrl = Base64.getUrlEncoder().encodeToString(pass.getBytes());
		System.out.println("contra :::" + encodedUrl);
		String sql = "select * from usuariosadea where LOGIN='" + login + "' and PASSWORD='" + encodedUrl + "'"
				+ " and fecha_vigencia >= sysdate and STATUS='A'";
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
   
   
//   use of redis
   
   @Override
   public String getUsersADEAredis() {
		JedisPool pool = new JedisPool("localhost", 6379);
		String response = "";
		String listKey = "user-session:";
		String username = "brendha";
		try (Jedis jedis = pool.getResource()) {

			Map<String, String> hash = new HashMap<>();
			
			String encodedUrl = Base64.getUrlEncoder().encodeToString(username.getBytes());
		       System.out.println("agregando con springboot :::"+encodedUrl);
//			hash.put("LOGIN", "chapi");
//			hash.put("PASSWORD", encodedUrl);
//			hash.put("NOMBRE", "chapi");
//			hash.put("CLIENTE","0");
//			hash.put("EMAIL", "chapi");
//			hash.put("FECHAALTA", "chapi");
//			hash.put("FECHABAJA", "chapi");
//			hash.put("STATUS", "chapi");
//			hash.put("INTENTOS", "0");
//			hash.put("FECHAREVOCADO", "chapi");
//			hash.put("FECHA_VIGENCIA", "chapi");
//			hash.put("NO_ACCESO", "0");
//			hash.put("APELLIDO_PATERNO", "chapi");
//			hash.put("APELLIDO_MATERNO", "chapi");
//			hash.put("AREA", "0");
//			hash.put("FECHAMODIFICACION", "chapi");
//			jedis.hset("user-session:" + username, hash);
//
//			response = "" + jedis.hgetAll("user-session:" + username);
//			System.out.println(response);
			JSONObject json = new JSONObject();
			int size = 0;
			Set<String> keys = jedis.keys("user-session:*");
			String code = "";

			JSONArray array = new JSONArray();
			JSONObject item = new JSONObject();
			UserRedis usrRedis = new UserRedis();

			for (String key : keys) {
				size++;
				code=key;
				if(jedis.hget(code, "LOGIN")!=null) {
					
					usrRedis.setLOGIN(jedis.hget(code, "LOGIN"));
					usrRedis.setPASSWORD(jedis.hget(code, "PASSWORD"));
					usrRedis.setNOMBRE(jedis.hget(code, "NOMBRE"));
					usrRedis.setCLIENTE(jedis.hget(code, "CLIENTE"));
					usrRedis.setEMAIL(jedis.hget(code, "EMAIL"));
					usrRedis.setFECHAALTA(jedis.hget(code, "FECHAALTA"));
					usrRedis.setFECHABAJA(jedis.hget(code, "FECHABAJA"));
					usrRedis.setSTATUS(jedis.hget(code, "STATUS"));
					usrRedis.setINTENTOS(jedis.hget(code, "INTENTOS"));
					usrRedis.setFECHAREVOCADO(jedis.hget(code, "FECHAREVOCADO"));
					usrRedis.setFECHA_VIGENCIA(jedis.hget(code, "FECHA_VIGENCIA"));
					usrRedis.setNO_ACCESO(jedis.hget(code, "NO_ACCESO"));
					usrRedis.setAPELLIDO_PATERNO(jedis.hget(code, "APELLIDO_PATERNO"));
					usrRedis.setAPELLIDO_MATERNO(jedis.hget(code, "APELLIDO_MATERNO"));
					usrRedis.setAREA(jedis.hget(code, "AREA"));
					usrRedis.setFECHAMODIFICACION(jedis.hget(code, "FECHAMODIFICACION"));
					System.out.println("name::::::::: " + usrRedis.getNOMBRE());
	
					item.put("CODE", code);
					item.put("LOGIN", usrRedis.getLOGIN());
					item.put("PASSWORD", usrRedis.getPASSWORD());
					item.put("NOMBRE", usrRedis.getNOMBRE());
					item.put("CLIENTE", usrRedis.getCLIENTE());
					item.put("EMAIL", usrRedis.getEMAIL());
					item.put("FECHAALTA", usrRedis.getFECHAALTA());
					item.put("FECHABAJA", usrRedis.getFECHABAJA());
					item.put("STATUS", usrRedis.getSTATUS());
					item.put("INTENTOS", usrRedis.getINTENTOS());
					item.put("FECHAREVOCADO", usrRedis.getFECHAREVOCADO());
					item.put("FECHA_VIGENCIA", usrRedis.getFECHA_VIGENCIA());
					item.put("NO_ACCESO", usrRedis.getNO_ACCESO());
					item.put("APELLIDO_PATERNO", usrRedis.getAPELLIDO_PATERNO());
					item.put("APELLIDO_MATERNO", usrRedis.getAPELLIDO_MATERNO());
					item.put("AREA", usrRedis.getAREA());
					item.put("FECHAMODIFICACION", usrRedis.getFECHAMODIFICACION());
					
					array.add(item);
					json.put("userredis", array);
				}
				System.out.println("tamaño::::::::::::::::::::::: " + size);
			}
			response = array.toJSONString();
			return "" + response;
		}

	}
   @Override
   public String getPapers() {
	   JedisPool pool = new JedisPool("localhost", 6379);
	   String respuesta = "";
	   String url = "https://doaj.org/api/search/articles/medicina";
       System.out.println("pagina a traer:: " + url);
       url += "?page=1&pageSize=20";
       URL obj = null;
       System.out.println("url DOAJ:: " + url);
       JSONParser jsonParser = new JSONParser();
       try {
    	   obj = new URL(url);
           SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
           sslContext.init(null, null, new SecureRandom());
           HttpsURLConnection httpsURLConnection = (HttpsURLConnection) obj.openConnection();
           httpsURLConnection.setSSLSocketFactory(sslContext.getSocketFactory());
           httpsURLConnection.setRequestMethod("GET");
           httpsURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
           httpsURLConnection.setRequestProperty("Content-Type", "text/plain");
           httpsURLConnection.setRequestProperty("charset", "utf-8");
           httpsURLConnection.connect();
           int responseCode = httpsURLConnection.getResponseCode();
           System.out.println("code from page:: " + responseCode);
           if (responseCode == HttpURLConnection.HTTP_OK) { // success
               BufferedReader in = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream(), "UTF8"));
               String inputLine;
               StringBuffer response = new StringBuffer();

               while ((inputLine = in.readLine()) != null) {
                   response.append(inputLine);
               }
               in.close();
               JSONObject jsonObject = (JSONObject) jsonParser.parse(""+response);
               JSONArray jsonArray = (JSONArray) jsonObject.get("results");
               respuesta += "" + jsonArray.toJSONString();
               System.out.println("respuesta de API Doaj:: " + respuesta);
           } else {
               System.out.println("fallo al intentar obtener la pagina http:: " + url);
           }
           httpsURLConnection.disconnect();
       } catch (SSLException ss) {
           System.out.println("fallo la conexion ssl de Doaj::: " + url);
           ss.printStackTrace();
       } catch (Exception e) {
           System.out.println("fallo la conexion de Doaj::: " + url);
           e.printStackTrace();
       }
	   return "" + respuesta;
   }
   
   
   public void sendcorreo() {
		final String fromEmail = "chapidevelop@gmail.com"; //requires valid gmail id
		final String password = "nklhailzbvlbkdjy"; // correct password for gmail id
		final String toEmail = "wailff_metall@hotmail.com"; // can be any email id 
		
		System.out.println("SSLEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
		props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
		props.put("mail.smtp.port", "465"); //SMTP Port
		
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		
		Session session = Session.getDefaultInstance(props, auth);
		this.sendImageEmail(session, "wailff_metall@hotmail.com", "email de prueba", "cuerpo de prueba");
	}
public void sendImageEmail(Session session, String toEmail, String subject, String body){
	try{
        MimeMessage msg = new MimeMessage(session);
        msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	     msg.addHeader("format", "flowed");
	     msg.addHeader("Content-Transfer-Encoding", "8bit");
	      
	     msg.setFrom(new InternetAddress("chapidevelop@gmail.com", "NoReply-JD"));

	     msg.setReplyTo(InternetAddress.parse("wailff_metall@hotmail.com", false));

	     msg.setSubject(subject, "UTF-8");

	     msg.setSentDate(new Date());

	     msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
	      
        // Create the message body part
        BodyPart messageBodyPart = new MimeBodyPart();

        messageBodyPart.setText(body);
        
        // Create a multipart message for attachment
        Multipart multipart = new MimeMultipart();

        //third part for displaying image in the email body
        messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("<h1>Email de registro</h1>" +
       		     "", "text/html");
        multipart.addBodyPart(messageBodyPart);
        
        //Set the multipart message to the email message
        msg.setContent(multipart);

        // Send message
        Transport.send(msg);
        System.out.println("EMail Sent Successfully !!");
     }catch (MessagingException e) {
        e.printStackTrace();
     } catch (UnsupportedEncodingException e) {
		 e.printStackTrace();
	}
}

}