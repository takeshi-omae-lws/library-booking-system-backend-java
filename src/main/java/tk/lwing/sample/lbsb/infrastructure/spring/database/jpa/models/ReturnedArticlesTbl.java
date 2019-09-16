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
import java.time.LocalDateTime;

@Entity
@Table(name = "returned_articles")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ReturnedArticlesTbl {

    @Id
    @Column(name = "returned_article_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int returnedArticleId;
    @Column(name = "borrowed_article_id")
    private String borrowedArticleId;
    @Column(name = "occurred_at")
    private LocalDateTime occurredAt;
}
