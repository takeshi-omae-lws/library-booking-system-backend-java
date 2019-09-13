package tk.lwing.sample.lbsb.domain.repositories;

import tk.lwing.sample.lbsb.domain.entites.BorrowingArticles;
import tk.lwing.sample.lbsb.domain.valueobjects.CustomerID;

import java.util.List;

public interface BorrowingArticlesRepository {

    BorrowingArticles save(final BorrowingArticles borrowingArticles);

    List<BorrowingArticles> findAll();

    BorrowingArticles update(final BorrowingArticles borrowingArticles);

    BorrowingArticles findByCustomerID(CustomerID customerID);

    List<BorrowingArticles> delete(List<BorrowingArticles> borrowingArticlesList);

}
