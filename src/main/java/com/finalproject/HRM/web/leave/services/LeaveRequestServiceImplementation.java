package com.finalproject.HRM.web.leave.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import com.finalproject.HRM.web.leave.entities.Leave;
import com.finalproject.HRM.web.leave.entities.LeaveRequest;
import com.finalproject.HRM.web.leave.repositories.LeaveRepository;
import com.finalproject.HRM.web.leave.repositories.LeaveRequestRepository;
import com.finalproject.HRM.web.leave.requestDtos.LeaveRequestDto;
import com.finalproject.HRM.web.leave.requestDtos.LeaveRequestStatusDto;
import com.finalproject.HRM.web.leave.responseDtos.LeaveRequestResponse;
import com.finalproject.HRM.web.user.dtos.UserDto;
import com.finalproject.HRM.web.user.entities.User;
import com.finalproject.HRM.web.user.repositories.UserRepository;
import com.finalproject.HRM.web.user.service.UserService;
import com.nimbusds.jose.shaded.json.parser.ParseException;

@Service
public class LeaveRequestServiceImplementation implements LeaveRequestService {
	@Autowired
	private LeaveRequestRepository leaveRequestRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private LeaveRepository leaveRepository;
	@Autowired
	private LeaveService leaveService;
	@Autowired
	private UserService userService;

	@Override
	public Page<LeaveRequestResponse> getAllLeaveRequests(int index, int size) {
		try {
			Pageable page = PageRequest.of(index, size);
			Page<LeaveRequest> leaveRequests = leaveRequestRepository.findAll(page);
			List<LeaveRequestResponse> leaveRequestDtoList = new ArrayList<>();

			for (LeaveRequest l : leaveRequests) {
				// Optional<Leave> leaveEntity = leaveRepository.findById(l.getLeaveId());
				// LeaveDto leave =
				// LeaveDto.builder().id(l.getLeaveId()).leaveName(leaveEntity.get().getLeaveName()).build();

				 LeaveDto leaveDto = leaveService.getLeaveById(l.getLeaveId());
				 UserDto userDto = userService.getUserById(l.getEmployeeId());
				 int days=(int)(l.getToDate()-l.getFromDate())/86400;
				LeaveRequestResponse leaveRequest = LeaveRequestResponse.builder().id(l.getId())
						.fromDate(l.getFromDate()).toDate(l.getToDate()).leaveReason(l.getLeaveReason()).leave(leaveDto)
						.leaveType(l.getLeaveType()).status(l.getStatus()).days(days+1).employee(userDto).build();

				leaveRequestDtoList.add(leaveRequest);

			}
			Page<LeaveRequestResponse> leaveRequestDtoPage = new PageImpl<LeaveRequestResponse>(leaveRequestDtoList,
					page, leaveRequestDtoList.size());

			return leaveRequestDtoPage;
		} catch (Exception ex) {
			System.out.println("Exception in get all leave Request" + ex.getLocalizedMessage());
		}
		return null;

	}
	public Page<LeaveRequestResponse> employeeGetAllLeaveRequests(int index, int size){
		try {
			Pageable page = PageRequest.of(index, size);
			Page<LeaveRequest> leaveRequests = leaveRequestRepository.findAll(page);
			List<LeaveRequestResponse> leaveRequestDtoList = new ArrayList<>();
			for (LeaveRequest l : leaveRequests.getContent()) {
				LeaveDto leaveDto = leaveService.getLeaveById(l.getLeaveId());
				UserDto userDto = userService.getUserById(l.getEmployeeId());
				int days=(int)(l.getToDate()-l.getFromDate())/86400;
				LeaveRequestResponse leaveRequest = LeaveRequestResponse.builder().id(l.getId()).leave(leaveDto)
						.fromDate(l.getFromDate()).toDate(l.getToDate()).leaveReason(l.getLeaveReason()).employee(userDto)
						.leaveType(l.getLeaveType()).status(l.getStatus()).days(days+1).verifiedBy(l.getVerfiedBy()).build();

				leaveRequestDtoList.add(leaveRequest);

			}
			Page<LeaveRequestResponse> leaveRequestDtoPage = new PageImpl<LeaveRequestResponse>(leaveRequestDtoList,
					page, leaveRequestDtoList.size());

			return leaveRequestDtoPage;
		} catch (Exception ex) {
			System.out.println("Exception in get all leave Request" + ex.getLocalizedMessage());
		}
		return null;
	}

