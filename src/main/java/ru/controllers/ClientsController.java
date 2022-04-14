package ru.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClientsController {

    @RequestMapping("/clients")
    public String getClients() {
        return "clients";
    }

    @RequestMapping("/add-clients")
    public String addClients() {
        return "add-clients";
    }
}
