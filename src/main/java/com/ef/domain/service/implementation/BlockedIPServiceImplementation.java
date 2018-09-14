package com.ef.domain.service.implementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ef.domain.repository.BlockedIPRepository;
import com.ef.domain.service.BlockedIPService;
import com.ef.domain.model.BlockedIP;

@Service
public class BlockedIPServiceImplementation implements BlockedIPService {

	@Autowired
	private BlockedIPRepository blockedIPRepository;

	@Override
	public List<BlockedIP> saveBlockedIPList(List<BlockedIP> blockedIPS) {
		return blockedIPRepository.save(blockedIPS);
	}
}