	@Override
	public LeaveRequestResponse saveLeaveRequest(LeaveRequestDto leaveRequest) {
		UserDto user = userService.getUserById(leaveRequest.getEmployeeId());
		LeaveDto leaveDto = leaveService.getLeaveById(leaveRequest.getLeaveId());
		LeaveRequest leaveRequestEntity = LeaveRequest.builder().id(leaveRequest.getId())
				.fromDate(leaveRequest.getFromDate()).toDate(leaveRequest.getToDate())
				.leaveReason(leaveRequest.getLeaveReason()).leaveId(leaveRequest.getLeaveId())
				.leaveType(leaveRequest.getLeaveType()).status(leaveRequest.getStatus())
				.employeeId(leaveRequest.getEmployeeId()).build();

		leaveRequestEntity = leaveRequestRepository.save(leaveRequestEntity);
		leaveRequest.setId(leaveRequestEntity.getId());

		return LeaveRequestResponse.builder().id(leaveRequest.getId()).fromDate(leaveRequest.getFromDate())
				.toDate(leaveRequest.getToDate()).leaveReason(leaveRequest.getLeaveReason()).leave(leaveDto)
				.leaveType(leaveRequest.getLeaveType()).status(leaveRequest.getStatus()).employee(user).build();

	}

	@Override
	public LeaveRequestResponse updateLeaveRequestDto(String id, LeaveRequestDto leaveRequest) {
		LeaveRequest leaveRequestEntity = leaveRequestRepository.findById(id).get();
		UserDto user = userService.getUserById(leaveRequest.getEmployeeId());
		LeaveDto leaveDto = leaveService.getLeaveById(leaveRequest.getLeaveId());
		leaveRequestEntity = LeaveRequest.builder().id(id).fromDate(leaveRequest.getFromDate())
				.toDate(leaveRequest.getToDate()).leaveReason(leaveRequest.getLeaveReason())
				.leaveId(leaveRequest.getLeaveId()).leaveType(leaveRequest.getLeaveType())
				.status(leaveRequest.getStatus()).employeeId(leaveRequest.getEmployeeId()).build();

		leaveRequestEntity = leaveRequestRepository.save(leaveRequestEntity);

		return LeaveRequestResponse.builder().id(leaveRequest.getId()).fromDate(leaveRequest.getFromDate())
				.toDate(leaveRequest.getToDate()).leaveReason(leaveRequest.getLeaveReason()).leave(leaveDto)
				.leaveType(leaveRequest.getLeaveType()).status(leaveRequest.getStatus()).employee(user).build();
	}

	@Override
	public String deleteLeaveRequest(String id) {
		leaveRequestRepository.deleteById(id);

		return id;
	}

	@Override
	public Page<LeaveRequestResponse> pagedLeaveDetailByEmployeeId(String employeeId, int index, int size) throws UsernameNotFoundException {
		Pageable page=PageRequest.of(index, size);
		Optional<User> userEntity = userRepository.findById(employeeId);
		if (userEntity.isEmpty())
			throw new UsernameNotFoundException(employeeId + " user not found");

		Page<LeaveRequest> leaveRequest = leaveRequestRepository.findByEmployeeId(employeeId,page);
		List<LeaveRequestResponse> leaveRequests = new ArrayList<>();
		for (LeaveRequest l : leaveRequest) {
			User u = userEntity.get();
			Optional<Leave> leaveEntity = leaveRepository.findById(l.getLeaveId());

			LeaveDto leave = LeaveDto.builder().id(l.getLeaveId()).leaveName(leaveEntity.get().getLeaveName()).build();
			UserDto employee = UserDto.builder().id(employeeId).fullName(u.getFullName()).email(u.getEmail())
					.department(u.getDepartment()).designation(u.getDesignation()).bio(u.getBio())
					.joinedDate(u.getJoinedDate()).photo(u.getPhoto()).build();
			LeaveRequestResponse leaveRequestResponse = LeaveRequestResponse.builder().id(l.getId())
					.fromDate(l.getFromDate()).toDate(l.getToDate()).leaveReason(l.getLeaveReason()).leave(leave)
					.leaveType(l.getLeaveType()).status(l.getStatus()).employee(employee).build();

			leaveRequests.add(leaveRequestResponse);
		}
		Page<LeaveRequestResponse> leaveRequestResponsePage=new PageImpl<LeaveRequestResponse>(leaveRequests, page, leaveRequests.size());

		return leaveRequestResponsePage;

	}
	
