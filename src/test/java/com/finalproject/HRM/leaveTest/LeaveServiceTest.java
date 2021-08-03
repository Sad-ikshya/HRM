package com.finalproject.HRM.leaveTest;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mock;

import com.finalproject.HRM.web.leave.dtos.LeaveDto;
import com.finalproject.HRM.web.leave.services.LeaveService;

public class LeaveServiceTest {
	@Mock
	static LeaveService leaveService;
	
	private static LeaveDto leave;
	
	@BeforeAll
	public void setup() {
		leaveService= mock(LeaveService.class);
	}
	
	public void testSaveLeave() {
		
	}
}
