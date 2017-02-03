import static org.junit.Assert.*;

import org.junit.Test;

public class EquationTest {

	@Test
	public void testSolve() {
		Equation e = new Equation("(5*x)");
		assertEquals(e.solve(1.0), 5, .001);
		assertEquals(e.solve(2.5), 12.5, .001);
		assertEquals(e.solve(-3), -15, .001);
		assertEquals(e.solve(0), 0, .001);
		
		e = new Equation("(x^2)");
		assertEquals(e.solve(1.0), 1, .001);
		assertEquals(e.solve(2.5), 6.25, .001);
		assertEquals(e.solve(-3), 9, .001);
		assertEquals(e.solve(0), 0, .001);
		
		e = new Equation("(2)");
		assertEquals(e.solve(1.0), 2, .001);
		assertEquals(e.solve(2.5), 2, .001);
		assertEquals(e.solve(-3), 2, .001);
		assertEquals(e.solve(0), 2, .001);
	}
	
	@Test
	public void testSlopeAt() {
		Grapher g = new Grapher("(5*x)");
		assertEquals(g.slopeAt(1.0), 5.0, .001);
		assertEquals(g.slopeAt(2.5), 5.0, .001);
		assertEquals(g.slopeAt(-3), 5.0, .001);
		assertEquals(g.slopeAt(0), 5.0, .001);
		
		g = new Grapher("(x^2)");
		assertEquals(g.slopeAt(1.0), 2.0, .001);
		assertEquals(g.slopeAt(2.5), 5.0, .001);
		assertEquals(g.slopeAt(-3), -6.0, .001);
		assertEquals(g.slopeAt(0), 0.0, .001);
		
		g = new Grapher("(2)");
		assertEquals(g.slopeAt(1.0), 0.0, .001);
		assertEquals(g.slopeAt(2.5), 0.0, .001);
		assertEquals(g.slopeAt(-3), 0.0, .001);
		assertEquals(g.slopeAt(0), 0.0, .001);
	}
	
	@Test
	public void testIntegrate() {
		Grapher g = new Grapher("(5*x)");
		assertEquals(g.integrate(0, 1.0, null), 2.5, .001);
		assertEquals(g.integrate(0, 2.5, null), 15.625, 15.625*0.001);
		assertEquals(g.integrate(0, -3, null), 22.5, .001);
		assertEquals(g.integrate(0, 0, null), 0, .001);
		
		g = new Grapher("(x^2)");
		assertEquals(g.integrate(0, 1.0, null), 1.0/3.0, .001);
		assertEquals(g.integrate(0, 2.5, null), 5.2083, .001);
		assertEquals(g.integrate(0, -3, null), -9, .001);
		assertEquals(g.integrate(0, 0, null), 0, .001);
		
		g = new Grapher("(2)");
		assertEquals(g.integrate(0, 1.0, null), 2, .001);
		assertEquals(g.integrate(0, 2.5, null), 5, .001);
		assertEquals(g.integrate(0, -3, null), -6, .001);
		assertEquals(g.integrate(0, 0, null), 0, .001);
	}
	
	

}
