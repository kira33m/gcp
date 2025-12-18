package uzum.spring.gcp.service.impl;

import uzum.spring.gcp.dto.response.UserResponseDto;
import uzum.spring.gcp.dto.request.UserRequestDto;
import uzum.spring.gcp.entity.User;
import uzum.spring.gcp.exception.BusinessException;
import uzum.spring.gcp.exception.UserAlreadyExistsException;
import uzum.spring.gcp.exception.UserNotFoundException;
import uzum.spring.gcp.repository.UserRepository;
import uzum.spring.gcp.mapper.UserMapper;
import org.springframework.stereotype.Service;
import uzum.spring.gcp.service.UserService;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll()
            .stream()
            .map(userMapper::toDto)
            .toList();
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found by id " + id));
        return userMapper.toDto(user);
    }

    @Override
    public UserResponseDto getUserByPinfl(String pinfl) {
        User user = userRepository.findByPinfl(pinfl)
            .orElseThrow(() -> new UserNotFoundException("User not found by pinfl " + pinfl));
        return userMapper.toDto(user);
    }

    @Override
    public UserResponseDto createUser(UserRequestDto dto) {
        if (userRepository.existsByPinfl(dto.pinfl())) {
            throw new UserAlreadyExistsException("User already exists with pinfl " + dto.pinfl());
        }

        validateBusinessRules(dto);

        User user = userMapper.toEntity(dto);
        User saved = userRepository.save(user);
        return userMapper.toDto(saved);
    }

    private void validateBusinessRules(UserRequestDto dto) {
        if (dto.age() != null && dto.age() < 14) {
            throw new BusinessException("User must be at least 14 years old");
        }
        if (dto.expiryDate().isBefore(dto.issueDate())) {
            throw new BusinessException("expiryDate cannot be before issueDate");
        }
        if (dto.expiryDate().isBefore(LocalDate.now())) {
            throw new BusinessException("Document is already expired");
        }
    }
}
