package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {

    @RequestMapping(value = "/body", method = RequestMethod.GET)
    public ResponseEntity<String> body() {
        String body
                = "<html>"
                + "<body>"
                + "<h1>Hellow world!</h1><h2>Spring Boot</h2>"
                + "</body>"
                + "</html>";
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @RequestMapping(value = "/body2", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> body2() {
        String body
                = "<html>"
                + "<body>"
                + "<h1>Hellow world!</h1><h2>Spring Boot</h2>"
                + "</body>"
                + "</html>";
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

}
