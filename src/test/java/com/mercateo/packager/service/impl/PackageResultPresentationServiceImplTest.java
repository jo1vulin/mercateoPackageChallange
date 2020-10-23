package com.mercateo.packager.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class PackageResultPresentationServiceImplTest {

    private PackageResultPresentationServiceImpl packageResultPresentationServiceImpl;

    private static ByteArrayOutputStream outContent;
    private static Logger logger = null;
    private static PrintStream printStream;

    @BeforeEach
    public void init() {
        PackageResultPresentationServiceImplTest.outContent = new ByteArrayOutputStream();
        PackageResultPresentationServiceImplTest.printStream = new PrintStream(PackageResultPresentationServiceImplTest.outContent);
        System.setOut(PackageResultPresentationServiceImplTest.printStream);
        PackageResultPresentationServiceImplTest.logger = LogManager.getLogger(PackageResultPresentationServiceImplTest.class);
        packageResultPresentationServiceImpl = new PackageResultPresentationServiceImpl();
    }

    @Test
    public void presentResult_no_result_present() {
        packageResultPresentationServiceImpl.presentResults(new ArrayList<>());
        assertThat(PackageResultPresentationServiceImplTest.outContent.toString().contains("INFO"));
        assertThat(PackageResultPresentationServiceImplTest.outContent.toString().contains(": -"));
    }

    @Test
    public void presentResult_result_present() {
        packageResultPresentationServiceImpl.presentResults(Collections.singletonList(1));
        assertThat(PackageResultPresentationServiceImplTest.outContent.toString().contains("INFO"));
        assertThat(PackageResultPresentationServiceImplTest.outContent.toString().contains(": 1"));
    }

}
