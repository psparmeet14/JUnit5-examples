package com.pslearning.junit5.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class MathHelperTest {
	MathHelper mathHelper = new MathHelper();
	
	@BeforeAll
	static void beforeAll() {
		System.out.println("Before all");
	}
	
	@BeforeEach
	void before() {
		System.out.println("Before each test");
	}
	
	@Test
	void sum_with3numbers() {
		System.out.println("Test 1");
		assertEquals(6, mathHelper.sum(new int[] {1,2,3}));
	}
	
	@Test
	void sum_with1number() {
		System.out.println("Test 2");
		assertEquals(1, mathHelper.sum(new int[] {1}));
	}
	
	@AfterEach
	void after() {
		System.out.println("After each test");
	}
	
	@AfterAll
	static void afterAll() {
		System.out.println("After all");	
	}
}
