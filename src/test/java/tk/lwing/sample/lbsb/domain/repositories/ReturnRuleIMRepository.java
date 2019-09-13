package tk.lwing.sample.lbsb.domain.repositories;

import tk.lwing.sample.lbsb.domain.entites.ReturnRule;
import tk.lwing.sample.lbsb.domain.valueobjects.AsOf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ReturnRuleIMRepository implements ReturnRuleRepository {

    private final Map<String, ReturnRule> returnRuleMap = new HashMap<>();

    @Override
    public ReturnRule save(ReturnRule returnRule) {
        this.returnRuleMap.put(returnRule.getId().get(), returnRule);
        return this.returnRuleMap.get(returnRule.getId().get());
    }

    @Override
    public List<ReturnRule> findAll() {
        return new ArrayList<>(this.returnRuleMap.values());
    }

    @Override
    public ReturnRule findLast() {
        Optional<Map.Entry<String, ReturnRule>> result =
                this.returnRuleMap.entrySet()
                        .stream()
                        .max((e1, e2) -> e1.getValue().getStartAt().compareTo(e2.getValue().getStartAt()));
        assert result.orElse(null) != null;
        return result.orElse(null).getValue();
    }

    @Override
    public ReturnRule findByAsOf(AsOf asOf) {
        Optional<Map.Entry<String, ReturnRule>> result =
                this.returnRuleMap.entrySet()
                        .stream()
                        .filter(e -> e.getValue().getStartAt().get().compareTo(asOf.get()) <= 0)
                        .max((e1, e2) -> e1.getValue().getStartAt().compareTo(e2.getValue().getStartAt()));
        assert result.orElse(null) != null;
        return result.orElse(null).getValue();
    }
}
