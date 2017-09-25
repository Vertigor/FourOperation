package fraction;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FractionsTest {
	
	private  Fractions fraction;

	@Before
	public void setUp() throws Exception {
		fraction = new Fractions(1,1);
	}

	@Test
	public void testSetValue() {
		fraction.setValue(2, 3);
		assertEquals(2, fraction.getNumerator());
		assertEquals(3, fraction.getDenominator());
	}

	@Test
	public void testMaxCommonDivisor() {
		int divisor = fraction.maxCommonDivisor(9, 6);
		assertEquals(3, divisor);
	}

	@Test
	public void testMinCommonMultiple() {
		int multiple = fraction.minCommonMultiple(9, 6);
		assertEquals(18, multiple);
	}

	@Test
	public void testPrintFraction() {
		assertEquals("1/1", fraction.printFraction());
	}

	@Test
	public void testChangeToInteger() {
		assertEquals(1, fraction.changeToInteger());
	}

}
