package com.finalprojectestablishments.finalprojectestablishments.dao;

import com.finalprojectestablishments.finalprojectestablishments.entity.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstablishmentDao extends JpaRepository<Establishment, Integer> {
}
