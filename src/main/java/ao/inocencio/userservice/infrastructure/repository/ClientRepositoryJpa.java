package ao.inocencio.userservice.infrastructure.repository;

import ao.inocencio.userservice.domain.model.Client;
import ao.inocencio.userservice.domain.repository.ClientRepository;
import ao.inocencio.userservice.infrastructure.persistence.ClientEntity;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public class ClientRepositoryJpa implements ClientRepository {
    private final EntityManager entityManager;

    public ClientRepositoryJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public Client save(Client client) {
        var entity = ClientEntity.fromDomain(client);
        entityManager.persist(entity);
        return entity.toDomain();
    }
    @Override
    public Optional<Client> findById(UUID id){
        return Optional.ofNullable(entityManager.find(ClientEntity.class, id))
                .map(ClientEntity::toDomain);
    }

    @Override
    public boolean existsById(UUID id) {
        return false;
    }
    @Transactional
    public boolean existsByPhoneNumber(String phoneNumber){
        var query = entityManager.createQuery(
                "SELECT COUNT(c) > 0 FROM ClientEntity c WHERE c.phoneNumber = :phoneNumber",
                Boolean.class
        );
        query.setParameter("phoneNumber", phoneNumber);
        return query.getSingleResult();
    }
}
