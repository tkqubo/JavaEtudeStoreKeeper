package test.com.qubo.challenge.storekeeper.elements;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import com.qubo.challenge.storekeeper.elements.Position;

public class PositionTest {
	Position u = Position.UP;
	Position d = Position.DOWN;
	Position l = Position.LEFT;
	Position r = Position.RIGHT;
	Position p1 = new Position(0, 0);
	Position p2 = new Position(1, 0);
	Position p3 = new Position(5, 8);
	Position p4 = new Position(4, 2);

	@Test
	public void testGetRow() {
		assertThat(u.getRow(), is(-1));
		assertThat(d.getRow(), is(1));
		assertThat(l.getRow(), is(0));
		assertThat(r.getRow(), is(0));
		assertThat(p1.getRow(), is(0));
		assertThat(p2.getRow(), is(1));
		assertThat(p3.getRow(), is(5));
		assertThat(p4.getRow(), is(4));
	}
	@Test
	public void testGetCol() {
		assertThat(u.getCol(), is(0));
		assertThat(d.getCol(), is(0));
		assertThat(l.getCol(), is(-1));
		assertThat(r.getCol(), is(1));
		assertThat(p1.getCol(), is(0));
		assertThat(p2.getCol(), is(0));
		assertThat(p3.getCol(), is(8));
		assertThat(p4.getCol(), is(2));
	}
	@Test
	public void testAdd() {
		assertThat(u.add(d), is(p1));
		assertThat(d.add(u), is(new Position(0, 0)));
		assertThat(l.add(r), is(p1));
		assertThat(r.add(l), is(new Position(0, 0)));
		assertThat(p2.add(u), is(p1));
		Position r4 = r.add(r).add(r).add(r);
		Position d2 = d.add(d);
		assertThat(r4.add(d2).add(r4).add(d).add(d2), is(p3));
		assertThat(d.add(d).add(d).add(d).add(r).add(r), is(p4));
	}

	@Test
	public void testReverse() {
		assertThat(u.reverse(), is(d));
		assertThat(d.reverse(), is(u));
		assertThat(l.reverse(), is(r));
		assertThat(r.reverse(), is(l));
		assertThat(p1.reverse(), is(p1));
		assertThat(p2.reverse(), is(u));
		assertThat(u.reverse().reverse(), is(u));
		assertThat(d.reverse().reverse(), is(d));
		assertThat(l.reverse().reverse(), is(l));
		assertThat(r.reverse().reverse(), is(r));
		assertThat(p1.reverse().reverse(), is(p1));
		assertThat(p2.reverse().reverse(), is(p2));
		assertThat(p3.reverse().reverse(), is(p3));
		assertThat(p4.reverse().reverse(), is(p4));
	}

	@Test
	public void testEqualsObject() {
		assertTrue(u.equals(u));
		assertTrue(d.equals(d));
		assertTrue(l.equals(l));
		assertTrue(r.equals(r));
		assertTrue(p1.equals(p1));
		assertTrue(p2.equals(p2));
		assertTrue(p3.equals(p3));
		assertTrue(p4.equals(p4));
		assertTrue(p2.equals(d));
		assertTrue(u.reverse().reverse().equals(u));
		assertTrue(d.reverse().reverse().equals(d));
		assertTrue(l.reverse().reverse().equals(l));
		assertTrue(r.reverse().reverse().equals(r));
		assertTrue(p1.reverse().reverse().equals(p1));
		assertTrue(p2.reverse().reverse().equals(p2));
		assertTrue(p3.reverse().reverse().equals(p3));
		assertTrue(p4.reverse().reverse().equals(p4));
	}

	@Test
	public void testToString() {
		assertThat(u.toString(), is("[R-1C0]"));
		assertThat(d.toString(), is("[R1C0]"));
		assertThat(l.toString(), is("[R0C-1]"));
		assertThat(r.toString(), is("[R0C1]"));
		assertThat(p1.toString(), is("[R0C0]"));
		assertThat(p2.toString(), is("[R1C0]"));
		assertThat(p3.toString(), is("[R5C8]"));
		assertThat(p4.toString(), is("[R4C2]"));
	}

}
