package com.epam.jwd.finaltask.dao;

import com.epam.jwd.finaltask.exception.DAOException;
import com.epam.jwd.finaltask.model.Booking;
import com.epam.jwd.finaltask.model.PaymentMethod;

import java.util.List;

public interface IBookingDao {
    List<Booking> getBookings() throws DAOException;

}

