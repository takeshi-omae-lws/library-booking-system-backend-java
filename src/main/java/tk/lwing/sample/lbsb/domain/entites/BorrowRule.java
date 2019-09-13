package tk.lwing.sample.lbsb.domain.entites;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import tk.lwing.sample.lbsb.domain.valueobjects.Amount;
import tk.lwing.sample.lbsb.domain.valueobjects.RuleID;
import tk.lwing.sample.lbsb.domain.valueobjects.StartAt;

@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@ToString
public class BorrowRule implements Rule {
    private final RuleID id;
    private final Amount maxAmount;
    private final StartAt startAt;

    public boolean isValid(Rule borrowingRule) {
        return this.maxAmount.get() >= ((BorrowRule) borrowingRule).getMaxAmount().get();
    }
}