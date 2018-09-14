package com.ef.domain.service.implementation;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ef.domain.dto.FindIpDto;
import com.ef.domain.model.Access;
import com.ef.domain.repository.AccessRepository;
import com.ef.domain.service.AccessLogService;

@Service
public class AccessLogServiceImplementation implements AccessLogService {
	@Autowired
	private AccessRepository accessRepository;

	@Override
	public void saveAccessLogList(List<Access> accessList) {
		accessRepository.save(accessList);
	}

	@Override
	public List<BigInteger> findIpByDateAndThreshold(FindIpDto findIpDto) {
		LocalDateTime endDate = findIpDto.getStartDate().plusHours(findIpDto.getDuration().getDurationInHours());
		return accessRepository.findIpByDateAndThreshold(findIpDto.getStartDate(), endDate, findIpDto.getThreshold());
	}
}
