package tk.lwing.sample.lbsb.domain.types;

public enum ArticleStatus {
    AVAILABLE(0),
    UNAVAILABLE(1),
    ;
    private final int code;

    private ArticleStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static ArticleStatus getStatus(int code) {
        ArticleStatus[] statuses = ArticleStatus.values();
        for (ArticleStatus status : statuses) {
            if (status.getCode() == code) {
                return status;
            }
        }
        return null;
    }
}
