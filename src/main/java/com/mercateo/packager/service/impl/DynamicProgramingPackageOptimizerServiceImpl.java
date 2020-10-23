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
     *  Complexity of this approach is O(N*W) - where N is totalNumberOfItems and W is the maxWeight
     *  Space taken is the same as complexity - since we are creating a 2D array
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
                // if the weight of the item is less then w (column of our 2d table)
                else if (weights[i - 1] <= w) {
                    // then we will choose the greater value from (value of the item with index i in our row +
                    // value of the item with coordinates of i and remaining weight) or the value we can get if we
                    // don't choose the item with index i
                    K[i][w] = Math.max(values[i - 1] + K[i - 1][w - weights[i - 1]], K[i - 1][w]);
                }
                else
                    // if weight is greater then the w (weight in the column) we will set the value we got for our previous row
                    K[i][w] = K[i - 1][w];
            }
        }

        int res = K[totalNumberOfItems][maxWeight];

        w = maxWeight;
        for (i = totalNumberOfItems; i > 0 && res > 0; i--) {

            // In case that result is found
            if (res != K[i - 1][w]) {
                int itemValue = values[i-1];
                int itemWeight = weights[i-1];
                int x, itemToInclude = 0;
                // we will check the values to see if there is an item with the same value
                // in order to take the one with less weight
                // Complexity is O(2^n) where n is the number of items included in the package
                for (x = 1; x <= totalNumberOfItems; x++) {
                    if (itemValue == values[x - 1]) {
                        itemToInclude = itemWeight >= weights[x - 1] && !result.contains(x) ? x : i;
                    }
                }
                result.add(itemToInclude);
                res = res - values[itemToInclude - 1];
                w = w - weights[itemToInclude - 1];
            }
        }

        return result;
    }
}
