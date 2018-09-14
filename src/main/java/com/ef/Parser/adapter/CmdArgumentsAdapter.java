package com.ef.Parser.adapter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.cli.CommandLine;
import com.ef.Parser.domain.service.implementation.Duration;
import com.ef.Parser.domain.dto.FindIpDto;

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
