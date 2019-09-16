package tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import tk.lwing.sample.lbsb.domain.entites.Article;
import tk.lwing.sample.lbsb.domain.types.ArticleStatus;
import tk.lwing.sample.lbsb.domain.types.Category;
import tk.lwing.sample.lbsb.domain.valueobjects.ArticleID;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConvertArticle {

    public static Article toDomain(BunchOfTable bunchOfTable) {
        ArticleID articleID =
                new ArticleID(bunchOfTable.getArticlesTbl().getArticleId());
        List<Category> categoryList =
                bunchOfTable.getArticleCategoriesTblList().stream()
                        .filter(item -> item.getArticleId().equals(articleID.get()))
                        .map(item -> Category.getCategory(item.getArticleCategoryId()))
                        .collect(Collectors.toList());
        return new Article(articleID, categoryList,
                ArticleStatus.getStatus(bunchOfTable.getArticlesTbl().getStatus()));
    }

    public static List<Article> toDomain(List<BunchOfTable> bunchOfTableList) {
        return bunchOfTableList.stream()
                .map(ConvertArticle::toDomain)
                .collect(Collectors.toList());
    }

    public static BunchOfTable toDb(Article article) {
        ArticlesTbl articlesTbl = ArticlesTbl.builder()
                .articleId(article.getId().get())
                .status(article.getStatus().getCode())
                .build();
        List<ArticleCategoriesTbl> articleCategoriesTblList = new ArrayList<>();
        for (Category category : article.getCategories()) {
            articleCategoriesTblList.add(ArticleCategoriesTbl.builder()
                    .articleId(article.getId().get())
                    .articleCategoryId(category.getCode())
                    .build());
        }
        return BunchOfTable.builder()
                .articlesTbl(articlesTbl)
                .articleCategoriesTblList(articleCategoriesTblList)
                .build();
    }

    public static List<BunchOfTable> toDb(List<Article> articles) {
        return articles.stream()
                .map(ConvertArticle::toDb)
                .collect(Collectors.toList());
    }

    @RequiredArgsConstructor
    @Builder
    @Getter
    public static class BunchOfTable {
        final ArticlesTbl articlesTbl;
        final List<ArticleCategoriesTbl> articleCategoriesTblList;
    }

    // #######################
    @Deprecated
    public static Article toDomain(
            ArticlesTbl articlesTbl,
            List<ArticleCategoriesTbl> articleCategoriesTblList) {

        ArticleID articleID = new ArticleID(articlesTbl.getArticleId());
        List<Category> categoryList =
                articleCategoriesTblList.stream()
                        .filter(item -> item.getArticleId().equals(articleID.get()))
                        .map(item -> Category.getCategory(item.getArticleCategoryId()))
                        .collect(Collectors.toList());

        return new Article(articleID, categoryList,
                ArticleStatus.getStatus(articlesTbl.getStatus()));
    }

    @Deprecated
    public static List<Article> toDomain(
            List<ArticlesTbl> articlesTblList,
            List<ArticleCategoriesTbl> articleCategoriesTblList) {

        return articlesTblList.stream()
                .map(item -> ConvertArticle.toDomain(item, articleCategoriesTblList))
                .collect(Collectors.toList());
    }

//    public static ArticlesTbl toDb(Article article) {
//        return new ArticlesTbl(
//                article.getId().get(),
//                toArticleCategoriesTblList(article)));
//        return null;
//    }

//    public static List<ArticlesTbl> toDb(List<Article> articles) {
//        return articles.stream()
//                .map(ConvertArticle::toDb)
//                .collect(Collectors.toList());
//    }

    @Deprecated
    public static ArticlesTbl toArticlesTbl(Article article) {
        return ArticlesTbl.builder()
                .articleId(article.getId().get())
                .status(article.getStatus().getCode())
                .build();
    }

    @Deprecated
    public static List<ArticleCategoriesTbl> toArticleCategoriesTblList(Article article) {
        return article.getCategories().stream()
                .map(item ->
                        ArticleCategoriesTbl.builder()
                                .articleId(article.getId().get())
                                .category(item.getCode())
                                .build())
                .collect(Collectors.toList());
    }

}
