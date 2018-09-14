package com.ef.domain.service;

import java.util.List;
import com.ef.domain.model.BlockedIP;

public interface BlockedIPService {

	List<BlockedIP> saveBlockedIPList(List<BlockedIP> blockedIPS);
}
