package tk.lwing.sample.lbsb.domain.valueobjects;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString(includeFieldNames = false)
public class Identifier {

    private final String id;

    public String get() {
        return this.id;
    }

    public static Identifier generate() {
        return new Identifier(
                UUID.randomUUID()
                        .toString()
                        .replace("-", "")
                        .toUpperCase());
    }

    public static String generateId() {
        return UUID.randomUUID()
                .toString()
                .replace("-", "")
                .toUpperCase();
    }

}
