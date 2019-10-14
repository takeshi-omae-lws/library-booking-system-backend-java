package tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.services;

import tk.lwing.sample.lbsb.domain.entites.BorrowRule;
import tk.lwing.sample.lbsb.domain.valueobjects.Amount;
import tk.lwing.sample.lbsb.domain.valueobjects.RuleID;
import tk.lwing.sample.lbsb.domain.valueobjects.StartAt;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models.BorrowRulesTbl;

import java.util.List;
import java.util.stream.Collectors;

public class ConvertBorrowRule {

    public static BorrowRule toDomain(BorrowRulesTbl table) {
        return new BorrowRule(new RuleID(table.getBorrowRuleId()),
                new Amount(table.getMaxAmount()),
                new StartAt(table.getStartAt()));
    }

    public static List<BorrowRule> toDomain(List<BorrowRulesTbl> tableList) {
        return tableList.stream()
                .map(ConvertBorrowRule::toDomain)
                .collect(Collectors.toList());
    }

    public static BorrowRulesTbl toDb(BorrowRule domain) {
        return BorrowRulesTbl.builder()
                .borrowRuleId(domain.getId().get())
                .build();
    }

    public static List<BorrowRulesTbl> toDb(List<BorrowRule> domainList) {
        return domainList.stream()
                .map(ConvertBorrowRule::toDb)
                .collect(Collectors.toList());
    }
}
