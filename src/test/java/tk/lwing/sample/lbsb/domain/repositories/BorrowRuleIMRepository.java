package tk.lwing.sample.lbsb.domain.repositories;

import tk.lwing.sample.lbsb.domain.entites.BorrowRule;
import tk.lwing.sample.lbsb.domain.valueobjects.AsOf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BorrowRuleIMRepository implements BorrowRuleRepository {

    private final Map<String, BorrowRule> borrowRuleMap = new HashMap<>();

    @Override
    public BorrowRule save(BorrowRule borrowRule) {
        this.borrowRuleMap.put(borrowRule.getId().get(), borrowRule);
        return this.borrowRuleMap.get(borrowRule.getId().get());
    }

    @Override
    public List<BorrowRule> findAll() {
        return new ArrayList<>(this.borrowRuleMap.values());
    }

    @Override
    public BorrowRule findLast() {
        Optional<Map.Entry<String, BorrowRule>> result =
                this.borrowRuleMap.entrySet()
                        .stream()
                        .max((e1, e2) -> e1.getValue().getStartAt().compareTo(e2.getValue().getStartAt()));
        assert result.orElse(null) != null;
        return result.orElse(null).getValue();
    }

    @Override
    public BorrowRule findByAsOf(AsOf asOf) {
        Optional<Map.Entry<String, BorrowRule>> result =
                this.borrowRuleMap.entrySet()
                        .stream()
                        .filter(e -> e.getValue().getStartAt().get().compareTo(asOf.get()) <= 0)
                        .max((e1, e2) -> e1.getValue().getStartAt().compareTo(e2.getValue().getStartAt()));
        assert result.orElse(null) != null;
        return result.orElse(null).getValue();
    }
}
