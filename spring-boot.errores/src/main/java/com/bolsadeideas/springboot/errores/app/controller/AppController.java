package com.bolsadeideas.springboot.errores.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bolsadeideas.springboot.errores.app.errors.UsuarioNoEncontradoException;
import com.bolsadeideas.springboot.errores.app.models.Usuario;
import com.bolsadeideas.springboot.errores.app.services.UsuarioService;

@Controller
public class AppController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/index")
	public String index() {
		Integer i = 100/0;
		return "index";
	}
	
	@GetMapping("ver/{id}")
	public String ver (@PathVariable Integer id, Model model) {
		//Usuario usuario = usuarioService.obtenerPorId(id);
		
		/*if(usuario == null){
			throw new UsuarioNoEncontradoException(id.toString());
		}*/
		
		Usuario usuario =  usuarioService.obtenerPorIdOptional(id).orElseThrow(() -> new UsuarioNoEncontradoException(id.toString()));
		
		model.addAttribute("usuario", usuario);	
		model.addAttribute("titulo", "Detalle usuario : ".concat(usuario.getNombre()));	
		return "ver";
	}
}
