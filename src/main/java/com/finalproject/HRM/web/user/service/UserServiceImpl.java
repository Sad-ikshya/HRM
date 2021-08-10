package com.finalproject.HRM.web.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.finalproject.HRM.common.utils.FileUploadHelper;
import com.finalproject.HRM.web.user.dtos.UserPaginationData;
import com.finalproject.HRM.web.user.dtos.UserDto;
import com.finalproject.HRM.web.user.entities.DeletedUser;
import com.finalproject.HRM.web.user.entities.User;
import com.finalproject.HRM.web.user.repositories.DeletedUserRepository;
import com.finalproject.HRM.web.user.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	DeletedUserRepository deletedUserRepo;
	
	@Autowired
	FileUploadHelper fileHelper;
	
	public Page<User> pagination(int pageNo, int limit, String sortBy,String department, String designation) {
		Page<User> userData;
		Sort sort = Sort.by(sortBy);
		Pageable Page = PageRequest.of(pageNo, limit, sort);
		if(department != null && designation == null)
		{
			userData = userRepository.findByDepartment(department, Page);
		}
		else if(department == null && designation != null)
		{
			userData = userRepository.findByDesignation(designation, Page);
		}
		else if(department !=null && designation != null)
		{
			userData = userRepository.findByDepartmentAndDesignation(department, designation, Page);
		}
		else
		{
			userData = userRepository.findAll(Page);
		}
		return userData;
	}
	
	@Override
	public UserPaginationData getAllUser(int pageNo, int limit, String sortBy,String department, String designation) {
		Page<User> pagedUser = pagination(pageNo,limit,sortBy,department, designation);
		List<User> userList= pagedUser.getContent();
		List<UserDto> userDtoList=new ArrayList<>();
		for(User user: userList) {
			UserDto userDto= UserDto.builder().id(user.getId())
							.fullName(user.getFullName())
							.email(user.getEmail())
							.department(user.getDepartment())
							.designation(user.getDesignation())
							.bio(user.getBio())
							.joinedDate(user.getJoinedDate())
							.roles(user.getRoles())
							.photo(user.getPhoto())
							.contact(user.getContact())						.build();
			
			userDtoList.add(userDto);
		}
		UserPaginationData paginationData = UserPaginationData.builder()
										.currentPage(pagedUser.getNumber())
										.totalPage(pagedUser.getTotalPages())
										.usersData(userDtoList)
										.build();
		return paginationData;
	}

	@Override
	public UserDto getUserByEmail(String email) {
		User user=userRepository.findByEmail(email)
				.orElseThrow(()->new IllegalStateException("User not Found"));
			
		UserDto userDto= UserDto.builder().id(user.getId())
										.fullName(user.getFullName())
										.email(user.getEmail())
										.department(user.getDepartment())
										.designation(user.getDesignation())
										.bio(user.getBio())
										.joinedDate(user.getJoinedDate())
										.roles(user.getRoles())
										.photo(user.getPhoto())
										.contact(user.getContact())
										.build();
		return userDto;
	}
	
	@Override
	public UserDto getUserById(String id) {
		User user=userRepository.findById(id)
				.orElseThrow(()->new IllegalStateException("User not Found"));
			
		UserDto userDto= UserDto.builder().id(user.getId())
										.fullName(user.getFullName())
										.email(user.getEmail())
										.department(user.getDepartment())
										.designation(user.getDesignation())
										.bio(user.getBio())
										.joinedDate(user.getJoinedDate())
										.roles(user.getRoles())
										.photo(user.getPhoto())
										.contact(user.getContact())
										.build();
		return userDto;
	}

	@Override
	public UserDto updateUser(String id, UserDto user, String adminID) {
		User userEntity = userRepository.findById(id)
							.orElseThrow(()->new IllegalStateException("User not Found"));
		
		User updatedUser=User.builder()
					.id(id)
					.fullName(user.getFullName()==null?userEntity.getFullName():user.getFullName())
					.email(userEntity.getEmail())
					.department(user.getDepartment()==null || adminID == null?
							userEntity.getDepartment():user.getDepartment())
					.designation(user.getDesignation()==null || adminID == null?
							userEntity.getDesignation():user.getDesignation())
					.bio(user.getBio()==null?userEntity.getBio():user.getBio())
					.roles(user.getRoles()==null || adminID == null?
							userEntity.getRoles():user.getRoles())
					.photo(user.getPhoto()==null?userEntity.getPhoto():user.getPhoto())
					.joinedDate(userEntity.getJoinedDate())
					.contact(user.getContact()==null?userEntity.getContact():user.getContact())
					.build();
		userRepository.save(updatedUser);
		return UserDto.builder()
					.fullName(updatedUser.getFullName())
					.email(updatedUser.getEmail())
					.department(updatedUser.getDepartment())
					.designation(updatedUser.getDesignation())
					.bio(updatedUser.getBio())
					.roles(updatedUser.getRoles())
					.photo(updatedUser.getPhoto())
					.joinedDate(updatedUser.getJoinedDate())
					.contact(updatedUser.getContact())
					.build();
	}
	
	@Override
	public String uploadImage(MultipartFile image) throws Exception
	{
		
		if(image.isEmpty())
		{
			throw new Exception("Select Image");
		}
		
		if(!image.getContentType().equals("image/jpeg"))
		{
			throw new Exception("can upload only jpg image");
		}
		
	
		return fileHelper.upload(image);
	}
	
	@Override
	public String deleteUserById(String id)
	{
		User user=userRepository.findById(id)
				.orElseThrow(()->new IllegalStateException("User not Found"));
		DeletedUser deletedUserEntity = DeletedUser.builder().id(user.getId())
													.fullName(user.getFullName())
													.email(user.getEmail())
													.department(user.getDepartment())
													.designation(user.getDesignation())
													.bio(user.getBio())
													.joinedDate(user.getJoinedDate())
													.roles(user.getRoles())
													.photo(user.getPhoto())
													.contact(user.getContact())
													.build();
		deletedUserRepo.save(deletedUserEntity);
		userRepository.deleteById(id);
		return "User with id : "+id+" is deleted successfully";
	}
}

