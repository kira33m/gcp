package uzum.spring.gcp.mapper;

import uzum.spring.gcp.entity.User;
import uzum.spring.gcp.dto.response.UserResponseDto;
import uzum.spring.gcp.dto.request.UserRequestDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserRequestDto dto) {
        return User.builder()
            .fullName(dto.fullName())
            .address(dto.address())
            .phone(dto.phone())
            .email(dto.email())
            .pinfl(dto.pinfl())
            .age(dto.age())
            .gender(dto.gender())
            .documentType(dto.documentType())
            .photo(dto.photo())
            .issueDate(dto.issueDate())
            .expiryDate(dto.expiryDate())
            .citizenship(dto.citizenship())
            .build();
    }

    public UserResponseDto toDto(User user) {
        return UserResponseDto.builder()
            .id(user.getId())
            .fullName(user.getFullName())
            .address(user.getAddress())
            .phone(user.getPhone())
            .email(user.getEmail())
            .pinfl(user.getPinfl())
            .age(user.getAge())
            .gender(user.getGender())
            .documentType(user.getDocumentType())
            .photo(user.getPhoto())
            .issueDate(user.getIssueDate())
            .expiryDate(user.getExpiryDate())
            .citizenship(user.getCitizenship())
            .build();
    }
}
