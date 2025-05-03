package ao.inocencio.userservice.application.usecases;

import ao.inocencio.userservice.application.dto.ClientResponseDTO;
import ao.inocencio.userservice.application.dto.CreateClientDTO;
import ao.inocencio.userservice.domain.exception.PhoneAlreadyExistsException;
import ao.inocencio.userservice.domain.model.Client;
import ao.inocencio.userservice.domain.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CreateClientUseCase {
    private final ClientRepository clientRepository;

    public CreateClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    @Transactional
    public ClientResponseDTO execute(CreateClientDTO createClientDTO) {
        if (clientRepository.existsByPhoneNumber(createClientDTO.phoneNumber())) {
            throw new PhoneAlreadyExistsException(createClientDTO.phoneNumber());
        }
        // Create a new Client
    var client = new Client(
            UUID.randomUUID(),
            createClientDTO.name(),
            createClientDTO.phoneNumber(),
            createClientDTO.email(),
            createClientDTO.country(),
            LocalDateTime.now()
    );
        // update some information
        if(createClientDTO.phoneNumber() != null){
            client.updateContactInfo(createClientDTO.phoneNumber(), createClientDTO.country());
        }
        // consent
        if (createClientDTO.consent()) {
            client.grantConsent();
        }
        // Save in the database
        var savedClient = clientRepository.save(client);

        return new ClientResponseDTO(
                savedClient.getId(),
                savedClient.getName(),
                savedClient.getPhoneNumber(),
                savedClient.getCountry(),
                savedClient.getEmail(),
                savedClient.isConsent(),
                savedClient.isActiveUser(),
                savedClient.getCreatedAt()
        );
    }
}