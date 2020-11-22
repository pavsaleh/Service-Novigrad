package com.example.servicenovigrad;
import com.google.common.collect.Lists;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ServiceTest {
    @Test
    public void serviceTypeTest() {
//        final User actual = new User("employee1", "employee1@gmail.com", "password", "John", "Smith", "Employee");
        int serviceHash = Math.abs(("employee1" + "Photo ID" + "34.99").hashCode());
        List<String> informationList = Lists.newArrayList("s1", "s2", "s3");
        List<String> documentList = Lists.newArrayList("s1", "s2", "s3");
        final Service service = new Service("employee1", "Photo ID", 49.99, serviceHash, informationList, documentList);
        assertEquals(service.serviceType, "Photo ID");
    }

    @Test
    public void serviceHashTest() {
        int serviceHash = Math.abs(("employee1" + "Photo ID" + "34.99").hashCode());
        List<String> informationList = Lists.newArrayList("s1", "s2", "s3");
        List<String> documentList = Lists.newArrayList("s1", "s2", "s3");
        final Service service = new Service("employee1", "Photo ID", 49.99, serviceHash, informationList, documentList);
        assertEquals(Math.abs(("employee1" + "Photo ID" + "34.99").hashCode()), serviceHash);
    }

    @Test
    public void serviceNullTest() {
        int serviceHash = Math.abs(("employee1" + "Photo ID" + "34.99").hashCode());
        List<String> informationList = Lists.newArrayList("s1", "s2", "s3");
        List<String> documentList = Lists.newArrayList("s1", "s2", "s3");
        final Service service = new Service("employee1", null, 49.99, serviceHash, informationList, documentList);
        Assert.assertFalse("Invalid or Blank Information", "".equals(service.serviceType));
    }

    @Test
    public void serviceNotNullTest() {
        int serviceHash = Math.abs(("employee1" + "Photo ID" + "34.99").hashCode());
        List<String> informationList = Lists.newArrayList("s1", "s2", "s3");
        List<String> documentList = Lists.newArrayList("s1", "s2", "s3");
        final Service service = new Service("employee1", "Photo ID", 49.99, serviceHash, informationList, documentList);
        Assert.assertNotNull(service);
    }
}
