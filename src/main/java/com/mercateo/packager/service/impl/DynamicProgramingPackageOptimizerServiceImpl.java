package com.mercateo.packager.service.impl;

import com.mercateo.packager.service.PackageOptimizerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Package optimizer service used for resolving 0-1 KnapSack problem using Dynamic programing approach
 *
 * @author jovan.vulin
 */
@Service
public class DynamicProgramingPackageOptimizerServiceImpl implements PackageOptimizerService {

    /**
     *  Method that uses Dynamic programing approach to resolve weight/value optimization problem for given parameters
     *
     * @param maxWeight represents the weight of the package that we need to fill with maximum value
     * @param weights represents the list of weights of all items we have to select from
     * @param values represents the list of values of all items we have to select from
     * @param totalNumberOfItems represents the total number of items we have to select from for given package
     *
     * @return List of integers representing the selected items for given input
     */
    public List<Integer> resolve(Integer maxWeight, Integer[] weights, Integer[] values, Integer totalNumberOfItems) {
        int i, w;
        int[][] K = new int[totalNumberOfItems + 1][maxWeight + 1];
        List<Integer> result = new ArrayList<>();

        for (i = 0; i <= totalNumberOfItems; i++) {
            for (w = 0; w <= maxWeight; w++) {
                if (i == 0 || w == 0)
                    K[i][w] = 0;
                else if (weights[i - 1] <= w)
                    K[i][w] = Math.max(values[i - 1] +
                            K[i - 1][w - weights[i - 1]], K[i - 1][w]);
                else
                    K[i][w] = K[i - 1][w];
            }
        }

        int res = K[totalNumberOfItems][maxWeight];

        w = maxWeight;
        for (i = totalNumberOfItems; i > 0 && res > 0; i--) {

            if (res != K[i - 1][w]) {
                result.add(i);
                res = res - values[i - 1];
                w = w - weights[i - 1];
            }
        }

        return result;
    }
}
