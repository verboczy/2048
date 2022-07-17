package hu.verboczy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @GetMapping("/asd")
    public String asd() {
        return "asd";
    }
}
