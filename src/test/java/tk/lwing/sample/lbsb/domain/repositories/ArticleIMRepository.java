package tk.lwing.sample.lbsb.domain.repositories;

import tk.lwing.sample.lbsb.domain.entites.Article;
import tk.lwing.sample.lbsb.domain.types.ArticleStatus;
import tk.lwing.sample.lbsb.domain.valueobjects.ArticleID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleIMRepository implements ArticleRepository {

    private final Map<String, Article> articleMap = new HashMap<>();

    @Override
    public Article save(Article article) {
        this.articleMap.put(article.getId().get(), article);
        return this.articleMap.get(article.getId().get());
    }

    @Override
    public List<Article> findAll() {
        return new ArrayList<Article>(this.articleMap.values());
    }


    @Override
    public List<Article> findAvailable() {
        List<Article> articles = new ArrayList<>();

        this.articleMap.values()
                .stream()
                .filter(article -> article.getStatus().equals(ArticleStatus.AVAILABLE))
                .forEach(articles::add);

        return articles;

    }

    @Override
    public Article findById(ArticleID id) {
        return this.articleMap.get(id.get());
    }

    @Override
    public Article updateArticleStatus(Article article) {
        ArticleID articleID = article.getId();
        this.articleMap.put(articleID.get(), article);
        return this.articleMap.get(articleID.get());
    }
}
