package com.finalprojectestablishments.finalprojectestablishments.controllers;

import com.finalprojectestablishments.finalprojectestablishments.dto.BookingDto;
import com.finalprojectestablishments.finalprojectestablishments.dto.ReviewDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.Booking;
import com.finalprojectestablishments.finalprojectestablishments.entity.Restaurant;
import com.finalprojectestablishments.finalprojectestablishments.entity.Review;
import com.finalprojectestablishments.finalprojectestablishments.entity.User;
import com.finalprojectestablishments.finalprojectestablishments.services.BookingService;
import com.finalprojectestablishments.finalprojectestablishments.services.RestaurantService;
import com.finalprojectestablishments.finalprojectestablishments.services.UserService;
import com.finalprojectestablishments.finalprojectestablishments.utils.converter.BookingConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/booking")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class BookingController {
    private BookingService bookingService;
    private UserService userService;
    private RestaurantService restaurantService;
    private BookingConverter bookingConverter;

    @GetMapping("")
    public ResponseEntity<List<BookingDto>> getAllBooking() {
        List<Booking> reviews = bookingService.findAll();
        List<BookingDto> bookingDtoList = bookingConverter.bookingListToBookingDtoList(reviews);
        return new ResponseEntity<>(bookingDtoList, HttpStatus.valueOf(200));
    }
    @GetMapping("/userName/{userName}")
    public ResponseEntity<List<BookingDto>> getAllBookingsByUserName(@PathVariable String userName) {
        List<Booking> bookings = bookingService.findByUserName(userName);
        List<BookingDto> bookingDto = bookingConverter.bookingListToBookingDtoList(bookings);
        return new ResponseEntity<>(bookingDto, HttpStatus.valueOf(200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> getOneBooking(@PathVariable int id) {
        Booking booking = bookingService.findById(id);
        BookingDto bookingDto = bookingConverter.bookingToBookingDto(booking);
        return new ResponseEntity<>(bookingDto, HttpStatus.valueOf(200));
    }

    //    @PostMapping("/{userId}/{restaurantId}")
    @PostMapping("/restaurant/{restaurantId}/{userName}")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveReview(
            @PathVariable int restaurantId,
            @PathVariable String userName,
            @RequestBody BookingDto bookingDto) {


        Booking booking = new Booking();
        User user = userService.findByUserName(userName);
        Restaurant restaurant = restaurantService.findById(restaurantId);
        booking.setUser(user);
        booking.setRestaurant(restaurant);
        booking.setReservationDateTime(bookingDto.getReservationDateTime());
        booking.setPurpose(bookingDto.getPurpose());
        booking.setGender(bookingDto.getGender());
        booking.setNumPeople(bookingDto.getNumPeople());
        booking.setWhoPays(bookingDto.getWhoPays());
        booking.setDesiredExpenses(bookingDto.getDesiredExpenses());
        bookingService.save(booking);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveBooking(@PathVariable int id,
                            @RequestBody BookingDto bookingDto) {

        Booking booking = bookingService.findById(id);

        booking.setReservationDateTime(bookingDto.getReservationDateTime());
        booking.setPurpose(bookingDto.getPurpose());
        booking.setGender(bookingDto.getGender());
        booking.setNumPeople(bookingDto.getNumPeople());
        booking.setWhoPays(bookingDto.getWhoPays());
        booking.setDesiredExpenses(bookingDto.getDesiredExpenses());
        bookingService.update(id, booking);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable int id) {

        bookingService.delete(id);
    }
}
