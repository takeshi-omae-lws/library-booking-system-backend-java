package tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import tk.lwing.sample.lbsb.domain.entites.Article;
import tk.lwing.sample.lbsb.domain.repositories.ArticleRepository;
import tk.lwing.sample.lbsb.domain.types.ArticleStatus;
import tk.lwing.sample.lbsb.domain.valueobjects.ArticleID;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models.ArticleCategoriesTbl;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models.ArticlesTbl;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models.ConvertArticle;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ArticleJpaRepository implements ArticleRepository {

    @NotNull
    private final ArticlesTblRepository articlesTblRepository;
    @NotNull
    private final ArticleCategoriesTblRepository articleCategoriesTblRepository;

    @Override
    public Article save(Article article) {
        ConvertArticle.BunchOfTable bunchOfTable = ConvertArticle.toDb(article);
        return ConvertArticle.toDomain(ConvertArticle.BunchOfTable.builder()
                .articlesTbl(
                        this.articlesTblRepository.save(
                                bunchOfTable.getArticlesTbl()))
                .articleCategoriesTblList(
                        this.articleCategoriesTblRepository
                                .saveAll(bunchOfTable.getArticleCategoriesTblList()))
                .build());
    }

    private ConvertArticle.BunchOfTable generateBunchOfTable(
            ArticlesTbl articlesTb) {
        List<ArticleCategoriesTbl> articleCategoriesTblList =
                this.articleCategoriesTblRepository.findByArticleId(
                        articlesTb.getArticleId());
        return ConvertArticle.BunchOfTable.builder()
                .articlesTbl(articlesTb)
                .articleCategoriesTblList(articleCategoriesTblList)
                .build();
    }

    private List<ConvertArticle.BunchOfTable> generateBunchOfTableList(
            List<ArticlesTbl> articlesTblList) {
        List<String> articleIds = new ArrayList<>();
        Map<String, ArticlesTbl> articlesTblMap = new HashMap<>();
        // TODO using #stream() ???
        for (ArticlesTbl articlesTbl : articlesTblList) {
            articlesTblMap.put(articlesTbl.getArticleId(), articlesTbl);
            articleIds.add(articlesTbl.getArticleId());
        }
        List<ArticleCategoriesTbl> articleCategoriesTblList =
                this.articleCategoriesTblRepository.findByArticleIdIn(articleIds);

        List<ConvertArticle.BunchOfTable> bunchOfTableList = new ArrayList<>();
        // TODO using #stream() ???
        for (String articleId : articleIds) {
            List<ArticleCategoriesTbl> tblList = new ArrayList<>();
            for (ArticleCategoriesTbl articleCategoriesTbl : articleCategoriesTblList) {
                if (articleId.equals(articleCategoriesTbl.getArticleId())) {
                    tblList.add(articleCategoriesTbl);
                }
            }
            ConvertArticle.BunchOfTable bunchOfTable =
                    ConvertArticle.BunchOfTable.builder()
                            .articlesTbl(articlesTblMap.get(articleId))
                            .articleCategoriesTblList(tblList)
                            .build();
            bunchOfTableList.add(bunchOfTable);
        }
        return bunchOfTableList;
    }

    @Override
    public List<Article> findAll() {
        return ConvertArticle.toDomain(
                this.generateBunchOfTableList(
                        this.articlesTblRepository.findAll()));
    }

    @Override
    public List<Article> findAvailable() {
        List<ArticlesTbl> articlesTblList =
                this.articlesTblRepository.findByStatus(
                        ArticleStatus.AVAILABLE.getCode());
        return ConvertArticle.toDomain(
                this.generateBunchOfTableList(articlesTblList));
    }

    @Override
    public Article findById(ArticleID id) {

        ArticlesTbl articlesTbl =
                this.articlesTblRepository.findById(id.get()).orElse(null);

        return articlesTbl == null ? null :
                ConvertArticle.toDomain(
                        this.generateBunchOfTable(articlesTbl));
    }

    @Override
    public Article updateArticleStatus(Article article) {

        final String id = article.getId().get();
        ArticlesTbl articlesTbl =
                this.articlesTblRepository.findById(id).orElse(null);
        if (articlesTbl == null) {
            return null;
        }
        articlesTbl.setStatus(article.getStatus().getCode());
        ArticlesTbl updatedArticlesTbl =
                this.articlesTblRepository.save(articlesTbl);
        return ConvertArticle.toDomain(
                this.generateBunchOfTable(updatedArticlesTbl));
    }
}
