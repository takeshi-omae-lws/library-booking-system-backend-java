package tk.lwing.sample.lbsb.infrastructure.spring.rest.resources;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ArticleBody {

    private String id;
    private List<Integer> categories;
    private int status;

    ArticleBody(String id, List<Integer> categories, int status) {
        this.id = id;
        this.categories = categories;
        this.status = status;
    }

    public static ArticleBodyBuilder builder() {
        return new ArticleBodyBuilder();
    }

    public static class ArticleBodyBuilder {
        private String id;
        private List<Integer> categories;
        private int status;

        ArticleBodyBuilder() {
        }

        public ArticleBodyBuilder id(String id) {
            this.id = id;
            return this;
        }

        public ArticleBodyBuilder categories(List<Integer> categories) {
            this.categories = categories;
            return this;
        }

        public ArticleBodyBuilder status(int status) {
            this.status = status;
            return this;
        }

        public ArticleBody build() {
            return new ArticleBody(id, categories, status);
        }

        public String toString() {
            return "ArticleBody.ArticleBodyBuilder(id=" + this.id + ", categories=" + this.categories + ", status=" + this.status + ")";
        }
    }
}
