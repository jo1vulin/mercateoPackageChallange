package com.mercateo.packager;

import com.mercateo.packager.repository.PackageItemDetailRepository;
import com.mercateo.packager.service.PackageOptimizerService;
import com.mercateo.packager.service.PackageResultPresentationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;


@SpringBootApplication
@Log4j2
public class PackagerApplication implements CommandLineRunner {

	@Autowired
	private PackageItemDetailRepository packageItemDetailRepository;
	@Autowired
	private PackageOptimizerService packageOptimizerService;
	@Autowired
	private PackageResultPresentationService packageResultPresentationService;

	public static void main(String[] args) throws IOException, URISyntaxException {
		SpringApplication.run(PackagerApplication.class, args);
	}

	public void run(final String... args) throws URISyntaxException, IOException {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the path to the sample file: ");
		String filePath = scanner.next();

		try {
			packageItemDetailRepository.findAll(filePath).forEach(packageItemDetail -> {

				List<Integer> result = packageOptimizerService.resolve(
						packageItemDetail.getPackageWeightCapacity(),
						packageItemDetail.getItemWeights(),
						packageItemDetail.getItemValues(),
						packageItemDetail.getItemValues().length);

				packageResultPresentationService.presentResults(result);

			});
		} catch (IOException exception) {
			log.error("Invalid path to the source file. Check your path!");
		}

	}
}


