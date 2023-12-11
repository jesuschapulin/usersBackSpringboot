package com.example.demo;


import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
 
@SpringBootApplication
public class TestApp {
//implements CommandLineRunner {
// 
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//     
//    public static void main(String[] args) {
//    	SpringApplication.run(DemoApplication.class, args);
//    }
// 
//    @Override
//    public void run(String... args) throws Exception {
//        String sql = "SELECT * FROM usuariosadea";
//         
//        List<User> users = jdbcTemplate.query(sql,
//                BeanPropertyRowMapper.newInstance(User.class));
//        for (User u:users) {
//        	System.out.println(u.getNOMBRE());
//        }
//    }
// 
}