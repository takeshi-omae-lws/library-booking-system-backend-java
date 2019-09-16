package tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models.ReturnedArticlesTbl;

public interface ReturnedArticlesTblRepository
        extends JpaRepository<ReturnedArticlesTbl, Integer> {
}
