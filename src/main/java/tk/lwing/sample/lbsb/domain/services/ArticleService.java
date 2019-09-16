package tk.lwing.sample.lbsb.domain.services;

import lombok.RequiredArgsConstructor;
import tk.lwing.sample.lbsb.domain.entites.Article;
import tk.lwing.sample.lbsb.domain.repositories.ArticleRepository;
import tk.lwing.sample.lbsb.domain.types.ArticleStatus;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ArticleService {

    @NotNull
    private final ArticleRepository articleRepository;

    // change ArticlesTbl status
    public List<Article> updateArticleStatus(List<Article> articles,
                                             ArticleStatus status) {
        for (Article article : articles) {
            article.setStatus(status);
            this.articleRepository.updateArticleStatus(article);
        }
        List<Article> updatedArticleList = new ArrayList<>();
        for (Article article : articles) {
            updatedArticleList.add(this.articleRepository.findById(article.getId()));
        }
        return updatedArticleList;
    }
}
