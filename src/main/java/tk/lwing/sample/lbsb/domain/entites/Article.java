package tk.lwing.sample.lbsb.domain.entites;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import tk.lwing.sample.lbsb.domain.types.ArticleStatus;
import tk.lwing.sample.lbsb.domain.types.Category;
import tk.lwing.sample.lbsb.domain.valueobjects.ArticleID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Article {

    @NotNull
    private final ArticleID id;
    @NotEmpty
    private List<Category> categories;
    @NotNull
    private ArticleStatus status;

    public boolean isAvailable() {
        return this.status.equals(ArticleStatus.AVAILABLE);
    }

    public void setStatus(ArticleStatus status) {
        this.status = status;
    }

}
