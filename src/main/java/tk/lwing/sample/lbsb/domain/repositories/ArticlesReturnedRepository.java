package tk.lwing.sample.lbsb.domain.repositories;

import tk.lwing.sample.lbsb.domain.events.ArticlesReturned;

import java.util.List;

public interface ArticlesReturnedRepository {

    ArticlesReturned save(final ArticlesReturned articlesReturned);

    List<ArticlesReturned> findAll();

    List<ArticlesReturned> saveAll(final List<ArticlesReturned> newArticlesReturned);
}
