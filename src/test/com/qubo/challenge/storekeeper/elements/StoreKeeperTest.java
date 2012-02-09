package test.com.qubo.challenge.storekeeper.elements;

import static com.qubo.challenge.storekeeper.elements.Direction.Down;
import static com.qubo.challenge.storekeeper.elements.Direction.Left;
import static com.qubo.challenge.storekeeper.elements.Direction.Right;
import static com.qubo.challenge.storekeeper.elements.Direction.Up;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.com.qubo.challenge.storekeeper.GameSolver;

import com.qubo.challenge.storekeeper.Loader;
import com.qubo.challenge.storekeeper.elements.Cell;
import com.qubo.challenge.storekeeper.elements.Constants;
import com.qubo.challenge.storekeeper.elements.Direction;
import com.qubo.challenge.storekeeper.elements.FieldObject;
import com.qubo.challenge.storekeeper.elements.Position;
import com.qubo.challenge.storekeeper.elements.StoreKeeper;
import com.qubo.challenge.storekeeper.elements.Terrain;

public class StoreKeeperTest {
	private static final String GAMEMAP4 = "gamemap_4.txt";
	private static final String ANSWER4 = "asssdwawwddsawassdssaaasawwdwwaaasasdwwwassdsssaawdsdwwwaswwdddwdsssawdwaaawassdwddssssdwdddwwasdsaaasawwwdsasdwwwwasaassssaawdsdwwwawdddwdsswaaaassdssaawdsdwwwawd";
	StoreKeeper storeKeeper;

	@Before
	public void before() {
		try {
			storeKeeper = new Loader().load(GAMEMAP4);
		} catch (FileNotFoundException e) {
			fail(e.getMessage());
		}
	}
	@After
	public void after() { storeKeeper = null; }

