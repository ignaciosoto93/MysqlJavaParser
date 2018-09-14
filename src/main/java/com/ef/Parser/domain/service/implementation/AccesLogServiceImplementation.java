package com.ef.Parser.domain.service.implementation;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ef.Parser.domain.dto.FindIpDto;
import com.ef.Parser.domain.model.Access;
import com.ef.Parser.domain.repository.AccessRepository;
import com.ef.Parser.domain.service.AccessLogService;

@Service
public class AccesLogServiceImplementation implements AccessLogService {
	@Autowired
	private AccessRepository accessRepository;

	@Override
	public void saveLog(Access access) {
		accessRepository.save(access);
	}

	@Override
	public void saveAccesLogList(List<Access> accessList) {
		accessRepository.save(accessList);
	}

	@Override
	public List<BigInteger> findIpByDateAndThreshold(FindIpDto findIpDto) {
		LocalDateTime endDate = findIpDto.getStartDate().plusHours(findIpDto.getDuration().getDurationInHours());
		return accessRepository.findIpByDateAndThreshold(findIpDto.getStartDate(), endDate, findIpDto.getThreshold());
	}
}
