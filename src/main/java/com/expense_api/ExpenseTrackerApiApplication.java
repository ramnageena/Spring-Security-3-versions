package com.expense_api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
@OpenAPIDefinition(info = @Info(title = "Expense Tracker API",
		description = "API service for managing and tracking user expenses, including adding, updating, retrieving, and deleting expense data.",
		contact = @Contact(
				name = "Ram Tiwari",
				email = " ramtiwari8716@gmail.com"
		)
)
)
public class ExpenseTrackerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseTrackerApiApplication.class, args);
      log.info("Expense Tracker API Started !!!!");

	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
