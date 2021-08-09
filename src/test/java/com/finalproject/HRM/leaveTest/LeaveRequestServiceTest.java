package com.finalproject.HRM.leaveTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.finalproject.HRM.web.leave.dtos.LeaveDto;
import com.finalproject.HRM.web.leave.entities.LeaveRequest;
import com.finalproject.HRM.web.leave.entities.LeaveType;
import com.finalproject.HRM.web.leave.entities.Status;
import com.finalproject.HRM.web.leave.requestDtos.LeaveRequestDto;
import com.finalproject.HRM.web.leave.requestDtos.LeaveRequestStatusDto;
import com.finalproject.HRM.web.leave.responseDtos.LeaveRequestResponse;
import com.finalproject.HRM.web.leave.services.LeaveRequestService;
import com.finalproject.HRM.web.leave.services.LeaveService;
import com.finalproject.HRM.web.user.dtos.UserDto;
import com.finalproject.HRM.web.user.service.UserService;

//@RunWith(SpringRunner.class)
@AutoConfigureDataMongo
public class LeaveRequestServiceTest {
	@Mock
	static LeaveRequestService leaveRequestService;

	private static LeaveDto leave;

	private static UserDto user;

	@Mock
	private static LeaveService leaveService;

	@Mock
	private static UserService userService;

	private static LeaveRequestDto leaveRequest;
	private static LeaveRequestResponse leaveRequestResponse;

	@BeforeAll
	static void setup() {
		leaveRequestService = mock(LeaveRequestService.class);

	}

	@Test
	public void testAddLeaveRequest() {

		leaveRequest = LeaveRequestDto.builder().fromDate(23456736L)
				.toDate(23456736L).leaveReason("busy in household work").leaveId("1234")
				.leaveType(LeaveType.FULL).status(Status.PENDING).employeeId("1234").build();

		leaveRequestResponse = LeaveRequestResponse.builder().id("12345").fromDate(23456736L)
				.toDate(23456736L).leaveReason("busy in household work").leave(leave)
				.leaveType(LeaveType.FULL).employee(user).build();

		when(leaveRequestService.saveLeaveRequest(leaveRequest)).thenReturn(leaveRequestResponse);
		LeaveRequestResponse addedLeaveRequest = leaveRequestService.saveLeaveRequest(leaveRequest);
		assertThat(addedLeaveRequest).usingRecursiveComparison().isEqualTo(leaveRequestResponse);
	}
	

	@Test
	public void testLeaveDetailByTodayDate() throws ParseException {
		List<LeaveRequestResponse> leaveRequests = new ArrayList<>();

		leaveRequests.add(leaveRequestResponse);
		Pageable page = PageRequest.of(0, 10);
		Page<LeaveRequestResponse> leaveRequestDtoPage = new PageImpl<LeaveRequestResponse>(leaveRequests, page,
				leaveRequests.size());
		when(leaveRequestService.leaveDetailByTodayDate(0, 10)).thenReturn(leaveRequestDtoPage);
		Page<LeaveRequestResponse> leaveRequestResponse=leaveRequestService.leaveDetailByTodayDate(0, 10);
		assertThat(leaveRequestResponse).usingRecursiveComparison().isEqualTo(leaveRequestDtoPage);

	}

	@Test
	public void testGetAllLeaveRequests() {

		LeaveRequestResponse leaveRequest = LeaveRequestResponse.builder().id("12345")
				.fromDate(23456736L).toDate(23456736L)
				.leaveReason("busy in household work").leave(leave).leaveType(LeaveType.FULL).status(Status.PENDING)
				.employee(user).build();

		List<LeaveRequestResponse> leaveRequests = new ArrayList<>();
		leaveRequests.add(leaveRequest);
		Pageable page = PageRequest.of(0, 10);
		Page<LeaveRequestResponse> leaveRequestDtoPage = new PageImpl<LeaveRequestResponse>(leaveRequests, page,
				leaveRequests.size());
		when(leaveRequestService.getAllLeaveRequests(0, 10)).thenReturn(leaveRequestDtoPage);

	}
	@Test 
	public void testEmployeeGetAllLeaveRequests() {
		LeaveRequestResponse leaveRequestResponse = LeaveRequestResponse.builder().id("12345").fromDate(23456736L)
				.toDate(23456736L).leaveReason("busy in household work").leave(leave)
				.leaveType(LeaveType.FULL).verifiedBy("23456").employee(user).build();
		List<LeaveRequestResponse> leaveRequests = new ArrayList<>();
		leaveRequests.add(leaveRequestResponse);
		Pageable page = PageRequest.of(0, 10);
		Page<LeaveRequestResponse> leaveRequestDtoPage = new PageImpl<LeaveRequestResponse>(leaveRequests, page,
				leaveRequests.size());
		when(leaveRequestService.getAllLeaveRequests(0, 10)).thenReturn(leaveRequestDtoPage);
		
	}

	@Test
	public void testUpadateLeaveRequest() {

		LeaveRequestDto leaveRequest = LeaveRequestDto.builder().id("123456").fromDate(23456736L)
				.toDate(23456736L).leaveReason("busy in household work").leaveId("1234")
				.leaveType(LeaveType.FULL).status(Status.PENDING).employeeId("1234").build();

		when(leaveRequestService.updateLeaveRequestDto("12345", leaveRequest)).thenReturn(leaveRequestResponse);
		LeaveRequestResponse updatedLeaveRequest = leaveRequestService.updateLeaveRequestDto("12345", leaveRequest);
		assertThat(updatedLeaveRequest).usingRecursiveComparison().isEqualTo(leaveRequestResponse);
	}

	@Test
	public void testLeaveDetailByEmlpoyeeId() {

		List<LeaveRequestResponse> leaveRequests = new ArrayList<>();

		leaveRequests.add(leaveRequestResponse);
		when(leaveRequestService.leaveDetailByEmployeeId("1234")).thenReturn(leaveRequests);
		List<LeaveRequestResponse> leaveRequestResponses = leaveRequestService.leaveDetailByEmployeeId("1234");
		assertThat(leaveRequestResponses).usingRecursiveComparison().isEqualTo(leaveRequests);

	}

	@Test
	public void testUpadateLeaveStatus() {

		LeaveRequestStatusDto leaveRequestStatus = LeaveRequestStatusDto.builder().status(Status.APPROVED).build();
		LeaveRequestResponse leaveRequestResponse = LeaveRequestResponse.builder().id("12345").fromDate(23456736L)
				.toDate(23456736L).leaveReason("busy in household work").leave(leave)
				.leaveType(LeaveType.FULL).verifiedBy("23456").employee(user).build();
		
		
		when(leaveRequestService.updateLeaveStatus("1234", leaveRequestStatus, "23456")).thenReturn(leaveRequestResponse);
		LeaveRequestResponse leaveRequestResponseDto= leaveRequestService.updateLeaveStatus("1234", leaveRequestStatus, "23456");
		assertThat(leaveRequestResponseDto).usingRecursiveComparison().isEqualTo(leaveRequestResponse);

	}

	@Test
	public void testDeleteLeaveRequest() {
		when(leaveRequestService.deleteLeaveRequest("12345")).thenReturn("12345");

	}

}