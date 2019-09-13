package tk.lwing.sample.lbsb.domain.entites;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import tk.lwing.sample.lbsb.domain.valueobjects.CustomerID;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@ToString
public class Customer {

    @NotNull
    private final CustomerID id;

}
