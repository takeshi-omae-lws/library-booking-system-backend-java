package tk.lwing.sample.lbsb.domain.services;

import lombok.RequiredArgsConstructor;
import tk.lwing.sample.lbsb.domain.entites.Article;
import tk.lwing.sample.lbsb.domain.entites.BorrowRule;
import tk.lwing.sample.lbsb.domain.entites.BorrowingArticles;
import tk.lwing.sample.lbsb.domain.entites.Customer;
import tk.lwing.sample.lbsb.domain.entites.KeepRule;
import tk.lwing.sample.lbsb.domain.events.ArticlesBorrowed;
import tk.lwing.sample.lbsb.domain.events.ArticlesReturned;
import tk.lwing.sample.lbsb.domain.repositories.ArticlesBorrowedRepository;
import tk.lwing.sample.lbsb.domain.repositories.ArticlesReturnedRepository;
import tk.lwing.sample.lbsb.domain.repositories.BorrowRuleRepository;
import tk.lwing.sample.lbsb.domain.repositories.BorrowingArticlesRepository;
import tk.lwing.sample.lbsb.domain.repositories.KeepRuleRepository;
import tk.lwing.sample.lbsb.domain.repositories.ReturnRuleRepository;
import tk.lwing.sample.lbsb.domain.types.Category;
import tk.lwing.sample.lbsb.domain.valueobjects.Amount;
import tk.lwing.sample.lbsb.domain.valueobjects.ArticlesBorrowedID;
import tk.lwing.sample.lbsb.domain.valueobjects.AsOf;
import tk.lwing.sample.lbsb.domain.valueobjects.CustomerID;
import tk.lwing.sample.lbsb.domain.valueobjects.DueAt;
import tk.lwing.sample.lbsb.domain.valueobjects.Identifier;
import tk.lwing.sample.lbsb.domain.valueobjects.OccurredAt;
import tk.lwing.sample.lbsb.domain.valueobjects.Period;
import tk.lwing.sample.lbsb.domain.valueobjects.RuleID;
import tk.lwing.sample.lbsb.domain.valueobjects.StartAt;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class BorrowingArticlesService {

    @NotNull
    private final BorrowingArticlesRepository borrowingArticlesRepository;
    @NotNull
    private final ArticlesBorrowedRepository articlesBorrowedRepository;
    @NotNull
    private final ArticlesReturnedRepository articlesReturnedRepository;
    @NotNull
    private final BorrowRuleRepository borrowRuleRepository;
    @NotNull
    private final KeepRuleRepository keepRuleRepository;
    @NotNull
    private final ReturnRuleRepository returnRuleRepository;

    // check BorrowingArticles status
    public boolean isValid(Customer customer, List<Article> wishArticles) {
        return isValidBorrowRule(customer.getId())
                && isValidKeepRule(customer.getId(), wishArticles);
    }

    private boolean isValidBorrowRule(CustomerID id) {
        BorrowingArticles borrowingArticles =
                this.borrowingArticlesRepository.findByCustomerID(id);
        int currentArticlesSize =
                borrowingArticles == null ? 0 : borrowingArticles.getArticles().size();
        BorrowRule currentStat = new BorrowRule(new RuleID("TMP"),
                new Amount(currentArticlesSize),
                new StartAt(LocalDateTime.now()));
        return this.borrowRuleRepository
                .findByAsOf(new AsOf(LocalDateTime.now()))
                .isValid(currentStat);
    }

    private boolean isValidKeepRule(
            CustomerID id, List<Article> wishArticles) {

        int wishAllCount = wishArticles.size();
        int wishNewCount = (int) wishArticles.stream()
                .filter(item -> item.getCategories().contains(Category.NEW)).count();

        BorrowingArticles borrowingArticles =
                this.borrowingArticlesRepository.findByCustomerID(id);
        int keepAllCount =
                borrowingArticles == null ? 0 : borrowingArticles.getArticles().size();
        int keepNewCount =
                (int) (borrowingArticles == null ? 0 :
                        borrowingArticles.getArticles().stream()
                                .filter(item -> item.getCategories().contains(Category.NEW))
                                .count());
        int afterAllCount = wishAllCount + keepAllCount;
        int afterNewCount = wishNewCount + keepNewCount;
        KeepRule currentStat = new KeepRule(new RuleID("TMP"),
                new Amount(afterAllCount),
                new Amount(afterNewCount),
                new StartAt(LocalDateTime.now()));

        return this.keepRuleRepository
                .findByAsOf(new AsOf(LocalDateTime.now()))
                .isValid(currentStat);
    }

    public BorrowingArticles updateInsertBorrowingArticles(Customer customer,
                                                           List<Article> articles) {
        if (this.borrowingArticlesRepository.findByCustomerID(customer.getId()) == null) {
            return this.borrowingArticlesRepository.save(new BorrowingArticles(customer.getId(), articles));
        }
        return this.borrowingArticlesRepository.update(new BorrowingArticles(customer.getId(), articles));
    }

    public BorrowingArticles deleteBorrowingArticles(Customer customer,
                                                     List<Article> articles) {
        BorrowingArticles currentBorrowingArticles =
                this.borrowingArticlesRepository.findByCustomerID(customer.getId());
        if (currentBorrowingArticles == null) {
            throw new RuntimeException("unknown .....");
        }
        List<Article> targetArticles = currentBorrowingArticles.getArticles();
        for (Article returnedArticle : articles) {
            targetArticles.remove(returnedArticle);
        }

        return this.borrowingArticlesRepository.update(
                new BorrowingArticles(customer.getId(), targetArticles));
    }

    public void saveArticlesBorrowed(Customer customer, List<Article> articles) {
        LocalDateTime now = LocalDateTime.now();
        Period lendingPeriod =
                this.returnRuleRepository.findByAsOf(new AsOf(now)).getLendingPeriod();
        for (Article article : articles) {
            ArticlesBorrowed articlesBorrowed =
                    new ArticlesBorrowed(new ArticlesBorrowedID(Identifier.generateId()),
                            customer, article, new OccurredAt(now),
                            new DueAt(now.plusDays(lendingPeriod.get())));
            this.articlesBorrowedRepository.save(articlesBorrowed);
        }
    }

    public void saveArticlesReturned(Customer customer, List<Article> articles) {
        LocalDateTime now = LocalDateTime.now();

        System.out.println("articles.size(): " + articles.size());
        for (Article article : articles) {

            ArticlesBorrowed ab = this.articlesBorrowedRepository
                    .findLastByCustomerIdAndArticleId(
                            customer.getId(), article.getId());
            System.out.println("ArticlesBorrowed: " + ab.toString());
            this.articlesReturnedRepository
                    .save(new ArticlesReturned(
                            this.articlesBorrowedRepository
                                    .findLastByCustomerIdAndArticleId(
                                            customer.getId(), article.getId()).getId(),
                            new OccurredAt(now)));
        }
    }
}
