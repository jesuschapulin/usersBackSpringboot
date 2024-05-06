package com.example.demo;

import java.util.List;
import com.example.models.empresas;

public interface EmpresasDao {
	List<empresas> getPrueba();
	List<empresas> getPruebaEmpresaByCredentials(String email,String secret);
	List<empresas> buscarEmpresas();
}
