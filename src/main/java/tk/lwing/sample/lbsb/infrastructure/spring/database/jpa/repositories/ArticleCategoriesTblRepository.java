package tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models.ArticleCategoriesTbl;

import java.util.List;

@Repository
public interface ArticleCategoriesTblRepository
        extends JpaRepository<ArticleCategoriesTbl, Integer> {

    List<ArticleCategoriesTbl> findByArticleId(String articleId);

    List<ArticleCategoriesTbl> findByArticleIdIn(List<String> articleIds);

}
