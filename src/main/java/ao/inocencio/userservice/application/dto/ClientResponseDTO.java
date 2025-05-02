package ao.inocencio.userservice.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ClientResponseDTO(
        UUID id,
        String name,
        String phoneNumber,
        String country,
        String email,
        boolean consent,
        boolean activeUser,
        LocalDateTime createdAt
) {
}
