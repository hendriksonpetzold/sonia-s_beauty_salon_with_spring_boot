package com.example.calendar.client;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/clients")

public class ClientController {
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    ClientService clientService;
    @GetMapping
    public List<Client> getClient(){

        return clientService.getClient();
    }

    @PostMapping
    public void addNewClient(@RequestBody Client client){
        clientService.addNewClient(client);
    }
}
