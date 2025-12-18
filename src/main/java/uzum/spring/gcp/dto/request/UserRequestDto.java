package uzum.spring.gcp.dto.request;

import jakarta.validation.constraints.*;
import lombok.Builder;
import uzum.spring.gcp.constant.enums.DocumentType;
import uzum.spring.gcp.constant.enums.Gender;

import java.time.LocalDate;

@Builder
public record UserRequestDto(
    @NotBlank(message = "Full name must not be empty")
    String fullName,

    @NotBlank(message = "Address must not be empty")
    String address,

    @NotBlank(message = "Phone must not be empty")
    String phone,

    @Email(message = "Invalid email address")
    @NotBlank(message = "Email must not be empty")
    String email,

    @NotBlank(message = "PINFL must not be empty")
    String pinfl,

    @NotNull(message = "Age must not be empty")
    @Min(value = 14, message = "Age must be greater or equal to 14")
    @Max(value = 120, message = "Age must be less or equal to 120")
    Integer age,

    Gender gender,

    @NotNull(message = "Document type must not be empty")
    DocumentType documentType,

    String photo,

    @NotNull(message = "Issue date must not be null")
    LocalDate issueDate,

    @NotNull(message = "Expiry date must not be null")
    LocalDate expiryDate,

    @NotBlank(message = "Citizenship must not be empty")
    String citizenship
) {}
