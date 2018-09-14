package com.ef;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.cli.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.ef.builder.OptionsFactory;
import com.ef.adapter.BlockedIPAdapter;
import com.ef.adapter.CmdArgumentsAdapter;
import com.ef.domain.service.AccessLogService;
import com.ef.domain.service.BlockedIPService;
import com.google.common.collect.Lists;
import com.google.common.net.InetAddresses;
import com.ef.adapter.BufferAccessAdapter;
import com.ef.domain.model.Access;
import com.ef.domain.dto.FindIpDto;
import com.ef.domain.model.BlockedIP;
import com.ef.exception.ParserError;
import com.ef.exception.ParserException;

@SpringBootApplication
public class Parser implements CommandLineRunner {

	@Autowired
	AccessLogService accessLogService;
	@Autowired
	BlockedIPService blockedIPService;



	public static void main(String[] args) throws ParserException {
		SpringApplication.run(Parser.class, args);
	}

	@Override
	public void run(String... strings) throws ParserException {
		Options options = new OptionsFactory().withDuration().withStartDate().withThreShold().withPath().getOptions();

		CommandLineParser parser = new DefaultParser();
		HelpFormatter formatter = new HelpFormatter();
		CommandLine cmd;

		try {
			cmd = parser.parse(options, strings);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			formatter.printHelp("utility-name", options);
			throw new ParserException(ParserError.NOT_VALID_ARGUMENTS, "Please, provide valid arguments");
		}

		persistFile(cmd.getOptionValue("p"));
		FindIpDto findIpDto = CmdArgumentsAdapter.adapt(cmd);

		List<BigInteger> results = accessLogService.findIpByDateAndThreshold(findIpDto);
		List<BlockedIP> blockedIPS = results.stream().map(ip -> BlockedIPAdapter.adapt(ip, findIpDto))
				.collect(Collectors.toList());

		blockedIPService.saveBlockedIPList(blockedIPS);

		results.forEach(r -> System.out.println(InetAddresses.fromInteger(r.intValue()).getHostAddress()));

	}

	private void persistFile(String path) throws ParserException {

		BufferedReader bufRead = new BufferedReaderFactory(path).invoke();
		List<Access> list = Lists.newArrayList();
		String myLine;

		try {
			while ((myLine = bufRead.readLine()) != null) {
				Access access = new BufferAccessAdapter(myLine).invoke();
				list.add(access);

			}
		} catch (IOException e) {
			throw new ParserException(ParserError.BUFFER_ERROR, "There was a problem reading the buffer");
		}
		accessLogService.saveAccessLogList(list);
	}

	private class BufferedReaderFactory {
		private final String path;

		BufferedReaderFactory(String path) {
			this.path = path;
		}

		BufferedReader invoke() {
			FileReader input;
			try {
				input = new FileReader(path);
			} catch (FileNotFoundException e) {
				throw new ParserException(ParserError.INVALID_PATH, "Invalid Path");
			}
			return new BufferedReader(input);
		}
	}

}

