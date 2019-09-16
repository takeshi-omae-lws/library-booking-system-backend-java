package tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models.KeepRulesTbl;

import java.time.LocalDateTime;

public interface KeepRulesTblRepository
        extends JpaRepository<KeepRulesTbl, String> {

    KeepRulesTbl findFirstByStartAtOrderByStartAtDesc(LocalDateTime dateTime);
}
