package com.bolsadeideas.springboot.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/variables")
public class EjemploVariablesPathController {

	@GetMapping("/")
	public String index(Model model) {
		return "variables/index";
	}
	
	@GetMapping("/string/{textoURL}")
	public String variables(@PathVariable (name="textoURL") String textoVariable, Model model) {
		model.addAttribute("resultado","El texto enviado en la ruta es: ".concat(textoVariable));
		return "variables/ver";
		
	}
	
	@GetMapping("/string/{textoURL}/{numURL}")
	public String variables(@PathVariable (name="numURL") Integer numVariable, @PathVariable (name="textoURL") String textoVariable, Model model) {
		
		model.addAttribute("resultado","El texto enviado en la ruta es: ".concat(textoVariable) + "el número enviado es  : " +numVariable);
		return "variables/ver";
		
	}
}
