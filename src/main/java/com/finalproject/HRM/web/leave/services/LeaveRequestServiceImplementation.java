package com.finalproject.HRM.web.leave.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.finalproject.HRM.web.leave.dtos.LeaveDto;
import com.finalproject.HRM.web.leave.dtos.LeaveRequestDto;
import com.finalproject.HRM.web.leave.entities.Leave;
import com.finalproject.HRM.web.leave.entities.LeaveRequest;
import com.finalproject.HRM.web.leave.repositories.LeaveRequestRepository;
import com.finalproject.HRM.web.user.dtos.UserDto;
import com.finalproject.HRM.web.user.entities.User;

@Service
public class LeaveRequestServiceImplementation implements LeaveRequestService {
	@Autowired
	private LeaveRequestRepository leaveRequestRepository;

	@Override
	public Page<LeaveRequestDto> getAllLeaveRequests(int index, int size) {
		Pageable page = PageRequest.of(index, size);
		Page<LeaveRequest> leaveRequests = leaveRequestRepository.findAll(page);
		List<LeaveRequestDto> leaveRequestDtoList = new ArrayList<>();
		for (LeaveRequest l : leaveRequests) {
			UserDto employee = UserDto.builder().id(l.getEmployee().getId()).build();
			LeaveDto leave = LeaveDto.builder().id(l.getLeave().getId()).build();
			LeaveRequestDto leaveRequest = LeaveRequestDto.builder().id(l.getId()).fromDate(l.getFromDate())
					.toDate(l.getToDate()).leaveReason(l.getLeaveReason()).leave(leave).leaveType(l.getLeaveType())
					.status(l.getStatus()).employee(employee).build();

			leaveRequestDtoList.add(leaveRequest);

		}
		Page<LeaveRequestDto> leaveRequestDtoPage = new PageImpl<LeaveRequestDto>(leaveRequestDtoList, page,
				leaveRequestDtoList.size());

		return leaveRequestDtoPage;

	}

	@Override
	public LeaveRequestDto saveLeaveRequest(LeaveRequestDto leaveRequest) {
		User employee = User.builder().id(leaveRequest.getEmployee().getId()).build();
		Leave leave = Leave.builder().id(leaveRequest.getLeave().getId()).build();
		LeaveRequest leaveRequestEntity = LeaveRequest.builder().id(leaveRequest.getId())
				.fromDate(leaveRequest.getFromDate()).toDate(leaveRequest.getToDate())
				.leaveReason(leaveRequest.getLeaveReason()).leave(leave).leaveType(leaveRequest.getLeaveType())
				.status(leaveRequest.getStatus()).employee(employee).build();

		leaveRequestEntity = leaveRequestRepository.save(leaveRequestEntity);
		leaveRequest.setId(leaveRequestEntity.getId());
		return leaveRequest;

	}

	@Override
	public LeaveRequestDto updateLeaveRequestDto(String id, LeaveRequestDto leaveRequest) {
		User employee = User.builder().id(leaveRequest.getEmployee().getId()).build();
		Leave leave = Leave.builder().id(leaveRequest.getLeave().getId()).build();
		LeaveRequest leaveRequestEntity = LeaveRequest.builder().id(id).fromDate(leaveRequest.getFromDate())
				.toDate(leaveRequest.getToDate()).leaveReason(leaveRequest.getLeaveReason()).leave(leave)
				.leaveType(leaveRequest.getLeaveType()).status(leaveRequest.getStatus()).employee(employee).build();

		leaveRequestEntity = leaveRequestRepository.save(leaveRequestEntity);
		leaveRequest.setId(leaveRequestEntity.getId());
		return leaveRequest;
	}

	@Override
	public String deleteLeaveRequest(String id) {
		leaveRequestRepository.deleteById(id);

		return id;
	}
}