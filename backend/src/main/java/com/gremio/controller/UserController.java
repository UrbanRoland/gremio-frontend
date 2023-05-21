package com.gremio.controller;

import com.gremio.model.dto.UserDetailsDto;
import com.gremio.model.dto.response.PageableResponse;
import com.gremio.service.interfaces.UserService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends AbstractController {

    private final UserService userService;
    UserController(final ConversionService conversionService, final UserService userService) {
        super(conversionService);
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public PageableResponse<UserDetailsDto> getAllUsers(final Pageable pageable) {
        return this.getPageableResponse(userService.getAllUser(pageable));
    }
}