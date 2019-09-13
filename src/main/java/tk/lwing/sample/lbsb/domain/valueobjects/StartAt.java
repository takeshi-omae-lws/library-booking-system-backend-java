package tk.lwing.sample.lbsb.domain.valueobjects;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString(includeFieldNames = false)
public class StartAt implements Comparable<StartAt> {

    private final LocalDateTime startAt;

    public LocalDateTime get() {
        return this.startAt;
    }

    @Override
    public int compareTo(StartAt startAt) {
        return this.startAt.compareTo(startAt.get());
    }
}
