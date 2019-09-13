package tk.lwing.sample.lbsb.domain.valueobjects;

import lombok.AllArgsConstructor;
import tk.lwing.sample.lbsb.domain.types.Category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class Categories {

    private List<Category> categories;

    public Categories add(Category category) {
        List<Category> _categories = new ArrayList<>(this.categories);
        _categories.add(category);
        return new Categories(_categories);
    }

    public List<Category> asList() {
        return Collections.unmodifiableList(this.categories);
    }
}
