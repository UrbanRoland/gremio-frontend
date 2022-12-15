package com.gremio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthContoller {

    @GetMapping(value = "/user")
    public String user(){
        return "Hello user!!!";
    }

    @GetMapping(value = "/admin")
    public String admin(){
        return "adadadadsadadas user!!!";
    }
}
