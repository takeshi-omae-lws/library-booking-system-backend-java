package tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import tk.lwing.sample.lbsb.domain.events.ArticlesBorrowed;
import tk.lwing.sample.lbsb.domain.repositories.ArticlesBorrowedRepository;
import tk.lwing.sample.lbsb.domain.valueobjects.ArticleID;
import tk.lwing.sample.lbsb.domain.valueobjects.CustomerID;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models.ArticleCategoriesTbl;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models.ArticlesTbl;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models.BorrowedArticlesTbl;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models.ConvertArticle;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models.ConvertArticleBorrowed;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models.CustomersTbl;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ArticlesBorrowedJpaRepository implements ArticlesBorrowedRepository {

    @NotNull
    private final BorrowedArticlesTblRepository borrowedArticlesTblRepository;
    @NotNull
    private final CustomersTblRepository customersTblRepository;
    @NotNull
    private final ArticlesTblRepository articlesTblRepository;
    @NotNull
    private final ArticleCategoriesTblRepository articleCategoriesTblRepository;

    /*
            private final BorrowedArticlesTbl borrowedArticlesTbl;
            private final CustomersTbl customersTbl;
            private final ConvertArticle.BunchOfTable articleBunchOfTable;
     */
    @Override
    public ArticlesBorrowed save(ArticlesBorrowed articlesBorrowed) {
        BorrowedArticlesTbl savedBorrowedArticlesTbl =
                this.borrowedArticlesTblRepository.save(
                        ConvertArticleBorrowed
                                .toDb(articlesBorrowed)
                                .getBorrowedArticlesTbl());
        ConvertArticleBorrowed.BunchOfTable bunchOfTable =
                this.generateBunchOfTable(savedBorrowedArticlesTbl);
        assert bunchOfTable != null;
        return ConvertArticleBorrowed.toDomain(bunchOfTable);
    }

    @Override
    public List<ArticlesBorrowed> findAll() {
        List<BorrowedArticlesTbl> borrowedArticlesTblList =
                this.borrowedArticlesTblRepository.findAll();

        List<ConvertArticleBorrowed.BunchOfTable> bunchOfTableList =
                generateBunchOfTableList(borrowedArticlesTblList);

        return ConvertArticleBorrowed.toDomain(bunchOfTableList);
    }

    @Override
    public ArticlesBorrowed findLastByCustomerIdAndArticleId(
            CustomerID customerID, ArticleID articleID) {
        BorrowedArticlesTbl borrowedArticlesTbl =
                this.borrowedArticlesTblRepository
                        .findFirstByCustomerIdAndArticleIdAndOccurredAtLessThan(
                                customerID.get(), articleID.get(), LocalDateTime.now());
        ConvertArticleBorrowed.BunchOfTable bunchOfTable =
                this.generateBunchOfTable(borrowedArticlesTbl);
        assert bunchOfTable != null;
        return ConvertArticleBorrowed.toDomain(bunchOfTable);
    }

    private ConvertArticleBorrowed.BunchOfTable generateBunchOfTable(
            BorrowedArticlesTbl borrowedArticlesTbl) {
        String customerId = borrowedArticlesTbl.getCustomerId();
        String articleId = borrowedArticlesTbl.getArticleId();
        CustomersTbl customersTbl =
                this.customersTblRepository.findById(customerId).orElse(null);
        ArticlesTbl articlesTbl =
                this.articlesTblRepository.findById(articleId).orElse(null);
        List<ArticleCategoriesTbl> articleCategoriesTblList =
                this.articleCategoriesTblRepository.findByArticleId(articleId);
        if (customersTbl == null || articlesTbl == null) {
            return null;
        }
        return ConvertArticleBorrowed.BunchOfTable.builder()
                .borrowedArticlesTbl(borrowedArticlesTbl)
                .customersTbl(customersTbl)
                .articleBunchOfTable(
                        ConvertArticle.BunchOfTable.builder()
                                .articlesTbl(articlesTbl)
                                .articleCategoriesTblList(
                                        articleCategoriesTblList).build())
                .build();
    }

    private List<ConvertArticleBorrowed.BunchOfTable> generateBunchOfTableList(
            List<BorrowedArticlesTbl> borrowedArticlesTblList) {
        return borrowedArticlesTblList.stream()
                .map(this::generateBunchOfTable)
                .collect(Collectors.toList());
    }
}
