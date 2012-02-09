package com.qubo.challenge.storekeeper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;

import com.qubo.challenge.storekeeper.elements.Direction;
import com.qubo.challenge.storekeeper.elements.StoreKeeper;

/**
 * ƒRƒ“ƒ\[ƒ‹ƒvƒƒOƒ‰ƒ€‚Æ‚µ‚Ä‘qŒÉ”Ô‚ğƒvƒŒƒC‚·‚é‚½‚ß‚ÌƒNƒ‰ƒX
 * @author Qubo
 */
public class ConsoleGame {
	/**
	 * ƒRƒ“ƒ\[ƒ‹“ü—Í‚ÌƒLƒƒƒ“ƒZƒ‹•¶šiHj
	 */
	private static final char COMMAND_CANCEL = '!';
	/**
	 * ƒRƒ“ƒ\[ƒ‹“ü—ÍFuã‚ÉˆÚ“®v
	 */
	public static final char COMMAND_MOVE_UP = 'w';
	/**
	 * ƒRƒ“ƒ\[ƒ‹“ü—ÍFu‰º‚ÉˆÚ“®v
	 */
	public static final char COMMAND_MOVE_DOWN = 's';
	/**
	 * ƒRƒ“ƒ\[ƒ‹“ü—ÍFu¶‚ÉˆÚ“®v
	 */
	public static final char COMMAND_MOVE_LEFT = 'a';
	/**
	 * ƒRƒ“ƒ\[ƒ‹“ü—ÍFu‰E‚ÉˆÚ“®v
	 */
	public static final char COMMAND_MOVE_RIGHT = 'd';
	/**
	 * ƒRƒ“ƒ\[ƒ‹“ü—ÍFu‚â‚è’¼‚µv
	 */
	public static final char COMMAND_UNDO = 'u';
	/**
	 * ƒRƒ“ƒ\[ƒ‹“ü—ÍFuƒŠƒZƒbƒgv
	 */
	public static final char COMMAND_RESET = '@';
	/**
	 * ƒRƒ“ƒ\[ƒ‹“ü—ÍFu’†’fv
	 */
	public static final char COMMAND_QUIT = '_';
	/**
	 * ã‰º¶‰E‚ÌˆÚ“®ŒnƒRƒ“ƒ\[ƒ‹“ü—Í‚ğ•¶š—ñ‚Æ‚µ‚Ä˜AŒ‹‚µ‚½‚à‚Ì
	 */
	private static final String COMMAND_MOVES = "" + COMMAND_MOVE_UP + COMMAND_MOVE_DOWN + COMMAND_MOVE_LEFT + COMMAND_MOVE_RIGHT;

	/**
	 * ‘qŒÉ”Ô‚ğƒvƒŒƒC‚·‚é
	 * @param path ƒQ[ƒ€ƒtƒ@ƒCƒ‹‚ÌƒpƒX
	 * @throws IOException
	 */
	public void play(String path) throws IOException {
		showSplash();

		Loader loader = new Loader();
		StoreKeeper storeKeeper = loader.load(path);
		storeKeeper.reset();

		render(storeKeeper);

		do {
			char command = requestCommand();
			if (command == COMMAND_QUIT) {
				break;
			} else if (command == COMMAND_RESET) {
				storeKeeper.reset();
			} else if (command == COMMAND_UNDO) {
				storeKeeper.undo();
			} else if (COMMAND_MOVES.contains("" + command)) {
				storeKeeper.move(toDirection(command));
			}

			render(storeKeeper);

		} while (!storeKeeper.isCleared());

		showResult(storeKeeper);
	}

