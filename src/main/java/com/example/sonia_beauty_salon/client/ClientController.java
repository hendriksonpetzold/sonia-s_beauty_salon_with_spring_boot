package com.example.sonia_beauty_salon.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
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

    @DeleteMapping(path = "{clientId}")
    public void deleteClientById(@PathVariable("clientId") Long clientId){
        clientService.deleteClientById(clientId);
    }

    @PutMapping(path = "{studentId}")
    public void updateClient(@PathVariable("studentId") Long studentId, @RequestBody Client client){
        System.out.println("FUNÇÃO PUT");
        clientService.updateClient(studentId, client);
    }

}
