package tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "borrowing_articles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowingArticlesTbl {

    @Id
    @Column(name = "borrowing_articles_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int borrowingArticlesId;
    @Column(name = "customer_id")
    private String customerId;
    @Column(name = "article_id")
    private String articleId;
}
