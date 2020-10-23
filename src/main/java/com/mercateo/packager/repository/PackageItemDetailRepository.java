package com.mercateo.packager.repository;

import com.mercateo.packager.domain.PackageItemDetail;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Repository for PackageItemDetail
 *
 * @author jovan.vulin
 */
@Repository
@Log4j2
public class PackageItemDetailRepository {

    @Value( "${package.maximum.weight}" )
    private Integer packageMaximumWeight;

    @Value( "${item.maximum.weight}" )
    private Integer itemMaximumWeight;

    @Value( "${item.maximum.value}" )
    private Integer itemMaximumValue;

    @Value( "${item.maximum.size}" )
    private Integer itemMaximumSize;

    /**
     * Return a {@link List} of {@link PackageItemDetail}
     *
     * @return List<PackageItemDetail>
     * @throws IOException in case of wrong path for input file
     */
    public List<PackageItemDetail> findAll(String inputFilePath) throws IOException {
        log.debug("Find all PackageItemDetails");
        List<PackageItemDetail> packageItemDetails = new ArrayList<>();

        Files.readAllLines(Path.of(inputFilePath))
                .stream()
                .filter(inputLine -> !StringUtils.isEmpty(inputLine))
                .map(String::trim)
                .forEach(inputLine -> {
                    List<String> errors = new ArrayList<>();
                    List<String> packageWeightCapacityAndItems = Arrays.asList(inputLine.split("\\s*:\\s*"));
                    int packageWeightCapacity = Integer.parseInt(packageWeightCapacityAndItems.get(0));

                    if (packageWeightCapacity > packageMaximumWeight) {
                        errors.add(String.format("Package with weight: %s exceeds package maximum weight: %s", packageWeightCapacity, packageMaximumWeight));
                    }

                    List<Integer> values = new ArrayList<>();
                    List<Integer> weights = new ArrayList<>();
                    Arrays.asList(packageWeightCapacityAndItems.get(1).replaceAll("[€]*[(]*[)]*", "").split("\\s+")).forEach(
                            item -> {
                                String[] itemProperties = item.split(",");
                                BigDecimal weight = new BigDecimal(itemProperties[1]);
                                if (weight.compareTo(BigDecimal.valueOf(itemMaximumWeight)) != 1) {
                                    weights.add(weight.movePointRight(2).intValue());
                                } else {
                                    errors.add(String.format("Item with weight: %s exceeds maximum weight: %s", weight, itemMaximumWeight));
                                }

                                int value = Integer.parseInt(itemProperties[2]);
                                if (value <= itemMaximumValue) {
                                    values.add(value);
                                } else {
                                    errors.add(String.format("Item with value: %s exceeds maximum value: %s", value, itemMaximumValue));
                                }

                            }
                        );

                        if (values.size() + 1 > itemMaximumSize) {
                            errors.add(String.format("Input line with value size: %s exceeds maximum value size: %s", values.size() + 1, itemMaximumSize));
                        }

                        if (errors.size() == 0) {
                            packageItemDetails.add(new PackageItemDetail(
                                    packageWeightCapacity * 100,
                                    weights.toArray(new Integer[0]),
                                    values.toArray(new Integer[0])));
                        } else {
                            log.error(errors);
                        }

                });

        log.debug("Return a list of results from parsed file: {}", packageItemDetails);
        return packageItemDetails;
    }
}
