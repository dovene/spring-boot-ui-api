package com.dev.uiapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = {"com.dev.uiapi.proxies"})
@SpringBootApplication
public class UiApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UiApiApplication.class, args);
	}

}
