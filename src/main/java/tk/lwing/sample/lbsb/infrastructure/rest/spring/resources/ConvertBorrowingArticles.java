package tk.lwing.sample.lbsb.infrastructure.rest.spring.resources;

import tk.lwing.sample.lbsb.domain.entites.Article;
import tk.lwing.sample.lbsb.domain.entites.BorrowingArticles;
import tk.lwing.sample.lbsb.domain.valueobjects.CustomerID;

import java.util.ArrayList;
import java.util.List;

public class ConvertBorrowArticles {

    public List<BorrowingArticles> toDomain(List<BorrowArticlesBody> borrowArticlesBodies) {

        List<BorrowingArticles> borrowingArticles = new ArrayList<>();
        for (BorrowArticlesBody borrowArticlesBody : borrowArticlesBodies) {
            CustomerID id =
                    new CustomerID(borrowArticlesBody.getCustomerBody().getId());
            List<Article> domainArticles = new ArrayList<>();
            for (ArticleBody articleBody :
                    borrowArticlesBody.getArticleBodyList()) {
                domainArticles.add(ConvertArticle.toDomain(articleBody));
            }
            BorrowingArticles domain = new BorrowingArticles(id,
                    domainArticles);
            borrowingArticles.add(domain);
        }
        return borrowingArticles;
    }
}
