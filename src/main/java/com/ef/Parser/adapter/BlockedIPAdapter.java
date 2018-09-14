package com.ef.Parser.adapter;

import java.math.BigInteger;
import com.ef.Parser.domain.dto.FindIpDto;
import com.ef.Parser.domain.model.BlockedIP;

public class BlockedIPAdapter {

	public static BlockedIP adapt(BigInteger ip, FindIpDto findIpDto) {
		return BlockedIP.builder().withIp(ip).withReason(
				"the ip had more than " + findIpDto.getThreshold() + " requests in a " + findIpDto.getDuration().name()
						+ " period").build();
	}
}
