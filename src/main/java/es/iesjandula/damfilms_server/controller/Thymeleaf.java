package es.iesjandula.damfilms_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.iesjandula.damfilms_server.repositories.IUsuarioRepository;

@Controller
public class Thymeleaf
{
	
	@Autowired
	IUsuarioRepository iUsuarioRepository;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String userReview(
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			Model model)
	{	
		if(this.iUsuarioRepository.encontrarUsuarioYContrasenia(email, password) != null)
		{
			return "home.html";	
		}
		
		return "login.html";
	}
}