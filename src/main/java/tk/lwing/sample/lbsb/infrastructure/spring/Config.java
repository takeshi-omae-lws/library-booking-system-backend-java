package tk.lwing.sample.lbsb.infrastructure.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.lwing.sample.lbsb.domain.repositories.ArticleRepository;
import tk.lwing.sample.lbsb.domain.repositories.ArticlesBorrowedRepository;
import tk.lwing.sample.lbsb.domain.repositories.ArticlesReturnedRepository;
import tk.lwing.sample.lbsb.domain.repositories.BorrowRuleRepository;
import tk.lwing.sample.lbsb.domain.repositories.BorrowingArticlesRepository;
import tk.lwing.sample.lbsb.domain.repositories.CustomerRepository;
import tk.lwing.sample.lbsb.domain.repositories.KeepRuleRepository;
import tk.lwing.sample.lbsb.domain.repositories.ReturnRuleRepository;
import tk.lwing.sample.lbsb.domain.services.ArticleService;
import tk.lwing.sample.lbsb.domain.services.BorrowingArticlesService;
import tk.lwing.sample.lbsb.domain.services.CustomerService;
import tk.lwing.sample.lbsb.domain.services.RuleService;
import tk.lwing.sample.lbsb.usecase.AdminUseCase;
import tk.lwing.sample.lbsb.usecase.CustomerUseCase;

@Configuration
public class Config {

    private final ArticleRepository articleRepository;
    private final ArticlesBorrowedRepository articlesBorrowedRepository;
    private final ArticlesReturnedRepository articlesReturnedRepository;
    private final BorrowingArticlesRepository borrowingArticlesRepository;
    private final BorrowRuleRepository borrowRuleRepository;
    private final CustomerRepository customerRepository;
    private final KeepRuleRepository keepRuleRepository;
    private final ReturnRuleRepository returnRuleRepository;

    private final ArticleService articleService;
    private final BorrowingArticlesService borrowingArticlesService;
    private final CustomerService customerService;
    private final RuleService ruleService;

    public Config(ArticleRepository articleRepository,
                  ArticlesBorrowedRepository articlesBorrowedRepository,
                  ArticlesReturnedRepository articlesReturnedRepository,
                  BorrowingArticlesRepository borrowingArticlesRepository,
                  BorrowRuleRepository borrowRuleRepository,
                  CustomerRepository customerRepository,
                  KeepRuleRepository keepRuleRepository,
                  ReturnRuleRepository returnRuleRepository) {

        this.articleRepository = articleRepository;
        this.articlesBorrowedRepository = articlesBorrowedRepository;
        this.articlesReturnedRepository = articlesReturnedRepository;
        this.borrowingArticlesRepository = borrowingArticlesRepository;
        this.borrowRuleRepository = borrowRuleRepository;
        this.customerRepository = customerRepository;
        this.keepRuleRepository = keepRuleRepository;
        this.returnRuleRepository = returnRuleRepository;

        this.articleService = new ArticleService(this.articleRepository);
        this.borrowingArticlesService = new BorrowingArticlesService(
                this.borrowingArticlesRepository,
                this.articlesBorrowedRepository,
                this.articlesReturnedRepository,
                this.borrowRuleRepository,
                this.keepRuleRepository,
                this.returnRuleRepository);
        this.customerService = new CustomerService(this.customerRepository);
        this.ruleService = new RuleService(this.borrowRuleRepository,
                this.returnRuleRepository, this.keepRuleRepository);
    }

    @Bean
    public AdminUseCase adminUseCase() {
        return new AdminUseCase(this.customerService, this.ruleService);
    }

    @Bean
    public CustomerUseCase customerUseCase() {
        return new CustomerUseCase(
                this.borrowingArticlesService, this.articleService);
    }

}
