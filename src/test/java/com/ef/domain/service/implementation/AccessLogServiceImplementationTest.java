package com.ef.domain.service.implementation;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import com.ef.domain.dto.FindIpDto;
import com.ef.domain.model.Access;
import com.ef.domain.repository.AccessRepository;
import com.google.common.collect.Lists;

public class AccessLogServiceImplementationTest {

	@InjectMocks
	AccessLogServiceImplementation instance;
	@Mock
	private AccessRepository accessRepository;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void saveAccessLogList() throws Exception {
		List<Access> accessList = Lists.newArrayList();
		Mockito.when(accessRepository.save(Matchers.anyListOf(Access.class))).thenReturn(accessList);
		instance.saveAccessLogList(accessList);
		Mockito.verify(accessRepository, times(1)).save(Matchers.anyListOf(Access.class));
	}

	@Test
	public void findIpByDateAndThresholdDaily() throws Exception {
		List<BigInteger> list = Lists.newArrayList();
		LocalDateTime now = LocalDateTime.now();
		FindIpDto findIpDto = FindIpDto.builder().withStartDate(now).withThreshold(100l).withDuration(Duration.daily)
				.build();
		Mockito.when(accessRepository
				.findIpByDateAndThreshold(any(LocalDateTime.class), any(LocalDateTime.class), any(Long.class)))
				.thenReturn(list);
		instance.findIpByDateAndThreshold(findIpDto);
		Mockito.verify(accessRepository, times(1))
				.findIpByDateAndThreshold(findIpDto.getStartDate(), findIpDto.getStartDate().plusHours(24),
						findIpDto.getThreshold());

	}

}
