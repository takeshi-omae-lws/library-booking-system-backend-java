package tk.lwing.sample.lbsb.domain.entites;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import tk.lwing.sample.lbsb.domain.valueobjects.Period;
import tk.lwing.sample.lbsb.domain.valueobjects.RuleID;
import tk.lwing.sample.lbsb.domain.valueobjects.StartAt;

@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@ToString
public class ReturnRule implements Rule {
    private final RuleID id;
    private final Period lendingPeriod;
    private final StartAt startAt;

    public boolean isValid(Rule returnRule) {
        return this.lendingPeriod.get() >= ((ReturnRule) returnRule).getLendingPeriod().get();
    }
}