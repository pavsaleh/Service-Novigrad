package com.example.servicenovigrad;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RatingTest {
    @Test
    public void usernameofCustomerTest(){
        Rating rate = new Rating ("Customer", "Employee", 3, "Meh");
        String expected = "Customer";
        assertEquals("Valid Customer", expected, rate.UsernameofCustomer);
    }

    @Test
    public void usernameofProviderTest(){
        Rating rate = new Rating ("Customer", "Employee", 3, "Meh");
        String expected = "Employee";
        assertEquals("Valid Employee", expected, rate.UserNameofEmployee);
    }

    @Test
    public void scoreTest(){
        Rating rate = new Rating ("Customer", "Employee", 3, "Meh");
        int expected = 3;
        assertEquals("Valid Rating", expected, rate.Score);
    }

    @Test
    public void commentTest(){
        Rating rate = new Rating ("Customer", "Employee", 3, "Meh");
        String expected = "Meh";
        assertEquals("Valid Comment", expected, rate.Comment);
    }

    @Test
    public void ratingNotNull(){
        Rating rate = new Rating ("Customer", "Employee", 3, "Meh");
        Assert.assertNotNull(rate);
    }
}