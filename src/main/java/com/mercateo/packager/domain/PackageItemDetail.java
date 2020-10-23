package com.mercateo.packager.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * POJO for representing one line of input file
 *
 * @author jovan.vulin
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PackageItemDetail {

    private Integer packageWeightCapacity;
    private Integer[] itemWeights;
    private Integer[] itemValues;

}
