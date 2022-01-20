package com.andresmya.backendmarketplace.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/utils")
public class UtilController {

    @RequestMapping(value = "/health",method = RequestMethod.HEAD)
    public void apiHealth(){}

}
