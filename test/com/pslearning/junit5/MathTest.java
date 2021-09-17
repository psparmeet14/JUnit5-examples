package com.pslearning.junit5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MathTest {

	@Test
	void minTest() {
		int actualMinimumNumber = Math.min(4, 6);
		
		int expectedMinimumNumber = 4;
		
		assertEquals(expectedMinimumNumber, actualMinimumNumber);
	}

	@Test
	void maxTest() {
		int actualMaximumNumber = Math.max(12, 14);
		
		int expectedMaximumNumber = 14;
		
		assertEquals(expectedMaximumNumber, actualMaximumNumber);
	}
}
