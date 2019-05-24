package com.restaurantsolution.controller;


import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
//import com.google.gson.Gson;
import com.restaurantsolution.dao.OrderDetailsDao;
import com.restaurantsolution.domain.AppUser;
import com.restaurantsolution.domain.AppUserRole;
import com.restaurantsolution.domain.ItemType;
import com.restaurantsolution.domain.OrderDetails;
//import com.restaurantsolution.domain.FoodItems;
//import com.restaurantsolution.domain.FoodItemsType;
import com.restaurantsolution.domain.UserRole;
import com.restaurantsolution.repository.AppRoleRepository;
import com.restaurantsolution.repository.AppUserRoleRepository;
import com.restaurantsolution.repository.ApplicationRepository;
import com.restaurantsolution.repository.FoodRepository;
import com.restaurantsolution.repository.OrderDetailsRepository;
import com.restaurantsolution.service.UserService;


@RestController
@RequestMapping("/Restaurant")
public class RestrauntController {

	@Autowired
	UserService userService;
	
	@Autowired
	ApplicationRepository applicationRepository;
	
	@Autowired
	AppRoleRepository appRoleRepository;

	@Autowired
	AppUserRoleRepository appUserRoleRepository;
	 
	@Autowired
	FoodRepository foodRepository;
	
	@Autowired
	OrderDetailsDao orderDetailsDao;
	
	@Autowired
	OrderDetailsRepository orderDetailsRepository;
	
	
@GetMapping("/")	
public String test() {
	
	return "Hi";
	
}

@CrossOrigin
@RequestMapping(value = "/user", method = RequestMethod.GET)
public AppUser saveUserDetails(String cli,String fullName) {
AppUser appuser=applicationRepository.findByContact(cli);
System.out.println("appuser--->" + appuser);
	
	if(appuser == null) {
		System.out.println("into null");

Set<AppUserRole> appUserRoleSet = new HashSet<AppUserRole>();
System.out.println("cli--->" + "fullname--->" + cli + fullName);
AppUser appUser2=new AppUser();
appUser2.setContact(cli);	
appUser2.setFullName(fullName);
appUser2.setPassword("");
appUser2.setUsername("");

AppUserRole appUserRole=new AppUserRole();
UserRole userRole=new UserRole();

 userRole=appRoleRepository.findByroleName("customer");
System.out.println("userRole" + userRole.getId());

appUserRole.setUserRole(userRole);
appUserRole.setAppUser(appUser2);
System.out.println("appUserRole-->" + appUserRole.getAppUser().getFullName());
System.out.println("appUserRole-->" + appUserRole.getUserRole().getRoleName());

appUserRoleSet.add(appUserRole);

//System.out.println("userRole" + userRole.getId());

appUser2.setAppUserRoles(appUserRoleSet);

//System.out.println("appuser-->" + new Gson().toJson(appUser));
 applicationRepository.save(appUser2);
 appUserRoleRepository.save(appUserRole);

 return appUser2;
	}
	else {
		System.out.println("into  non null");
		return appuser;
	}
	
}

@RequestMapping(value = "/admin/user", method = RequestMethod.GET)
public AppUser getAdminUserDetails(String username,String password) {
	
	return userService.getAdminUserByUserName(username, password);
}


@CrossOrigin
@GetMapping(value="/otp/send",produces = MediaType.APPLICATION_JSON_VALUE)
public String sendOtp(String contact,String authkey)
{
	Random random = new Random();
	String randomPIN = String.format("%04d", random.nextInt(10000));
	System.out.println("otp--->"+randomPIN);
	
	
	 String url ="http://control.msg91.com/api/sendotp.php?authkey="+authkey+"&sender=Restaurant&mobile="+contact+"&message=your verification code is "+randomPIN+"&otp_expiry=1&otp="+randomPIN+"";
	 System.out.println(url);
	 RestTemplate restTemplate = new RestTemplate();
	System.out.println(restTemplate.getForObject(url, String.class));
	 
	 return randomPIN;
	  
	 
	

}

@CrossOrigin
@GetMapping(value="/otp/verify",produces = MediaType.APPLICATION_JSON_VALUE)
public String verifyOtp(String contact,String authkey,String otp)
{
	
	System.out.println("verifyOtp"+otp);
	 String url ="https://control.msg91.com/api/verifyRequestOTP.php?authkey="+authkey+"&mobile="+contact+"&otp="+otp;
	// System.out.println(url);
	 RestTemplate restTemplate = new RestTemplate();
	System.out.println(restTemplate.getForObject(url, String.class));
	String data=restTemplate.getForObject(url, String.class);
	 
	 return data;
	  
	 
	

}


@CrossOrigin
@GetMapping(value="/getfooditems",produces = MediaType.APPLICATION_JSON_VALUE)
public List<ItemType> getFoodItems()
{
	System.out.println("food details" + foodRepository.findAll());
return foodRepository.findAll();

}

@CrossOrigin
@PostMapping(value="/saveOrderDetails")
@ResponseBody
public String saveOrderDetails(HttpServletRequest request,HttpServletResponse response) {
	String jsonData=request.getParameter("data");
	System.out.println("jsonData--->"+jsonData);
	String message=orderDetailsDao.saveOrderDetails(jsonData);
	return message;
}


@CrossOrigin
@GetMapping(value="admin/getOrderDetails",produces = MediaType.APPLICATION_JSON_VALUE)
public List<OrderDetails> getOrderDetails()
{
//	System.out.println("data-->" + new GsonBuilder().create().toJson(OrderDetailsRepository.findAll()));
	System.out.println("data-->"+orderDetailsRepository.findAll().iterator().next().getOrderInitiatedAt());
//	orderDetailsDao.scheduleService();
	return orderDetailsRepository.findAll();
	

}

@CrossOrigin
@PutMapping(value="admin/UpdateOrderDetails")
@ResponseBody
public String updateOrderDetails(HttpServletRequest request,HttpServletResponse response) {
	String jsonData=request.getParameter("orderDetails");
	System.out.println("jsonData--->"+jsonData);
	OrderDetails orderDetails=new OrderDetails();
	JsonObject jobject = new Gson().fromJson(jsonData, JsonObject.class);
	orderDetails=orderDetailsRepository.getOne(jobject.get("orderId").getAsLong());
//	String message=orderDetailsDao.saveOrderDetails(jsonData);
	Date date=java.util.Calendar.getInstance().getTime();  
	System.out.println(date);
	if(jobject.get("action").getAsString().equalsIgnoreCase("delivered")) {
		orderDetails.setOrderDeliveredAt(date);
	}
	else if(jobject.get("action").getAsString().equalsIgnoreCase("accept")) {
		orderDetails.setOrderProcessedAt(date);
		orderDetails.setOrderWaitTime(jobject.get("timeRequire").getAsString());
		
	}
	
	orderDetails.setOrderFlag(jobject.get("action").getAsString());
	
	orderDetailsRepository.save(orderDetails);
	return "true";
	
}

@CrossOrigin
@GetMapping(value="/getOrderDetails",produces = MediaType.APPLICATION_JSON_VALUE)
public List<OrderDetails> getOrderDetails(String tableId,String contact)
{

	List<OrderDetails> orderdetails=orderDetailsRepository.findByTableIdContact(tableId,contact);
	if(!orderdetails.isEmpty()) {
		return orderdetails;
		
	}
	else {
		
		return null;
	}

}
	
	
}
