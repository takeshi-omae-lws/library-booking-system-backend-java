package tk.lwing.sample.lbsb.usecase;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.lwing.sample.lbsb.domain.entites.Article;
import tk.lwing.sample.lbsb.domain.entites.BorrowingArticles;
import tk.lwing.sample.lbsb.domain.entites.Customer;
import tk.lwing.sample.lbsb.domain.services.ArticleService;
import tk.lwing.sample.lbsb.domain.services.BorrowingArticlesService;
import tk.lwing.sample.lbsb.domain.types.ArticleStatus;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequiredArgsConstructor
public class CustomerUseCase {

    private static final Logger logger =
            LoggerFactory.getLogger(
                    new Object() {
                    }.getClass().getEnclosingClass());

    @NotNull
    private final BorrowingArticlesService borrowingArticlesService;
    @NotNull
    private final ArticleService articleService;


    // Borrow
    BorrowingArticles borrowArticles(Customer customer, List<Article> articles) {
        // check BorrowingArticles status
        logger.debug("borrowingArticlesService.isValid() : " +
                this.borrowingArticlesService.isValid(customer, articles));
        if (!this.borrowingArticlesService.isValid(customer, articles)) {
            throw new RuntimeException("wrong selected articles");
        }

        // check Articles status
        for (Article article : articles) {
            logger.debug("article.isAvailable() : " +
                    article.isAvailable());
            if (!article.isAvailable()) {
                throw new RuntimeException(
                        "your selected articles contain unavailable article");
            }
        }
        // change Articles status
        this.articleService.updateStatus(
                articles, ArticleStatus.UNAVAILABLE);

        // change BorrowingArticles status
        BorrowingArticles updatedBorrowingArticles =
                this.borrowingArticlesService.updateInsertBorrowingArticles(
                        customer, articles);

        // save ArticlesBorrowed
        this.borrowingArticlesService.saveArticlesBorrowed(
                customer, articles);

        return updatedBorrowingArticles;
    }

    // ArticlesReturned
    BorrowingArticles returnArticles(Customer customer,
                                     List<Article> articles) {
        // save ArticlesReturn
        this.borrowingArticlesService.saveArticlesReturned(customer, articles);

        // change BorrowingArticles status
        BorrowingArticles updatedBorrowingArticles =
                this.borrowingArticlesService.deleteBorrowingArticles(
                        customer, articles);

        // change Articles status
        this.articleService.updateStatus(
                articles, ArticleStatus.AVAILABLE);

        return updatedBorrowingArticles;
    }
}
