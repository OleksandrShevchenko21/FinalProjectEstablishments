package com.finalprojectestablishments.finalprojectestablishments.dao;

import com.finalprojectestablishments.finalprojectestablishments.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingDao extends JpaRepository<Booking,Integer> {
//    Optional<Booking> findById(int id);
//
//    void save(Booking booking);
//
//    void deleteById(int id);
}

