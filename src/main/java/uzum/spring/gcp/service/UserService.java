package uzum.spring.gcp.service;

import uzum.spring.gcp.dto.request.UserRequestDto;
import uzum.spring.gcp.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserById(Long id);

    UserResponseDto getUserByPinfl(String pinfl);

    UserResponseDto createUser(UserRequestDto dto);
}
