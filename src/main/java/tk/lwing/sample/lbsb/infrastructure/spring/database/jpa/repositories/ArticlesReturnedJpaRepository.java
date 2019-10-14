package tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import tk.lwing.sample.lbsb.domain.events.ArticlesReturned;
import tk.lwing.sample.lbsb.domain.repositories.ArticlesReturnedRepository;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.services.ConvertArticleReturned;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArticlesReturnedJpaRepository implements ArticlesReturnedRepository {

    @NotNull
    private final ReturnedArticlesTblRepository returnedArticlesTblRepository;

    @Override
    public ArticlesReturned save(ArticlesReturned articlesReturned) {
        return ConvertArticleReturned.toDomain(
                this.returnedArticlesTblRepository.save(
                        ConvertArticleReturned.toDb(articlesReturned)));
    }

    @Override
    public List<ArticlesReturned> findAll() {
        return ConvertArticleReturned.toDomain(
                this.returnedArticlesTblRepository.findAll());
    }

    @Override
    public List<ArticlesReturned> saveAll(List<ArticlesReturned> newArticlesReturned) {
        return ConvertArticleReturned.toDomain(this.returnedArticlesTblRepository.saveAll(
                ConvertArticleReturned.toDb(newArticlesReturned)));
    }
}
