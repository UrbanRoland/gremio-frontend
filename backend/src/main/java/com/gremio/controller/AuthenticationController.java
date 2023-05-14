package com.gremio.controller;

import com.gremio.model.User;
import com.gremio.model.dto.UserDetailsDto;
import com.gremio.model.dto.request.CreateUserRequest;
import com.gremio.service.interfaces.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    
    private final UserService userService;
    private final ConversionService conversionService;
    
    @PostMapping
    public UserDetailsDto createUser(@RequestBody @Valid final CreateUserRequest request) {
        final User user = userService.create(conversionService.convert(request, User.class));

       return conversionService.convert(user, UserDetailsDto.class);

    }
}