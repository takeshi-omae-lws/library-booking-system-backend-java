package tk.lwing.sample.lbsb.domain.repositories;

import tk.lwing.sample.lbsb.domain.events.ArticlesBorrowed;
import tk.lwing.sample.lbsb.domain.valueobjects.ArticleID;
import tk.lwing.sample.lbsb.domain.valueobjects.CustomerID;

import java.util.List;

public interface ArticlesBorrowedRepository {

    ArticlesBorrowed save(final ArticlesBorrowed articlesBorrowed);

    List<ArticlesBorrowed> findAll();

    ArticlesBorrowed findLastByCustomerIdAndArticleId(
            CustomerID customerID, ArticleID articleID);
}
