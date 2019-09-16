package tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models.CustomersTbl;

public interface CustomersTblRepository
        extends JpaRepository<CustomersTbl, String> {
}
