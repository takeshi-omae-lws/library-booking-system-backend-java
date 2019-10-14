package tk.lwing.sample.lbsb.infrastructure.spring.rest.services;

import tk.lwing.sample.lbsb.domain.entites.Customer;
import tk.lwing.sample.lbsb.domain.valueobjects.CustomerID;
import tk.lwing.sample.lbsb.infrastructure.spring.rest.resources.CustomerBody;

public class ConvertCustomer {

    public static Customer toDomain(CustomerBody customerBody) {
        return new Customer(new CustomerID(customerBody.getId()));
    }

    public static CustomerBody toBody(Customer customer) {
        return CustomerBody.builder().id(customer.getId().get()).build();
    }
}
