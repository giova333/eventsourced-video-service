package com.gladunalexander.videoservice;

import com.gladunalexander.videoservice.events.Channels;
import com.gladunalexander.videoservice.external.AccountClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(Channels.class)
@EnableFeignClients(clients = AccountClient.class)
public class VideoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoServiceApplication.class, args);
	}

}
