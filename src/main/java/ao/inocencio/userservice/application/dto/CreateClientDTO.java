package ao.inocencio.userservice.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateClientDTO(
        @NotBlank @Size(max = 255) String name,
        @NotBlank @Size(max = 9) String phoneNumber,
        String country,
        String email,
        boolean consent
) {
}
