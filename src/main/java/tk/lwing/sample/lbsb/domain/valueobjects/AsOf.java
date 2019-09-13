package tk.lwing.sample.lbsb.domain.valueobjects;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString(includeFieldNames = false)
public class AsOf implements Comparable<AsOf> {
    private final LocalDateTime asOf;

    public LocalDateTime get() {
        return this.asOf;
    }

    @Override
    public int compareTo(AsOf asOf) {
        return this.asOf.compareTo(asOf.get());
    }
}
