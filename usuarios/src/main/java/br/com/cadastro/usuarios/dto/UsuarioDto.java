package br.com.cadastro.usuarios.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioDto implements Serializable {

  private static final long serialVersionUID = 5775799572077992014L;

  private Long id;
  private String nome;
  private Integer idade;
  private String email;
}
