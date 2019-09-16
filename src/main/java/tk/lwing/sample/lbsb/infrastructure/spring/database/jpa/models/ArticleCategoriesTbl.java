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

/*
create table article_categories (
  articleCategoryId SERIAL
  articleId VARHAR not null
  category INTEGER default 0 not null
);
*/

@Entity
@Table(name = "article_categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleCategoriesTbl {

    @Id
    @Column(name = "article_category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int articleCategoryId;
    @Column(name = "article_id")
    private String articleId;
    @Column(name = "category")
    private int category;

}
