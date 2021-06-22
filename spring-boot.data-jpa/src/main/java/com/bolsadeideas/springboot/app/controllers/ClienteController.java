package com.bolsadeideas.springboot.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.dao.IClienteDao;
import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.service.IClienteService;
import com.bolsadeideas.springboot.app.util.paginator.PageRender;

@Controller
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;

	@RequestMapping(value="listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name="page",defaultValue="0") int page, Model model) {
		Pageable pageRequest = PageRequest.of(page, 4);
		Page<Cliente> clientes = clienteService.findAll(pageRequest);
		
		PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);
		model.addAttribute("titulo","Listado de clientes");
		model.addAttribute("clientes",clientes);
		model.addAttribute("page",pageRender);
		return "listar";
	}
	
	@GetMapping(value="/form")
	public String crear(Map<String, Object> model) {
	Cliente cliente = new Cliente();
	model.put("titulo","Forumulario de cliente");
	model.put("cliente", cliente);
	return "form";
	}
	
	@RequestMapping(value="/form/{id}")
	public String editar (@PathVariable(value="id") Long id, Map<String,Object> model, RedirectAttributes flash) {
		Cliente cliente = null;
		
		if(id >0) {
			cliente = clienteService.findOne(id);
			if(cliente == null) {
				flash.addFlashAttribute("error", "Cliente no existe en la BBDD");
				return "redirect:/listar";
			}
		}else {
			flash.addFlashAttribute("error", "El id del cliente no puede ser 0");
			return "redirect:/listar";
		}
		
		model.put("cliente", cliente);
		model.put("titulo", "Editar cliente");
		
		return "form";
		
	}
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String guardar( @Valid Cliente cliente, BindingResult result, RedirectAttributes flash, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("titulo","Listado de clientes");
			return "form";
		}
		String mensajeFlash= (cliente.getId() == null) ? "Cliente creado con éxito" : "cliente modificado con éxito";
		clienteService.save(cliente);
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listar";
	}
	
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar (@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if(id>0) {
			clienteService.delete(id);
			flash.addFlashAttribute("success", "Cliente creado con éxito");
		}
		
		return "redirect:/listar";
	}
	
	
}
