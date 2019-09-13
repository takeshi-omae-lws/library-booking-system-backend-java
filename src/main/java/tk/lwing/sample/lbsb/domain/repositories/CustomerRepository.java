package tk.lwing.sample.lbsb.domain.repositories;

import tk.lwing.sample.lbsb.domain.entites.Customer;

import java.util.List;

public interface CustomerRepository {

    Customer save(final Customer customer);

    List<Customer> findAll();
}
