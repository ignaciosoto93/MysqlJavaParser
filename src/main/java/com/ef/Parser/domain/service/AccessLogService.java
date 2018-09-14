package com.ef.Parser.domain.service;

import java.math.BigInteger;
import java.util.List;
import com.ef.Parser.domain.dto.FindIpDto;
import com.ef.Parser.domain.model.Access;

public interface AccessLogService {

	void saveLog(Access access);

	void saveAccesLogList(List<Access> accessList);

	List<BigInteger> findIpByDateAndThreshold(FindIpDto findIpDto);
}
