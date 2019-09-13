package tk.lwing.sample.lbsb.domain.valueobjects;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString(includeFieldNames = false)
public class DueAt implements Comparable<DueAt> {
    private final LocalDateTime dueAt;

    public LocalDateTime get() {
        return this.dueAt;
    }

    @Override
    public int compareTo(DueAt dueAt) {
        return this.dueAt.compareTo(dueAt.get());
    }
}
