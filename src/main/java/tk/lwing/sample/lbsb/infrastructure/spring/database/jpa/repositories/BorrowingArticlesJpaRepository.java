package tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.repositories;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import tk.lwing.sample.lbsb.domain.entites.Article;
import tk.lwing.sample.lbsb.domain.entites.BorrowingArticles;
import tk.lwing.sample.lbsb.domain.repositories.BorrowingArticlesRepository;
import tk.lwing.sample.lbsb.domain.types.ArticleStatus;
import tk.lwing.sample.lbsb.domain.types.Category;
import tk.lwing.sample.lbsb.domain.valueobjects.ArticleID;
import tk.lwing.sample.lbsb.domain.valueobjects.CustomerID;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models.ArticleCategoriesTbl;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models.ArticlesTbl;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models.BorrowingArticlesTbl;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models.CustomersTbl;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.services.ConvertArticle;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.services.ConvertBorrowingArticles;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.services.ConvertCustomer;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BorrowingArticlesJpaRepository implements BorrowingArticlesRepository {

    @NotNull
    private final BorrowingArticlesTblRepository borrowingArticlesTblRepository;
    @NotNull
    private final CustomersTblRepository customersTblRepository;
    @NotNull
    private final ArticlesTblRepository articlesTblRepository;
    @NotNull
    private final ArticleCategoriesTblRepository articleCategoriesTblRepository;

    private static final Logger logger = LoggerFactory.getLogger(
            new Object() {
            }.getClass().getEnclosingClass());

    private ConvertBorrowingArticles.BunchOfTable generateBunchOfTable(
            BorrowingArticlesTbl borrowingArticlesTbl) {

        String customerId = borrowingArticlesTbl.getCustomerId();
        CustomersTbl customersTbl =
                this.customersTblRepository.findById(customerId).orElse(null);

        String articleId = borrowingArticlesTbl.getArticleId();
        ArticlesTbl articlesTbl =
                this.articlesTblRepository.findById(articleId).orElse(null);
        List<ArticleCategoriesTbl> articleCategoriesTblList =
                this.articleCategoriesTblRepository.findByArticleId(articleId);
        ConvertArticle.BunchOfTable articleBunchOfTable =
                ConvertArticle.BunchOfTable.builder()
                        .articlesTbl(articlesTbl)
                        .articleCategoriesTblList(articleCategoriesTblList)
                        .build();
        List<ConvertArticle.BunchOfTable> articleBunchOfTableList =
                new ArrayList<>();
        articleBunchOfTableList.add(articleBunchOfTable);

        return ConvertBorrowingArticles.BunchOfTable.builder()
                .borrowingArticlesTbl(borrowingArticlesTbl)
                .customersTbl(customersTbl)
                .articleBunchOfTableList(articleBunchOfTableList)
                .build();
    }

    private List<ConvertBorrowingArticles.BunchOfTable> generateBunchOfTableList(
            List<BorrowingArticlesTbl> borrowingArticlesTblList) {

        for (BorrowingArticlesTbl borrowingArticlesTbl :
                borrowingArticlesTblList) {

        }
        return borrowingArticlesTblList.stream()
                .map(this::generateBunchOfTable)
                .collect(Collectors.toList());
    }

    @Override
    public BorrowingArticles save(BorrowingArticles borrowingArticles) {

        List<ConvertBorrowingArticles.BunchOfTable> bunchOfTableList =
                ConvertBorrowingArticles.toDb(borrowingArticles);
        List<BorrowingArticlesTbl> borrowingArticlesTblList = new ArrayList<>();
        for (ConvertBorrowingArticles.BunchOfTable bunchOfTable :
                bunchOfTableList) {
            borrowingArticlesTblList.add(bunchOfTable.getBorrowingArticlesTbl());
        }
        List<BorrowingArticlesTbl> savedBorrowingArticlesTblList =
                this.borrowingArticlesTblRepository.saveAll(borrowingArticlesTblList);

        List<ConvertBorrowingArticles.BunchOfTable> saveBunchOfTableList =
                this.generateBunchOfTableList(savedBorrowingArticlesTblList);

        List<BorrowingArticles> savedBorrowingArticlesList =
                ConvertBorrowingArticles.toDomain(saveBunchOfTableList);
        List<Article> savedArticles = new ArrayList<>();
        for (BorrowingArticles savedBorrowingArticles : savedBorrowingArticlesList) {
            if (!borrowingArticles.getCustomer().getId().equals(savedBorrowingArticles.getCustomer().getId())) {
                return null;
            }
            savedArticles.addAll(savedBorrowingArticles.getArticles());
        }

        return new BorrowingArticles(borrowingArticles.getCustomer(), savedArticles);
    }

    @Override
    public List<BorrowingArticles> findAll() {

        List<BorrowingArticlesTbl> borrowingArticlesTblList =
                this.borrowingArticlesTblRepository.findAll().stream()
                        .sorted(Comparator.comparing(BorrowingArticlesTbl::getCustomerId)
                                .thenComparing(BorrowingArticlesTbl::getArticleId))
                        .collect(Collectors.toList());

        List<String> customerIds = borrowingArticlesTblList.stream()
                .sorted(Comparator.comparing(BorrowingArticlesTbl::getCustomerId))
                .map(BorrowingArticlesTbl::getCustomerId)
                .collect(Collectors.toList())
                .stream()
                .distinct()
                .collect(Collectors.toList());
        List<CustomersTbl> customersTblList =
                this.customersTblRepository.findByCustomerIdIn(customerIds);
        Map<String, CustomersTbl> customersTblMap = new HashMap<>();
        for (CustomersTbl customersTbl : customersTblList) {
            customersTblMap.put(customersTbl.getCustomerId(), customersTbl);
        }
        List<String> articleIds = borrowingArticlesTblList.stream()
                .sorted(Comparator.comparing(BorrowingArticlesTbl::getArticleId))
                .map(BorrowingArticlesTbl::getArticleId)
                .collect(Collectors.toList())
                .stream()
                .distinct()
                .collect(Collectors.toList());
        List<ArticlesTbl> articlesTblList =
                this.articlesTblRepository.findByArticleIdIn(articleIds).stream()
                        .sorted(Comparator.comparing(ArticlesTbl::getArticleId))
                        .collect(Collectors.toList());
        List<ArticleCategoriesTbl> articleCategoriesTblList =
                this.articleCategoriesTblRepository.findByArticleIdIn(articleIds).stream()
                        .sorted(Comparator.comparing(ArticleCategoriesTbl::getArticleId))
                        .collect(Collectors.toList());
        int posI2 = 0;
        int posI3 = 0;
        String nextCustomerId = "";
        String articleId1 = "";
        String articleId2 = "";
        String articleId3 = "";
        String nextArticleId3 = "";
        List<Category> categories = new ArrayList<>();
        List<Article> articles = new ArrayList<>();
        List<BorrowingArticles> borrowingArticlesList = new ArrayList<>();
        int borrowingTblSize = borrowingArticlesTblList.size();
        int articlesTblSize = articlesTblList.size();
        int categoriesTblSize = articleCategoriesTblList.size();
        for (int i = 0; i < borrowingTblSize; i++) {
            BorrowingArticlesTbl borrowingArticlesTbl =
                    borrowingArticlesTblList.get(i);
            String customerId = borrowingArticlesTbl.getCustomerId();
            articleId1 = borrowingArticlesTbl.getArticleId();
            nextCustomerId = i < borrowingTblSize - 1 ?
                    borrowingArticlesTblList.get(i + 1).getCustomerId() :
                    customerId;
            loop2:
            for (int i2 = posI2; i2 < articlesTblSize; i2++) {
                posI2 = i2 + 1;
                ArticlesTbl articlesTbl = articlesTblList.get(i2);
                articleId2 = articlesTbl.getArticleId();
                if (articleId1.equals(articleId2)) {
                    for (int i3 = posI3; i3 < categoriesTblSize; i3++) {
                        posI3 = i3 + 1;
                        ArticleCategoriesTbl articleCategoriesTbl =
                                articleCategoriesTblList.get(i3);
                        articleId3 = articleCategoriesTbl.getArticleId();
                        nextArticleId3 = i3 < categoriesTblSize - 1 ?
                                articleCategoriesTblList.get(i3 + 1).getArticleId() : "";
                        if (articleId1.equals(articleId3)) {
                            categories.add(Category.getCategory(articleCategoriesTbl.getCategory()));
                        }
                        if (articleId1.equals(articleId3) && !articleId1.equals(nextArticleId3)) {
                            articles.add(new Article(new ArticleID(articleId1),
                                    categories,
                                    ArticleStatus.getStatus(articlesTbl.getStatus())));
                            categories = new ArrayList<>();
                            posI3 = posI3 - 1;
                            break loop2;
                        }
                    }
                }
            }
            if (i == borrowingTblSize - 1 || !customerId.equals(nextCustomerId)) {
                borrowingArticlesList.add(new BorrowingArticles(
                        ConvertCustomer.toDomain(customersTblMap.get(customerId)),
                        articles));
                articles = new ArrayList<>();
            }
        }
        return borrowingArticlesList;
    }

    @Override
    public BorrowingArticles update(BorrowingArticles borrowingArticles) {
        List<ConvertBorrowingArticles.BunchOfTable> bunchOfTableList =
                ConvertBorrowingArticles.toDb(borrowingArticles);

        return null;
    }

    @Override
    public BorrowingArticles findByCustomerID(CustomerID customerID) {
        return null;
    }

    @Override
    public List<BorrowingArticles> delete(List<BorrowingArticles> borrowingArticlesList) {
        return null;
    }
}
