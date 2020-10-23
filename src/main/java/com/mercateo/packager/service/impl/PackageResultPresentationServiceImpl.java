package com.mercateo.packager.service.impl;

import com.mercateo.packager.service.PackageResultPresentationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Service for managing result user output
 *
 * @author jovan.vulin
 */
@Service
@Log4j2
public class PackageResultPresentationServiceImpl implements PackageResultPresentationService {

    @Override
    public void presentResults(List<Integer> results) {
        if (results.isEmpty()) {
            log.info("-");
        } else {
            log.info(results.stream().sorted().map(Objects::toString).collect(Collectors.joining( "," )));
        }
    }
}
