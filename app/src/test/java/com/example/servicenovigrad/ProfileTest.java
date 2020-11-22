package com.example.servicenovigrad;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ProfileTest {
    @Test
    public void phoneNumberTest() {
        userProfile p = new userProfile("93 Stewart St.", "0123456789", "Test", "Test");
        String expected = "6666666666";
        assertEquals("Valid Phone Number", expected.length(), p.Phonenumber.length());
    }

    @Test
    public void companyTest() {
        userProfile p = new userProfile(null, "0123456789", "", "Test");
        Assert.assertTrue("Invalid company name", "".equals(p.CompanyName));
    }

    @Test
    public void addressTest() {
        userProfile p = new userProfile(null, "0123456789", "", "Test");
        Assert.assertFalse("Invalid address", "".equals(p.Address));
    }
    @Test
    public void profileNullTest() {
        userProfile p = new userProfile(null, "0123456789", "", "Test");
        Assert.assertFalse("Required entry is incomplete", "".equals(p.Address) && "".equals(p.CompanyName));
    }
}