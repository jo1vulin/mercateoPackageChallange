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
    public void resolve_different_values_and_weights() {
        List<Integer> result = dynamicProgramingPackageOptimizerService.resolve(50, new Integer[]{10, 20, 30}, new Integer[]{60, 100, 120}, 3);
        assertThat(result.containsAll(Arrays.asList(2,3))).isTrue();
    }

    @Test
    public void resolve_same_values_different_weight_choose_less_weight() {
        List<Integer> result = dynamicProgramingPackageOptimizerService.resolve(60, new Integer[]{10, 20, 30, 25}, new Integer[]{60, 100, 120, 120}, 4);
        assertThat(result.containsAll(Arrays.asList(1,2,4))).isTrue();
    }

    @Test
    public void resolve_no_solution() {
        List<Integer> result = dynamicProgramingPackageOptimizerService.resolve(1, new Integer[]{10}, new Integer[]{60}, 1);
        assertThat(result.isEmpty()).isTrue();
    }
}
