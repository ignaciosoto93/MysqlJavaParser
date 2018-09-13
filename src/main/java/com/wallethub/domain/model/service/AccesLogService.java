package com.wallethub.domain.model.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import com.wallethub.domain.model.Access;
import com.wallethub.domain.model.service.implementation.Duration;

public interface AccesLogService {

	void saveLog(Access access);

	void saveAccesLogList(List<Access> accessList);

	List<BigInteger> findIpByDateAndThreshold(LocalDateTime date, long threshold, Duration duration);
}
