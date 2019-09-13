package tk.lwing.sample.lbsb.domain.valueobjects;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString(includeFieldNames = false)
public class OccurredAt implements Comparable<OccurredAt> {
    private final LocalDateTime occurredAt;

    public LocalDateTime get() {
        return this.occurredAt;
    }

    @Override
    public int compareTo(OccurredAt occurredAt) {
        return this.occurredAt.compareTo(occurredAt.get());
    }
}
