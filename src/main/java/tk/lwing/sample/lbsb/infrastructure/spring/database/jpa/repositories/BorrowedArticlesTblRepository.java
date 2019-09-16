package tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models.BorrowedArticlesTbl;

import java.time.LocalDateTime;

public interface BorrowedArticlesTblRepository
        extends JpaRepository<BorrowedArticlesTbl, String> {

    BorrowedArticlesTbl findFirstByCustomerIdAndArticleIdAndOccurredAtLessThan
            (String customerId, String articleId, LocalDateTime asOf);

}
