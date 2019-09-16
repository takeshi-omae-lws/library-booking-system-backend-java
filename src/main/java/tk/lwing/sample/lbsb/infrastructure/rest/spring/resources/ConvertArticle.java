package tk.lwing.sample.lbsb.infrastructure.rest.spring.resources;

import tk.lwing.sample.lbsb.domain.entites.Article;
import tk.lwing.sample.lbsb.domain.types.ArticleStatus;
import tk.lwing.sample.lbsb.domain.types.Category;
import tk.lwing.sample.lbsb.domain.valueobjects.ArticleID;

import java.util.List;
import java.util.stream.Collectors;

public class ConvertArticle {

    public static Article toDomain(ArticleBody articleBody) {
        List<Category> categories = articleBody.getCategories().stream()
                .map(Category::getCategory)
                .collect(Collectors.toList());
        return new Article(new ArticleID(articleBody.getId()),
                categories,
                ArticleStatus.getStatus(articleBody.getStatus()));
    }

    public static ArticleBody toBody(Article article) {
        List<Integer> categoryList = article.getCategories().stream()
                .map(Category::getCode)
                .collect(Collectors.toList());
        return ArticleBody.builder().id(article.getId().get())
                .categories(categoryList)
                .status(article.getStatus().getCode())
                .build();
    }
}
