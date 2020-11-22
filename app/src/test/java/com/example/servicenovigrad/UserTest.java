package com.example.servicenovigrad;
import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void customerTest() {
        final User actual = new User("customer1", "customer1@gmail.com", "password", "John", "Smith", "Customer");
        final String expected = "Customer";
        assertEquals("Good Login credentials", expected, actual.AccountType);
    }

    @Test
    public void userNotNull(){
        final User actual = new User("employee1", "employee1@gmail.com", "password", "John", "Smith", "Customer");
        Assert.assertNotNull(actual);
    }

    @Test
    public void employeeTest(){
        final User actual = new User("employee1", "employee1@gmail.com", "password", "John", "Smith", "Employee");
        final String expected = "Employee";
        assertEquals("Good Login credentials", expected, actual.AccountType);
    }
}