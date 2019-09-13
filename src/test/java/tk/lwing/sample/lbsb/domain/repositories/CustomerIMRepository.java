package tk.lwing.sample.lbsb.domain.repositories;

import tk.lwing.sample.lbsb.domain.entites.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerIMRepository implements CustomerRepository {

    private final Map<String, Customer> customerMap = new HashMap<>();

    @Override
    public Customer save(Customer customer) {
        this.customerMap.put(customer.getId().get(), customer);
        return this.customerMap.get(customer.getId().get());
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(this.customerMap.values());
    }
}
