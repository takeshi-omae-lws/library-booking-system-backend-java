package tk.lwing.sample.lbsb.infrastructure.rest.spring.resources;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BorrowArticlesBody {
    private CustomerBody customerBody;
    private List<ArticleBody> articleBodyList;

    BorrowArticlesBody(CustomerBody customerBody, List<ArticleBody> articleBodyList) {
        this.customerBody = customerBody;
        this.articleBodyList = articleBodyList;
    }

    public static BorrowArticlesBodyBuilder builder() {
        return new BorrowArticlesBodyBuilder();
    }

    public static class BorrowArticlesBodyBuilder {
        private CustomerBody customerBody;
        private List<ArticleBody> articleBodyList;

        BorrowArticlesBodyBuilder() {
        }

        public BorrowArticlesBodyBuilder customerBody(CustomerBody customerBody) {
            this.customerBody = customerBody;
            return this;
        }

        public BorrowArticlesBodyBuilder articleBodyList(List<ArticleBody> articleBodyList) {
            this.articleBodyList = articleBodyList;
            return this;
        }

        public BorrowArticlesBody build() {
            return new BorrowArticlesBody(customerBody, articleBodyList);
        }

        public String toString() {
            return "BorrowArticlesBody.BorrowArticlesBodyBuilder(customerBody=" + this.customerBody + ", articleBodyList=" + this.articleBodyList + ")";
        }
    }
}
