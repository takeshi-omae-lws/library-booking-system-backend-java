package tk.lwing.sample.lbsb.domain.repositories;

import tk.lwing.sample.lbsb.domain.entites.KeepRule;
import tk.lwing.sample.lbsb.domain.valueobjects.AsOf;

import java.util.List;

public interface KeepRuleRepository {

    KeepRule save(final KeepRule keepRule);

    List<KeepRule> findAll();

    KeepRule findLast();

    KeepRule findByAsOf(AsOf asOf);
}
