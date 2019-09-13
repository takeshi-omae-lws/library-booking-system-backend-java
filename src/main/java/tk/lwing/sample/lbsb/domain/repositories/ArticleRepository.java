package tk.lwing.sample.lbsb.domain.repositories;

import tk.lwing.sample.lbsb.domain.entites.Article;
import tk.lwing.sample.lbsb.domain.valueobjects.ArticleID;

import java.util.List;

public interface ArticleRepository {

    Article save(final Article article);

    List<Article> findAll();

    List<Article> findByValid();

    Article findById(final ArticleID id);

    Article update(final Article article);
}
