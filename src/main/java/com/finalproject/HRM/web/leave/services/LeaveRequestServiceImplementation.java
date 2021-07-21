package com.finalproject.HRM.web.leave.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.finalproject.HRM.web.leave.dtos.LeaveDto;
import com.finalproject.HRM.web.leave.dtos.LeaveRequestDto;
import com.finalproject.HRM.web.leave.entities.Leave;
import com.finalproject.HRM.web.leave.entities.LeaveRequest;
import com.finalproject.HRM.web.leave.repositories.LeaveRepository;
import com.finalproject.HRM.web.leave.repositories.LeaveRequestRepository;
import com.finalproject.HRM.web.user.dtos.UserDto;
import com.finalproject.HRM.web.user.entities.User;
import com.finalproject.HRM.web.user.repositories.UserRepository;

@Service
public class LeaveRequestServiceImplementation implements LeaveRequestService {
	@Autowired
	private LeaveRequestRepository leaveRequestRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private LeaveRepository leaveRepository;

	@Override
	public Page<LeaveRequestDto> getAllLeaveRequests(int index, int size) {
		Pageable page = PageRequest.of(index, size);
		Page<LeaveRequest> leaveRequests = leaveRequestRepository.findAll(page);
		List<LeaveRequestDto> leaveRequestDtoList = new ArrayList<>();
		for (LeaveRequest l : leaveRequests) {
			Optional<Leave> leaveEntity = leaveRepository.findById(l.getLeaveId());
			LeaveDto leave = LeaveDto.builder().id(l.getLeaveId()).leaveName(leaveEntity.get().getLeaveName()).build();
			Optional<User> userEntity = userRepository.findById(l.getEmployee().getId());
			User u = userEntity.get();
			UserDto employee = UserDto.builder().id(l.getEmployee().getId()).fullName(u.getFullName())
					.email(u.getEmail()).department(u.getDepartment()).designation(u.getDesignation()).bio(u.getBio())
					.joinedDate(u.getJoinedDate()).photo(u.getPhoto()).build();
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
		String leaveId = leave.getId();
		LeaveRequest leaveRequestEntity = LeaveRequest.builder().id(leaveRequest.getId()).fromDate(leaveRequest.getFromDate())
				.toDate(leaveRequest.getToDate()).leaveReason(leaveRequest.getLeaveReason()).leaveId(leaveId)
				.leaveType(leaveRequest.getLeaveType()).status(leaveRequest.getStatus()).employee(employee).build();

		leaveRequestEntity = leaveRequestRepository.save(leaveRequestEntity);
		leaveRequest.setId(leaveRequestEntity.getId());
		return leaveRequest;
	
	}

	@Override
	public LeaveRequestDto updateLeaveRequestDto(String id, LeaveRequestDto leaveRequest) {
		User employee = User.builder().id(leaveRequest.getEmployee().getId()).build();
		Leave leave = Leave.builder().id(leaveRequest.getLeave().getId()).build();
		String leaveId = leave.getId();
		LeaveRequest leaveRequestEntity = LeaveRequest.builder().id(id).fromDate(leaveRequest.getFromDate())
				.toDate(leaveRequest.getToDate()).leaveReason(leaveRequest.getLeaveReason()).leaveId(leaveId)
				.leaveType(leaveRequest.getLeaveType()).status(leaveRequest.getStatus()).employee(employee).build();

		leaveRequestEntity = leaveRequestRepository.save(leaveRequestEntity);
		leaveRequest.setId(leaveRequestEntity.getId());
		return leaveRequest;
	}

	@Override
	public String deleteLeaveRequest(String id) {
		LeaveRequest leaveRequest = leaveRequestRepository.findById(id).get();
		User employee = User.builder().id(leaveRequest.getEmployee().getId()).build();
		Leave leave = Leave.builder().id(leaveRequest.getLeaveId()).build();
		String leaveId = leave.getId();
		LeaveRequest deletedLeaveRequest = LeaveRequest.builder().id(leaveRequest.getId())
				.fromDate(leaveRequest.getFromDate()).toDate(leaveRequest.getToDate())
				.leaveReason(leaveRequest.getLeaveReason()).leaveId(leaveId).leaveType(leaveRequest.getLeaveType())
				.status(leaveRequest.getStatus()).employee(employee).build();

		leaveRequestRepository.save(deletedLeaveRequest);
		leaveRequestRepository.deleteById(id);

		return id;
	}

	@Override
	public List<LeaveRequestDto> leaveDetailByEmployeeId(String employeeId) throws UsernameNotFoundException {
		Optional<User> userEntity = userRepository.findById(employeeId);
		if (userEntity.isEmpty())
			throw new UsernameNotFoundException(employeeId + " user not found");

		List<LeaveRequest> leaveRequest = leaveRequestRepository.findByEmployeeId(employeeId);
		List<LeaveRequestDto> leaveRequests = new ArrayList<>();
		for (LeaveRequest l : leaveRequest) {
			User u = userEntity.get();
			Optional<Leave> leaveEntity = leaveRepository.findById(l.getLeaveId());

			LeaveDto leave = LeaveDto.builder().id(l.getLeaveId()).leaveName(leaveEntity.get().getLeaveName()).build();
			UserDto employee = UserDto.builder().id(employeeId).fullName(u.getFullName()).email(u.getEmail())
					.department(u.getDepartment()).designation(u.getDesignation()).bio(u.getBio())
					.joinedDate(u.getJoinedDate()).photo(u.getPhoto()).build();
			LeaveRequestDto leaveRequestDto = LeaveRequestDto.builder().id(l.getId()).fromDate(l.getFromDate())
					.toDate(l.getToDate()).leaveReason(l.getLeaveReason()).leave(leave).leaveType(l.getLeaveType())
					.status(l.getStatus()).employee(employee).build();

			leaveRequests.add(leaveRequestDto);
		}

		return leaveRequests;

	}

	@Override
	public Page<LeaveRequestDto> leaveDetailByDate(Date date, int index, int size) {
		Pageable page = PageRequest.of(index, size);
		Page<LeaveRequest> leaveRequest = leaveRequestRepository.findByFromDate(date, page);
		List<LeaveRequestDto> leaveRequests = new ArrayList<>();
		for (LeaveRequest l : leaveRequest) {
			Optional<Leave> leaveEntity = leaveRepository.findById(l.getLeaveId());
			LeaveDto leave = LeaveDto.builder().id(l.getLeaveId()).leaveName(leaveEntity.get().getLeaveName()).build();
			Optional<User> userEntity = userRepository.findById(l.getEmployee().getId());
			User u = userEntity.get();
			UserDto employee = UserDto.builder().id(l.getEmployee().getId()).fullName(u.getFullName())
					.email(u.getEmail()).department(u.getDepartment()).designation(u.getDesignation()).bio(u.getBio())
					.joinedDate(u.getJoinedDate()).photo(u.getPhoto()).build();
			LeaveRequestDto leaveRequestDto = LeaveRequestDto.builder().id(l.getId()).fromDate(l.getFromDate())
					.toDate(l.getToDate()).leaveReason(l.getLeaveReason()).leave(leave).leaveType(l.getLeaveType())
					.status(l.getStatus()).employee(employee).build();

			leaveRequests.add(leaveRequestDto);
		}
		Page<LeaveRequestDto> leaveRequestDtoPage = new PageImpl<LeaveRequestDto>(leaveRequests, page,
				leaveRequests.size());

		return leaveRequestDtoPage;
	}
}