package tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import tk.lwing.sample.lbsb.domain.entites.Article;
import tk.lwing.sample.lbsb.domain.entites.BorrowingArticles;
import tk.lwing.sample.lbsb.domain.valueobjects.CustomerID;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConvertBorrowingArticles {

    public static BorrowingArticles toDomain(BunchOfTable bunchOfTable) {
        return new BorrowingArticles(
                new CustomerID(
                        bunchOfTable.getCustomersTbl().getCustomerId()),
                ConvertArticle.toDomain(bunchOfTable.getArticleBunchOfTableList())
        );
    }

    public static List<BorrowingArticles> toDomain(List<BunchOfTable> bunchOfTableList) {
        return bunchOfTableList.stream()
                .map(ConvertBorrowingArticles::toDomain)
                .collect(Collectors.toList());
    }

    public static List<BunchOfTable> toDb(BorrowingArticles domain) {

        ConvertArticle.toDb(domain.getArticles());

        String customerId = domain.getId().get();
        CustomersTbl customersTbl = CustomersTbl.builder()
                .customerId(customerId).build();

        List<BunchOfTable> bunchOfTableList = new ArrayList<>();
        for (Article article : domain.getArticles()) {
            BorrowingArticlesTbl borrowingArticlesTbl = BorrowingArticlesTbl.builder()
                    .articleId(article.getId().get())
                    .customerId(customerId)
                    .build();

            BunchOfTable bunchOfTable = BunchOfTable.builder()
                    .borrowingArticlesTbl(borrowingArticlesTbl)
                    .customersTbl(customersTbl)
                    .articleBunchOfTableList(ConvertArticle.toDb(domain.getArticles()))
                    .build();
            bunchOfTableList.add(bunchOfTable);
        }
        return bunchOfTableList;
    }

    public static List<BunchOfTable> toDb(List<BorrowingArticles> domains) {
        List<BunchOfTable> bunchOfTableList = new ArrayList<>();
        for (BorrowingArticles domain : domains) {
            bunchOfTableList.addAll(toDb(domain));
        }
        return bunchOfTableList;
    }

    @RequiredArgsConstructor
    @Builder
    @Getter
    public static class BunchOfTable {
        private final BorrowingArticlesTbl borrowingArticlesTbl;
        private final CustomersTbl customersTbl;
        private final List<ConvertArticle.BunchOfTable> articleBunchOfTableList;
    }
}
