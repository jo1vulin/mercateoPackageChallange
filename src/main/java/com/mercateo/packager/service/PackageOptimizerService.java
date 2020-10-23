package com.mercateo.packager.service;

import java.util.List;

/**
 * Service interface for resolving package optimization problem
 *
 * @author jovan.vulin
 */
public interface PackageOptimizerService {

    List<Integer> resolve(Integer maxWeight, Integer[] weights, Integer[] values, Integer totalNumberOfItems);
}
