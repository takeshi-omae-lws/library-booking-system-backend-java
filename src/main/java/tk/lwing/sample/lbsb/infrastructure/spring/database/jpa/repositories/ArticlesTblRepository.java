package tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models.ArticlesTbl;

import java.util.List;

public interface ArticlesTblRepository
        extends JpaRepository<ArticlesTbl, String> {

    List<ArticlesTbl> findByStatus(final int status);

    List<ArticlesTbl> findByArticleIdIn(final List<String> articleIds);
}
