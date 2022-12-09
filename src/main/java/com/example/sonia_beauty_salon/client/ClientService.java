package com.example.sonia_beauty_salon.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    public void deleteClientById(Long clientId){
        boolean exists = clientRepository.existsById(clientId);
        if(!exists){
            throw new IllegalStateException("Cliente nao existe");
        }
        clientRepository.deleteById(clientId);
    }
    @Transactional
    public void updateClient(Long clientId, Client client){
        Client clientById = clientRepository.findById(clientId).orElseThrow(() -> new IllegalStateException("nao temos esse cliente"));
        List<Client> clients = clientRepository.findClientQuery();
        boolean isBookingEmpty = true;
        if(client.getNameOfClient() != null && client.getNameOfClient().length() > 0){
            clientById.setNameOfClient(client.getNameOfClient());
        }
        if(client.getNameOfAttendant() != null && client.getNameOfAttendant().length() > 0){
            clientById.setNameOfAttendant(client.getNameOfAttendant());
        }
        if(client.getService() != null && client.getService().length() > 0){
            clientById.setService(client.getService());
        }
        if(client.getPrice() != null){
            clientById.setPrice(client.getPrice());
        }
        if(client.getInitialOfBooking() != null || client.getFinishOfBooking() != null){
            System.out.println("VERIFICADNO NULLO");
            if(client.getInitialOfBooking() == null){
                client.setInitialOfBooking(clientById.getInitialOfBooking());
            }
            if(client.getFinishOfBooking() == null){
                client.setFinishOfBooking(clientById.getFinishOfBooking());
            }
            if(client.getFinishOfBooking().isAfter(client.getInitialOfBooking())){
                System.out.println("ENTREI NA FUNÇÃO DO LOOP");
                if(clients.size() > 0){

                    for(int index =0; index < clients.size(); index++){
                        Client newClient =  clients.get(index);


                        if(client.getInitialOfBooking().isAfter(newClient.getFinishOfBooking())
                                || client.getFinishOfBooking().isEqual(clientById.getFinishOfBooking())
                                || client.getInitialOfBooking().isEqual(clientById.getInitialOfBooking())
                                || client.getInitialOfBooking().isEqual(newClient.getFinishOfBooking())
                                || client.getFinishOfBooking().isBefore(newClient.getInitialOfBooking())
                                ||  client.getFinishOfBooking().isEqual(newClient.getInitialOfBooking())
                        ){
                            isBookingEmpty = true;
                        } else {
                            isBookingEmpty = false;
                            break;
                        }
                    }
                }

            } else {
                throw new IllegalStateException("Horário adicionado de forma incorreta");
            }

            if(isBookingEmpty == true){
                System.out.println("PUT COM SUCESSO");
                clientById.setInitialOfBooking(client.getInitialOfBooking());
                clientById.setFinishOfBooking(client.getFinishOfBooking());
            } else{
                System.out.println("JA TEM ESSE HORARIO");
                throw new IllegalStateException("esse horário ja esta marcado");
            }

        }



    }
    public void addNewClient(Client client) {

        List<Client> clients = clientRepository.findClientQuery();
        boolean isBookingEmpty = true;
        if(client.getFinishOfBooking().isAfter(client.getInitialOfBooking())){
            if(clients.size() > 0){

                for(int index =0; index < clients.size(); index++){
                    Client newClient =  clients.get(index);


                    if(client.getInitialOfBooking().isAfter(newClient.getFinishOfBooking())
                            || client.getInitialOfBooking().isEqual(newClient.getFinishOfBooking())
                            || client.getFinishOfBooking().isBefore(newClient.getInitialOfBooking())
                            || client.getFinishOfBooking().isEqual(newClient.getInitialOfBooking())
                    ){
                        isBookingEmpty = true;
                    } else {
                        isBookingEmpty = false;
                        break;
                    }
                }
            }

        } else {
            throw new IllegalStateException("Horário adicionado de forma incorreta");
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
