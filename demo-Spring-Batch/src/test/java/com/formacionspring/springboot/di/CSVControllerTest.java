package com.formacionspring.springboot.di;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.formacionspring.springboot.di.controller.CSVController;

@ExtendWith(MockitoExtension.class)
public class CSVControllerTest {

	private CSVController controller;
	
	@Test
	public void findAll() {
		Mockito
		.when(controller.findAll());
	}
}
