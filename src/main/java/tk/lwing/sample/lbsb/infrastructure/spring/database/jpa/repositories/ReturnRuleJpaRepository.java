package tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import tk.lwing.sample.lbsb.domain.entites.ReturnRule;
import tk.lwing.sample.lbsb.domain.repositories.ReturnRuleRepository;
import tk.lwing.sample.lbsb.domain.valueobjects.AsOf;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models.ConvertReturnRule;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReturnRuleJpaRepository implements ReturnRuleRepository {

    @NotNull
    private final ReturnRulesTblRepository returnRulesTblRepository;

    @Override
    public ReturnRule save(ReturnRule returnRule) {
        return ConvertReturnRule.toDomain(
                this.returnRulesTblRepository.save(
                        ConvertReturnRule.toDb(returnRule)));
    }

    @Override
    public List<ReturnRule> findAll() {
        return ConvertReturnRule.toDomain(
                this.returnRulesTblRepository.findAll());
    }

    @Override
    public ReturnRule findLast() {
        return ConvertReturnRule.toDomain(
                this.returnRulesTblRepository
                        .findFirstByStartAtOrderByStartAtDesc(LocalDateTime.MAX));
    }

    @Override
    public ReturnRule findByAsOf(AsOf asOf) {
        return ConvertReturnRule.toDomain(
                this.returnRulesTblRepository
                        .findFirstByStartAtOrderByStartAtDesc(asOf.get()));
    }

}
