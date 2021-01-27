package com.epam.jwd.finaltask.service;

import com.epam.jwd.finaltask.model.Booking;
import com.epam.jwd.finaltask.model.PaymentInfo;
import com.epam.jwd.finaltask.model.PaymentMethod;

import java.util.List;

public interface IBookingService {
    List<Booking> getBookings();
    Booking getBookingById(int bookingId);
    Booking getEmptyBooking();
    void create(String checkInDate, String checkOutDate, int adultsCount, int childrenCount, String comment, int roomTypeId, int guestId);

    void update(int bookingid, String checkInDate, String checkOutDate, int adultsCount, String guestName, String guestEmail,
                String guestMobile, String guestAddress, int newStatus);

    void delete(int bookingid);
}
