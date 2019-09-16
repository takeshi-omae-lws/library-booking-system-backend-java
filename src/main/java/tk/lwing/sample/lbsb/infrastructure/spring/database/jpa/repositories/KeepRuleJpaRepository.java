package tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import tk.lwing.sample.lbsb.domain.entites.KeepRule;
import tk.lwing.sample.lbsb.domain.repositories.KeepRuleRepository;
import tk.lwing.sample.lbsb.domain.valueobjects.AsOf;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models.ConvertKeepRule;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class KeepRuleJpaRepository implements KeepRuleRepository {

    @NotNull
    private final KeepRulesTblRepository keepRulesTblRepository;

    @Override
    public KeepRule save(KeepRule keepRule) {
        return ConvertKeepRule.toDomain(
                this.keepRulesTblRepository.save(
                        ConvertKeepRule.toDb(keepRule)));
    }

    @Override
    public List<KeepRule> findAll() {
        return ConvertKeepRule.toDomain(
                this.keepRulesTblRepository.findAll());
    }

    @Override
    public KeepRule findLast() {
        return ConvertKeepRule.toDomain(
                this.keepRulesTblRepository
                        .findFirstByStartAtOrderByStartAtDesc(LocalDateTime.MAX));
    }

    @Override
    public KeepRule findByAsOf(AsOf asOf) {
        return ConvertKeepRule.toDomain(
                this.keepRulesTblRepository
                        .findFirstByStartAtOrderByStartAtDesc(asOf.get()));
    }
}
