package infrastructure.gateway;

import domain.entities.Usuario;
import domain.gateways.UsuarioRepository;
import infrastructure.mappers.UsuarioMapper;
import infrastructure.persistence.repositories.SpringUsuarioRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UsuarioRepositoryAdapter implements UsuarioRepository {

    private final SpringUsuarioRepository springRepository;
    private final UsuarioMapper mapper;

    public UsuarioRepositoryAdapter(SpringUsuarioRepository springRepository, UsuarioMapper mapper) {
        this.springRepository = springRepository;
        this.mapper = mapper;
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        var entity = mapper.toEntity(usuario);
        var entitySalva = springRepository.save(entity);
        return mapper.toDomain(entitySalva);
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return springRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return springRepository.findByEmail(email)
                .map(mapper::toDomain);
    }

    @Override
    public void deletar(Long id) {
        springRepository.deleteById(id);
    }

    @Override
    public Boolean existe(Long id) {
        return springRepository.existsById(id);
    }

    @Override
    public List<Usuario> listarTodos() {
        return springRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}