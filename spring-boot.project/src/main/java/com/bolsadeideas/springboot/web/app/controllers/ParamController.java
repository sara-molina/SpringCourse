package com.bolsadeideas.springboot.web.app.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/params")
public class ParamController {

	@GetMapping("/")
	public String index() {
		
		return "params/index";
	}
	@GetMapping("/string")
	public String param(@RequestParam(required=false,defaultValue = "valor") String texto, Model model) {
		model.addAttribute("resultado", "El parámetro enviado es: ".concat(texto));
		return "params/ver";
	}
	
	@GetMapping("/mix-param")
	public String param(@RequestParam(required=false,defaultValue = "valor") String saludo,@RequestParam Integer numero, Model model) {
		model.addAttribute("resultado", "El saludo enviado es: ".concat(saludo) + "y el número es " + numero);
		return "params/ver";
	}
	
	@GetMapping("/mix-param-request")
	public String param(HttpServletRequest request , Model model) {
		String saludo = request.getParameter("saludo");
		Integer num=null;
		try {
		num = Integer.parseInt(request.getParameter("numero"));
		}catch(NumberFormatException e) {
			
		}
		model.addAttribute("resultado", "El saludo enviado es: ".concat(saludo) + "y el número es " + num);
		return "params/ver";
	}
}
