package tk.lwing.sample.lbsb.domain.repositories;

import tk.lwing.sample.lbsb.domain.events.ArticlesBorrowed;
import tk.lwing.sample.lbsb.domain.valueobjects.ArticleID;
import tk.lwing.sample.lbsb.domain.valueobjects.CustomerID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ArticlesBorrowedIMRepository implements ArticlesBorrowedRepository {

    private final Map<String, ArticlesBorrowed> articlesBorrowedMap = new HashMap<>();

    @Override
    public ArticlesBorrowed save(ArticlesBorrowed articlesBorrowed) {
        this.articlesBorrowedMap.put(articlesBorrowed.getId().get(), articlesBorrowed);
        return this.articlesBorrowedMap.get(articlesBorrowed.getId().get());
    }

    @Override
    public List<ArticlesBorrowed> findAll() {
        return new ArrayList<ArticlesBorrowed>(this.articlesBorrowedMap.values());
    }

    @Override
    public ArticlesBorrowed findLastByCustomerIdAndArticleId(
            CustomerID customerID, ArticleID articleID) {

        Optional<ArticlesBorrowed> articlesBorrowed =
                this.articlesBorrowedMap.values()
                        .stream()
                        .filter(item -> item.getCustomer().getId().equals(customerID))
                        .filter(item -> item.getArticle().getId().equals(articleID))
                        .max((e1, e2) -> e1.getBorrowedAt().compareTo(e2.getBorrowedAt()));

        return articlesBorrowed.orElse(null);
    }
}
