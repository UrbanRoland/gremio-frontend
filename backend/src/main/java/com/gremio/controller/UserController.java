package com.gremio.controller;

import com.gremio.annotations.UserRequired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @PostMapping(value = "/test")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String test() {
        return "it works";
    }
}