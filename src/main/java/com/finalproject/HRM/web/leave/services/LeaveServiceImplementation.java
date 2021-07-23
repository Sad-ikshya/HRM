package com.finalproject.HRM.web.leave.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.HRM.web.leave.dtos.LeaveDto;
import com.finalproject.HRM.web.leave.entities.Leave;
import com.finalproject.HRM.web.leave.repositories.LeaveRepository;

@Service
public class LeaveServiceImplementation implements LeaveService {
	@Autowired
	private LeaveRepository leaveRepository;

	@Override
	public LeaveDto saveLeave(LeaveDto leave) {
		Leave leaveEntity = Leave.builder().id(leave.getId()).leaveName(leave.getLeaveName()).days(leave.getDays())
				.build();

		leaveEntity = leaveRepository.save(leaveEntity);
		leave.setId(leaveEntity.getId());
		return leave;
	}

}
