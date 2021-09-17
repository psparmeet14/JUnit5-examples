package com.pslearning.junit5.helper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class StringHelperTest {

	StringHelper helper = new StringHelper();
	
	@Test
	void testTruncateAInFirst2Positions() {
		assertEquals("CD", helper.truncateAInFirst2Positions("AACD"));
		assertEquals("CD", helper.truncateAInFirst2Positions("ACD"));
		// AACD => CD ACD => CD CDEF => CDEF CDAA => CDAA
	}
	
	@ParameterizedTest(name = "{0} when AA is truncated gives {1}")
	@CsvSource(value = {"AACD, CD", "ACD, CD", "CDEF, CDEF", "CDAA, CDAA", "A,''"})
	void testTruncateStringParameterizedTest(String word, String expectedResult) {
		assertEquals(expectedResult, helper.truncateAInFirst2Positions(word));
	}

	@Test
	@DisplayName("First and Last two characters must be same")
	void testAreFirstAndLastTwoCharactersTheSame() {
		assertTrue(helper.areFirstAndLastTwoCharactersTheSame("AABCDAA"));
	}
	
	@ParameterizedTest(name = "first and last two characters of {0} must be same")
	@ValueSource(strings = {"AABCDAA", "CDDTACD", "GGFDGG", "AABSAA"})
	void firstAndLastTwoCharactersSameParameterizedTest(String word) {
		assertTrue(helper.areFirstAndLastTwoCharactersTheSame(word));
	}
}
