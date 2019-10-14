package tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models.ReturnRulesTbl;

import java.time.LocalDateTime;

public interface ReturnRulesTblRepository
        extends JpaRepository<ReturnRulesTbl, String> {

    ReturnRulesTbl findFirstByStartAtBeforeOrderByStartAtDesc(LocalDateTime dateTime);
}
