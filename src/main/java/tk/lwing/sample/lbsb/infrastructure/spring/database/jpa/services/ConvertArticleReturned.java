package tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.services;

import tk.lwing.sample.lbsb.domain.events.ArticlesReturned;
import tk.lwing.sample.lbsb.domain.valueobjects.ArticlesBorrowedID;
import tk.lwing.sample.lbsb.domain.valueobjects.OccurredAt;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models.ReturnedArticlesTbl;

import java.util.List;
import java.util.stream.Collectors;

public class ConvertArticleReturned {

    public static ArticlesReturned toDomain(ReturnedArticlesTbl table) {

        return new ArticlesReturned(
                new ArticlesBorrowedID(table.getBorrowedArticleId()),
                new OccurredAt(table.getOccurredAt()));
    }

    public static List<ArticlesReturned> toDomain(List<ReturnedArticlesTbl> tableList) {
        return tableList.stream()
                .map(ConvertArticleReturned::toDomain)
                .collect(Collectors.toList());
    }

    public static ReturnedArticlesTbl toDb(ArticlesReturned domain) {
        return ReturnedArticlesTbl.builder()
                .borrowedArticleId(domain.getId().get())
                .occurredAt(domain.getReturnedAt().get())
                .build();
    }

    public static List<ReturnedArticlesTbl> toDb(List<ArticlesReturned> domainList) {
        return domainList.stream()
                .map(ConvertArticleReturned::toDb)
                .collect(Collectors.toList());
    }

}
