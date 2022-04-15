package com.khakimova.ipblocker.controller;

import com.khakimova.ipblocker.aop.IpBlocker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MainController {

    @GetMapping("/")
    @IpBlocker
    public void main() {
    }
}
