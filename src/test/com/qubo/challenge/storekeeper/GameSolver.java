package test.com.qubo.challenge.storekeeper;

import java.io.FileNotFoundException;

import com.qubo.challenge.storekeeper.ConsoleGame;
import com.qubo.challenge.storekeeper.Loader;
import com.qubo.challenge.storekeeper.elements.Direction;
import com.qubo.challenge.storekeeper.elements.StoreKeeper;

public class GameSolver {
	public StoreKeeper solve(String path, String answer) {
		StoreKeeper storeKeeper = null;
		try {
			Loader loader = new Loader();
			storeKeeper = loader.load(path);
			solve(storeKeeper, answer);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return storeKeeper;
	}
	public void solve(StoreKeeper storeKeeper, String answer) {
		storeKeeper.reset();
		for (char dir : answer.toCharArray()) {
			Direction direction = toDirection(dir);
			storeKeeper.move(direction);
		}
	}

	private Direction toDirection(char dir) {
		Direction direction = null;
		switch (dir) {
		case ConsoleGame.COMMAND_MOVE_UP:
			direction = Direction.Up;
			break;
		case ConsoleGame.COMMAND_MOVE_DOWN:
			direction = Direction.Down;
			break;
		case ConsoleGame.COMMAND_MOVE_LEFT:
			direction = Direction.Left;
			break;
		case ConsoleGame.COMMAND_MOVE_RIGHT:
			direction = Direction.Right;
			break;
		}
		return direction;
	}
}
