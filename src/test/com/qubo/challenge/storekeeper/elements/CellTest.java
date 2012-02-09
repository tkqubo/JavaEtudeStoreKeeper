package test.com.qubo.challenge.storekeeper.elements;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import com.qubo.challenge.storekeeper.elements.Cell;
import com.qubo.challenge.storekeeper.elements.Constants;
import com.qubo.challenge.storekeeper.elements.FieldObject;
import com.qubo.challenge.storekeeper.elements.Position;
import com.qubo.challenge.storekeeper.elements.Terrain;

public class CellTest {
	private Cell cell1 = new Cell(Terrain.Wall, null, new Position(0, 0));
	private Cell cell2 = new Cell(Terrain.Dest, FieldObject.Player, new Position(3, 4));
	private Cell cell3 = new Cell(Terrain.Dest, FieldObject.Stuff, new Position(5, 6));
	private Cell cell4 = new Cell(Terrain.Dest, null, new Position(1, 2));
	private Cell cell5 = new Cell(null, FieldObject.Player, new Position(7, 8));
	private Cell cell6 = new Cell(null, FieldObject.Stuff, new Position(9, 10));
	private Cell cell7 = new Cell(null, null, new Position(11, 12));

	@Test
	public void testGetField() {
		assertThat(cell1.getTerrain(), is(Terrain.Wall));
		assertThat(cell2.getTerrain(), is(Terrain.Dest));
		assertThat(cell3.getTerrain(), is(Terrain.Dest));
		assertThat(cell4.getTerrain(), is(Terrain.Dest));
		assertNull(cell5.getTerrain());
		assertNull(cell6.getTerrain());
		assertNull(cell7.getTerrain());
	}

	@Test
	public void testGetObject() {
		assertNull(cell1.getObject());
		assertThat(cell2.getObject(), is(FieldObject.Player));
		assertThat(cell3.getObject(), is(FieldObject.Stuff));
		assertNull(cell4.getObject());
		assertThat(cell5.getObject(), is(FieldObject.Player));
		assertThat(cell6.getObject(), is(FieldObject.Stuff));
		assertNull(cell7.getObject());
	}

	@Test
	public void testGetOriginalObject() {
		assertNull(cell1.getOriginalObject());
		assertThat(cell2.getOriginalObject(), is(FieldObject.Player));
		assertThat(cell3.getOriginalObject(), is(FieldObject.Stuff));
		assertNull(cell4.getOriginalObject());
		assertThat(cell5.getOriginalObject(), is(FieldObject.Player));
		assertThat(cell6.getOriginalObject(), is(FieldObject.Stuff));
		assertNull(cell7.getOriginalObject());
	}

	@Test
	public void testGetPosition() {
		assertThat(cell1.getPosition(), is(new Position(0, 0)));
		assertThat(cell2.getPosition(), is(new Position(3, 4)));
		assertThat(cell3.getPosition(), is(new Position(5, 6)));
		assertThat(cell4.getPosition(), is(new Position(1, 2)));
		assertThat(cell5.getPosition(), is(new Position(7, 8)));
		assertThat(cell6.getPosition(), is(new Position(9, 10)));
		assertThat(cell7.getPosition(), is(new Position(11, 12)));
	}

	@Test
	public void testSetObject() { }

	@Test
	public void testCell() { }

	@Test
	public void testReset() { }

	@Test
	public void testToChar() {
		assertThat(cell1.toChar(), is(Constants.SYMBOL_WALL));
		assertThat(cell2.toChar(), is(Constants.SYMBOL_PLAYER_ON_DEST));
		assertThat(cell3.toChar(), is(Constants.SYMBOL_STUFF_ON_DEST));
		assertThat(cell4.toChar(), is(Constants.SYMBOL_DEST));
		assertThat(cell5.toChar(), is(Constants.SYMBOL_PLAYER));
		assertThat(cell6.toChar(), is(Constants.SYMBOL_STUFF));
		assertThat(cell7.toChar(), is(Constants.SYMBOL_EMPTY));
	}

	@Test
	public void testToString() {
		assertThat(cell1.toString(), is("" + Constants.SYMBOL_WALL));
		assertThat(cell2.toString(), is("" + Constants.SYMBOL_PLAYER_ON_DEST));
		assertThat(cell3.toString(), is("" + Constants.SYMBOL_STUFF_ON_DEST));
		assertThat(cell4.toString(), is("" + Constants.SYMBOL_DEST));
		assertThat(cell5.toString(), is("" + Constants.SYMBOL_PLAYER));
		assertThat(cell6.toString(), is("" + Constants.SYMBOL_STUFF));
		assertThat(cell7.toString(), is("" + Constants.SYMBOL_EMPTY));
	}
}
