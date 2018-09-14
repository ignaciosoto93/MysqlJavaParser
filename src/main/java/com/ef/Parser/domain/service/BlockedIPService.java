package com.ef.Parser.domain.service;

import java.util.List;
import com.ef.Parser.domain.model.BlockedIP;

public interface BlockedIPService {

	BlockedIP saveBlockedIP(BlockedIP blockedIP);

	List<BlockedIP> saveBlockedIPList(List<BlockedIP> blockedIPS);
}
