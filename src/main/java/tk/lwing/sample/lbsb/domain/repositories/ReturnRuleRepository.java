package tk.lwing.sample.lbsb.domain.repositories;

import tk.lwing.sample.lbsb.domain.entites.ReturnRule;
import tk.lwing.sample.lbsb.domain.valueobjects.AsOf;

import java.util.List;

public interface ReturnRuleRepository {

    ReturnRule save(final ReturnRule returnRule);

    List<ReturnRule> findAll();

    ReturnRule findLast();

    ReturnRule findByAsOf(AsOf asOf);
}
