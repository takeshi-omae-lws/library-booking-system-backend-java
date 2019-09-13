package tk.lwing.sample.lbsb.domain.services;

import lombok.RequiredArgsConstructor;
import tk.lwing.sample.lbsb.domain.entites.Customer;
import tk.lwing.sample.lbsb.domain.repositories.CustomerRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequiredArgsConstructor
public class CustomerService {

    @NotNull
    private final CustomerRepository customerRepository;

    public Customer create(Customer customer) {
        return this.customerRepository.save(customer);
    }

    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

}
