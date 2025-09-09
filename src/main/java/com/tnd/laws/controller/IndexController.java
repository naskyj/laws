package com.tnd.laws.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class IndexController {

    @GetMapping("/")
    public String index() throws IOException {

        return "Home Page";
    }
}
