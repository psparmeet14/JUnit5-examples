package com.pslearning.junit5;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

// Absence of failure is success (fundamental of unit testing)
// assertions - Checks in place
// CUT - Code under test

/*
 * Assertions:
 * assertEquals()
 * assertNotNull()
 * assertNull()
 * assertFalse()
 * assertTrue()
 * assertArrayEquals()
 * assertThrows() instead of expected attribute
 * assertTimeout() instead of timeout attribute
 * assertAll()
 * 
 * Annotations:
 * @Test
 * @BeforeEach instead of @Before
 * @AfterEach instead of @After
 * @BeforeAll instead of @BeforeClass
 * @AfterAll instead of @AfterClass
 * @DisplayName
 * @ParameterizedTest
 * @ValueSource
 * @CsvSource
 * @RepeatedTest NEW in JUNIT5
 * @Disabled instead of @Ignore
 * @Nested NEW in JUNIT5
 * @Tag instead of @Category
 * 
 * Classes:
 * TestInfo
 * 
 * Inject Parameters into JUnit Jupiter Unit Tests
 * A core JUnit5 philosophy - prefer extension points over features.
 * 1. one of the extension point interface - ParameterResolver - used to inject parameters into test methods.
 * 
 * Ways to make JUnit aware of extensions (a process known as "registration")
 * - declarative registration (i.e. registration via source code)
 */
class StringTest {
	
	private String str;
	
	@BeforeAll
	static void beforeAll() {
		System.out.println("Initialize connection to database");
	}
	
	@AfterAll 
	static void afterAll() {
		System.out.println("Close connection to database");
	}
	
	@BeforeEach
	void beforeEach(TestInfo testInfo) {
		System.out.println("Initialize Test Data for " + testInfo.getDisplayName());
	}
	
	@AfterEach
	void afterEach(TestInfo testInfo) {
		System.out.println("Clean up Test Data for " + testInfo.getDisplayName());
	}

	@Test
	@Disabled
	void length_basic() {
		// Write test code
		// Invoke method square(4) => CUT
		// Checks in place - 16 => Assertions (most important part of Junit test)
		int actualLength = "ABCD".length();
		int expectedLength = 4;
		assertEquals(expectedLength, actualLength);
	}
	
	@Test
	void length_greater_than_zero() {
		assertTrue("ABCD".length() > 0);
		assertTrue("ABC".length() > 0);
		assertTrue("A".length() > 0);
		assertTrue("DEF".length() > 0);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"ABCD", "ABC", "A", "DEF"})
	void length_greater_than_zero_parameterized_test(String str) {
		assertTrue(str.length() > 0);
	}
	
	@ParameterizedTest
	@ValueSource(ints = {11, 22, 44, 100})
	void number_is_greater_than_ten(int number) {
		assertTrue(number > 10);
	}
	
	@ParameterizedTest
	@ValueSource(longs = {100L, 200L, 300L, 400L, 500L}) 
	void check_number_multiple_of_hundred(long longNumber){
		assertTrue((longNumber % 100) == 0);
	}
	
	@ParameterizedTest(name = "{0} toUpperCase is {1}")
	@CsvSource(value = {"abcd, ABCD", "abc, ABC", "'',''", "abcdefg, ABCDEFG"})
	void upperCase(String word, String capitalizedWord) {
		assertEquals(capitalizedWord, word.toUpperCase());
	}
	
	@ParameterizedTest(name = "{0} length is {1}")
	@CsvSource(value = {"abcd, 4", "abc, 3", "'', 0", "abcdefg, 7"})
	void length(String word, int expectedLength) {
		assertEquals(expectedLength, word.length());
	}
	
	@ParameterizedTest
	@CsvSource(value = {"abcd, c", "according, c", "rajesh, j", "parmeet, r"})
	void charAt(String word, char expectedCharacter) {
		assertEquals(expectedCharacter, word.charAt(2));
	}
	
	@Test
	@DisplayName("When String is null, throw an exception")
	void length_exception() {
		String str = null;
		assertThrows(NullPointerException.class, () -> str.length());
	}

	@Test
	void toUpperCase_basic() {
		assertEquals("ABCD", "abcd".toUpperCase());
	}
	
	@Test
	@RepeatedTest(10)
	void contains_basic() {
		assertFalse("abcdefgh".contains("ijk"));
	}
	
	@Test
	@RepeatedTest(5)
	void charAt_basic() {
		assertEquals('b', "abcd".charAt(1));
	}
	
	@Test
	void split_basic() {
		String str = "abc def ghi";
		String actualResult[] = str.split(" ");
		String[] expectedResult = new String[] {"abc", "def", "ghi"};
		
		assertArrayEquals(expectedResult, actualResult);
	}
	
	@Test
	void performanceTest() {
		assertTimeout(Duration.ofSeconds(5), () -> {
			for (int i = 0; i < 500000; i++) {
				int j = i;
				System.out.println(j);
			}
		});
	}
	
	@Nested
	@DisplayName("For an empty string")
	class EmptyStringTests {
		
		@BeforeEach
		void setToEmpty() {
			str = "";
		}
		
		@Test
		@DisplayName("Length should be zero")
		void lengthIsZero() {
			assertEquals(0, str.length());
		}
		
		@Test
		@DisplayName("Uppercase is empty")
		void upperCaseIsEmpty() {
			assertEquals("", str.toUpperCase());
		}
	}
	
	@Nested
	@DisplayName("For large strings")
	class LargeStringTests {
		
		@BeforeEach
		void setToLargeString() {
			str = "abdajuhdjahfojihfaudhfjlkdahgfjldahgljdahgdjalhg";
		}
		
		@Test
		@DisplayName("Length should be greater than 10")
		void lengthIsGreaterThanTen() {
			assertTrue(str.length() > 10);
		}
		
		@Test 
		@DisplayName("String should contain 'fojih'")
		void contains() {
			assertTrue(str.contains("fojih"));
		}
	}
	
}
