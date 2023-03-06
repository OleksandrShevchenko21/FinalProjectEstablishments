package com.finalprojectestablishments.finalprojectestablishments.utils;

import com.finalprojectestablishments.finalprojectestablishments.dto.BookingDto;
import com.finalprojectestablishments.finalprojectestablishments.dto.ReviewDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.Booking;
import com.finalprojectestablishments.finalprojectestablishments.entity.Review;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class BookingConverter {

    public BookingDto bookingToBookingDto(Booking booking) {
        BookingDto dto = new BookingDto();
        dto.setId(booking.getId());
        dto.setReservationDateTime(booking.getReservationDateTime());
        dto.setPurpose(booking.getPurpose());
        dto.setGender(booking.getGender());
        dto.setNumPeople(booking.getNumPeople());
        dto.setWhoPays(booking.getWhoPays());
        dto.setDesiredExpenses(booking.getDesiredExpenses());
        dto.setUserId(booking.getUser().getId());
        dto.setRestaurantId(booking.getRestaurant().getId());

        return dto;
    }

    public List<BookingDto> bookingListToBookingDtoList(List<Booking> bookingList) {
        return bookingList.stream().map(this::bookingToBookingDto).collect(Collectors.toList());
    }
}
