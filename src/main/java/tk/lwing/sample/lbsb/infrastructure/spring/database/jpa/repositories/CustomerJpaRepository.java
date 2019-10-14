package tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import tk.lwing.sample.lbsb.domain.entites.Customer;
import tk.lwing.sample.lbsb.domain.repositories.CustomerRepository;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.services.ConvertCustomer;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomerJpaRepository implements CustomerRepository {

    @NotNull
    private final CustomersTblRepository customersTblRepository;

    @Override
    public Customer save(Customer customer) {
        return ConvertCustomer.toDomain(
                this.customersTblRepository.save(ConvertCustomer.toDb(customer)));
    }

    @Override
    public List<Customer> findAll() {
        return ConvertCustomer.toDomain(this.customersTblRepository.findAll());
    }
}
