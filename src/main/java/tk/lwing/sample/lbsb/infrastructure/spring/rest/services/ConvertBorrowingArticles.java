package tk.lwing.sample.lbsb.infrastructure.spring.rest.services;

import tk.lwing.sample.lbsb.domain.entites.Article;
import tk.lwing.sample.lbsb.domain.entites.BorrowingArticles;
import tk.lwing.sample.lbsb.infrastructure.spring.rest.resources.ArticleBody;
import tk.lwing.sample.lbsb.infrastructure.spring.rest.resources.BorrowArticlesBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConvertBorrowingArticles {


    public static BorrowingArticles toDomain(BorrowArticlesBody body) {

        List<Article> domainArticleList = body.getArticleBodyList().stream()
                .map(ConvertArticle::toDomain)
                .collect(Collectors.toList());

        return new BorrowingArticles(
                ConvertCustomer.toDomain(body.getCustomerBody()),
                domainArticleList);
    }

    public static BorrowArticlesBody toBody(BorrowingArticles borrowingArticles) {

        List<ArticleBody> articleBodyList = borrowingArticles.getArticles().stream()
                .map(ConvertArticle::toBody)
                .collect(Collectors.toList());

        return BorrowArticlesBody.builder()
                .customerBody(ConvertCustomer.toBody(borrowingArticles.getCustomer()))
                .articleBodyList(articleBodyList)
                .build();
    }

    public static List<BorrowingArticles> toDomain(List<BorrowArticlesBody> borrowArticlesBodies) {

        List<BorrowingArticles> borrowingArticles = new ArrayList<>();
        for (BorrowArticlesBody borrowArticlesBody : borrowArticlesBodies) {
            List<Article> domainArticles = new ArrayList<>();
            for (ArticleBody articleBody :
                    borrowArticlesBody.getArticleBodyList()) {
                domainArticles.add(ConvertArticle.toDomain(articleBody));
            }
            BorrowingArticles domain = new BorrowingArticles(
                    ConvertCustomer.toDomain(borrowArticlesBody.getCustomerBody()),
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
                    .customerBody(ConvertCustomer.toBody(domain.getCustomer()))
                    .articleBodyList(articleBodyList)
                    .build();
            bodies.add(body);
        }
        return bodies;
    }
}
