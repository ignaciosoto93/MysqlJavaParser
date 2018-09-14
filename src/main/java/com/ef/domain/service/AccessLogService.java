package com.ef.domain.service;

import java.math.BigInteger;
import java.util.List;
import com.ef.domain.dto.FindIpDto;
import com.ef.domain.model.Access;

public interface AccessLogService {


	void saveAccessLogList(List<Access> accessList);

	List<BigInteger> findIpByDateAndThreshold(FindIpDto findIpDto);
}
