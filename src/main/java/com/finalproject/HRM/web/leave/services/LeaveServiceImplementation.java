package com.finalproject.HRM.web.leave.services;

import java.util.ArrayList;
import java.util.List;

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

	@Override
	public List<LeaveDto> getAllLeaves() {
		List<Leave> leaves=leaveRepository.findAll();
		List<LeaveDto> leaveDtoList=new ArrayList<>();
		for(Leave leave:leaves) {
			LeaveDto leaveDto=LeaveDto.builder().id(leave.getId()).leaveName(leave.getLeaveName()).days(leave.getDays())
					.build();
			
			leaveDtoList.add(leaveDto);
		}
		return leaveDtoList;
	}

	@Override
	public LeaveDto updateLeave(String id, LeaveDto leave) {
		Leave leaveEntity = Leave.builder().id(id).leaveName(leave.getLeaveName()).days(leave.getDays())
				.build();

		leaveEntity = leaveRepository.save(leaveEntity);
		leave.setId(leaveEntity.getId());
		return leave;
	}

	@Override
	public LeaveDto getLeaveById(String id) {
		Leave leave=leaveRepository.findById(id).get();
		return LeaveDto.builder().id(leave.getId()).leaveName(leave.getLeaveName()).days(leave.getDays())
				.build();
	}

	@Override
	public String deleteLeave(String id) {
		leaveRepository.deleteById(id);
		return id;
	}

	

}
