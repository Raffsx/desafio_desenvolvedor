package br.com.cadastro.usuarios.controller;

import java.util.List;

import br.com.cadastro.usuarios.dto.UsuarioDto;
import br.com.cadastro.usuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

  private final UsuarioService usuarioService;

  @Autowired
  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @GetMapping
  public ModelAndView listar() {
    ModelAndView modelAndView = new ModelAndView("usuario/lista");
    List<UsuarioDto> usuarios = usuarioService.findAll();
    modelAndView.addObject("usuarios", usuarios);
    return modelAndView;
  }

  @GetMapping("/formulario")
  public ModelAndView formulario(UsuarioDto usuario) {
    ModelAndView modelAndView = new ModelAndView("usuario/formulario");
    modelAndView.addObject("usuario", usuario);
    return modelAndView;
  }

  @PostMapping("/salvar")
  public ModelAndView salvar(@Valid UsuarioDto usuario, BindingResult result) {
    if (result.hasErrors()) {
      return formulario(usuario);
    }
    usuarioService.save(usuario);
    return listar();
  }

  @GetMapping("/editar/{id}")
  public ModelAndView editarUsuario(@PathVariable("id") Long id) {
    UsuarioDto usuario = usuarioService.findById(id);
    return formulario(usuario);
  }

  @GetMapping("/deletar/{id}")
  public ModelAndView deletar(@PathVariable Long id) {
    usuarioService.delete(id);
    return listar();
  }

}

