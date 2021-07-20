package com.finalproject.HRM;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.finalproject.HRM.dtos.UserDto;
import com.finalproject.HRM.web.leave.dtos.LeaveDto;
import com.finalproject.HRM.web.leave.dtos.LeaveRequestDto;
import com.finalproject.HRM.web.leave.entities.LeaveType;
import com.finalproject.HRM.web.leave.services.LeaveRequestService;

public class LeaveRequestServiceTest {
	@Mock
	static LeaveRequestService leaveRequestService;

	@BeforeAll
	static void setup() {
		leaveRequestService = mock(LeaveRequestService.class);
	}

	@Test
	public void testAddLeaveRequest() {
		LeaveDto leave = LeaveDto.builder().id("1122").build();
		UserDto user = UserDto.builder().id("1234").build();
		LeaveRequestDto leaveRequest = LeaveRequestDto.builder().id("12345").fromDate(new Date(2021 - 07 - 10))
				.toDate(new Date(2021 - 07 - 115)).leaveReason("busy in household work").leave(leave)
				.leaveType(LeaveType.FULL).employee(user).build();

		when(leaveRequestService.saveLeaveRequest(leaveRequest)).thenReturn(leaveRequest);
		LeaveRequestDto addedLeaveRequest = leaveRequestService.saveLeaveRequest(leaveRequest);
		assertThat(addedLeaveRequest).usingRecursiveComparison().isEqualTo(leaveRequest);
	}

	@Test
	public void testGetAllLeaveRequests() {
		LeaveDto leave = LeaveDto.builder().id("1122").build();
		UserDto user = UserDto.builder().id("1234").build();
		LeaveRequestDto leaveRequest = LeaveRequestDto.builder().id("12345").fromDate(new Date(2021 - 07 - 10))
				.toDate(new Date(2021 - 07 - 115)).leaveReason("busy in household work").leave(leave)
				.leaveType(LeaveType.FULL).employee(user).build();

		List<LeaveRequestDto> leaveRequests = new ArrayList<>();
		leaveRequests.add(leaveRequest);
		Pageable page = PageRequest.of(0, 10);
		Page<LeaveRequestDto> leaveRequestDtoPage = new PageImpl<LeaveRequestDto>(leaveRequests, page,
				leaveRequests.size());
		when(leaveRequestService.getAllLeaveRequests(0, 10)).thenReturn(leaveRequestDtoPage);

	}

	@Test
	public void testUpadateLeaveRequest() {
		LeaveDto leave = LeaveDto.builder().id("1122").build();
		UserDto user = UserDto.builder().id("1234").build();
		LeaveRequestDto leaveRequest = LeaveRequestDto.builder().id("123456").fromDate(new Date(2021 - 07 - 10))
				.toDate(new Date(2021 - 07 - 115)).leaveReason("busy in household work").leave(leave)
				.leaveType(LeaveType.FULL).employee(user).build();

		when(leaveRequestService.updateLeaveRequestDto("12345", leaveRequest)).thenReturn(leaveRequest);
		LeaveRequestDto updatedLeaveRequest = leaveRequestService.updateLeaveRequestDto("12345", leaveRequest);
		assertThat(updatedLeaveRequest).usingRecursiveComparison().isEqualTo(leaveRequest);
	}

	@Test
	public void testDeleteLeaveRequest() {
		when(leaveRequestService.deleteLeaveRequest("12345")).thenReturn("12345");

	}
}