package com.hn.example.demohn.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.hn.example.demohn"})
@MapperScan(basePackages = { "com.hn.example.demohn.biz.dao" })
public class DemohnApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemohnApplication.class, args);
	}

	@RestController
	public static class EchoTest {

		@GetMapping("/")
		public String echo() {
			return "DemohnApplication";
		}

	}

}
