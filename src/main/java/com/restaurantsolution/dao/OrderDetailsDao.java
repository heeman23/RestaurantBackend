package com.restaurantsolution.dao;

import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.restaurantsolution.domain.OrderDetails;
import com.restaurantsolution.domain.OrderFoodDetails;
import com.restaurantsolution.repository.ApplicationRepository;
import com.restaurantsolution.repository.OrderDetailsRepository;
import com.restaurantsolution.repository.OrderFoodDetailsRepository;

@Service
public class OrderDetailsDao {

@Autowired
ApplicationRepository applicationRepository;

@Autowired
OrderDetailsRepository orderDetailsRepository;

@Autowired

OrderFoodDetailsRepository orderFoodDetailsRepository;
	
public String saveOrderDetails(String orderDetails)	{
	
try {
	
	JsonObject jobject = new Gson().fromJson(orderDetails, JsonObject.class);
//	JSONObject jobject=(JSONObject)new JSONParser().parse(orderDetails);
//	String paymentAmount=(String)jobject.get("paymentAmount");
	String paymentAmount=jobject.get("paymentAmount").getAsString();
	OrderDetails orderdetails=new OrderDetails();
	orderdetails.setPaymentAmount(paymentAmount);
	
	orderdetails.setAppUser(applicationRepository.findByContact(jobject.get("usercontact").getAsString()));
	
	orderdetails.setIsPaymentDone(false);
	orderdetails.setOrderFlag("INITIATED");
	Date date=java.util.Calendar.getInstance().getTime();  
	System.out.println(date);
	orderdetails.setOrderInitiatedAt(date);
	orderdetails.setTableNumber(jobject.get("tableId").getAsString());
//	@SuppressWarnings("unchecked")
//	
//	List<OrderFoodDetails> listOrderFoodDetails = (List<OrderFoodDetails>)jobject.get("orderDetails");
//	orderdetails.setOrderFoodDetails(listOrderFoodDetails);
	
	orderDetailsRepository.save(orderdetails);
	

	JsonArray jsonArray=(JsonArray)jobject.get("orderDetails").getAsJsonArray();
	for(int i=0; i<jsonArray.size(); i++) {
		OrderFoodDetails orderFoodDetails=new OrderFoodDetails();
		System.out.println("foodname--->"+((JsonObject)jsonArray.get(i).getAsJsonObject()).get("orderFood").getAsString());
		orderFoodDetails.setOrderFood(((JsonObject)jsonArray.get(i).getAsJsonObject()).get("orderFood").getAsString());
		orderFoodDetails.setOrderFoodType(((JsonObject)jsonArray.get(i).getAsJsonObject()).get("orderFoodType").getAsString());
		orderFoodDetails.setOrderDetails(orderdetails);
		orderFoodDetailsRepository.save(orderFoodDetails);
	}
	
//	orderdetails.setOrderFoodDetails(jobject.get("orderDetails"));
	return "success";
} catch (Exception e) {
	
	// TODO Auto-generated catch block
	System.out.println(e.getMessage());
	e.printStackTrace();
	return "failure";
}	


	
	
}
	
@Scheduled(fixedRate = 5000)
public void scheduleService() {
	System.out.println("schedule service running");
	
} 
	
}
