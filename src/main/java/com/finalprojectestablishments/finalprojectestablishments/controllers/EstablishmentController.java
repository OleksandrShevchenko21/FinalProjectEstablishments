package com.finalprojectestablishments.finalprojectestablishments.controllers;

import com.finalprojectestablishments.finalprojectestablishments.dao.EstablishmentDao;
import com.finalprojectestablishments.finalprojectestablishments.dto.EstablishmentDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.Establishment;
import com.finalprojectestablishments.finalprojectestablishments.services.EstablishmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/establishments")
@AllArgsConstructor
public class EstablishmentController {
    private EstablishmentDao establishmentDao;
    private EstablishmentService establishmentService;

    @GetMapping("")
    public ResponseEntity<List<Establishment>> getAllEstablishments() {
        List<Establishment> all = establishmentDao.findAll();
        return new ResponseEntity<>(all, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Establishment> getOneEstablishment(@PathVariable int id) {
        Establishment establishment = establishmentDao.findById(id).get();
        return new ResponseEntity<>(establishment, HttpStatusCode.valueOf(200));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveEstablishment(@RequestBody Establishment establishment) {
        establishmentService.save(establishment);
    }

    @PatchMapping("/{id}")
    public void updateEstablishment(@PathVariable int id, @RequestBody EstablishmentDto establishmentDto) {
        Establishment establishment = establishmentDao.findById(id).get();
        establishment.setName(establishmentDto.getEstablishmentName());
        establishmentDao.save(establishment);
    }

    @DeleteMapping("/{id}")
    public void deleteEstablishment(@PathVariable int id) {
        establishmentDao.deleteById(id);
    }

}
