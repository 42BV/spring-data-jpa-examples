package org.springframework.data.jpa.example.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.data.jpa.example.domain.Address;
import org.springframework.data.jpa.example.domain.Customer;
import org.springframework.data.jpa.example.domain.QCustomer;

import com.mysema.query.types.Path;

public class EvaluatingVisitorTest {

    @Test
    public void testPathEvaluate() {
        Address address = new Address();
        address.setCity("bla");
        address.setStreet("blaway 142");
        Customer customer = new Customer();
        customer.setAddress(address);

        Path<String> streetPath = QCustomer.customer.address.street;
        
        assertEquals("blaway 142", streetPath.accept(new EvaluatingVisitor(), customer));
        assertThat(customer, new PropertyMatcher<String>(streetPath, Matchers.equalTo("blaway 142")));
    }
    
}
