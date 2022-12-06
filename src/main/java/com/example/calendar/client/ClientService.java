package com.example.calendar.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    ClientRepository clientRepository;
    public List<Client> getClient(){
       return clientRepository.findAll();
    }

    public void addNewClient(Client client) {

        List<Client> clients = clientRepository.findClientQuery();
        boolean isBookingEmpty = true;
        if(clients.size() > 0){
            for(int index =0; index < clients.size(); index++){
                Client newClient =  clients.get(index);


                if(client.getInitialOfBooking().isAfter(newClient.getFinishOfBooking()) || client.getFinishOfBooking().isBefore(newClient.getInitialOfBooking()) ){
                    isBookingEmpty = true;
                } else {
                    isBookingEmpty = false;
                    break;
                }
        }
        }

        if(isBookingEmpty == true){

            System.out.println("ADD COM SUCESSO");
            clientRepository.save(client);
        } else{
            System.out.println("JA TEM ESSE HORARIO");
            throw new IllegalStateException("esse horário ja esta marcado");
        }





    }

}
