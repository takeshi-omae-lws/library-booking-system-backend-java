package tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.services;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import tk.lwing.sample.lbsb.domain.events.ArticlesBorrowed;
import tk.lwing.sample.lbsb.domain.valueobjects.ArticlesBorrowedID;
import tk.lwing.sample.lbsb.domain.valueobjects.DueAt;
import tk.lwing.sample.lbsb.domain.valueobjects.OccurredAt;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models.BorrowedArticlesTbl;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models.CustomersTbl;

import java.util.List;
import java.util.stream.Collectors;

public class ConvertArticleBorrowed {

    public static ArticlesBorrowed toDomain(BunchOfTable bunchOfTable) {

        BorrowedArticlesTbl borrowedArticlesTbl =
                bunchOfTable.getBorrowedArticlesTbl();
        CustomersTbl customersTbl = bunchOfTable.getCustomersTbl();
        ConvertArticle.BunchOfTable articleBunchOfTable =
                bunchOfTable.getArticleBunchOfTable();

        return new ArticlesBorrowed(
                new ArticlesBorrowedID(borrowedArticlesTbl.getBorrowedArticleId()),
                ConvertCustomer.toDomain(customersTbl),
                ConvertArticle.toDomain(articleBunchOfTable),
                new OccurredAt(borrowedArticlesTbl.getOccurredAt()),
                new DueAt(borrowedArticlesTbl.getDueAt()));
    }

    public static List<ArticlesBorrowed> toDomain(
            List<BunchOfTable> tableList) {
        return tableList.stream()
                .map(ConvertArticleBorrowed::toDomain)
                .collect(Collectors.toList());
    }

    public static BunchOfTable toDb(ArticlesBorrowed domain) {
        return BunchOfTable.builder()
                .borrowedArticlesTbl(
                        BorrowedArticlesTbl.builder()
                                .borrowedArticleId(domain.getId().get())
                                .articleId(domain.getId().get())
                                .customerId(domain.getCustomer().getId().get())
                                .articleId(domain.getArticle().getId().get())
                                .occurredAt(domain.getBorrowedAt().get())
                                .dueAt(domain.getDueAt().get())
                                .build())
                .customersTbl(ConvertCustomer.toDb(domain.getCustomer()))
                .articleBunchOfTable(ConvertArticle.toDb(domain.getArticle()))
                .build();
    }

    public static List<BunchOfTable> toDb(List<ArticlesBorrowed> domainList) {
        return domainList.stream()
                .map(ConvertArticleBorrowed::toDb)
                .collect(Collectors.toList());
    }

    @RequiredArgsConstructor
    @Builder
    @Getter
    @EqualsAndHashCode(of = "borrowedArticlesTbl")
    public static class BunchOfTable {
        private final BorrowedArticlesTbl borrowedArticlesTbl;
        private final CustomersTbl customersTbl;
        private final ConvertArticle.BunchOfTable articleBunchOfTable;
    }
}
