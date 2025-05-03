package ao.inocencio.userservice.infrastructure.persistence;

import ao.inocencio.userservice.domain.model.Client;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "clients")
@Getter @Setter
public class ClientEntity {
    @Id
    private UUID id;

    @Column(nullable = false, length= 255)
    private String name;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    private String country;

    @Column(nullable = true, length = 255, unique = true)
    private String email;

    @Column(nullable = false)
    private boolean consent;

    @Column(name = "active_user", nullable = false)
    private boolean activeUser;

    @Column(name = "created_at", nullable = false, updatable = false)
        @CreationTimestamp
        private LocalDateTime createdAt;

    // Method to convert the Domain to Entity
    public static ClientEntity fromDomain(Client client){
        ClientEntity entity = new ClientEntity();
        entity.setId(client.getId());
        entity.setName(client.getName());
        entity.setPhoneNumber(client.getPhoneNumber());
        entity.setCountry(client.getCountry());
        entity.setEmail(client.getEmail());
        entity.setConsent(client.isConsent());
        entity.setActiveUser(client.isActiveUser());
        entity.setCreatedAt(client.getCreatedAt());
        return entity;
    }
    // Method to convert Entity to Domain
    public Client toDomain(){
        Client client = new Client(
                this.id,
                this.name,
                this.phoneNumber,
                this.email,
                this.country,
                this.createdAt
        );
        client.updateContactInfo(this.phoneNumber, this.country);
        client.setConsent(this.consent);
        client.setActiveUser(this.activeUser);
        return client;
    }
}
