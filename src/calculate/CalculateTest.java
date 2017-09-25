package calculate;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fraction.Fractions;

public class CalculateTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAddtion() {
		Fractions result = Calculate.addtion(new Fractions(1,2), new Fractions(1,2));
		assertEquals(1, result.getNumerator());
		assertEquals(1, result.getDenominator());
	}

	@Test
	public void testSubtraction() {
		Fractions result = Calculate.subtraction(new Fractions(1,2), new Fractions(1,2));
		assertEquals(0, result.getNumerator());
		assertEquals(1, result.getDenominator());
	}

	@Test
	public void testMultiplication() {
		Fractions result = Calculate.multiplication(new Fractions(1,2), new Fractions(1,2));
		assertEquals(1, result.getNumerator());
		assertEquals(4, result.getDenominator());
	}

	@Test
	public void testDivision() {
		Fractions result = Calculate.division(new Fractions(1,2), new Fractions(1,2));
		assertEquals(1, result.getNumerator());
		assertEquals(1, result.getDenominator());
	}

}
