package ao.inocencio.userservice.domain.repository;

import ao.inocencio.userservice.domain.model.Client;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository {
    Client save(Client client);
    Optional<Client> findById(UUID id);
    boolean existsById(UUID id);
    boolean existsByPhoneNumber(String phoneNumber);
}
