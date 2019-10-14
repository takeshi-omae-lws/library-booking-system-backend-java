package com.exapmle.scratch;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Sanction {

    private List<SanctionResponse> sanctionResponses;

    public Sanction(List<SanctionResponse> sanctionResponses) {
        this.sanctionResponses = sanctionResponses;
    }

    public boolean isOnSanctionList(String name, LocalDate birthDate) {

        if (this.sanctionResponses.isEmpty()) {
            return false;
        }

        boolean listHasNoBirthDate = sanctionResponses.stream()
                .map(SanctionResponse::getBirthDate)
                .anyMatch(Optional::isEmpty);

        if (!listHasNoBirthDate) {
            return this.sanctionResponses.stream()
                    .anyMatch(item -> item.getBirthDate().filter(bd -> bd.equals(birthDate)).isPresent());
        }

        return true;
    }


}
