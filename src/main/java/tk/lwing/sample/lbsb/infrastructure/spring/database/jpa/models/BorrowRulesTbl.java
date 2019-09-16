package tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "borrow_rules")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BorrowRulesTbl {
    @Id
    @Column(name = "borrow_rule_id")
    private String borrowRuleId;
    @Column(name = "max_amount")
    private int maxAmount;
    @Column(name = "start_at")
    private LocalDateTime startAt;
}
