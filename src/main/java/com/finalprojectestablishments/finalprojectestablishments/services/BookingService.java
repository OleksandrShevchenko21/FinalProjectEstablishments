package com.finalprojectestablishments.finalprojectestablishments.services;

import com.finalprojectestablishments.finalprojectestablishments.dao.BookingDao;
import com.finalprojectestablishments.finalprojectestablishments.dao.ReviewDao;
import com.finalprojectestablishments.finalprojectestablishments.dto.BookingDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.Booking;
import com.finalprojectestablishments.finalprojectestablishments.entity.Review;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {
    private BookingDao bookingDao;

    public List<Booking> findAll() {

        return bookingDao.findAll();
    }

    public Booking findById(int id) {
        return bookingDao.findById(id).get();
    }

    public void save(Booking booking) {

        bookingDao.save(booking);
    }

    public void update(int id, Booking booking) {
        bookingDao.save(booking);
    }

    public void delete(int id) {
        bookingDao.deleteById(id);
    }

}
