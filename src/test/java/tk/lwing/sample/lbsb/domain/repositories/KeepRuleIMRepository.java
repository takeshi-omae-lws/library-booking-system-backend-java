package tk.lwing.sample.lbsb.domain.repositories;

import tk.lwing.sample.lbsb.domain.entites.KeepRule;
import tk.lwing.sample.lbsb.domain.valueobjects.AsOf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class KeepRuleIMRepository implements KeepRuleRepository {

    private final Map<String, KeepRule> keepRuleMap = new HashMap<>();

    @Override
    public KeepRule save(KeepRule keepRule) {
        this.keepRuleMap.put(keepRule.getId().get(), keepRule);
        return this.keepRuleMap.get(keepRule.getId().get());
    }

    @Override
    public List<KeepRule> findAll() {
        return new ArrayList<KeepRule>(this.keepRuleMap.values());
    }


    @Override
    public KeepRule findLast() {
//        List<KeepRule> keepRules = new ArrayList<>();
//        for (Map.Entry<String, KeepRule> keepRule :
//                this.keepRuleMap.entrySet()) {
//            keepRules.add(keepRule.getValue());
//        }
//
//        Comparator<KeepRule> startAtComparator =
//                Comparator.comparing(KeepRule::getStartAt);

        Optional<Map.Entry<String, KeepRule>> result =
                this.keepRuleMap.entrySet()
                        .stream()
                        .max((e1, e2) -> e1.getValue().getStartAt().compareTo(e2.getValue().getStartAt()));
        assert result.orElse(null) != null;
        return result.orElse(null).getValue();
    }

    @Override
    public KeepRule findByAsOf(AsOf asOf) {
        Optional<Map.Entry<String, KeepRule>> result =
                this.keepRuleMap.entrySet()
                        .stream()
                        .filter(e -> e.getValue().getStartAt().get().compareTo(asOf.get()) <= 0)
                        .max((e1, e2) -> e1.getValue().getStartAt().compareTo(e2.getValue().getStartAt()));
        assert result.orElse(null) != null;
        return result.orElse(null).getValue();
    }
}
