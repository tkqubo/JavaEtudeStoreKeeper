package test.com.qubo.challenge.storekeeper;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.text.MessageFormat;

import org.junit.Test;

import com.qubo.challenge.storekeeper.Loader;
import com.qubo.challenge.storekeeper.elements.StoreKeeper;

public class LoaderTest {
	private String NL = System.getProperty("line.separator");
	private String GAME1 =
			"#########" + NL +
			"#       #" + NL +
			"# ##o## #" + NL +
			"# p   o #" + NL +
			"##o...# #" + NL +
			"##    # #" + NL +
			"####  ###" + NL +
			"#########" + NL;
	private String GAME2 =
			"########" + NL +
			"# p    #" + NL +
			"# .   .#" + NL +
			"#  oo  #" + NL +
			"#      #" + NL +
			"########" + NL;
	private String GAME3 =
			"#####" + NL +
			"#. .#" + NL +
			"# op#" + NL +
			"#ooo#" + NL +
			"# o #" + NL +
			"#...#" + NL +
			"#####" + NL;
	private String GAME4 =
			" ###########" + NL +
			" #  #  # p #" + NL +
			" #  .o #   #" + NL +
			" #  #. # o##" + NL +
			"##o # .#  # " + NL +
			"#   # o.  # " + NL +
			"#   #  #  # " + NL +
			"########### " + NL;
	private String[] GAMES = { GAME1, GAME2, GAME3, GAME4 };

	@Test
	public void testLoad() {
		for (int i = 0; i < 4; i++) {
			testPath(i + 1, GAMES[i]);
		}
	}
	private static void testPath(int num, String map) {
		try {
			Loader loader = new Loader();
			String path = MessageFormat.format("gamemap_{0}.txt", num);
			StoreKeeper game = loader.load(path);
			assertThat(game.toString(), is(map));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}
}
