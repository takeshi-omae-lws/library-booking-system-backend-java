package tk.lwing.sample.lbsb.domain.repositories;

import tk.lwing.sample.lbsb.domain.entites.BorrowRule;
import tk.lwing.sample.lbsb.domain.valueobjects.AsOf;

import java.util.List;

public interface BorrowRuleRepository {

    BorrowRule save(final BorrowRule borrowRule);

    List<BorrowRule> findAll();

    BorrowRule findLast();

    BorrowRule findByAsOf(AsOf asOf);
}
