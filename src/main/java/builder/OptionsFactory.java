package builder;

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
						.build());
		return this;
	}

	public OptionsFactory withThreShold() {
		options.addOption(Option.builder("t").longOpt("threshold").hasArg(true).required(true)
				.desc("minimum number of request for such IP").build());
		return this;
	}

	public OptionsFactory() {
		options = new Options();
	}

	public Options getOptions() {

		return options;
	}
}
