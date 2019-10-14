package tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.services;

import tk.lwing.sample.lbsb.domain.entites.Customer;
import tk.lwing.sample.lbsb.domain.valueobjects.CustomerID;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models.CustomersTbl;

import java.util.List;
import java.util.stream.Collectors;

public class ConvertCustomer {

    public static Customer toDomain(CustomersTbl table) {
        return new Customer(new CustomerID(table.getCustomerId()));
    }

    public static List<Customer> toDomain(List<CustomersTbl> tableList) {
        return tableList.stream()
                .map(ConvertCustomer::toDomain)
                .collect(Collectors.toList());
    }

    public static CustomersTbl toDb(Customer domain) {
        return CustomersTbl.builder()
                .customerId(domain.getId().get())
                .build();
    }

    public static List<CustomersTbl> toDb(List<Customer> domainList) {
        return domainList.stream()
                .map(ConvertCustomer::toDb)
                .collect(Collectors.toList());
    }
}
