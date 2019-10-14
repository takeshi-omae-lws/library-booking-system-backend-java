package tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models.BorrowRulesTbl;

import java.time.LocalDateTime;

public interface BorrowRulesTblRepository
        extends JpaRepository<BorrowRulesTbl, String> {

    BorrowRulesTbl findFirstByStartAtBeforeOrderByStartAtDesc(LocalDateTime dateTime);

}
