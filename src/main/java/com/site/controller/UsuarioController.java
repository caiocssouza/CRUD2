package com.site.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.site.model.Usuario;
import com.site.repositorio.UsuarioRepositorio;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	public UsuarioRepositorio usuarioRepositorio;
	
	@GetMapping("/novo")
	public ModelAndView novoUsuario() {
		ModelAndView mv = new ModelAndView("/novo");
		mv.addObject("usuario", new Usuario());
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvarUsuario(@Valid Usuario usuario) {
		ModelAndView mv = new ModelAndView("redirect:/");
		usuarioRepositorio.save(usuario);
		return mv;
	}
	
	@GetMapping("/adm/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("/auth/adm/admlistar");
		List<Usuario> listaUsuarios = usuarioRepositorio.findAll();
		mv.addObject("usuarios", listaUsuarios);
		return mv;
	}
	
	@GetMapping("/adm/apagar/{id}")
	public ModelAndView apagar(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("redirect:/usuario/adm/listar");
		usuarioRepositorio.deleteById(id);
		return mv;
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("/auth/user/usereditar");
		Usuario usuario = usuarioRepositorio.getOne(id);
		mv.addObject("usuario", usuario);
		return mv;
	}
	
	@PostMapping("/editar/{id}")
    public ModelAndView editar(Usuario usuario) {
		ModelAndView mv = new ModelAndView("redirect:/usuario/adm/listar");
		usuarioRepositorio.save(usuario);
		return mv;
    }
	
	
}
