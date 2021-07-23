package com.finalproject.HRM.web.leave.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.HRM.web.leave.dtos.LeaveDto;
import com.finalproject.HRM.web.leave.entities.Leave;
import com.finalproject.HRM.web.leave.repositories.LeaveRepository;
import com.finalproject.HRM.web.leave.responseDtos.LeaveBalanceDto;
import com.finalproject.HRM.web.leave.responseDtos.LeaveRequestResponse;
import com.finalproject.HRM.web.leave.responseDtos.LeaveSummaryDto;
import com.finalproject.HRM.web.user.dtos.UserDto;
import com.finalproject.HRM.web.user.service.UserService;

@Service
public class LeaveServiceImplementation implements LeaveService {
	@Autowired
	private LeaveRepository leaveRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private LeaveRequestService leaveRequestService;

	@Override
	public LeaveDto saveLeave(LeaveDto leave) {
		Leave leaveEntity = Leave.builder().id(leave.getId()).leaveName(leave.getLeaveName()).days(leave.getDays())
				.build();

		leaveEntity = leaveRepository.save(leaveEntity);
		leave.setId(leaveEntity.getId());
		return leave;
	}

	@Override
	public List<LeaveDto> getAllLeaves() {
		List<Leave> leaves = leaveRepository.findAll();
		List<LeaveDto> leaveDtoList = new ArrayList<>();
		for (Leave leave : leaves) {
			LeaveDto leaveDto = LeaveDto.builder().id(leave.getId()).leaveName(leave.getLeaveName())
					.days(leave.getDays()).build();

			leaveDtoList.add(leaveDto);
		}
		return leaveDtoList;
	}

	@Override
	public LeaveDto updateLeave(String id, LeaveDto leave) {
		Leave leaveEntity = Leave.builder().id(id).leaveName(leave.getLeaveName()).days(leave.getDays()).build();

		leaveEntity = leaveRepository.save(leaveEntity);
		leave.setId(leaveEntity.getId());
		return leave;
	}

	@Override
	public LeaveDto getLeaveById(String id) {
		Leave leave = leaveRepository.findById(id).get();
		return LeaveDto.builder().id(leave.getId()).leaveName(leave.getLeaveName()).days(leave.getDays()).build();
	}

	@Override
	public String deleteLeave(String id) {
		leaveRepository.deleteById(id);
		return id;
	}

	@Override
	public List<LeaveSummaryDto> getLeaveSummary(String employeeId) {
		UserDto user = userService.getUserById(employeeId);
		if (user.getId() != "") {
			List<LeaveRequestResponse> leaveRequestsDto = leaveRequestService.leaveDetailByEmployeeId(employeeId);
			List<LeaveSummaryDto> leaveSummaries = new ArrayList<LeaveSummaryDto>();
			List<LeaveDto> leaveList = getAllLeaves();

			for (LeaveDto leave : leaveList) {
				LeaveSummaryDto leaveSummary = new LeaveSummaryDto();
				leaveSummary.setLeaveName(leave.getLeaveName());
				leaveSummary.setTotal(leave.getDays());
				leaveSummary.setUse(
						leaveRequestsDto.stream().filter(req -> req.getLeave().getId().equals(leave.getId())).count());
				leaveSummary.setBalance(leaveSummary.getTotal() - leaveSummary.getUse());

				leaveSummaries.add(leaveSummary);
			}

			return leaveSummaries;
		}
		return null;
	}

	@Override
	public List<LeaveBalanceDto> getLeaveBalance(String employeeId) {
		List<LeaveSummaryDto> leaveSummaryList = getLeaveSummary(employeeId);
		List<LeaveBalanceDto> leaveBalanceList = new ArrayList<>();
		for (LeaveSummaryDto leave : leaveSummaryList) {
			LeaveBalanceDto leaveBalance = new LeaveBalanceDto();
			leaveBalance.setLeaveName(leave.getLeaveName());
			leaveBalance.setBalance(leave.getBalance());

			leaveBalanceList.add(leaveBalance);
		}
		return leaveBalanceList;
	}

}
