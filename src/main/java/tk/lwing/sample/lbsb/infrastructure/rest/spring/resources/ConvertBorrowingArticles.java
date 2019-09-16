package tk.lwing.sample.lbsb.infrastructure.rest.spring.resources;

import tk.lwing.sample.lbsb.domain.entites.Article;
import tk.lwing.sample.lbsb.domain.entites.BorrowingArticles;
import tk.lwing.sample.lbsb.domain.valueobjects.CustomerID;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConvertBorrowingArticles {


    public static BorrowingArticles toDomain(BorrowArticlesBody body) {

        List<Article> domainArticleList = body.getArticleBodyList().stream()
                .map(ConvertArticle::toDomain)
                .collect(Collectors.toList());

        return new BorrowingArticles(
                new CustomerID(body.getCustomerBody().getId()),
                domainArticleList);
    }

    public static BorrowArticlesBody toBody(BorrowingArticles borrowingArticles) {

        List<ArticleBody> articleBodyList = borrowingArticles.getArticles().stream()
                .map(ConvertArticle::toBody)
                .collect(Collectors.toList());

        return BorrowArticlesBody.builder()
                .customerBody(CustomerBody.builder().id(borrowingArticles.getId().get()).build())
                .articleBodyList(articleBodyList)
                .build();
    }

    public static List<BorrowingArticles> toDomain(List<BorrowArticlesBody> borrowArticlesBodies) {

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

    public static List<BorrowArticlesBody> toBody(List<BorrowingArticles> borrowingArticles) {
        List<BorrowArticlesBody> bodies = new ArrayList<>();
        for (BorrowingArticles domain : borrowingArticles) {

            List<ArticleBody> articleBodyList = domain.getArticles().stream()
                    .map(ConvertArticle::toBody)
                    .collect(Collectors.toList());

            BorrowArticlesBody body = BorrowArticlesBody.builder()
                    .customerBody(CustomerBody.builder().id(domain.getId().get()).build())
                    .articleBodyList(articleBodyList)
                    .build();
            bodies.add(body);
        }
        return bodies;
    }
}
