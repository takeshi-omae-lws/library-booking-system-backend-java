package tk.lwing.sample.lbsb.usecase;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.lwing.sample.lbsb.domain.entites.Article;
import tk.lwing.sample.lbsb.domain.entites.BorrowRule;
import tk.lwing.sample.lbsb.domain.entites.BorrowingArticles;
import tk.lwing.sample.lbsb.domain.entites.Customer;
import tk.lwing.sample.lbsb.domain.entites.KeepRule;
import tk.lwing.sample.lbsb.domain.entites.ReturnRule;
import tk.lwing.sample.lbsb.domain.entites.Rule;
import tk.lwing.sample.lbsb.domain.repositories.ArticleIMRepository;
import tk.lwing.sample.lbsb.domain.repositories.ArticleRepository;
import tk.lwing.sample.lbsb.domain.repositories.ArticlesBorrowedIMRepository;
import tk.lwing.sample.lbsb.domain.repositories.ArticlesBorrowedRepository;
import tk.lwing.sample.lbsb.domain.repositories.ArticlesReturnedIMRepository;
import tk.lwing.sample.lbsb.domain.repositories.ArticlesReturnedRepository;
import tk.lwing.sample.lbsb.domain.repositories.BorrowRuleIMRepository;
import tk.lwing.sample.lbsb.domain.repositories.BorrowRuleRepository;
import tk.lwing.sample.lbsb.domain.repositories.BorrowingArticlesIMRepository;
import tk.lwing.sample.lbsb.domain.repositories.BorrowingArticlesRepository;
import tk.lwing.sample.lbsb.domain.repositories.CustomerIMRepository;
import tk.lwing.sample.lbsb.domain.repositories.CustomerRepository;
import tk.lwing.sample.lbsb.domain.repositories.KeepRuleIMRepository;
import tk.lwing.sample.lbsb.domain.repositories.KeepRuleRepository;
import tk.lwing.sample.lbsb.domain.repositories.ReturnRuleIMRepository;
import tk.lwing.sample.lbsb.domain.repositories.ReturnRuleRepository;
import tk.lwing.sample.lbsb.domain.services.ArticleService;
import tk.lwing.sample.lbsb.domain.services.BorrowingArticlesService;
import tk.lwing.sample.lbsb.domain.services.CustomerService;
import tk.lwing.sample.lbsb.domain.services.RuleService;
import tk.lwing.sample.lbsb.domain.types.ArticleStatus;
import tk.lwing.sample.lbsb.domain.types.Category;
import tk.lwing.sample.lbsb.domain.valueobjects.Amount;
import tk.lwing.sample.lbsb.domain.valueobjects.ArticleID;
import tk.lwing.sample.lbsb.domain.valueobjects.CustomerID;
import tk.lwing.sample.lbsb.domain.valueobjects.Identifier;
import tk.lwing.sample.lbsb.domain.valueobjects.Period;
import tk.lwing.sample.lbsb.domain.valueobjects.RuleID;
import tk.lwing.sample.lbsb.domain.valueobjects.StartAt;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustomerUseCaseTests {

    private static final Logger logger =
            LoggerFactory.getLogger(
                    new Object() {
                    }.getClass().getEnclosingClass());

    private CustomerUseCase customerUseCase;
    private AdminUseCase adminUseCase;

    private CustomerService customerService;
    private RuleService ruleService;
    private ArticleService articleService;
    private BorrowingArticlesService borrowingArticlesService;

    private final ArticleRepository articleRepository = new ArticleIMRepository();
    private final ArticlesBorrowedRepository articlesBorrowedRepository =
            new ArticlesBorrowedIMRepository();
    private ArticlesReturnedRepository articlesReturnedRepository =
            new ArticlesReturnedIMRepository();
    private final BorrowingArticlesRepository borrowingArticlesRepository =
            new BorrowingArticlesIMRepository();
    private final BorrowRuleRepository borrowRuleRepository =
            new BorrowRuleIMRepository();
    private final CustomerRepository customerRepository =
            new CustomerIMRepository();
    private final KeepRuleRepository keepRuleRepository =
            new KeepRuleIMRepository();
    private final ReturnRuleRepository returnRuleRepository =
            new ReturnRuleIMRepository();

    private List<Category> categories1 = new ArrayList<>();
    private List<Category> categories2 = new ArrayList<>();
    private List<Category> categories3 = new ArrayList<>();
    private List<Category> categories4 = new ArrayList<>();


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

        // Create instances of Service Class
        this.customerService = new CustomerService(this.customerRepository);
        this.articleService = new ArticleService(this.articleRepository);
        this.ruleService = new RuleService(this.borrowRuleRepository,
                this.returnRuleRepository, this.keepRuleRepository);
        this.borrowingArticlesService = new BorrowingArticlesService(
                this.borrowingArticlesRepository,
                this.articlesBorrowedRepository,
                this.articlesReturnedRepository,
                this.borrowRuleRepository,
                this.keepRuleRepository,
                this.returnRuleRepository);

        // Create instances of UseCase Class
        this.adminUseCase = new AdminUseCase(this.customerService,
                this.ruleService);

        this.customerUseCase = new CustomerUseCase(
                this.borrowingArticlesService, this.articleService);

        // Generate Initial Data of Customer
        for (int i = 0; i < 10; i++) {
            this.customerRepository.save(
                    new Customer(new CustomerID(Identifier.generateId())));
        }
        logger.debug("Initial Customer: " +
                this.customerRepository.findAll().size() + " " +
                this.customerRepository.findAll());

        // Generate Initial Data of Rule
        this.basicRules.add(bRule1);
        this.basicRules.add(kRule1);
        this.basicRules.add(rRule1);
        List<Rule> savedAllRuleList = this.ruleService.saveRules(this.basicRules);
        logger.debug("Initial Rule: " + savedAllRuleList.toString());

        // Generate Initial Data of Article
        this.categories1.add(Category.NEW);
        this.categories2.add(Category.CLASSIC);
        this.categories3.add(Category.STANDARD);
        this.categories4.add(Category.NEW);
        this.categories4.add(Category.CLASSIC);
        this.categories4.add(Category.STANDARD);

        List<List<Category>> categoriesData = new ArrayList<>();
        categoriesData.add(categories1);
        categoriesData.add(categories2);
        categoriesData.add(categories3);
        categoriesData.add(categories4);

        for (int i = 1; i < 100; i++) {
            for (int j = 0; j < 3; j++) {
                this.articleRepository.save((
                        new Article(new ArticleID(Identifier.generateId()),
                                categoriesData.get(j),
                                ArticleStatus.AVAILABLE)));
            }
        }

        logger.debug("Initial Article Data: " +
                this.articleRepository.findAll().size() + " " +
                this.articleRepository.findAll());
    }

    @Test
    public void borrowTest() {
        Customer customer1 = new Customer(new CustomerID("CSTMR001"));
        Customer savedCustomer = this.adminUseCase.createNewCustomer(customer1);
        logger.debug("Saved New Customer: " +
                savedCustomer.toString());
        logger.debug("All Customers: " +
                this.customerRepository.findAll().size() + " " +
                this.customerRepository.findAll().toString());

        List<Article> articles1 = new ArrayList<>();
        articles1.add(this.articleRepository.save((
                new Article(new ArticleID("ATC001"),
                        this.categories1, ArticleStatus.AVAILABLE))));
        articles1.add(this.articleRepository.save((
                new Article(new ArticleID("ATC002"),
                        this.categories2, ArticleStatus.AVAILABLE))));
        articles1.add(this.articleRepository.save((
                new Article(new ArticleID("ATC003"),
                        this.categories3, ArticleStatus.AVAILABLE))));
        articles1.add(this.articleRepository.save((
                new Article(new ArticleID("ATC004"),
                        this.categories4, ArticleStatus.AVAILABLE))));

        logger.debug("All Article: " +
                this.articleRepository.findAll().size() + " " +
                this.articleRepository.findAll().toString());

        logger.debug("Args Customer: " + customer1.toString() +
                "Args ArticlesTbl: " + articles1.toString());

        //
        // Execute
        //
        logger.debug("////////////////////////////////////////");
        logger.debug("// Execute borrow");
        BorrowingArticles borrowingArticles =
                this.customerUseCase.borrowArticles(customer1, articles1);

        logger.debug("////////////////////////////////////////");
        logger.debug("Borrowed: " + borrowingArticles.toString());
        logger.debug("BorrowingArticles: " +
                this.borrowingArticlesRepository
                        .findByCustomerID(customer1.getId()).toString());
        for (Article article : articles1) {
            logger.debug("Article: " +
                    this.articleRepository.findById(article.getId()).toString());
        }
        logger.debug("ArticlesBorrowed: " +
                this.articlesBorrowedRepository.findAll().toString());
        logger.debug("ArticlesReturned: " +
                this.articlesReturnedRepository.findAll().toString());


        List<Article> articles2 = new ArrayList<>();
        articles2.add(this.articleRepository.save((
                new Article(new ArticleID("ATC001"),
                        this.categories1, ArticleStatus.AVAILABLE))));
        articles2.add(this.articleRepository.save((
                new Article(new ArticleID("ATC002"),
                        this.categories2, ArticleStatus.AVAILABLE))));

        //
        // Execute
        //
        logger.debug("////////////////////////////////////////");
        logger.debug("// Execute return");
        BorrowingArticles afterBorrowingArticles =
                this.customerUseCase.returnArticles(customer1, articles2);

        logger.debug("////////////////////////////////////////");
        logger.debug("Borrowed: " + borrowingArticles.toString());
        logger.debug("BorrowingArticles: " +
                this.borrowingArticlesRepository
                        .findByCustomerID(customer1.getId()).toString());
        for (Article article : articles1) {
            logger.debug("Article: " +
                    this.articleRepository.findById(article.getId()).toString());
        }
        logger.debug("ArticlesBorrowed: " +
                this.articlesBorrowedRepository.findAll().toString());
        logger.debug("ArticlesReturned: " +
                this.articlesReturnedRepository.findAll().toString());
    }

}
