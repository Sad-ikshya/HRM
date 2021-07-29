package com.finalproject.HRM.leaveTest;

import static org.mockito.Mockito.mock;

import org.mockito.Mock;

import com.finalproject.HRM.web.leave.services.LeaveService;

public class LeaveServiceTest {
	@Mock
	static LeaveService leaveService;
	
	public void setup() {
		leaveService=mock(LeaveService.class);
	}

}
