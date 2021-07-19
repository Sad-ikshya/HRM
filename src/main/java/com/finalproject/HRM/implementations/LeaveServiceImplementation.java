package com.finalproject.HRM.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.HRM.dtos.LeaveDto;
import com.finalproject.HRM.entities.Leave;
import com.finalproject.HRM.repositories.LeaveRepository;
import com.finalproject.HRM.services.LeaveService;

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
