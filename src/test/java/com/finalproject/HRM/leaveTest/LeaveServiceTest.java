package com.finalproject.HRM.leaveTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.finalproject.HRM.web.leave.dtos.LeaveDto;
import com.finalproject.HRM.web.leave.responseDtos.LeaveBalanceDto;
import com.finalproject.HRM.web.leave.responseDtos.LeaveBalanceResponse;
import com.finalproject.HRM.web.leave.responseDtos.LeaveSummaryDto;
import com.finalproject.HRM.web.leave.services.LeaveService;

public class LeaveServiceTest {
	@Mock
	static LeaveService leaveService;
	
	
	private static LeaveDto leave;
	
	@BeforeAll
	public static void setup() {
		leaveService= mock(LeaveService.class);
		
	}
	
	@Test
	public void testSaveLeave() {
		leave=LeaveDto.builder().leaveName("sick").days(15).build();
		
		when(leaveService.saveLeave(leave)).thenReturn(leave);
		LeaveDto addedLeave=leaveService.saveLeave(leave);
		assertThat(addedLeave).usingRecursiveComparison().isEqualTo(leave);
		
	}
	
	@Test
	public void testGetAllLeaves() {
		leave=LeaveDto.builder().id("1234").leaveName("sick").days(15).build();
		List<LeaveDto> leaves=new ArrayList<>();
		leaves.add(leave);
		when(leaveService.getAllLeaves()).thenReturn(leaves);
		List<LeaveDto> leaveDtoList=leaveService.getAllLeaves();
		assertThat(leaveDtoList).usingRecursiveComparison().isEqualTo(leaves);
		
		
	}
	
	@Test
	public void testUpdateleave() {
		leave=LeaveDto.builder().id("12345").leaveName("annual").days(10).build();
		
		when(leaveService.updateLeave("1234", leave)).thenReturn(leave);
		LeaveDto updatedLeave=leaveService.updateLeave("1234", leave);
		assertThat(updatedLeave).usingRecursiveComparison().isEqualTo(leave);
	}
	
	@Test 
	public void testleaveById() {
		leave=LeaveDto.builder().id("12345").leaveName("annual").days(10).build();

		when(leaveService.getLeaveById("1234")).thenReturn(leave);
		LeaveDto leaveDto=leaveService.getLeaveById("1234");
		assertThat(leaveDto).usingRecursiveComparison().isEqualTo(leave);
		
	}
	@Test
	public void testDeleteLeave() {
		when(leaveService.deleteLeave("1234")).thenReturn("1234");
	}
	@Test
	public void testGetPagedLeaveSummary() {
		leave=LeaveDto.builder().id("12345").leaveName("sick").days(10).build();
		LeaveSummaryDto leavesummaryDto=LeaveSummaryDto.builder().leaveName("sick").balance(5).use(5).build();
		List<LeaveSummaryDto> leaveSummaries=new ArrayList<LeaveSummaryDto>();
		leaveSummaries.add(leavesummaryDto);
		Pageable page=PageRequest.of(0, 10);
		Page<LeaveSummaryDto> leaveSummaryDto=new PageImpl<LeaveSummaryDto>(leaveSummaries, page,leaveSummaries.size());
		when(leaveService.getPagedLeaveSummary("2345", 0, 10)).thenReturn(leaveSummaryDto);
		Page<LeaveSummaryDto> pagedLeaveSummaries=leaveService.getPagedLeaveSummary("2345", 0, 10);
		assertThat(pagedLeaveSummaries).usingRecursiveComparison().isEqualTo(leaveSummaryDto);
		
	}
	@Test
	public void testGetLeaveSummary() {
		leave=LeaveDto.builder().id("12345").leaveName("sick").days(10).build();
		List<LeaveSummaryDto> leaveSummaries=new ArrayList<>();
		LeaveSummaryDto leaveSummaryDto=LeaveSummaryDto.builder().leaveName("sick").balance(5).use(5).build();
		leaveSummaries.add(leaveSummaryDto);
		
		when(leaveService.getLeaveSummary("2345")).thenReturn(leaveSummaries);
		List<LeaveSummaryDto> leaveSummariesList=leaveService.getLeaveSummary("2345");
		assertThat(leaveSummariesList).usingRecursiveComparison().isEqualTo(leaveSummaries);
	}
	@Test
	public void testGetLeaveBalance() {
		List<LeaveBalanceDto> leaveBalances=new ArrayList<>();
		LeaveBalanceDto leaveBalance=LeaveBalanceDto.builder().leaveName("sick").balance(5).build();
		leaveBalances.add(leaveBalance);
		when(leaveService.getLeaveBalance("2345")).thenReturn(leaveBalances);
		List<LeaveBalanceDto> leaveBalanceList=leaveService.getLeaveBalance("2345");
		assertThat(leaveBalanceList).usingRecursiveComparison().isEqualTo(leaveBalances);

		
	}
	@Test
	public void testGetToatalLeaveBalance() {
		LeaveBalanceResponse leaveBalanceResponse=LeaveBalanceResponse.builder().remaining(5).total(10).used(5).build();
		when(leaveService.getTotalLeaveBalance("2345")).thenReturn(leaveBalanceResponse);
		LeaveBalanceResponse leaveBalance=leaveService.getTotalLeaveBalance("2345");
		assertThat(leaveBalance).usingRecursiveComparison().isEqualTo(leaveBalanceResponse);
		
		
	}
}
