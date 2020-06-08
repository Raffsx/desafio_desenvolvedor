package br.com.cadastro.usuarios.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuario implements Serializable {

  private static final long serialVersionUID = 5775799572077992014L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Campo nome é obrigatório")
  private String nome;

  @NotNull(message = "Idade é um campo obrigatório")
  private Integer idade;

  @NotBlank(message = "Email é um campo obrigatório")
  @Email(message = "Email precisa ser válido")
  private String email;
}
