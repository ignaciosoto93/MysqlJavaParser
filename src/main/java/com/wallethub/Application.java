package com.wallethub;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.apache.commons.cli.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import builder.OptionsFactory;
import com.google.common.collect.Lists;
import com.google.common.net.InetAddresses;
import com.wallethub.domain.model.Access;
import com.wallethub.domain.model.service.AccesLogService;
import com.wallethub.domain.model.service.implementation.Duration;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	AccesLogService accesLogService;

	public static void main(String[] args) throws IOException, java.text.ParseException {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		Options options = new OptionsFactory().withDuration().withStartDate().withThreShold().getOptions();

		CommandLineParser parser = new DefaultParser();
		HelpFormatter formatter = new HelpFormatter();
		CommandLine cmd = null;
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

		try {
			cmd = parser.parse(options, strings);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			formatter.printHelp("utility-name", options);

			System.exit(1);
		}

		persistFile();
		String startDateString = cmd.getOptionValue("s");
		String durationString = cmd.getOptionValue("d");
		String threshold = cmd.getOptionValue("t");
		LocalDateTime startDate = LocalDateTime.parse(startDateString, dateFormatter);
		Duration duration = Duration.valueOf(durationString);
		List<Long> results = accesLogService.findIpByDateAndThreshold(startDate, Long.valueOf(threshold), duration);
		results.forEach(r -> System.out.println(InetAddresses.fromInteger(r.intValue()).getHostAddress()));

	}

	private void persistFile() throws IOException {
		FileReader input = null;
		try {
			input = new FileReader("./Java_MySQL_Test/access.log");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader bufRead = new BufferedReader(input);
		String myLine = null;

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

		List<Access> list = Lists.newArrayList();

		while ((myLine = bufRead.readLine()) != null) {
			String[] split = myLine.split("\\|");

			InetAddress IP = InetAddress.getByName(split[1]);
			Long integerAddress = getUnsignedInt(InetAddresses.coerceToInteger(IP));
			String desc = split[2];

			LocalDateTime dateTime = LocalDateTime.parse(split[0], dateFormatter);
			Access access = Access.builder().withIp(integerAddress).withDate(dateTime).withRequestInfo(desc)
					.withResponseStatus(split[3]).withExtraInfo(split[4]).build();
			list.add(access);

		}
		accesLogService.saveAccesLogList(list);
	}

	public static long getUnsignedInt(int x) {
		return x & 0x00000000ffffffffL;
	}
}

