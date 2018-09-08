package com.wallethub;

import org.apache.commons.cli.*;
import builder.OptionsFactory;

public class JavaMysqlTest {

	public static void main(String[] args) {

		Options options = new OptionsFactory().withDuration().withStartDate().withThreShold().getOptions();

		CommandLineParser parser = new DefaultParser();
		HelpFormatter formatter = new HelpFormatter();
		CommandLine cmd;

		try {
			cmd = parser.parse(options, args);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			formatter.printHelp("utility-name", options);

			System.exit(1);
		}

	}

}

