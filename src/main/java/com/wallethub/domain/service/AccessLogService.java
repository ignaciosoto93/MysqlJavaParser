package com.wallethub.domain.service;

import java.math.BigInteger;
import java.util.List;
import com.wallethub.domain.dto.FindIpDto;
import com.wallethub.domain.model.Access;

public interface AccessLogService {

	void saveLog(Access access);

	void saveAccesLogList(List<Access> accessList);

	List<BigInteger> findIpByDateAndThreshold(FindIpDto findIpDto);
}
