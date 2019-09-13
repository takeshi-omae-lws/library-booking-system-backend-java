package tk.lwing.sample.lbsb.domain.services;

import lombok.RequiredArgsConstructor;
import tk.lwing.sample.lbsb.domain.entites.BorrowRule;
import tk.lwing.sample.lbsb.domain.entites.KeepRule;
import tk.lwing.sample.lbsb.domain.entites.ReturnRule;
import tk.lwing.sample.lbsb.domain.entites.Rule;
import tk.lwing.sample.lbsb.domain.repositories.BorrowRuleRepository;
import tk.lwing.sample.lbsb.domain.repositories.KeepRuleRepository;
import tk.lwing.sample.lbsb.domain.repositories.ReturnRuleRepository;
import tk.lwing.sample.lbsb.domain.valueobjects.AsOf;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class RuleService {

    private final BorrowRuleRepository borrowRuleRepository;
    private final ReturnRuleRepository returnRuleRepository;
    private final KeepRuleRepository keepRuleRepository;

    public List<Rule> saveRules(List<Rule> rules) {
        List<Rule> results = new ArrayList<>();
        for (Rule rule : rules) {
            if (rule instanceof BorrowRule) {
                results.add(this.borrowRuleRepository.save((BorrowRule) rule));
            }
            if (rule instanceof ReturnRule) {
                results.add(this.returnRuleRepository.save((ReturnRule) rule));
            }
            if (rule instanceof KeepRule) {
                results.add(this.keepRuleRepository.save((KeepRule) rule));
            }
        }
        return results;
    }

    // get rule
    public List<Rule> findAllRulesByAsOf(AsOf asOf) {
        List<Rule> results = new ArrayList<>();
        results.add(this.borrowRuleRepository.findByAsOf(asOf));
        results.add(this.returnRuleRepository.findByAsOf(asOf));
        results.add(this.keepRuleRepository.findByAsOf(asOf));
        return results;
    }

    public List<Rule> findAllLatestRules() {
        List<Rule> results = new ArrayList<>();
        AsOf asOf = new AsOf(LocalDateTime.now());
        results.add(this.borrowRuleRepository.findByAsOf(asOf));
        results.add(this.returnRuleRepository.findByAsOf(asOf));
        results.add(this.keepRuleRepository.findByAsOf(asOf));
        return results;
    }


}
