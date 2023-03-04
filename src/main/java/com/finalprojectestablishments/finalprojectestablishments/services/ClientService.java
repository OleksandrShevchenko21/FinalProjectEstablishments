package com.finalprojectestablishments.finalprojectestablishments.services;

import com.finalprojectestablishments.finalprojectestablishments.dao.ClientDao;
import com.finalprojectestablishments.finalprojectestablishments.dto.ClientDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.Client;
import com.finalprojectestablishments.finalprojectestablishments.entity.Establishment;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ClientService {


    private ClientDao clientDao;
    public void save (Client client){
        clientDao.save(client);
    }

//    public Client getClientById(Long id) {
//        Optional<Client> clientOptional = clientDao.findById(id);
//        if (clientOptional.isPresent()) {
//            return clientOptional.get();
//        } else {
//            throw new EntityNotFoundException("Client with ID " + id + " not found");
//        }
//    }
//
//    public Client addClient(ClientDto clientDto) {
//        Client client = new Client();
//        client.setClientName(clientDto.getClientName());
//
//        return clientDao.save(client);
//    }
//
//    public Client updateClient(Long id, ClientDto clientDto) {
//        Optional<Client> clientOptional = clientDao.findById(id);
//        if (clientOptional.isPresent()) {
//            Client client = clientOptional.get();
//            client.setClientName(clientDto.getClientName());
//            return clientDao.save(client);
//        } else {
//            throw new EntityNotFoundException("Client with ID " + id + " not found");
//        }
//    }
//
//    public void deleteClient(Long id) {
//        Optional<Client> clientOptional = clientDao.findById(id);
//        if (clientOptional.isPresent()) {
//            clientDao.delete(clientOptional.get());
//        } else {
//            throw new EntityNotFoundException("Client with ID " + id + " not found");
//        }
//    }

//    public List<Booking> getClientBookings(Long clientId) {
//        Optional<Client> clientOptional = clientDao.findById(clientId);
//        if (clientOptional.isPresent()) {
//            return clientOptional.get().getBookings();
//        } else {
//            throw new EntityNotFoundException("Client with ID " + clientId + " not found");
//        }
//    }

}
