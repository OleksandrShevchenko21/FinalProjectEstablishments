package com.finalprojectestablishments.finalprojectestablishments.controllers;

import com.finalprojectestablishments.finalprojectestablishments.dao.ClientDao;
import com.finalprojectestablishments.finalprojectestablishments.dto.ClientDto;
import com.finalprojectestablishments.finalprojectestablishments.dto.EstablishmentDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.Client;
import com.finalprojectestablishments.finalprojectestablishments.entity.Establishment;
import com.finalprojectestablishments.finalprojectestablishments.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@AllArgsConstructor
public class ClientController {


    private ClientDao clientDao;
    private ClientService clientService;

    @GetMapping("")
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> all = clientDao.findAll();
        return new ResponseEntity<>(all, HttpStatusCode.valueOf(200));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable int id) {
        Client client = clientDao.findById(id).get();
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveClient(@RequestBody Client client) {
        clientDao.save(client);
    }
    @PatchMapping("/{id}")
    public void updateClient(@PathVariable int id, @RequestBody ClientDto clientDto) {
        Client client = clientDao.findById(id).get();
        client.setClientName(clientDto.getClientName());
        clientDao.save(client);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable int id) {
        clientDao.deleteById(id);

    }

//    @GetMapping("/{clientId}/bookings")
//    public ResponseEntity<List<Booking>> getClientBookings(@PathVariable Long clientId) {
//        List<Booking> bookings = clientService.getClientBookings(clientId);
//        return new ResponseEntity<>(bookings, HttpStatus.OK);
//    }

}