package tk.lwing.sample.lbsb.domain.repositories;

import org.junit.Test;
import tk.lwing.sample.lbsb.domain.entites.Article;
import tk.lwing.sample.lbsb.domain.entites.BorrowRule;
import tk.lwing.sample.lbsb.domain.entites.BorrowingArticles;
import tk.lwing.sample.lbsb.domain.entites.Customer;
import tk.lwing.sample.lbsb.domain.entites.KeepRule;
import tk.lwing.sample.lbsb.domain.entites.ReturnRule;
import tk.lwing.sample.lbsb.domain.events.ArticlesBorrowed;
import tk.lwing.sample.lbsb.domain.events.ArticlesReturned;
import tk.lwing.sample.lbsb.domain.types.ArticleStatus;
import tk.lwing.sample.lbsb.domain.types.Category;
import tk.lwing.sample.lbsb.domain.valueobjects.Amount;
import tk.lwing.sample.lbsb.domain.valueobjects.ArticleID;
import tk.lwing.sample.lbsb.domain.valueobjects.ArticlesBorrowedID;
import tk.lwing.sample.lbsb.domain.valueobjects.AsOf;
import tk.lwing.sample.lbsb.domain.valueobjects.CustomerID;
import tk.lwing.sample.lbsb.domain.valueobjects.DueAt;
import tk.lwing.sample.lbsb.domain.valueobjects.OccurredAt;
import tk.lwing.sample.lbsb.domain.valueobjects.Period;
import tk.lwing.sample.lbsb.domain.valueobjects.RuleID;
import tk.lwing.sample.lbsb.domain.valueobjects.StartAt;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class IMTests {

    private ArticleRepository articleRepository = new ArticleIMRepository();
    private CustomerRepository customerRepository = new CustomerIMRepository();
    private ArticlesBorrowedRepository articlesBorrowedRepository =
            new ArticlesBorrowedIMRepository();
    private ArticlesReturnedRepository articlesReturnedRepository =
            new ArticlesReturnedIMRepository();
    private BorrowingArticlesRepository borrowingArticlesRepository =
            new BorrowingArticlesIMRepository();
    private BorrowRuleRepository borrowRuleRepository =
            new BorrowRuleIMRepository();
    private KeepRuleRepository keepRuleRepository = new KeepRuleIMRepository();
    private ReturnRuleRepository returnRuleRepository =
            new ReturnRuleIMRepository();

    @Test
    public void doArticle() {
        List<Category> categories1 = new ArrayList<>();
        categories1.add(Category.NEW);
        List<Category> categories2 = new ArrayList<>();
        categories2.add(Category.CLASSIC);
        List<Category> categories3 = new ArrayList<>();
        categories3.add(Category.STANDARD);

        Article article1 = new Article(new ArticleID("A001"),
                categories1, ArticleStatus.AVAILABLE);
        Article article2 = new Article(new ArticleID("A002"),
                categories2, ArticleStatus.AVAILABLE);
        Article article3 = new Article(new ArticleID("A003"),
                categories3, ArticleStatus.UNAVAILABLE);

        this.articleRepository.save(article1);
        this.articleRepository.save(article2);
        this.articleRepository.save(article3);

        System.out.println(this.articleRepository.findAll().toString());

        System.out.println(this.articleRepository.findByValid().toString());

        article1.setStatus(ArticleStatus.UNAVAILABLE);
        System.out.println(this.articleRepository.update(article1).toString());
        System.out.println(this.articleRepository.findAll().toString());

    }

    @Test
    public void doCustomer() {

        Customer customer1 = new Customer(new CustomerID("CST001"));
        Customer customer2 = new Customer(new CustomerID("CST002"));
        Customer customer3 = new Customer(new CustomerID("CST003"));

        this.customerRepository.save(customer1);
        this.customerRepository.save(customer2);
        this.customerRepository.save(customer3);

        System.out.println(this.customerRepository.findAll().toString());
    }

    @Test
    public void doArticlesBorrowed() {
        // before
        Customer customer1 = new Customer(new CustomerID("CST001"));
        Customer customer2 = new Customer(new CustomerID("CST002"));
        Customer customer3 = new Customer(new CustomerID("CST003"));
        Customer customer4 = new Customer(new CustomerID("CST004"));
        this.customerRepository.save(customer1);
        this.customerRepository.save(customer2);
        this.customerRepository.save(customer3);
        this.customerRepository.save(customer4);

        List<Category> categories1 = new ArrayList<>();
        categories1.add(Category.NEW);
        List<Category> categories2 = new ArrayList<>();
        categories2.add(Category.CLASSIC);
        List<Category> categories3 = new ArrayList<>();
        categories3.add(Category.STANDARD);
        List<Category> categories4 = new ArrayList<>();
        categories4.add(Category.NEW);
        categories4.add(Category.CLASSIC);
        categories4.add(Category.STANDARD);
        Article article1 = new Article(new ArticleID("A001"),
                categories1, ArticleStatus.AVAILABLE);
        Article article2 = new Article(new ArticleID("A002"),
                categories2, ArticleStatus.AVAILABLE);
        Article article3 = new Article(new ArticleID("A003"),
                categories3, ArticleStatus.UNAVAILABLE);
        Article article4 = new Article(new ArticleID("A004"),
                categories4, ArticleStatus.AVAILABLE);
        Article article5 = new Article(new ArticleID("A005"),
                categories4, ArticleStatus.AVAILABLE);
        Article article6 = new Article(new ArticleID("A006"),
                categories4, ArticleStatus.AVAILABLE);
        this.articleRepository.save(article1);
        this.articleRepository.save(article2);
        this.articleRepository.save(article3);
        this.articleRepository.save(article4);
        this.articleRepository.save(article5);
        this.articleRepository.save(article6);

        // Execute Test
        ArticlesBorrowed articlesBorrowed1 =
                new ArticlesBorrowed(new ArticlesBorrowedID("AB001"),
                        customer1, article1,
                        new OccurredAt(LocalDateTime.now()),
                        new DueAt(LocalDateTime.now().plusDays(5)));
        ArticlesBorrowed articlesBorrowed2 =
                new ArticlesBorrowed(new ArticlesBorrowedID("AB002"),
                        customer2, article2,
                        new OccurredAt(LocalDateTime.now()),
                        new DueAt(LocalDateTime.now().plusDays(5)));
        ArticlesBorrowed articlesBorrowed3 =
                new ArticlesBorrowed(new ArticlesBorrowedID("AB003"),
                        customer3, article3,
                        new OccurredAt(LocalDateTime.now()),
                        new DueAt(LocalDateTime.now().plusDays(5)));
        this.articlesBorrowedRepository.save(articlesBorrowed1);
        this.articlesBorrowedRepository.save(articlesBorrowed2);
        this.articlesBorrowedRepository.save(articlesBorrowed3);

        System.out.println(this.articlesBorrowedRepository.findAll().toString());

        ArticlesBorrowed foundArticleBorrowed =
                this.articlesBorrowedRepository.findLastByCustomerIdAndArticleId(
                        customer2.getId(), article2.getId());
        System.out.println(foundArticleBorrowed.toString());
    }

    @Test
    public void doArticlesReturned() {
        // before
        Customer customer1 = new Customer(new CustomerID("CST001"));
        Customer customer2 = new Customer(new CustomerID("CST002"));
        Customer customer3 = new Customer(new CustomerID("CST003"));
        this.customerRepository.save(customer1);
        this.customerRepository.save(customer2);
        this.customerRepository.save(customer3);

        List<Category> categories1 = new ArrayList<>();
        categories1.add(Category.NEW);
        List<Category> categories2 = new ArrayList<>();
        categories2.add(Category.CLASSIC);
        List<Category> categories3 = new ArrayList<>();
        categories3.add(Category.STANDARD);
        Article article1 = new Article(new ArticleID("A001"),
                categories1, ArticleStatus.AVAILABLE);
        Article article2 = new Article(new ArticleID("A002"),
                categories2, ArticleStatus.AVAILABLE);
        Article article3 = new Article(new ArticleID("A003"),
                categories3, ArticleStatus.UNAVAILABLE);
        this.articleRepository.save(article1);
        this.articleRepository.save(article2);
        this.articleRepository.save(article3);

        ArticlesBorrowed articlesBorrowed1 =
                new ArticlesBorrowed(new ArticlesBorrowedID("AB001"),
                        customer1, article1,
                        new OccurredAt(LocalDateTime.now()),
                        new DueAt(LocalDateTime.now().plusDays(5)));
        ArticlesBorrowed articlesBorrowed2 =
                new ArticlesBorrowed(new ArticlesBorrowedID("AB002"),
                        customer2, article2,
                        new OccurredAt(LocalDateTime.now()),
                        new DueAt(LocalDateTime.now().plusDays(5)));
        ArticlesBorrowed articlesBorrowed3 =
                new ArticlesBorrowed(new ArticlesBorrowedID("AB003"),
                        customer3, article3,
                        new OccurredAt(LocalDateTime.now()),
                        new DueAt(LocalDateTime.now().plusDays(5)));
        this.articlesBorrowedRepository.save(articlesBorrowed1);
        this.articlesBorrowedRepository.save(articlesBorrowed2);
        this.articlesBorrowedRepository.save(articlesBorrowed3);

        // Execute Test
        ArticlesReturned articlesReturned1 =
                new ArticlesReturned(articlesBorrowed1.getId(),
                        new OccurredAt(LocalDateTime.now()));
        ArticlesReturned articlesReturned2 =
                new ArticlesReturned(articlesBorrowed2.getId(),
                        new OccurredAt(LocalDateTime.now()));
        ArticlesReturned articlesReturned3 =
                new ArticlesReturned(articlesBorrowed3.getId(),
                        new OccurredAt(LocalDateTime.now()));

        this.articlesReturnedRepository.save(articlesReturned1);
        this.articlesReturnedRepository.save(articlesReturned2);
        this.articlesReturnedRepository.save(articlesReturned3);

        System.out.println(this.articlesReturnedRepository.findAll().toString());
    }

    @Test
    public void doBorrowingArticles() {
        // before
        Customer customer1 = new Customer(new CustomerID("CST001"));
        Customer customer2 = new Customer(new CustomerID("CST002"));
        Customer customer3 = new Customer(new CustomerID("CST003"));
        this.customerRepository.save(customer1);
        this.customerRepository.save(customer2);
        this.customerRepository.save(customer3);

        List<Category> categories1 = new ArrayList<>();
        categories1.add(Category.NEW);
        List<Category> categories2 = new ArrayList<>();
        categories2.add(Category.CLASSIC);
        List<Category> categories3 = new ArrayList<>();
        categories3.add(Category.STANDARD);
        Article article1 = new Article(new ArticleID("A001"),
                categories1, ArticleStatus.AVAILABLE);
        Article article2 = new Article(new ArticleID("A002"),
                categories2, ArticleStatus.AVAILABLE);
        Article article3 = new Article(new ArticleID("A003"),
                categories3, ArticleStatus.UNAVAILABLE);
        Article article4 = new Article(new ArticleID("A004"),
                categories1, ArticleStatus.AVAILABLE);
        Article article5 = new Article(new ArticleID("A005"),
                categories2, ArticleStatus.AVAILABLE);
        Article article6 = new Article(new ArticleID("A006"),
                categories3, ArticleStatus.UNAVAILABLE);
        Article article7 = new Article(new ArticleID("A007"),
                categories1, ArticleStatus.AVAILABLE);
        Article article8 = new Article(new ArticleID("A008"),
                categories2, ArticleStatus.AVAILABLE);
        Article article9 = new Article(new ArticleID("A009"),
                categories3, ArticleStatus.UNAVAILABLE);
        this.articleRepository.save(article1);
        this.articleRepository.save(article2);
        this.articleRepository.save(article3);
        this.articleRepository.save(article4);
        this.articleRepository.save(article5);
        this.articleRepository.save(article6);
        this.articleRepository.save(article7);
        this.articleRepository.save(article8);
        this.articleRepository.save(article9);

        // Do Test
        List<Article> articles1 = new ArrayList<>();
        articles1.add(article1);
        articles1.add(article2);
        articles1.add(article3);
        List<Article> articles2 = new ArrayList<>();
        articles2.add(article4);
        articles2.add(article5);
        articles2.add(article6);
        List<Article> articles3 = new ArrayList<>();
        articles2.add(article7);
        articles2.add(article8);
        articles2.add(article9);

        BorrowingArticles borrowingArticles1 =
                new BorrowingArticles(customer1.getId(), articles1);
        BorrowingArticles borrowingArticles2 =
                new BorrowingArticles(customer2.getId(), articles2);
        BorrowingArticles borrowingArticles3 =
                new BorrowingArticles(customer3.getId(), articles3);

        this.borrowingArticlesRepository.save(borrowingArticles1);
        this.borrowingArticlesRepository.save(borrowingArticles2);
        this.borrowingArticlesRepository.save(borrowingArticles3);

        System.out.println(this.borrowingArticlesRepository.findAll().toString());

        BorrowingArticles borrowingArticles2Up = this.borrowingArticlesRepository
                .findByCustomerID(new CustomerID("CST002"));
        System.out.println(borrowingArticles2Up.toString());

        List<Article> articles2Up = new ArrayList<>();
        articles2Up.add(article4);

        borrowingArticles2Up.setArticles(articles2Up);
        System.out.println(this.borrowingArticlesRepository
                .update(borrowingArticles2Up).toString());

        System.out.println(this.borrowingArticlesRepository
                .findByCustomerID(new CustomerID("CST002")));

        System.out.println(this.borrowingArticlesRepository
                .findAll().toString());
    }

    @Test
    public void doBorrowRule() {
        BorrowRule borrowRule1 = new BorrowRule(new RuleID("BR001"),
                new Amount(1),
                new StartAt(LocalDateTime.of(2001, Month.JANUARY, 1, 1, 1)));
        BorrowRule borrowRule2 = new BorrowRule(new RuleID("BR002"),
                new Amount(2),
                new StartAt(LocalDateTime.of(2001, Month.FEBRUARY, 1, 1, 1)));
        BorrowRule borrowRule3 = new BorrowRule(new RuleID("BR003"),
                new Amount(3),
                new StartAt(LocalDateTime.of(2001, Month.MARCH, 1, 1, 1)));

        this.borrowRuleRepository.save(borrowRule1);
        this.borrowRuleRepository.save(borrowRule2);
        this.borrowRuleRepository.save(borrowRule3);

        System.out.println(this.borrowRuleRepository.findAll().toString());

        System.out.println(this.borrowRuleRepository.findLast());

        System.out.println(this.borrowRuleRepository.findByAsOf(
                new AsOf(LocalDateTime.of(2001, Month.FEBRUARY, 10, 1, 1))));
    }

    @Test
    public void doTestKeepRule() {
        KeepRule keepRule1 = new KeepRule(new RuleID("KR001"),
                new Amount(1), new Amount(1),
                new StartAt(LocalDateTime.of(2001, Month.JANUARY, 1, 1, 1)));
        KeepRule keepRule2 = new KeepRule(new RuleID("KR002"),
                new Amount(2), new Amount(2),
                new StartAt(LocalDateTime.of(2001, Month.FEBRUARY, 1, 1, 1)));
        KeepRule keepRule3 = new KeepRule(new RuleID("KR003"),
                new Amount(3), new Amount(3),
                new StartAt(LocalDateTime.of(2001, Month.MARCH, 1, 1, 1)));
        this.keepRuleRepository.save(keepRule1);
        this.keepRuleRepository.save(keepRule2);
        this.keepRuleRepository.save(keepRule3);

        System.out.println(this.keepRuleRepository.findAll().toString());

        System.out.println(this.keepRuleRepository.findLast().toString());

        System.out.println(this.keepRuleRepository.findByAsOf(
                new AsOf(LocalDateTime.of(2001, Month.FEBRUARY, 10, 1, 1)))
                .toString());
    }

    @Test
    public void doReturnRule() {
        ReturnRule returnRule1 = new ReturnRule(new RuleID("RR001"),
                new Period(5),
                new StartAt(LocalDateTime.of(2001, Month.JANUARY, 1, 1, 1)));
        ReturnRule returnRule2 = new ReturnRule(new RuleID("RR002"),
                new Period(10),
                new StartAt(LocalDateTime.of(2001, Month.FEBRUARY, 1, 1, 1)));
        ReturnRule returnRule3 = new ReturnRule(new RuleID("RR003"),
                new Period(15),
                new StartAt(LocalDateTime.of(2001, Month.MARCH, 1, 1, 1)));
        this.returnRuleRepository.save(returnRule1);
        this.returnRuleRepository.save(returnRule2);
        this.returnRuleRepository.save(returnRule3);

        System.out.println(this.returnRuleRepository.findAll().toString());

        System.out.println(this.returnRuleRepository.findLast().toString());

        System.out.println(this.returnRuleRepository.findByAsOf(
                new AsOf(LocalDateTime.of(2001, Month.FEBRUARY, 10, 1, 1)))
                .toString());

    }

}
