package com.finalproject.HRM.web.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.finalproject.HRM.common.utils.FileUploadHelper;
import com.finalproject.HRM.web.user.dtos.UserDto;
import com.finalproject.HRM.web.user.entities.DeletedUser;
import com.finalproject.HRM.web.user.entities.Role;
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

	private Date today = new Date();
	
	public List<User> pagination(int pageNo, int limit, String sortBy,String department, String designation) {
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
		return userData.getContent();
	}
	
	@Override
	public List<UserDto> getAllUser(int pageNo, int limit, String sortBy,String department, String designation) {
		List<User> userList= pagination(pageNo,limit,sortBy,department, designation);
		List<UserDto> userDtoList=new ArrayList<>();
		for(User user: userList) {
			UserDto userDto= UserDto.builder().id(user.getId())
							.fullName(user.getFullName())
							.email(user.getEmail())
							.department(user.getDepartment())
							.designation(user.getDesignation())
							.bio(user.getBio())
							.joinedDate(user.getJoinedDate())
							.role(user.getRole())
							.photo(user.getPhoto())
							.build();
			
			userDtoList.add(userDto);
		}
		return userDtoList;
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
										.role(user.getRole())
										.photo(user.getPhoto())
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
										.role(user.getRole())
										.photo(user.getPhoto())
										.build();
		return userDto;
	}

	@Override
	public UserDto updateUser(String id, UserDto user) {
		User userEntity = userRepository.findById(id)
							.orElseThrow(()->new IllegalStateException("User not Found"));
		
		User updatedUser= null;
//		if(userEntity.getRole() == Role.ADMIN)
		{
			updatedUser=User.builder()
					.id(id)
					.fullName(user.getFullName()==null?userEntity.getFullName():user.getFullName())
					.email(user.getEmail()==null?userEntity.getEmail():user.getEmail())
					.department(user.getDepartment()==null || userEntity.getRole() != Role.ADMIN?
							userEntity.getDepartment():user.getDepartment())
					.designation(user.getDesignation()==null || userEntity.getRole() != Role.ADMIN?
							userEntity.getDesignation():user.getDesignation())
					.bio(user.getBio()==null?userEntity.getBio():user.getBio())
					.role(userEntity.getRole() != Role.ADMIN?userEntity.getRole():user.getRole())
					.photo(user.getPhoto()==null?userEntity.getPhoto():user.getPhoto())
					.joinedDate(userEntity.getJoinedDate())
					.build();
		}
//		else {
//			updatedUser=User.builder()
//					.id(id)
//					.fullName(user.getFullName()==null?userEntity.getFullName():user.getFullName())
//					.email(user.getEmail()==null?userEntity.getEmail():user.getEmail())
//					.department(userEntity.getDepartment())
//					.designation(userEntity.getDesignation())
//					.bio(user.getBio()==null?userEntity.getBio():user.getBio())
//					.role(user.getRole()==null?userEntity.getRole():user.getRole())
//					.photo(user.getPhoto()==null?userEntity.getPhoto():user.getPhoto())
//					.joinedDate(userEntity.getJoinedDate())
//					.build();
//		}
//		
		userRepository.save(updatedUser);
		return user;
	}
	
	@Override
	public String uploadImage(MultipartFile image) throws Exception
	{
//		System.out.println(image.getOriginalFilename());
//		System.out.println(image.getSize());
//		System.out.println(image.getContentType());
		
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
													.role(user.getRole())
													.photo(user.getPhoto())
													.build();
		deletedUserRepo.save(deletedUserEntity);
		userRepository.deleteById(id);
		return "User with id : "+id+" is deleted successfully";
	}
}

