package com.charset.timezone.service;

import com.charset.timezone.model.TimeZone;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Service
public class TimeZoneServiceImpl implements TimeZoneService {

	RestTemplate restTemplate = new RestTemplate();
    public static String error;

    public TimeZone getTimeZoneData(String endPoint) {
		TimeZone response = null;error="";
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		if(endPoint.startsWith("US")) {
			 try {
				 response = restTemplate.getForObject("http://worldtimeapi.org/api/timezone/" + "America/" + endPoint.split(":")[1].trim(), TimeZone.class)
						 ;
			 }catch (HttpClientErrorException e){
			 	error = "Resource not found";
			 }
		}
		return response;
	}
}
