package tk.lwing.sample.lbsb.domain.repositories;

import tk.lwing.sample.lbsb.domain.entites.Article;
import tk.lwing.sample.lbsb.domain.entites.BorrowingArticles;
import tk.lwing.sample.lbsb.domain.valueobjects.CustomerID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BorrowingArticlesIMRepository implements BorrowingArticlesRepository {

    private final Map<String, BorrowingArticles> borrowingArticlesMap = new HashMap<>();

    @Override
    public BorrowingArticles save(BorrowingArticles borrowingArticles) {
        this.borrowingArticlesMap.put(borrowingArticles.getId().get(),
                borrowingArticles);
        return this.borrowingArticlesMap.get(borrowingArticles.getId().get());
    }

    @Override
    public List<BorrowingArticles> findAll() {
        return new ArrayList<>(this.borrowingArticlesMap.values());
    }

    @Override
    public BorrowingArticles update(BorrowingArticles borrowingArticles) {
        BorrowingArticles target =
                this.borrowingArticlesMap.get(borrowingArticles.getId().get());
        target.setArticles(borrowingArticles.getArticles());
        this.borrowingArticlesMap.put(borrowingArticles.getId().get(), target);
        return this.borrowingArticlesMap.get(borrowingArticles.getId().get());
    }

    @Override
    public BorrowingArticles findByCustomerID(CustomerID customerID) {
        return this.borrowingArticlesMap.get(customerID.get());
    }

    @Override
    public List<BorrowingArticles> delete(
            List<BorrowingArticles> borrowingArticlesList) {

        BorrowingArticles targetBorrowingArticles =
                this.borrowingArticlesMap.get(
                        borrowingArticlesList.get(0).getId().get());
        List<Article> targetArticles = targetBorrowingArticles.getArticles();

        return null;
    }
}
