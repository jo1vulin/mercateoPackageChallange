package com.mercateo.packager.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PackageItemDetailTest {


    @Test
    public void equalsVerifier() throws Exception {

        PackageItemDetail packageItemDetail1 = new PackageItemDetail(20,new Integer[0], new Integer[0]);
        PackageItemDetail packageItemDetail2 = new PackageItemDetail(20,new Integer[0], new Integer[0]);
        assertThat(packageItemDetail1).isEqualTo(packageItemDetail2);

        packageItemDetail2.setPackageWeightCapacity(21);
        assertThat(packageItemDetail1).isNotEqualTo(packageItemDetail2);

        packageItemDetail2.setPackageWeightCapacity(null);
        assertThat(packageItemDetail1).isNotEqualTo(packageItemDetail2);
    }
}
