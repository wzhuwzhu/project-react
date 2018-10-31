package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import common.*;
import entity.*;
import function.*;
import mapper.*;
import web.*;

@SpringBootApplication
public class ChemLabApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChemLabApplication.class, args);
		StaffManagement staff = StaffManagementMapper.selectAll();
		System.out.println(staff);
	}
}
