package tk.lwing.sample.lbsb.usecase;

import lombok.RequiredArgsConstructor;
import tk.lwing.sample.lbsb.domain.entites.Customer;
import tk.lwing.sample.lbsb.domain.entites.Rule;
import tk.lwing.sample.lbsb.domain.services.CustomerService;
import tk.lwing.sample.lbsb.domain.services.RuleService;
import tk.lwing.sample.lbsb.domain.valueobjects.AsOf;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequiredArgsConstructor
public class AdminUseCase {

    @NotNull
    private final CustomerService customerService;
    @NotNull
    private final RuleService ruleService;

    public Customer createNewCustomer(Customer customer) {
        return this.customerService.create(customer);
    }

    public List<Customer> getAllCustomers() {
        return this.customerService.findAll();
    }

    public List<Rule> createNewRules(List<Rule> newRules) {
        return this.ruleService.saveRules(newRules);
    }

    public List<Rule> getLatestAllRules() {
        return this.ruleService.findAllLatestRules();
    }

    public List<Rule> getAvailableRules(AsOf asOf) {
        return this.ruleService.findAllRulesByAsOf(asOf);
    }
}
