package tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.lwing.sample.lbsb.domain.entites.Article;
import tk.lwing.sample.lbsb.domain.types.ArticleStatus;
import tk.lwing.sample.lbsb.domain.types.Category;
import tk.lwing.sample.lbsb.domain.valueobjects.ArticleID;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models.ArticleCategoriesTbl;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models.ArticlesTbl;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConvertArticleTest {

    private static final Logger logger =
            LoggerFactory.getLogger(
                    new Object() {
                    }.getClass().getEnclosingClass());

    private ArticlesTbl articlesTbl_A001 = ArticlesTbl.builder()
            .articleId("A001").status(0).build();
    private ArticlesTbl articlesTbl_A002 = ArticlesTbl.builder()
            .articleId("A002").status(1).build();
    private ArticlesTbl articlesTbl_A003 = ArticlesTbl.builder()
            .articleId("A003").status(0).build();
    private ArticlesTbl articlesTbl_A004 = ArticlesTbl.builder()
            .articleId("A004").status(0).build();
    private List<ArticleCategoriesTbl> articleCategoriesTblList_A001 =
            new ArrayList<>();
    private List<ArticleCategoriesTbl> articleCategoriesTblList_A002 =
            new ArrayList<>();
    private List<ArticleCategoriesTbl> articleCategoriesTblList_A003 =
            new ArrayList<>();
    private List<ArticleCategoriesTbl> articleCategoriesTblList_A004 =
            new ArrayList<>();

    private ConvertArticle.BunchOfTable bunchOfTable_A001;
    private ConvertArticle.BunchOfTable bunchOfTable_A002;
    private ConvertArticle.BunchOfTable bunchOfTable_A003;
    private ConvertArticle.BunchOfTable bunchOfTable_A004;

    private Article article_A001;
    private Article article_A002;
    private Article article_A003;
    private Article article_A004;
    private List<Category> categories_A001 = new ArrayList<>();
    private List<Category> categories_A002 = new ArrayList<>();
    private List<Category> categories_A003 = new ArrayList<>();
    private List<Category> categories_A004 = new ArrayList<>();

    @Before
    public void setUp() {
        this.articleCategoriesTblList_A001
                .add(ArticleCategoriesTbl.builder()
                        .articleId("A001").category(0).build());

        this.articleCategoriesTblList_A002
                .add(ArticleCategoriesTbl.builder()
                        .articleId("A002").category(1).build());

        this.articleCategoriesTblList_A003
                .add(ArticleCategoriesTbl.builder()
                        .articleId("A003").category(0).build());
        this.articleCategoriesTblList_A003
                .add(ArticleCategoriesTbl.builder()
                        .articleId("A003").category(2).build());

        this.articleCategoriesTblList_A004
                .add(ArticleCategoriesTbl.builder()
                        .articleId("A004").category(0).build());
        this.articleCategoriesTblList_A004
                .add(ArticleCategoriesTbl.builder()
                        .articleId("A004").category(1).build());
        this.articleCategoriesTblList_A004
                .add(ArticleCategoriesTbl.builder()
                        .articleId("A004").category(2).build());

        this.bunchOfTable_A001 =
                ConvertArticle.BunchOfTable.builder()
                        .articlesTbl(this.articlesTbl_A001)
                        .articleCategoriesTblList(this.articleCategoriesTblList_A001)
                        .build();
        this.bunchOfTable_A002 =
                ConvertArticle.BunchOfTable.builder()
                        .articlesTbl(this.articlesTbl_A002)
                        .articleCategoriesTblList(this.articleCategoriesTblList_A002)
                        .build();
        this.bunchOfTable_A003 =
                ConvertArticle.BunchOfTable.builder()
                        .articlesTbl(this.articlesTbl_A003)
                        .articleCategoriesTblList(this.articleCategoriesTblList_A003)
                        .build();
        this.bunchOfTable_A004 =
                ConvertArticle.BunchOfTable.builder()
                        .articlesTbl(this.articlesTbl_A004)
                        .articleCategoriesTblList(this.articleCategoriesTblList_A004)
                        .build();

        this.categories_A001.add(Category.NEW);
        this.article_A001 = new Article(new ArticleID("A001"),
                this.categories_A001, ArticleStatus.AVAILABLE);

        this.categories_A002.add(Category.CLASSIC);
        this.article_A002 = new Article(new ArticleID("A002"),
                this.categories_A002, ArticleStatus.UNAVAILABLE);

        this.categories_A003.add(Category.NEW);
        this.categories_A003.add(Category.STANDARD);
        this.article_A003 = new Article(new ArticleID("A003"),
                this.categories_A003, ArticleStatus.AVAILABLE);

        this.categories_A004.add(Category.NEW);
        this.categories_A004.add(Category.CLASSIC);
        this.categories_A004.add(Category.STANDARD);
        this.article_A004 = new Article(new ArticleID("A004"),
                this.categories_A004, ArticleStatus.AVAILABLE);
    }

    @Test
    public void toDomainTest() {

        Article expect = this.article_A001;
        Article actual = ConvertArticle.toDomain(this.bunchOfTable_A001);
        logger.info("ACTUAL:" + actual.toString());
        assertThat(actual).isEqualTo(expect);

        expect = this.article_A002;
        actual = ConvertArticle.toDomain(this.bunchOfTable_A002);
        logger.info("ACTUAL:" + actual.toString());
        assertThat(actual).isEqualTo(expect);

        expect = this.article_A003;
        actual = ConvertArticle.toDomain(this.bunchOfTable_A003);
        logger.info("ACTUAL:" + actual.toString());
        assertThat(actual).isEqualTo(expect);

        expect = this.article_A004;
        actual = ConvertArticle.toDomain(this.bunchOfTable_A004);
        logger.info("ACTUAL:" + actual.toString());
        assertThat(actual).isEqualTo(expect);

        List<ConvertArticle.BunchOfTable> bunchOfTableList = new ArrayList<>();
        bunchOfTableList.add(this.bunchOfTable_A001);
        bunchOfTableList.add(this.bunchOfTable_A002);
        bunchOfTableList.add(this.bunchOfTable_A003);
        bunchOfTableList.add(this.bunchOfTable_A004);

        List<Article> listExpect = new ArrayList<>();
        listExpect.add(this.article_A001);
        listExpect.add(this.article_A002);
        listExpect.add(this.article_A003);
        listExpect.add(this.article_A004);
        listExpect.sort((a, b) -> a.getId().get().compareTo(b.getId().get()));

        List<Article> listActual = ConvertArticle.toDomain(bunchOfTableList);
        listActual.sort((a, b) -> a.getId().get().compareTo(b.getId().get()));
        logger.info("ACTUAL:" + listActual.toString());
        assertThat(listActual).isEqualTo(listExpect);
    }

    @Test
    public void toDbTest() {
        ConvertArticle.BunchOfTable expect = this.bunchOfTable_A001;
        ConvertArticle.BunchOfTable actual = ConvertArticle.toDb(this.article_A001);
        logger.info("ACTUAL:" + actual.toString());
        assertThat(actual.getArticlesTbl()).describedAs("A001")
                .isEqualTo(expect.getArticlesTbl());
        assertThat(actual.getArticleCategoriesTblList()).describedAs("A001")
                .isEqualTo(expect.getArticleCategoriesTblList());

        expect = this.bunchOfTable_A002;
        actual = ConvertArticle.toDb(this.article_A002);
        logger.info("ACTUAL:" + actual.toString());
        assertThat(actual.getArticlesTbl()).describedAs("A002")
                .isEqualTo(expect.getArticlesTbl());
        assertThat(actual.getArticleCategoriesTblList()).describedAs("A002")
                .isEqualTo(expect.getArticleCategoriesTblList());

        expect = this.bunchOfTable_A003;
        actual = ConvertArticle.toDb(this.article_A003);
        logger.info("ACTUAL:" + actual.toString());
        assertThat(actual.getArticlesTbl()).describedAs("A003")
                .isEqualTo(expect.getArticlesTbl());
        assertThat(actual.getArticleCategoriesTblList()).describedAs("A003")
                .isEqualTo(expect.getArticleCategoriesTblList());

        expect = this.bunchOfTable_A004;
        actual = ConvertArticle.toDb(this.article_A004);
        logger.info("ACTUAL:" + actual.toString());
        assertThat(actual.getArticlesTbl()).describedAs("A004")
                .isEqualTo(expect.getArticlesTbl());
        assertThat(actual.getArticleCategoriesTblList()).describedAs("A004")
                .isEqualTo(expect.getArticleCategoriesTblList());

        List<ConvertArticle.BunchOfTable> listExpect = new ArrayList<>();
        listExpect.add(this.bunchOfTable_A001);
        listExpect.add(this.bunchOfTable_A002);
        listExpect.add(this.bunchOfTable_A003);
        listExpect.add(this.bunchOfTable_A004);
        listExpect.sort((a, b) -> a.getArticlesTbl().getArticleId()
                .compareTo(b.getArticlesTbl().getArticleId()));

        List<Article> domainList = new ArrayList<>();
        domainList.add(this.article_A001);
        domainList.add(this.article_A002);
        domainList.add(this.article_A003);
        domainList.add(this.article_A004);
        List<ConvertArticle.BunchOfTable> listActual =
                ConvertArticle.toDb(domainList);
        listActual.sort((a, b) -> a.getArticlesTbl().getArticleId()
                .compareTo(b.getArticlesTbl().getArticleId()));
        logger.info("ACTUAL:" + listActual.toString());
        assertThat(listActual).describedAs("List")
                .isEqualTo(listExpect);
    }
}
