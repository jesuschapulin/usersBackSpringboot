package com.example.demo;

import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.example.models.empresas;

@Repository
public class empresasController implements EmpresasDao{
	 private final JdbcTemplate jdbcTemplate;
	 @Autowired
	 public empresasController(JdbcTemplate jdbcTemplate) {
	     this.jdbcTemplate = jdbcTemplate;
	 }
	@Override
	   public List<empresas> getPrueba() {
		   List<empresas> lista = new ArrayList<empresas>();
	       SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
	                .withProcedureName("buscar_empresa_por_nombre")
	                .declareParameters(
	                        new SqlParameter("i_nombre", Types.VARCHAR),
	                        new SqlOutParameter("o_id_empresa", Types.INTEGER),
	                        new SqlOutParameter("o_nombre_resultado", Types.VARCHAR)
	                );

	        Map<String, Object> inParams = Collections.singletonMap("i_nombre", "Empresa B");
	        Map<String, Object> outParams = jdbcCall.execute(inParams);

	        int idEmpresa = (int) outParams.get("o_id_empresa");
	        String nombreResultado = (String) outParams.get("o_nombre_resultado");
	        empresas em = new empresas();
	        em.setId_empresa(idEmpresa);
	        em.setNombre(nombreResultado);
	        lista.add(em);
	        System.out.println("ID Empresa: " + idEmpresa + ", Nombre: " + nombreResultado);
	        return lista;
	   }
	   @Override
	   public List<empresas> getPruebaEmpresaByCredentials(String i_email, String secret) {
		   List<empresas> lista = new ArrayList<empresas>();
	       SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
	                .withProcedureName("validarUsuario")
	                .declareParameters(
	                        new SqlParameter("i_email", Types.VARCHAR),
	                        new SqlParameter("i_secret", Types.VARCHAR), // Agrega el nuevo parámetro aquí
	                        new SqlOutParameter("o_id_empresa", Types.INTEGER),
	                        new SqlOutParameter("o_nombre_resultado", Types.VARCHAR)
	                );

	       Map<String, Object> inParams = new HashMap<>();
	       inParams.put("i_email", i_email);
	       inParams.put("i_secret", secret);
	       ///inParams.put("i_otro_parametro", 123); // Reemplaza con el valor deseado
	        Map<String, Object> outParams = jdbcCall.execute(inParams);

	        int idEmpresa = (int) outParams.get("o_id_empresa");
	        String nombreResultado = (String) outParams.get("o_nombre_resultado");
	        empresas em = new empresas();
	        em.setId_empresa(idEmpresa);
	        em.setNombre(nombreResultado);
	        lista.add(em);
	        System.out.println("ID Empresa: " + idEmpresa + ", Nombre: " + nombreResultado);
	        return lista;
	   }
	   @Override
	   public List<empresas> buscarEmpresas() {
		    List<empresas> empresas = new ArrayList<>();
		    try {
		        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
		                .withProcedureName("buscar_empresas")
		                .returningResultSet("o_cursor", (rs, rowNum) -> {
		                    // Imprimir los nombres de las columnas
		                    ResultSetMetaData metaData = rs.getMetaData();
		                    int columnCount = metaData.getColumnCount();
//		                    for (int i = 1; i <= columnCount; i++) {
//		                        System.out.println("Nombre de columna " + i + ": " + metaData.getColumnName(i));
//		                    }
		                    // Mapea cada fila del resultado del cursor a objetos Empresa
		                    empresas empresa = new empresas();
		                    empresa.setNombre(rs.getString("NOMBRE"));
		                    empresa.setId_empresa(rs.getInt("ID_EMPRESA"));
		                    // Mapea otros campos si es necesario
		                    return empresa;
		                });
		        MapSqlParameterSource params = new MapSqlParameterSource()
		                .addValue("i_nombre", "nombre");
		        Map<String, Object> result = jdbcCall.execute(params);
		        // Accede a los resultados del procedimiento almacenado
		        @SuppressWarnings("unchecked")
		        List<empresas> resultados = (List<empresas>) result.get("o_cursor");
		        empresas.addAll(resultados);
		    } catch (Exception ex) {
		        // Manejar la excepción apropiadamente, por ejemplo, logueándola o lanzándola nuevamente
		        ex.printStackTrace();
		    }
		    return empresas;
	   }	
}
