package ao.inocencio.userservice.infrastructure.config;

import ao.inocencio.userservice.domain.repository.ClientRepository;
import ao.inocencio.userservice.infrastructure.repository.ClientRepositoryJpa;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public ClientRepository clientRepository(EntityManager entityManager) {
        return new ClientRepositoryJpa(entityManager);
    }
}
