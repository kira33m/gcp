package uzum.spring.gcp.dto.response;

import lombok.Builder;
import  java.time.LocalDate;

@Builder
public record ErrorDto(
    int status,
    String message,
    LocalDate timestamp,
    String path
) {}
