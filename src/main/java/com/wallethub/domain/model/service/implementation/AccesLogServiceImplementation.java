package com.wallethub.domain.model.service.implementation;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wallethub.domain.model.Access;
import com.wallethub.domain.model.repository.AccessRepository;
import com.wallethub.domain.model.service.AccesLogService;

@Service
public class AccesLogServiceImplementation implements AccesLogService {
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
	public List<Long> findIpByDateAndThreshold(LocalDateTime startDate, long threshold, Duration duration) {
		LocalDateTime endDate = startDate.plusHours(duration.getDurationInHours());
		return accessRepository.findIpByDateAndThreShold(startDate, endDate, threshold);
	}
}
