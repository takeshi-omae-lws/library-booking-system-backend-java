package tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "articles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticlesTbl {

    @Id
    @Column(name = "article_id")
    private String articleId;

    @Column(name = "status")
    private int status;

}
