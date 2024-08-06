package com.springboot.ultimate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author prabhakar, @Date 24-07-2024
 */
@RestController
public class TestClass {

    @GetMapping(value = "/hello")
    public String hello(){
        return "<h1>Hello Spring Boot Developers...@</h1>";
    }
}
