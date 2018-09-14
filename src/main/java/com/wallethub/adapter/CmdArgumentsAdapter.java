package com.wallethub.adapter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.cli.CommandLine;
import com.wallethub.domain.dto.FindIpDto;
import com.wallethub.domain.service.implementation.Duration;

public class CmdArgumentsAdapter {

	public static FindIpDto adapt(CommandLine cmd) {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd.HH:mm:ss");

		String startDateString = cmd.getOptionValue("s");
		String durationString = cmd.getOptionValue("d");
		String threshold = cmd.getOptionValue("t");
		LocalDateTime startDate = LocalDateTime.parse(startDateString, dateFormatter);
		Duration duration = Duration.valueOf(durationString);

		return FindIpDto.builder().withStartDate(startDate).withDuration(duration)
				.withThreshold(Long.valueOf(threshold)).build();
	}
}
