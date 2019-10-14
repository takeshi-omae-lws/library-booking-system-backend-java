package tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import tk.lwing.sample.lbsb.domain.entites.BorrowRule;
import tk.lwing.sample.lbsb.domain.repositories.BorrowRuleRepository;
import tk.lwing.sample.lbsb.domain.valueobjects.AsOf;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.services.ConvertBorrowRule;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BorrowRuleJpaRepository implements BorrowRuleRepository {

    @NotNull
    private final BorrowRulesTblRepository borrowRulesTblRepository;

    @Override
    public BorrowRule save(BorrowRule borrowRule) {
        return ConvertBorrowRule.toDomain(
                this.borrowRulesTblRepository.save(
                        ConvertBorrowRule.toDb(borrowRule)));
    }

    @Override
    public List<BorrowRule> findAll() {
        return ConvertBorrowRule.toDomain(
                this.borrowRulesTblRepository.findAll());
    }

    @Override
    public BorrowRule findLast() {
        return ConvertBorrowRule.toDomain(
                this.borrowRulesTblRepository
                        .findFirstByStartAtBeforeOrderByStartAtDesc(LocalDateTime.MAX));
    }

    @Override
    public BorrowRule findByAsOf(AsOf asOf) {
        return ConvertBorrowRule.toDomain(
                this.borrowRulesTblRepository
                        .findFirstByStartAtBeforeOrderByStartAtDesc(asOf.get()));

    }

}
