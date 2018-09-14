package com.ef.Parser.builder;

import java.util.Date;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class OptionsFactory {

	private Options options;

	public OptionsFactory withStartDate() {
		options.addOption(
				Option.builder("s").longOpt("startDate").hasArg(true).required(true).desc("startDate of query")
						.build());
		return this;

	}

	public OptionsFactory withDuration() {
		options.addOption(
				Option.builder("d").longOpt("duration").hasArg(true).required(true).desc("duration since startDate")
						.type(Date.class).build());
		return this;
	}

	public OptionsFactory withThreShold() {
		options.addOption(Option.builder("t").longOpt("threshold").hasArg(true).required(true)
				.desc("minimum number of request for such IP").build());
		return this;
	}

	public OptionsFactory withPath() {
		options.addOption(
				Option.builder("p").longOpt("path").hasArg(true).required(true).desc("path where the log is located")
						.build());
		return this;
	}

	public OptionsFactory() {
		options = new Options();
	}

	public Options getOptions() {

		return options;
	}
}
