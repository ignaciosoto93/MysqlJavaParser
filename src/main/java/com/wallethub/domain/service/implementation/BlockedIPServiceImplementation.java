package com.wallethub.domain.service.implementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wallethub.domain.model.BlockedIP;
import com.wallethub.domain.repository.BlockedIPRepository;
import com.wallethub.domain.service.BlockedIPService;

@Service
public class BlockedIPServiceImplementation implements BlockedIPService {

	@Autowired
	private BlockedIPRepository blockedIPRepository;

	@Override
	public BlockedIP saveBlockedIP(BlockedIP blockedIP) {
		return blockedIPRepository.save(blockedIP);
	}

	@Override
	public List<BlockedIP> saveBlockedIPList(List<BlockedIP> blockedIPS)  {
		return blockedIPRepository.save(blockedIPS);
	}
}
