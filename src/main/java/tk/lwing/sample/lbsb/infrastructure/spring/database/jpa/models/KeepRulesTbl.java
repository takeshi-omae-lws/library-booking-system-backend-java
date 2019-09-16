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
@Table(name = "keep_rules")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class KeepRulesTbl {

    @Id
    @Column(name = "keep_rule_id")
    private String keepRuleId;
    @Column(name = "max_amount")
    private int maxAmount;
    @Column(name = "max_new_amount")
    private int maxNewAmount;
    @Column(name = "start_at")
    private LocalDateTime startAt;
}
