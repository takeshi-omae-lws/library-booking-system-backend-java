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
public class KeepRule implements Rule {
    private final RuleID id;
    private final Amount maxAmount;
    private final Amount maxNewAmount;
    private final StartAt startAt;

    public boolean isValid(Rule havingRule) {
        return (this.maxAmount.get() >= ((KeepRule) havingRule).getMaxAmount().get() &&
                this.maxNewAmount.get() >= ((KeepRule) havingRule).getMaxNewAmount().get());
    }
}