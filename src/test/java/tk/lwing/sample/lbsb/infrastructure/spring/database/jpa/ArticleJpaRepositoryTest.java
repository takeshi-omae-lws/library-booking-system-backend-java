package tk.lwing.sample.lbsb.infrastructure.spring.database.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.lwing.sample.lbsb.domain.entites.Article;
import tk.lwing.sample.lbsb.domain.types.ArticleStatus;
import tk.lwing.sample.lbsb.domain.types.Category;
import tk.lwing.sample.lbsb.domain.valueobjects.ArticleID;
import tk.lwing.sample.lbsb.domain.valueobjects.Identifier;
import tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.repositories.ArticleJpaRepository;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleJpaRepositoryTest {

    private static final Logger logger =
            LoggerFactory.getLogger(
                    new Object() {
                    }.getClass().getEnclosingClass());

    @Autowired
    private ArticleJpaRepository articleJpaRepository;

    @Test
    public void doTestSave() {

        List<Category> categories = new ArrayList<>();
        categories.add(Category.NEW);
        Article article = new Article(
                new ArticleID(Identifier.generateId()),
                categories,
                ArticleStatus.AVAILABLE);

        Article savedArticle = this.articleJpaRepository.save(article);

        logger.info("Saved Article: " + savedArticle.toString());
    }
}