	/**
	 * ƒQ[ƒ€‹N“®‰æ–Ê‚ğ•\¦‚·‚é
	 */
	private void showSplash() {
		System.out.println("QQQQ¡¡¡QQQQQQQQQQQ¡QQQQQQQQQQQQ¡¡QQ");
		System.out.println("QQQ¡QQQ¡QQQQQ¡¡¡¡¡¡¡¡¡¡QQQ¡¡¡¡¡Q¡QQ");
		System.out.println("QQ¡Q¡¡¡Q¡QQQQ¡QQQQ¡QQQQQQQQ¡Q¡Q¡QQQ");
		System.out.println("¡¡QQQQQQQ¡¡QQ¡¡¡¡¡¡¡¡¡¡Q¡¡¡¡¡¡¡¡¡¡¡");
		System.out.println("QQ¡¡¡¡¡¡¡QQQQ¡Q¡QQ¡QQ¡QQQQQ¡Q¡Q¡QQQ");
		System.out.println("QQ¡QQQQQ¡QQQQ¡Q¡¡¡¡¡¡¡QQQQ¡QQ¡QQ¡QQ");
		System.out.println("QQ¡¡¡¡¡¡¡QQQQ¡Q¡QQ¡QQ¡QQ¡¡¡¡¡¡¡¡¡¡¡");
		System.out.println("QQ¡QQQQQQQQQQ¡Q¡¡¡¡¡¡¡QQQQ¡QQ¡QQ¡QQ");
		System.out.println("Q¡Q¡¡¡¡¡¡QQQQ¡QQQQ¡QQQQQQQ¡¡¡¡¡¡¡QQ");
		System.out.println("¡QQ¡QQQQ¡QQQ¡Q¡¡¡¡¡¡¡¡¡QQQ¡QQ¡QQ¡QQ");
		System.out.println("QQQ¡¡¡¡¡¡QQQ¡QQQQQ¡QQQQQQQ¡¡¡¡¡¡¡QQ");
		System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ");
		System.out.println("Please Enter Key... Game Start");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try { reader.readLine(); } catch (IOException e) { e.printStackTrace(); }
	}

	/**
	 * ƒQ[ƒ€I—¹Œã‚ÌŒ‹‰Ê‚ğ•\¦‚·‚é
	 * @param storeKeeper
	 */
	private void showResult(StoreKeeper storeKeeper) {
		String message = storeKeeper.isCleared() ? "ƒQ[ƒ€ƒNƒŠƒAI‚¨‚ß‚Å‚Æ‚¤II" : "ƒQ[ƒ€‚ğ’†’f‚µ‚Ü‚µ‚½I";
		System.out.println(message);
	}
	/**
	 * ƒQ[ƒ€‰æ–Ê‚ğ•\¦‚·‚é
	 * @param storeKeeper
	 */
	private void render(StoreKeeper storeKeeper) {
		System.out.println(storeKeeper);
		System.out.println(MessageFormat.format("ˆÚ“®‰ñ”: {0}", storeKeeper.getStep()));
	}
	/**
	 * ã‰º¶‰E‚ÌˆÚ“®Œn‚ÌƒRƒ“ƒ\[ƒ‹“ü—Í‚ğA{@link Direction}ƒIƒuƒWƒFƒNƒg‚É•ÏŠ·‚·‚é<br>
	 * “ü—Í‚ªˆÚ“®Œn‚Å‚Í‚È‚¢ê‡‚ÍAnull‚ğ•Ô‚·
	 * @param command ƒRƒ“ƒ\[ƒ‹“ü—Í
	 * @return {@link Direction}ƒIƒuƒWƒFƒNƒg
	 */
	private Direction toDirection(char command) {
		switch (command) {
		case COMMAND_MOVE_UP: return Direction.Up;
		case COMMAND_MOVE_DOWN: return Direction.Down;
		case COMMAND_MOVE_LEFT: return Direction.Left;
		case COMMAND_MOVE_RIGHT: return Direction.Right;
		default: return null;
		}
	}
	/**
	 * ƒRƒ“ƒ\[ƒ‹‚©‚çˆê•¶šæ“¾‚·‚éBƒLƒƒƒ“ƒZƒ‹•¶š({@link ConsoleGame#COMMAND_CANCEL})‚ªŠÜ‚Ü‚ê‚Ä‚¢‚éê‡‚Í-1‚ğ•Ô‚·B
	 * @return ƒRƒ}ƒ“ƒh•¶š
	 * @throws IOException
	 */
	private char requestCommand() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(
				"ˆÚ“®: (ã->w, ¶->a, ‰º->s, ‰E->d) + Enter"
				+ "\r\n"
				+ "–ß‚·->u, ƒŠƒZƒbƒg->@, ’†’f->_, “ü—ÍƒLƒƒƒ“ƒZƒ‹->!‚ğŠÜ‚ß‚é");
		String line = reader.readLine();
		if (!line.contains("" + COMMAND_CANCEL) && line.length() > 0) {
			return line.charAt(0);
		} else {
			return (char) -1;
		}
	}
}
