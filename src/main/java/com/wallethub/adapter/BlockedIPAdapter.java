package com.wallethub.adapter;

import java.math.BigInteger;
import com.wallethub.domain.dto.FindIpDto;
import com.wallethub.domain.model.BlockedIP;

public class BlockedIPAdapter {

	public static BlockedIP adapt(BigInteger ip, FindIpDto findIpDto) {
		return BlockedIP.builder().withIp(ip).withReason(
				"the ip had more than " + findIpDto.getThreshold() + " requests in a " + findIpDto.getDuration().name()
						+ " period").build();
	}
}
