package infrastructure.gateway;

import domain.entities.TipoUsuario;
import domain.gateways.TipoUsuarioRepository;
import infrastructure.mappers.TipoUsuarioMapper;
import infrastructure.persistence.repositories.SpringTipoUsuarioRepository;
import infrastructure.persistence.entities.TipoUsuarioEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TipoUsuarioRepositoryAdapter implements TipoUsuarioRepository {
    private final SpringTipoUsuarioRepository springRepository;
    private final TipoUsuarioMapper mapper;

    public TipoUsuarioRepositoryAdapter(SpringTipoUsuarioRepository repository, TipoUsuarioMapper mapper) {
        this.springRepository = repository;
        this.mapper = mapper;
    }

    @Override
    public TipoUsuario salvar(TipoUsuario tipo) {
        TipoUsuarioEntity entity = mapper.toEntity(tipo);
        return mapper.toDomain(springRepository.save(entity));
    }

    @Override
    public List<TipoUsuario> listarTodos() {
        return springRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

}
