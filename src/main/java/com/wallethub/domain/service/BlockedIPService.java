package com.wallethub.domain.service;

import java.util.List;
import com.wallethub.domain.model.BlockedIP;

public interface BlockedIPService {

	BlockedIP saveBlockedIP(BlockedIP blockedIP);

	List<BlockedIP> saveBlockedIPList(List<BlockedIP> blockedIPS);
}
