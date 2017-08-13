package kr.pe.ned.resourceserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class RootController {

    @GetMapping("/")
    public Map<String, Object> index() {

        Map<String, Object> map = new HashMap<>();
        map.put("id", UUID.randomUUID());
        map.put("content", "Hello World");
        return map;
    }

}
