package ao.inocencio.userservice.application.web;

import ao.inocencio.userservice.application.dto.ClientResponseDTO;
import ao.inocencio.userservice.application.dto.CreateClientDTO;
import ao.inocencio.userservice.application.usecases.CreateClientUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/client/")
public class ClientController {
    private final CreateClientUseCase createClientUseCase;

    public ClientController(CreateClientUseCase createClientUseCase) {
        this.createClientUseCase = createClientUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientResponseDTO clientResponse (@RequestBody @Valid CreateClientDTO createClientDTO) {
        return createClientUseCase.execute(createClientDTO);
    }
}
