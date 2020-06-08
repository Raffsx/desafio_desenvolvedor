package br.com.cadastro.usuarios.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.cadastro.usuarios.dto.UsuarioDto;
import br.com.cadastro.usuarios.entity.Usuario;
import br.com.cadastro.usuarios.exception.ObjectNotFoundException;
import br.com.cadastro.usuarios.mapper.Mappable;
import br.com.cadastro.usuarios.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements Mappable {

  private final UsuarioRepository usuarioRepository;
  private final ModelMapper mapper;

  @Autowired
  public UsuarioService(UsuarioRepository usuarioRepository, ModelMapper mapper) {
    this.usuarioRepository = usuarioRepository;
    this.mapper = mapper;
  }

  public UsuarioDto findById(Long id) {
    Optional<Usuario> usuario = usuarioRepository.findById(id);
    Usuario c =
            usuario.orElseThrow(() -> new ObjectNotFoundException("Nenhum usuário encontrado: " + id));
    return map(c, UsuarioDto.class);
  }

  public List<UsuarioDto> findAll() {
    List<Usuario> usuarios = usuarioRepository.findAll();
    return usuarios.stream().map(m -> map(m, UsuarioDto.class)).collect(Collectors.toList());
  }

  public void save(UsuarioDto usuarioDto) {
    Usuario usuario = map(usuarioDto, Usuario.class);
    usuarioRepository.save(usuario);
  }

  public void update(UsuarioDto usuarioDto, Long id) {
    Optional<Usuario> usuario = usuarioRepository.findById(id);
    if (usuario.isPresent()) {
      usuarioDto.setId(id);
      usuarioRepository.save(map(usuarioDto, Usuario.class));
    }
  }

  public void delete(Long id) {
    usuarioRepository.deleteById(id);
  }

  @Override
  public ModelMapper mapper() {
    return this.mapper;
  }


}
