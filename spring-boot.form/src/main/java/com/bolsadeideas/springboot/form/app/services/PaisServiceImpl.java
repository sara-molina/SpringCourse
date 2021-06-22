package com.bolsadeideas.springboot.form.app.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.form.app.model.domain.Pais;

@Service
public class PaisServiceImpl implements PaisService{

	private List<Pais> lista;
	
	public PaisServiceImpl() {
		this.lista= Arrays.asList(
					new Pais(1,"ES","España"),
					new Pais(2,"ME","Méjico"),
					new Pais(3,"CL","Chile"),
					new Pais(4,"PE","Perú"));
	}
	
	@Override
	public List<Pais> listar() {
		return lista;
	}

	@Override
	public Pais obtenerPorId(Integer id) {
		Pais resultado = null;
		for(Pais pais : lista) {
			if(id.equals(pais.getId())) {
				resultado = pais;
				break;
			}
		}
		return resultado;
	}
}
