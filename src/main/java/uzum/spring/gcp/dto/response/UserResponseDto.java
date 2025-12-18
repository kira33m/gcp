package uzum.spring.gcp.dto.response;

import lombok.Builder;
import uzum.spring.gcp.constant.enums.DocumentType;
import uzum.spring.gcp.constant.enums.Gender;

import java.time.LocalDate;

@Builder
public record UserResponseDto(
    Long id,
    String fullName,
    String address,
    String phone,
    String email,
    String pinfl,
    Integer age,
    Gender gender,
    DocumentType documentType,
    String photo,
    LocalDate issueDate,
    LocalDate expiryDate,
    String citizenship
) {}
