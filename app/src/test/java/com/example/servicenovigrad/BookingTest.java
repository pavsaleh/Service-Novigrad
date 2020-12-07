package com.example.servicenovigrad;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookingTest {
    @Test
    public void customerNameTest(){
        Booking appointment = new Booking("Customer", "Employee", "2020 Dec 06 13:45:00","2020 Dec 06 14:45:00", "Driver's licence", "Customer", "Customer", "1980 Dec 06", "75 Laurier Avenue");
        String expected = "Customer";
        assertEquals("Valid Customer", expected, appointment.customerName);
    }

    @Test
    public void providerNameTest(){
        Booking appointment = new Booking("Customer", "Employee", "2020 Dec 06 13:45:00","2020 Dec 06 14:45:00", "Driver's licence", "Customer", "Customer", "1980 Dec 06", "75 Laurier Avenue");
        String expected = "Employee";
        assertEquals("Valid Service Provider", expected, appointment.providerName);
    }

    @Test
    public void startTimeTest(){
        Booking appointment = new Booking("Customer", "Employee", "2020 Dec 06 13:45:00","2020 Dec 06 14:45:00", "Driver's licence", "Customer", "Customer", "1980 Dec 06", "75 Laurier Avenue");
        String expected = "2020 Dec 06 13:45:00";
        assertEquals("Valid Start Time", expected, appointment.startTime);
    }

    @Test
    public void endTimeTest(){
        Booking appointment = new Booking("Customer", "Employee", "2020 Dec 06 13:45:00","2020 Dec 06 14:45:00", "Driver's licence", "Customer", "Customer", "1980 Dec 06", "75 Laurier Avenue");
        String expected = "2020 Dec 06 14:45:00";
        assertEquals("Valid End Time", expected, appointment.endTime);
    }

    @Test
    public void serviceNameTest(){
        Booking appointment = new Booking("Customer", "Employee", "2020 Dec 06 13:45:00","2020 Dec 06 14:45:00", "Driver's licence", "Customer", "Customer", "1980 Dec 06", "75 Laurier Avenue");
        String expected = "Driver's licence";
        assertEquals("Valid Service Type", expected, appointment.serviceName);
    }
    @Test
    public void bookingNotNull(){
        Booking appointment = new Booking("Customer", "Employee", "2020 Dec 06 13:45:00","2020 Dec 06 14:45:00", "Driver's licence", "Customer", "Customer", "1980 Dec 06", "75 Laurier Avenue");
        Assert.assertNotNull(appointment);
    }
}