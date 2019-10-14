package tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.services;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import tk.lwing.sample.lbsb.domain.entites.Article;
import tk.lwing.sample.lbsb.domain.types.ArticleStatus;
import tk.lwing.sample.lbsb.domain.types.Category;
import tk.lwing.sample.lbsb.domain.valueobjects.ArticleID;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models.ArticleCategoriesTbl;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models.ArticlesTbl;

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
                        .map(item -> Category.getCategory(item.getCategory()))
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
                    .category(category.getCode())
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
    @ToString
    @EqualsAndHashCode(of = "articlesTbl")
    public static class BunchOfTable {
        final ArticlesTbl articlesTbl;
        final List<ArticleCategoriesTbl> articleCategoriesTblList;
    }

}
