package uzum.spring.gcp.controller;

import uzum.spring.gcp.dto.request.UserRequestDto;
import uzum.spring.gcp.dto.response.UserResponseDto;
import uzum.spring.gcp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gcp/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }
    @PostMapping
    public UserResponseDto createUser(@Valid @RequestBody UserRequestDto dto) {
        return userService.createUser(dto);
    }

    @GetMapping("/by-id/{id}")
    public UserResponseDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/by-pinfl/{pinfl}")
    public UserResponseDto getUserByPinfl(@PathVariable String pinfl) {
        return userService.getUserByPinfl(pinfl);
    }
}
