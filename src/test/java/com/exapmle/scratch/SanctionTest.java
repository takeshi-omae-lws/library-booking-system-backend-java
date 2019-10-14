package com.exapmle.scratch;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SanctionTest {
    List<SanctionResponse> sanctionResponses000 = new ArrayList<>();
    List<SanctionResponse> sanctionResponses001 = new ArrayList<>();
    List<SanctionResponse> sanctionResponses002 = new ArrayList<>();

    @Before
    public void setUp() {

        this.sanctionResponses000.add(
                new SanctionResponse("Taro", Optional.empty()));
        this.sanctionResponses000.add(
                new SanctionResponse("Taro", Optional.empty()));
        this.sanctionResponses000.add(
                new SanctionResponse("Taro", Optional.empty()));


        this.sanctionResponses001.add(
                new SanctionResponse("Taro",
                        Optional.of(LocalDate.of(1981, 1, 1))));
        this.sanctionResponses001.add(
                new SanctionResponse("Taro",
                        Optional.of(LocalDate.of(1982, 2, 2))));
        this.sanctionResponses001.add(
                new SanctionResponse("Taro",
                        Optional.of(LocalDate.of(1983, 3, 3))));


        this.sanctionResponses002.add(
                new SanctionResponse("Taro",
                        Optional.of(LocalDate.of(1981, 1, 1))));
        this.sanctionResponses002.add(
                new SanctionResponse("Taro", Optional.empty()));
        this.sanctionResponses002.add(
                new SanctionResponse("Taro",
                        Optional.of(LocalDate.of(1983, 3, 3))));
    }

    @Test
    public void isOnSanctionListTest() {

        Sanction sanction000 = new Sanction(this.sanctionResponses000);

        boolean result000 = sanction000.isOnSanctionList("Taro",
                LocalDate.of(1981, 1, 1));
        System.out.println(result000);

        boolean result000a = sanction000.isOnSanctionList("Taro",
                LocalDate.of(1981, 1, 2));
        System.out.println(result000a);


        Sanction sanction001 = new Sanction(this.sanctionResponses001);

        boolean result001 = sanction001.isOnSanctionList("Taro",
                LocalDate.of(1981, 1, 1));
        System.out.println(result001);

        boolean result001a = sanction001.isOnSanctionList("Taro",
                LocalDate.of(1981, 1, 2));
        System.out.println(result001a);


        Sanction sanction002 = new Sanction(this.sanctionResponses002);

        boolean result002 = sanction002.isOnSanctionList("Taro",
                LocalDate.of(1981, 10, 1));
        System.out.println(result002);

    }
}
