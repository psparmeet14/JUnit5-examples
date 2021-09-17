package com.pslearning.junit5.helper;

public class MathHelper {
	
	public int sum(int[] numbers) {
		int sum = 0;
		for (int number : numbers) {
			sum += number;
		}
		return sum;
	}
}
