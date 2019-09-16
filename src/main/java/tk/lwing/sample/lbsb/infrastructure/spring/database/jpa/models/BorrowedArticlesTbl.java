package tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "borrowed_articles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowedArticlesTbl {

    @Id
    @Column(name = "borrowed_article_id")
    private String borrowedArticleId;
    @Column(name = "customer_id")
    private String customerId;
    @Column(name = "article_id")
    private String articleId;
    @Column(name = "occurred_at")
    private LocalDateTime occurredAt;
    @Column(name = "due_at")
    private LocalDateTime dueAt;
}