	@Test
	public void testIsCleared() {
		assertFalse(storeKeeper.isCleared());
		storeKeeper.move(Down);
		assertFalse(storeKeeper.isCleared());
		storeKeeper.move(Down);
		assertFalse(storeKeeper.isCleared());
		storeKeeper.move(Down);
		assertFalse(storeKeeper.isCleared());
		storeKeeper.move(Down);
		assertFalse(storeKeeper.isCleared());
		storeKeeper.move(Down);
		assertFalse(storeKeeper.isCleared());
		storeKeeper.move(Down);
		assertFalse(storeKeeper.isCleared());
		storeKeeper.undo();
		assertFalse(storeKeeper.isCleared());
		storeKeeper.undo();
		assertFalse(storeKeeper.isCleared());
		storeKeeper.undo();
		assertFalse(storeKeeper.isCleared());
		storeKeeper.undo();
		assertFalse(storeKeeper.isCleared());

		storeKeeper.reset();
		assertFalse(storeKeeper.isCleared());

		GameSolver solver = new GameSolver();
		solver.solve(storeKeeper, ANSWER4);
		assertTrue(storeKeeper.isCleared());
	}
	@Test
	public void testReset() {
		GameSolver solver = new GameSolver();
		solver.solve(storeKeeper, ANSWER4);
		assertTrue(storeKeeper.isCleared());

		storeKeeper.reset();
		assertFalse(storeKeeper.isCleared());
	}
	@Test
	public void testMove() {
		doTestMove(Down, Up);
		doTestMove(Right, Down, Left, Up);
		doTestMove(Left, Down, Right, Up);
		doTestMove(Left, Down, Down, Down, Down, Down, Up, Up, Up, Up, Up, Right);
	}
	public void doTestMove(Direction... dirs) {
		String state1 = storeKeeper.toString();
		for (Direction direction : dirs) {
			storeKeeper.move(direction);
		}
		String state2 = storeKeeper.toString();
		assertThat(state2, is(state1));
		storeKeeper.reset();
	}
	@Test
	public void testUndo() {
		doTestUndo(Left, Down, Down, Down, Down, Down);
		doTestUndo(Down, Down, Down, Down, Down, Down, Down, Down, Down);
		doTestUndo(Left, Down, Down, Down, Down, Right, Right, Right, Up, Up);
	}
	public void doTestUndo(Direction... dirs) {
		String state1 = storeKeeper.toString();
		for (Direction direction : dirs) {
			storeKeeper.move(direction);
		}
		for (@SuppressWarnings("unused") Direction direction : dirs) {
			storeKeeper.undo();
		}
		String state2 = storeKeeper.toString();
		assertThat(state2, is(state1));
		storeKeeper.reset();
	}
	@Test
	public void testGetCell() {
		Cell cell;
		cell = storeKeeper.getCell(new Position(1, 9));
		assertThat(cell.getObject(), is(FieldObject.Player));
		assertNull(cell.getTerrain());
		assertThat(cell.getPosition(), is(new Position(1, 9)));

		cell = storeKeeper.getCell(new Position(1, 8));
		assertNull(cell.getObject());
		assertNull(cell.getTerrain());
		assertThat(cell.getPosition(), is(new Position(1, 8)));

		cell = storeKeeper.getCell(new Position(1, 7));
		assertNull(cell.getObject());
		assertThat(cell.getTerrain(), is(Terrain.Wall));
		assertThat(cell.getPosition(), is(new Position(1, 7)));

		cell = storeKeeper.getCell(new Position(2, 4));
		assertNull(cell.getObject());
		assertThat(cell.getTerrain(), is(Terrain.Dest));
		assertThat(cell.getPosition(), is(new Position(2, 4)));

		cell = storeKeeper.getCell(new Position(2, 5));
		assertThat(cell.getObject(), is(FieldObject.Stuff));
		assertNull(cell.getTerrain());
		assertThat(cell.getPosition(), is(new Position(2, 5)));
	}
	@Test
	public void testGetStep() {
		assertThat(storeKeeper.getStep(), is(0));
		storeKeeper.move(Down);
		assertThat(storeKeeper.getStep(), is(1));
		storeKeeper.move(Down);
		assertThat(storeKeeper.getStep(), is(2));
		storeKeeper.move(Down);
		assertThat(storeKeeper.getStep(), is(3));
		storeKeeper.move(Down);
		assertThat(storeKeeper.getStep(), is(4));
		storeKeeper.move(Down);
		assertThat(storeKeeper.getStep(), is(4));
		storeKeeper.move(Down);
		assertThat(storeKeeper.getStep(), is(4));
		storeKeeper.move(Up);
		assertThat(storeKeeper.getStep(), is(5));
		storeKeeper.move(Up);
		assertThat(storeKeeper.getStep(), is(6));
		storeKeeper.move(Up);
		assertThat(storeKeeper.getStep(), is(7));
		storeKeeper.move(Up);
		assertThat(storeKeeper.getStep(), is(8));
		storeKeeper.move(Up);
		assertThat(storeKeeper.getStep(), is(8));
		storeKeeper.move(Up);
		assertThat(storeKeeper.getStep(), is(8));
		storeKeeper.move(Up);
		assertThat(storeKeeper.getStep(), is(8));
		storeKeeper.undo();
		assertThat(storeKeeper.getStep(), is(7));
		storeKeeper.undo();
		assertThat(storeKeeper.getStep(), is(6));
		storeKeeper.undo();
		assertThat(storeKeeper.getStep(), is(5));
		storeKeeper.undo();
		assertThat(storeKeeper.getStep(), is(4));
		storeKeeper.undo();
		assertThat(storeKeeper.getStep(), is(3));
		storeKeeper.undo();
		assertThat(storeKeeper.getStep(), is(2));
		storeKeeper.undo();
		assertThat(storeKeeper.getStep(), is(1));
		storeKeeper.undo();
		assertThat(storeKeeper.getStep(), is(0));
		storeKeeper.undo();
		assertThat(storeKeeper.getStep(), is(0));
		storeKeeper.undo();
		assertThat(storeKeeper.getStep(), is(0));
		storeKeeper.move(Left);
		assertThat(storeKeeper.getStep(), is(1));
		storeKeeper.undo();
		assertThat(storeKeeper.getStep(), is(0));
	}
	@Test
	public void testToString() {
		String string = storeKeeper.toString();
		StringBuilder builder = new StringBuilder();
		for (Iterable<Cell> cellRow : storeKeeper) {
			for (Cell cell : cellRow) {
				builder.append(cell.toChar());
			}
			builder.append(Constants.LINE_SEPARATOR);
		}
		assertThat(string, is(builder.toString()));
	}
	@Test
	public void testIterator() {
		int row = 0;
		for (Iterable<Cell> cellRow : storeKeeper) {
			int col = 0;
			for (@SuppressWarnings("unused") Cell cell : cellRow) {
				col++;
			}
			assertThat(col, is(12));
			row++;
		}
		assertThat(row, is(8));
	}
}
