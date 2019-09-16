package tk.lwing.sample.lbsb.infrastructure.rest.spring;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tk.lwing.sample.lbsb.domain.entites.Article;
import tk.lwing.sample.lbsb.domain.entites.BorrowingArticles;
import tk.lwing.sample.lbsb.domain.entites.Customer;
import tk.lwing.sample.lbsb.infrastructure.rest.spring.resources.ArticleBody;
import tk.lwing.sample.lbsb.infrastructure.rest.spring.resources.BorrowArticlesBody;
import tk.lwing.sample.lbsb.infrastructure.rest.spring.resources.ConvertArticle;
import tk.lwing.sample.lbsb.infrastructure.rest.spring.resources.ConvertBorrowingArticles;
import tk.lwing.sample.lbsb.infrastructure.rest.spring.resources.ConvertCustomer;
import tk.lwing.sample.lbsb.usecase.CustomerUseCase;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/return")
public class ReturnController {

    @NotNull
    private final CustomerUseCase customerUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BorrowArticlesBody save(@RequestBody @Validated BorrowArticlesBody borrowArticles) {

        Customer customer =
                ConvertCustomer.toDomain(borrowArticles.getCustomerBody());
        List<Article> articles = new ArrayList<>();
        for (ArticleBody articleBody : borrowArticles.getArticleBodyList()) {
            articles.add(ConvertArticle.toDomain(articleBody));
        }

        BorrowingArticles borrowingArticles =
                this.customerUseCase.returnArticles(customer, articles);

        return ConvertBorrowingArticles.toBody(borrowingArticles);
    }
}
