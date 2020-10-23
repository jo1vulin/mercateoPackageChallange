package com.mercateo.packager.resource;

import com.mercateo.packager.domain.PackageItemDetail;
import com.mercateo.packager.repository.PackageItemDetailRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class PackageItemDetailRepositoryTest {

    private PackageItemDetailRepository packageItemDetailRepository;

    @BeforeEach()
    public void init() {
        packageItemDetailRepository = new PackageItemDetailRepository();
        ReflectionTestUtils.setField(packageItemDetailRepository, "packageMaximumWeight", 100);
        ReflectionTestUtils.setField(packageItemDetailRepository, "itemMaximumWeight", 100);
        ReflectionTestUtils.setField(packageItemDetailRepository, "itemMaximumValue", 100);
        ReflectionTestUtils.setField(packageItemDetailRepository, "itemMaximumSize", 15);
    }

    @Test
    public void findAll_no_errors() throws IOException, URISyntaxException {
        Integer maxCapacity =  8100;
        Integer[] values = {45, 98, 3, 76, 9, 48};
        Integer[] weights = {5338, 8862, 7848, 7230, 3018, 4634};

        List<PackageItemDetail> packageItemDetailList = packageItemDetailRepository.findAll(getFilePath("sampleInputValid.txt"));

        assertThat(packageItemDetailList.get(0).getPackageWeightCapacity()).isEqualTo(maxCapacity);
        Arrays.asList(packageItemDetailList.get(0).getItemValues()).containsAll(Arrays.asList(values));
        Arrays.asList(packageItemDetailList.get(0).getItemWeights()).containsAll(Arrays.asList(weights));
    }

    @Test
    public void findAll_errors() throws IOException, URISyntaxException {
        List<PackageItemDetail> packageItemDetailList = packageItemDetailRepository.findAll(getFilePath("sampleInputInvalid.txt"));

        assertThat(packageItemDetailList.size()).isEqualTo(0);
    }

    private String getFilePath(String fileName) throws URISyntaxException {
        URL res = getClass().getClassLoader().getResource(fileName);
        File file = Paths.get(res.toURI()).toFile();
        return file.getAbsolutePath();
    }
}
