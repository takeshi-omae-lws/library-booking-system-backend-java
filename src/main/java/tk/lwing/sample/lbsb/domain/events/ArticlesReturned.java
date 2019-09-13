package tk.lwing.sample.lbsb.domain.events;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import tk.lwing.sample.lbsb.domain.valueobjects.ArticlesBorrowedID;
import tk.lwing.sample.lbsb.domain.valueobjects.OccurredAt;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class ArticlesReturned {

    private final ArticlesBorrowedID id;
    private final OccurredAt returnedAt;
}
