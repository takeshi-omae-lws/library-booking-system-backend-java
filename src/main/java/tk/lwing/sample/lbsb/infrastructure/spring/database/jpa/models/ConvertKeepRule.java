package tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models;

import tk.lwing.sample.lbsb.domain.entites.KeepRule;
import tk.lwing.sample.lbsb.domain.valueobjects.Amount;
import tk.lwing.sample.lbsb.domain.valueobjects.RuleID;
import tk.lwing.sample.lbsb.domain.valueobjects.StartAt;

import java.util.List;
import java.util.stream.Collectors;

public class ConvertKeepRule {

    public static KeepRule toDomain(KeepRulesTbl table) {
        return new KeepRule(new RuleID(table.getKeepRuleId()),
                new Amount(table.getMaxAmount()),
                new Amount(table.getMaxNewAmount()),
                new StartAt(table.getStartAt()));
    }

    public static List<KeepRule> toDomain(List<KeepRulesTbl> tableList) {
        return tableList.stream()
                .map(ConvertKeepRule::toDomain)
                .collect(Collectors.toList());
    }

    public static KeepRulesTbl toDb(KeepRule domain) {
        return KeepRulesTbl.builder()
                .keepRuleId(domain.getId().get())
                .build();
    }

    public static List<KeepRulesTbl> toDb(List<KeepRule> domainList) {
        return domainList.stream()
                .map(ConvertKeepRule::toDb)
                .collect(Collectors.toList());
    }
}
