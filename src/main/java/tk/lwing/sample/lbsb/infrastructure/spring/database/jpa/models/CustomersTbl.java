package tk.lwing.sample.lbsb.infrastructure.spring.database.jpa.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data(staticConstructor = "newInstance")
public class CustomersTbl {

    @Id
    @Column(name = "customer_id")
    private String customerId;

}
