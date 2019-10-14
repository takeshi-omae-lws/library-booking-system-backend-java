package com.exapmle.scratch;

import java.time.LocalDate;
import java.util.Optional;

class SanctionResponse {
    private final String name;
    private Optional<LocalDate> birthDate;

    SanctionResponse(String name, Optional<LocalDate> birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getName() {
        return this.name;
    }

    public Optional<LocalDate> getBirthDate() {
        return this.birthDate;
    }
}
