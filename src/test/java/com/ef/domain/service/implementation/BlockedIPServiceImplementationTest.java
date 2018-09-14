package com.ef.domain.service.implementation;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import com.ef.domain.model.BlockedIP;
import com.ef.domain.repository.BlockedIPRepository;
import com.google.common.collect.Lists;

public class BlockedIPServiceImplementationTest {

	@InjectMocks
	BlockedIPServiceImplementation instance;
	@Mock
	private BlockedIPRepository blockedIPRepository;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void saveBlockedIPList() {
		List<BlockedIP> blockedIPS = Lists.newArrayList();
		Mockito.when(blockedIPRepository.save(Matchers.anyListOf(BlockedIP.class))).thenReturn(blockedIPS);
		instance.saveBlockedIPList(blockedIPS);
		Mockito.verify(blockedIPRepository, Mockito.times(1)).save(Matchers.anyListOf(BlockedIP.class));
	}

}