	@Override
	public List<LeaveRequestResponse> leaveDetailByEmployeeId(String employeeId){
		Optional<User> userEntity = userRepository.findById(employeeId);
		if (userEntity.isEmpty())
			throw new UsernameNotFoundException(employeeId + " user not found");

		List<LeaveRequest> leaveRequest = leaveRequestRepository.findByEmployeeId(employeeId);
		List<LeaveRequestResponse> leaveRequests = new ArrayList<>();
		for (LeaveRequest l : leaveRequest) {
			User u = userEntity.get();
			Optional<Leave> leaveEntity = leaveRepository.findById(l.getLeaveId());

			LeaveDto leave = LeaveDto.builder().id(l.getLeaveId()).leaveName(leaveEntity.get().getLeaveName()).build();
			UserDto employee = UserDto.builder().id(employeeId).fullName(u.getFullName()).email(u.getEmail())
					.department(u.getDepartment()).designation(u.getDesignation()).bio(u.getBio())
					.joinedDate(u.getJoinedDate()).photo(u.getPhoto()).build();
			LeaveRequestResponse leaveRequestResponse = LeaveRequestResponse.builder().id(l.getId())
					.fromDate(l.getFromDate()).toDate(l.getToDate()).leaveReason(l.getLeaveReason()).leave(leave)
					.leaveType(l.getLeaveType()).status(l.getStatus()).employee(employee).build();

			leaveRequests.add(leaveRequestResponse);
		}
		return leaveRequests;
	}

	public  List<LeaveRequest> filterDate() throws java.text.ParseException
	{	
		DateFormat formatter = new SimpleDateFormat("yyy/MM/dd");

		Date today1 = new Date();

		Date todayWithZeroTime = formatter.parse(formatter.format(today1));
		long todayunix = todayWithZeroTime.getTime() / 1000;
		
		List<LeaveRequest> leaveRequest = leaveRequestRepository.findAll();
		List<LeaveRequest> newlist = new ArrayList<>() ;
		
		for(LeaveRequest leaveReq:leaveRequest)
		{
			long from = leaveReq.getFromDate();
			long to = leaveReq.getToDate();
			if(from<=todayunix && to >= todayunix)
			{
				newlist.add(leaveReq);
			}
		}
		System.out.println(newlist);
		return newlist;
	}
	
	@Override
	public Page<LeaveRequestResponse> leaveDetailByTodayDate(int index, int size) throws java.text.ParseException {
		Pageable page = PageRequest.of(index, size);
		List<LeaveRequest> leaveRequest = filterDate();
		List<LeaveRequestResponse> leaveRequests = new ArrayList<>();
		for (LeaveRequest l : leaveRequest) {
			
//			if(l.getFromDate()<date &&date<l.getToDate()) {
				
			LeaveDto leaveDto = leaveService.getLeaveById(l.getLeaveId());
			UserDto userDto = userService.getUserById(l.getEmployeeId());
			LeaveRequestResponse leaveRequestResponse = LeaveRequestResponse.builder().id(l.getId())
					.fromDate(l.getFromDate()).toDate(l.getToDate()).leaveReason(l.getLeaveReason()).leave(leaveDto)
					.leaveType(l.getLeaveType()).status(l.getStatus()).employee(userDto).build();

			leaveRequests.add(leaveRequestResponse);
//			}
		}
		Page<LeaveRequestResponse> leaveRequestDtoPage = new PageImpl<LeaveRequestResponse>(leaveRequests, page,
				leaveRequests.size());

		return leaveRequestDtoPage;
	}

	@Override
	public LeaveRequestResponse updateLeaveStatus(String leaveRequestId, LeaveRequestStatusDto leaveRequestStatus,String adminId) {
		LeaveRequest leaveRequestEntity = leaveRequestRepository.findById(leaveRequestId).get();
		UserDto user = userService.getUserById(leaveRequestEntity.getEmployeeId());
		UserDto verifiedBy= userService.getUserById(adminId);

		LeaveDto leaveDto = leaveService.getLeaveById(leaveRequestEntity.getLeaveId());
		
		leaveRequestEntity.setStatus(leaveRequestStatus.getStatus());
		leaveRequestEntity.setVerfiedBy(verifiedBy.getFullName());

		leaveRequestEntity = leaveRequestRepository.save(leaveRequestEntity);

		return LeaveRequestResponse.builder().id(leaveRequestEntity.getId()).fromDate(leaveRequestEntity.getFromDate())
				.toDate(leaveRequestEntity.getToDate()).leaveReason(leaveRequestEntity.getLeaveReason()).leave(leaveDto)
				.leaveType(leaveRequestEntity.getLeaveType()).verifiedBy(verifiedBy.getFullName()).status(leaveRequestEntity.getStatus()).employee(user).build();
	
	}
}