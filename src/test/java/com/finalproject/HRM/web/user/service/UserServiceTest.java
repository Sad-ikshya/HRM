package com.finalproject.HRM.web.user.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.finalproject.HRM.web.user.dtos.UserDto;
import com.finalproject.HRM.web.user.entities.DeletedUser;
import com.finalproject.HRM.web.user.entities.Role;
import com.finalproject.HRM.web.user.entities.User;
import com.finalproject.HRM.web.user.repositories.DeletedUserRepository;
import com.finalproject.HRM.web.user.repositories.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

	@Mock
	private static UserRepository userRepo;
	
	@Mock
	private static DeletedUserRepository deletedUserRepo;
	
	@InjectMocks
	UserServiceImpl userServiceImpl;
	
	private static Date today;
	
	@BeforeAll
	public static void init()
	{
		today = new Date();
	}
	
//	@Disabled
//	@Test
//	public void testGetListOfUser() {
//		int pageNo = 0;
//		int limit = 4;
//		Sort sort = Sort.by("id");
//		PageRequest page = PageRequest.of(pageNo, limit, sort);
//		List<User> userList = new ArrayList<>();
//		userList.add(new User("123","rukesh basukala","rukeshbasukala@gmail.com","backend","intern",
//				"rukesh bio",today,Role.EMPLOYEE,""));
//		userList.add(new User("124","manika basukala","manika@gmail.com","backend","developer",
//				"mainka bio",today,Role.EMPLOYEE,""));
//		userList.add(new User("125","pranisha baniya","pranisha@gmail.com","frontend","intern",
//				"pranisha bio",today,Role.EMPLOYEE,""));
//		userList.add(new User("126","kamless swual","kamless@gmail.com","frontend","develpoer",
//				"kamless bio",today,Role.EMPLOYEE,""));
//		when(userRepo.findAll(page)).thenReturn((Page<User>) userList);
//		when(userRepo.findByDepartment("backend", page)).thenReturn(null);
//		when(userRepo.findByDesignation("intern", page)).thenReturn(null);
//		when(userRepo.findByDepartmentAndDesignation("backend", "intern", page));
//		
//		List<UserDto> result = userServiceImpl.getAllUser(0, 4, "id", null, null);
//		assertEquals(2, result.size());
//	}

	@Test
	public void testAddUser()
	{
		User user = new User("123","rukesh basukala","rukeshbasukala@gmail.com","backend","intern",
				"rukesh bio",today,Role.EMPLOYEE,"");
		when(userRepo.insert(user)).thenReturn(user);
		
		UserDto userDto = new UserDto("123","rukesh basukala","rukeshbasukala@gmail.com","backend","intern",
				"rukesh bio",today,Role.EMPLOYEE,"");
		UserDto result = userServiceImpl.saveUser(userDto);
		assertEquals(userDto, result);
	}

	@Test
	public void testGetUserById()
	{
		User user = new User("123","rukesh basukala","rukeshbasukala@gmail.com","backend","intern",
				"rukesh bio",today,Role.EMPLOYEE,"");
		when(userRepo.findById("123")).thenReturn(Optional.of(user));
		
		UserDto userDto = new UserDto ("123","rukesh basukala","rukeshbasukala@gmail.com","backend","intern",
				"rukesh bio",today,Role.EMPLOYEE,"");
		UserDto result = userServiceImpl.getUserById("123");
		
		assertEquals("rukesh basukala", result.getFullName());
		assertEquals("rukeshbasukala@gmail.com", result.getEmail());
		assertEquals("backend", result.getDepartment());
		assertEquals("intern", result.getDesignation());
		assertEquals("rukesh bio", result.getBio());
		assertEquals(today, result.getJoinedDate());
		assertEquals(Role.EMPLOYEE, result.getRole());
//		assertEquals(userDto, result);
	}
	
	@Test
	public void testUpdateUser()
	{
		User user = new User("123","rukesh basukala","rukeshbasukala@gmail.com","backend","intern",
				"rukesh bio",today,Role.EMPLOYEE,"");
		when(userRepo.findById("123")).thenReturn(Optional.of(user));
		when(userRepo.save(user)).thenReturn(user);
		
		UserDto userDto = new UserDto("123","rukesh basukala","rukeshbasukala@gmail.com","backend","intern",
				"rukesh bio",today,Role.EMPLOYEE,"");
		UserDto result = userServiceImpl.saveUser(userDto);
		assertEquals(userDto, result);
	}
	
	@Test
	public void testDeleteUser()
	{
		User user = new User("123","rukesh basukala","rukeshbasukala@gmail.com","backend","intern",
				"rukesh bio",today,Role.EMPLOYEE,"");
		DeletedUser deletedUser = new DeletedUser("123","rukesh basukala",
				"rukeshbasukala@gmail.com","backend","intern","rukesh bio",today,Role.EMPLOYEE,"");
		when(userRepo.findById("123")).thenReturn(Optional.of(user));
		when(deletedUserRepo.save(deletedUser)).thenReturn(deletedUser);
		
		userServiceImpl.deleteUserById("123");
		verify(userRepo, times(1)).deleteById("123");
		
		String result = userServiceImpl.deleteUserById("123");
		assertEquals("User with id : 123 is deleted successfully", result);
	}
}
