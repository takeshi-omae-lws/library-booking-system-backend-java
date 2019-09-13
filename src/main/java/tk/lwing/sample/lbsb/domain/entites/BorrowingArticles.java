package tk.lwing.sample.lbsb.domain.entites;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import tk.lwing.sample.lbsb.domain.valueobjects.CustomerID;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class BorrowingArticles {
    @NotNull
    private final CustomerID id;
    private List<Article> articles;

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
