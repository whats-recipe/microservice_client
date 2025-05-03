package ao.inocencio.userservice.infrastructure.persistence;

import ao.inocencio.userservice.domain.model.Client;

public class ClientMapper {
    public static ClientEntity fromDomain(Client client) {
        ClientEntity entity = new ClientEntity();
        entity.setId(client.getId());
        entity.setName(client.getName());
        entity.setPhoneNumber(client.getPhoneNumber());
        entity.setCountry(client.getCountry());
        entity.setEmail(client.getEmail());
        entity.setConsent(client.isConsent());
        entity.setActiveUser(client.isActiveUser());
        entity.setCreatedAt(entity.getCreatedAt());
        return entity;
    }
    public static Client toDomain(ClientEntity entity) {
        Client client =  new Client(
                entity.getId(),
                entity.getName(),
                entity.getPhoneNumber(),
                entity.getEmail(),
                entity.getCountry(),
                entity.getCreatedAt()
        );
                client.updateContactInfo(entity.getPhoneNumber(), entity.getCountry());
                client.setConsent(entity.isConsent());
                client.setActiveUser(entity.isActiveUser());

                if (entity.getPhoneNumber() != null) {
                    client.updateContactInfo(entity.getPhoneNumber(), entity.getCountry());
                }
                if (entity.isConsent()) {
                    client.grantConsent();
                }
                if (!entity.isActiveUser()){
                    client.deactivate();
                }

                return client;
    }
}
