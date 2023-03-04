package com.finalprojectestablishments.finalprojectestablishments.services;

import com.finalprojectestablishments.finalprojectestablishments.dao.EstablishmentDao;
import com.finalprojectestablishments.finalprojectestablishments.entity.Establishment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EstablishmentService {
    private EstablishmentDao establishmentDao;

    public void save (Establishment establishment){
        establishmentDao.save(establishment);
    }
}
