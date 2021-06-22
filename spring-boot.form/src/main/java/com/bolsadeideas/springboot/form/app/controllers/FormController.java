package com.bolsadeideas.springboot.form.app.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.form.app.Editors.NombreMayusculaEditor;
import com.bolsadeideas.springboot.form.app.Editors.PaisPropertyEditor;
import com.bolsadeideas.springboot.form.app.Editors.RolesEditor;
import com.bolsadeideas.springboot.form.app.model.domain.Pais;
import com.bolsadeideas.springboot.form.app.model.domain.Role;
import com.bolsadeideas.springboot.form.app.model.domain.Usuario;
import com.bolsadeideas.springboot.form.app.services.PaisService;
import com.bolsadeideas.springboot.form.app.services.RoleService;
import com.bolsadeideas.springboot.form.app.validation.UsuarioValidador;

@Controller
@SessionAttributes("user")
public class FormController {
	
	@Autowired
	private UsuarioValidador validador;
	
	@Autowired
	private PaisService paisService;
	
	@Autowired
	private PaisPropertyEditor paisEditor;
	
	@Autowired
	public RoleService rolesService;
	
	@Autowired
	private RolesEditor roleEditor;
	
	@InitBinder
	public void InitBinder (WebDataBinder binder) {
		binder.addValidators(validador);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "fechaNacimiento", new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(String.class, new NombreMayusculaEditor());
		binder.registerCustomEditor(Pais.class,"pais", paisEditor);
		binder.registerCustomEditor(Role.class,"roles", roleEditor);
	}
	
	@ModelAttribute("listaPaises")
	public List<String> listaPaises() {
		return Arrays.asList("España","Méjico","Chile","Peru","Colombia");
	}
	
	@ModelAttribute("listaDePaises")
	public List<Pais> paises() {
		return paisService.listar();
	}
	
	@ModelAttribute("paisesMap")
	public Map<String, String> paisesMap() {
		Map<String,String> paisesMap = new HashMap<String,String>();
		
		paisesMap.put("ES", "España");
		paisesMap.put("MX", "Méjico");
		paisesMap.put("CL", "Chile");
		paisesMap.put("PE", "Perú");
		paisesMap.put("CO", "Colombia");
		
		return paisesMap;
		
	}
	
	@ModelAttribute("listaRolesString")
	private List<String> listaRolesString(){
		List<String> roles = new ArrayList<>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		roles.add("ROLE_MODERATOR");
		return roles;
	}
	
	@ModelAttribute("rolesMap")
	public Map<String, String> rolesMap() {
		Map<String,String> rolesMap = new HashMap<String,String>();
		
		rolesMap.put("ROLE_ADMIN", "Adminsitrador");
		rolesMap.put("ROLE_USER", "Moderador");
		rolesMap.put("ROLE_MDOERATOR", "Usuario");
		return rolesMap;
		
	}

	@ModelAttribute("listaRoles")
	public List<Role> listaRoles(){
		return this.rolesService.listar();
	}
	
	@ModelAttribute("genero")
	public List<String> genero(){
		return Arrays.asList("Hombre","Mujer");
	}
	
	

	@GetMapping("/form")
	public String form(Model model) {
		model.addAttribute("titulo","Formulario para usuario");
		
		Usuario usuario = new Usuario();
		usuario.setNombre("John");
		usuario.setApellido("Silver");
		usuario.setId("1234556");
		usuario.setHabilitar(true);
		usuario.setValorSecreto("secreto");
		usuario.setPais(new Pais(1,"ES","España"));
		usuario.setRoles(Arrays.asList(new Role(3,"Moderador","ROLE_MODERATOR")));
		
		model.addAttribute("user",usuario);
		
		return "form";
	}

	
	@PostMapping("/form")
	public String procesarFormulario(@Valid @ModelAttribute("user") Usuario usuario, BindingResult result, Model model) {
		validador.validate(usuario, result);		
		if(result.hasErrors()) {
			/*Map<String,String> errores = new HashMap<>();
			
			result.getFieldErrors().forEach(err ->{
				errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			});
			model.addAttribute("error",errores); */
			return "form";
		}
		return "redirect:/ver";
	}
	
	
	@GetMapping("/ver")
	public String ver( @SessionAttribute(name="user", required=false) Usuario usuario, Model model, SessionStatus status){
		model.addAttribute("titulo","Resultado del form");
		if(usuario == null) {
			return "redirect:/form";
		}
		status.setComplete();
		return "resultado";
	}
	

}
