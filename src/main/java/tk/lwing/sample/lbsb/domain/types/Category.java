package tk.lwing.sample.lbsb.domain.types;

public enum Category {
    NEW(0),
    CLASSIC(1),
    STANDARD(2),
    ;

    private final int code;

    private Category(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static Category getCategory(int code) {
        Category[] categories = Category.values();
        for (Category category : categories) {
            if (category.getCode() == code) {
                return category;
            }
        }
        return null;
    }
}
