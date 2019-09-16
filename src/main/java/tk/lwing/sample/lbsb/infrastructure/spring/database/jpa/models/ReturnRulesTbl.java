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
@Table(name = "return_rules")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ReturnRulesTbl {

    @Id
    @Column(name = "return_rule_id")
    private String returnRuleId;
    @Column(name = "period")
    private int period;
    @Column(name = "start_at")
    private LocalDateTime startAt;
}
