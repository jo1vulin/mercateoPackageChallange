package com.mercateo.packager.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DynamicProgramingPackageOptimizerServiceImplTest {

    private DynamicProgramingPackageOptimizerServiceImpl dynamicProgramingPackageOptimizerService;

    @BeforeEach
    public void init() {
        dynamicProgramingPackageOptimizerService = new DynamicProgramingPackageOptimizerServiceImpl();
    }

    @Test
    public void resolve() {
        List<Integer> result = dynamicProgramingPackageOptimizerService.resolve(50, new Integer[]{10, 20, 30}, new Integer[]{60, 100, 120}, 3);
        assertThat(result.containsAll(Arrays.asList(2,3))).isTrue();
    }
}
