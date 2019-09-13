package tk.lwing.sample.lbsb.usecase;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.lwing.sample.lbsb.domain.entites.BorrowRule;
import tk.lwing.sample.lbsb.domain.entites.Customer;
import tk.lwing.sample.lbsb.domain.entites.KeepRule;
import tk.lwing.sample.lbsb.domain.entites.ReturnRule;
import tk.lwing.sample.lbsb.domain.entites.Rule;
import tk.lwing.sample.lbsb.domain.repositories.BorrowRuleIMRepository;
import tk.lwing.sample.lbsb.domain.repositories.BorrowRuleRepository;
import tk.lwing.sample.lbsb.domain.repositories.CustomerIMRepository;
import tk.lwing.sample.lbsb.domain.repositories.CustomerRepository;
import tk.lwing.sample.lbsb.domain.repositories.KeepRuleIMRepository;
import tk.lwing.sample.lbsb.domain.repositories.KeepRuleRepository;
import tk.lwing.sample.lbsb.domain.repositories.ReturnRuleIMRepository;
import tk.lwing.sample.lbsb.domain.repositories.ReturnRuleRepository;
import tk.lwing.sample.lbsb.domain.services.CustomerService;
import tk.lwing.sample.lbsb.domain.services.RuleService;
import tk.lwing.sample.lbsb.domain.valueobjects.Amount;
import tk.lwing.sample.lbsb.domain.valueobjects.AsOf;
import tk.lwing.sample.lbsb.domain.valueobjects.CustomerID;
import tk.lwing.sample.lbsb.domain.valueobjects.Identifier;
import tk.lwing.sample.lbsb.domain.valueobjects.Period;
import tk.lwing.sample.lbsb.domain.valueobjects.RuleID;
import tk.lwing.sample.lbsb.domain.valueobjects.StartAt;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AdminUseCaseTests {

    private static final Logger logger =
            LoggerFactory.getLogger(
                    new Object() {
                    }.getClass().getEnclosingClass());

    private AdminUseCase adminUseCase;

    private CustomerService customerService;
    private RuleService ruleService;

    private final CustomerRepository customerRepository =
            new CustomerIMRepository();
    private final BorrowRuleRepository borrowRuleRepository =
            new BorrowRuleIMRepository();
    private final ReturnRuleRepository returnRuleRepository =
            new ReturnRuleIMRepository();
    private final KeepRuleRepository keepRuleRepository =
            new KeepRuleIMRepository();

    // basic data
    private final Customer cst001 =
            new Customer(new CustomerID(Identifier.generateId()));
    private final Customer cst002 =
            new Customer(new CustomerID(Identifier.generateId()));
    private final Customer cst003 =
            new Customer(new CustomerID(Identifier.generateId()));
    private final Customer cst004 =
            new Customer(new CustomerID(Identifier.generateId()));
    private final Customer cst005 =
            new Customer(new CustomerID(Identifier.generateId()));
    private final Customer cst006 =
            new Customer(new CustomerID(Identifier.generateId()));
    private final Customer cst007 =
            new Customer(new CustomerID(Identifier.generateId()));
    private final Customer cst008 =
            new Customer(new CustomerID(Identifier.generateId()));
    private final Customer cst009 =
            new Customer(new CustomerID(Identifier.generateId()));
    private final Customer cst010 =
            new Customer(new CustomerID(Identifier.generateId()));

    private final LocalDateTime nowDate = LocalDateTime.now();
    private final LocalDateTime before05Days = LocalDateTime.now().minusDays(5);
    private final LocalDateTime before10Days = LocalDateTime.now().minusDays(10);
    private final LocalDateTime before15Days = LocalDateTime.now().minusDays(15);

    private final List<Rule> basicRules = new ArrayList<>();

    private final BorrowRule bRule1 =
            new BorrowRule(new RuleID(Identifier.generateId()),
                    new Amount(5), new StartAt(this.before15Days));
    private final KeepRule kRule1 =
            new KeepRule(new RuleID(Identifier.generateId()),
                    new Amount(7), new Amount(2),
                    new StartAt(this.before15Days));
    private final ReturnRule rRule1 =
            new ReturnRule(new RuleID(Identifier.generateId()),
                    new Period(30), new StartAt(this.before15Days));

    @Before
    public void setUp() {
        this.customerService = new CustomerService(this.customerRepository);
        this.ruleService = new RuleService(this.borrowRuleRepository,
                this.returnRuleRepository, this.keepRuleRepository);

        this.adminUseCase = new AdminUseCase(this.customerService,
                this.ruleService);

        this.customerService.create(cst001);
        this.customerService.create(cst002);
        this.customerService.create(cst003);
        this.customerService.create(cst004);
        this.customerService.create(cst005);
        this.customerService.create(cst006);
        this.customerService.create(cst007);
        this.customerService.create(cst008);
        this.customerService.create(cst009);
        this.customerService.create(cst010);

        this.basicRules.add(bRule1);
        this.basicRules.add(kRule1);
        this.basicRules.add(rRule1);
        List<Rule> savedAllRuleList = this.ruleService.saveRules(this.basicRules);
        logger.debug("Saved All Rule List: " + savedAllRuleList.toString());
    }

    @Test
    public void doAdminUseCase() {

        Customer customer1 = new Customer(new CustomerID("CSTMR001"));
        Customer savedCustomer = this.adminUseCase.createNewCustomer(customer1);
        logger.debug("Saved New Customer: " +
                savedCustomer.toString());
        logger.debug("All Customers: " +
                this.adminUseCase.getAllCustomers().toString());

        List<Rule> newRules = new ArrayList<>();
        BorrowRule borrowRule1 =
                new BorrowRule(new RuleID(Identifier.generateId()),
                        new Amount(1), new StartAt(this.before10Days));
        KeepRule keepRule1 =
                new KeepRule(new RuleID(Identifier.generateId()),
                        new Amount(1), new Amount(1),
                        new StartAt(this.before10Days));
        ReturnRule returnRule1 =
                new ReturnRule(new RuleID(Identifier.generateId()),
                        new Period(1), new StartAt(this.before05Days));
        BorrowRule borrowRule2 =
                new BorrowRule(new RuleID(Identifier.generateId()),
                        new Amount(2), new StartAt(this.before05Days));
        KeepRule keepRule2 =
                new KeepRule(new RuleID(Identifier.generateId()),
                        new Amount(3), new Amount(2),
                        new StartAt(this.before05Days));
        ReturnRule returnRule2 =
                new ReturnRule(new RuleID(Identifier.generateId()),
                        new Period(5), new StartAt(this.before05Days));
        BorrowRule borrowRule3 =
                new BorrowRule(new RuleID(Identifier.generateId()),
                        new Amount(2), new StartAt(this.nowDate));
        KeepRule keepRule3 =
                new KeepRule(new RuleID(Identifier.generateId()),
                        new Amount(3), new Amount(2),
                        new StartAt(this.nowDate));
        ReturnRule returnRule3 =
                new ReturnRule(new RuleID(Identifier.generateId()),
                        new Period(5), new StartAt(this.nowDate));
        newRules.add(borrowRule1);
        newRules.add(keepRule1);
        newRules.add(returnRule1);
        newRules.add(borrowRule2);
        newRules.add(keepRule2);
        newRules.add(returnRule2);
        newRules.add(borrowRule3);
        newRules.add(keepRule3);
        newRules.add(returnRule3);

        logger.debug("New Saved Rules: " +
                this.adminUseCase.createNewRules(newRules).toString());

        logger.debug("Latest Rules: " +
                this.adminUseCase.getLatestAllRules().toString());

        logger.debug("Available Rules: " +
                this.adminUseCase.getAvailableRules(
                        new AsOf(nowDate)).toString());


    }

}
