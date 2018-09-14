package com.ef.Parser;

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
import com.ef.Parser.builder.OptionsFactory;
import com.ef.Parser.adapter.BlockedIPAdapter;
import com.ef.Parser.adapter.CmdArgumentsAdapter;
import com.ef.Parser.domain.service.AccessLogService;
import com.google.common.collect.Lists;
import com.google.common.net.InetAddresses;
import com.ef.Parser.adapter.BufferAccessAdapter;
import com.ef.Parser.domain.model.Access;
import com.ef.Parser.domain.dto.FindIpDto;
import com.ef.Parser.domain.model.BlockedIP;
import com.ef.Parser.domain.service.BlockedIPService;
import com.ef.Parser.exception.JavaMysqlParserError;
import com.ef.Parser.exception.JavaMysqlParserException;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	AccessLogService accessLogService;
	@Autowired
	BlockedIPService blockedIPService;

	public static void main(String[] args) throws IOException, java.text.ParseException {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		Options options = new OptionsFactory().withDuration().withStartDate().withThreShold().withPath().getOptions();

		CommandLineParser parser = new DefaultParser();
		HelpFormatter formatter = new HelpFormatter();
		CommandLine cmd = null;

		try {
			cmd = parser.parse(options, strings);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			formatter.printHelp("utility-name", options);

			System.exit(1);
		}

		persistFile(cmd.getOptionValue("p"));
		FindIpDto findIpDto = CmdArgumentsAdapter.adapt(cmd);

		List<BigInteger> results = accessLogService.findIpByDateAndThreshold(findIpDto);
		List<BlockedIP> blockedIPS = results.stream().map(ip -> BlockedIPAdapter.adapt(ip, findIpDto))
				.collect(Collectors.toList());

		blockedIPService.saveBlockedIPList(blockedIPS);

		results.forEach(r -> System.out.println(InetAddresses.fromInteger(r.intValue()).getHostAddress()));

	}

	private void persistFile(String path) throws IOException {
		BufferedReader bufRead = new BufferedReaderFactory(path).invoke();

		List<Access> list = Lists.newArrayList();
		String myLine;
		while ((myLine = bufRead.readLine()) != null) {
			Access access = new BufferAccessAdapter(myLine).invoke();
			list.add(access);

		}
		accessLogService.saveAccesLogList(list);
	}

	private class BufferedReaderFactory {
		private String path;

		public BufferedReaderFactory(String path) {
			this.path = path;
		}

		public BufferedReader invoke() {
			FileReader input = null;
			try {
				input = new FileReader(path);
			} catch (FileNotFoundException e) {
				throw new JavaMysqlParserException(JavaMysqlParserError.INVALID_PATH, "Invalid Path");
			}
			return new BufferedReader(input);
		}
	}

}

