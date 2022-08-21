package com.up.socketservice.utils;

import com.google.gson.Gson;
import com.up.socketservice.dto.DriverDto;
import com.up.socketservice.dto.HailingDto;
import com.up.socketservice.dto.LocationDto;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.client.RestTemplate;

public class SaveDbUtil {
    public static Integer saveLocation(LocationDto locationDto) throws Exception {
        String postUrl = "http://localhost:9090/api/location";
        Gson gson = new Gson();
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(postUrl);
        StringEntity postingString = new StringEntity(gson.toJson(locationDto), "UTF-8"); //gson.tojson() converts your pojo to json
        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json");

        HttpResponse response = httpClient.execute(post);


        return Integer.parseInt(EntityUtils.toString(response.getEntity()));
    }

    public static Integer saveHailing(HailingDto hailingDto) throws Exception {
        String postUrl = "http://localhost:9090/api/hailing";
        Gson gson = new Gson();
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(postUrl);
        StringEntity postingString = new StringEntity(gson.toJson(hailingDto), "UTF-8"); //gson.tojson() converts your pojo to json
        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json");

        HttpResponse response = httpClient.execute(post);


        return Integer.parseInt(EntityUtils.toString(response.getEntity()));
    }

    public static Integer updateDriver(String driverId, Double cost) throws Exception {
        String uri = "http://localhost:9090/api/driver/1";
        RestTemplate restTemplate = new RestTemplate();
        DriverDto driverDto = restTemplate.getForObject(uri, DriverDto.class);

        driverDto.setBalance((long) (driverDto.balance + cost));
        driverDto.setRide_count(driverDto.ride_count + 1);

        String postUrl = "http://localhost:9090/api/driver";
        Gson gson = new Gson();
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(postUrl);
        StringEntity postingString = new StringEntity(gson.toJson(driverDto), "UTF-8"); //gson.tojson() converts your pojo to json
        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json");

        HttpResponse response = httpClient.execute(post);


        return Integer.parseInt(EntityUtils.toString(response.getEntity()));
    }
}
