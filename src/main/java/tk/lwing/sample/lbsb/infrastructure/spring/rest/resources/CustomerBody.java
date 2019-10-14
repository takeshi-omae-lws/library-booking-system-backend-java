package tk.lwing.sample.lbsb.infrastructure.spring.rest.resources;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerBody {

    private String id;

    CustomerBody(String id) {
        this.id = id;
    }

    public static CustomerBodyBuilder builder() {
        return new CustomerBodyBuilder();
    }

    public static class CustomerBodyBuilder {
        private String id;

        CustomerBodyBuilder() {
        }

        public CustomerBodyBuilder id(String id) {
            this.id = id;
            return this;
        }

        public CustomerBody build() {
            return new CustomerBody(id);
        }

        public String toString() {
            return "CustomerBody.CustomerBodyBuilder(id=" + this.id + ")";
        }
    }
}
