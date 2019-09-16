package tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models;

import tk.lwing.sample.lbsb.domain.entites.ReturnRule;
import tk.lwing.sample.lbsb.domain.valueobjects.Period;
import tk.lwing.sample.lbsb.domain.valueobjects.RuleID;
import tk.lwing.sample.lbsb.domain.valueobjects.StartAt;

import java.util.List;
import java.util.stream.Collectors;

public class ConvertReturnRule {

    public static ReturnRule toDomain(ReturnRulesTbl table) {
        return new ReturnRule(new RuleID(table.getReturnRuleId()),
                new Period(table.getPeriod()),
                new StartAt(table.getStartAt()));
    }

    public static List<ReturnRule> toDomain(List<ReturnRulesTbl> tableList) {
        return tableList.stream()
                .map(ConvertReturnRule::toDomain)
                .collect(Collectors.toList());
    }

    public static ReturnRulesTbl toDb(ReturnRule domain) {
        return ReturnRulesTbl.builder()
                .returnRuleId(domain.getId().get())
                .build();
    }

    public static List<ReturnRulesTbl> toDb(List<ReturnRule> domainList) {
        return domainList.stream()
                .map(ConvertReturnRule::toDb)
                .collect(Collectors.toList());
    }
}
