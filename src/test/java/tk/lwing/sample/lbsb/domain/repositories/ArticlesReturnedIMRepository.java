package tk.lwing.sample.lbsb.domain.repositories;

import tk.lwing.sample.lbsb.domain.events.ArticlesReturned;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticlesReturnedIMRepository implements ArticlesReturnedRepository {

    private final Map<String, ArticlesReturned> articlesReturnedMap = new HashMap<>();

    @Override
    public ArticlesReturned save(ArticlesReturned articlesReturned) {
        this.articlesReturnedMap.put(articlesReturned.getId().get(),
                articlesReturned);
        return this.articlesReturnedMap.get(articlesReturned.getId().get());
    }

    @Override
    public List<ArticlesReturned> findAll() {
        return new ArrayList<ArticlesReturned>(this.articlesReturnedMap.values());
    }

    @Override
    public List<ArticlesReturned> saveAll(List<ArticlesReturned> newArticlesReturned) {
        return null;
    }
}
