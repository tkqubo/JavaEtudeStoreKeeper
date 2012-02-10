package com.qubo.challenge.storekeeper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;

import com.qubo.challenge.storekeeper.elements.Direction;
import com.qubo.challenge.storekeeper.elements.StoreKeeper;

/**
 * コンソールプログラムとして倉庫番をプレイするためのクラス
 * @author Qubo
 */
public class ConsoleGame {
	/**
	 * コンソール入力のキャンセル文字（？）
	 */
	private static final char COMMAND_CANCEL = '!';
	/**
	 * コンソール入力：「上に移動」
	 */
	public static final char COMMAND_MOVE_UP = 'w';
	/**
	 * コンソール入力：「下に移動」
	 */
	public static final char COMMAND_MOVE_DOWN = 's';
	/**
	 * コンソール入力：「左に移動」
	 */
	public static final char COMMAND_MOVE_LEFT = 'a';
	/**
	 * コンソール入力：「右に移動」
	 */
	public static final char COMMAND_MOVE_RIGHT = 'd';
	/**
	 * コンソール入力：「やり直し」
	 */
	public static final char COMMAND_UNDO = 'u';
	/**
	 * コンソール入力：「リセット」
	 */
	public static final char COMMAND_RESET = '@';
	/**
	 * コンソール入力：「中断」
	 */
	public static final char COMMAND_QUIT = '_';
	/**
	 * 上下左右の移動系コンソール入力を文字列として連結したもの
	 */
	private static final String COMMAND_MOVES = "" + COMMAND_MOVE_UP + COMMAND_MOVE_DOWN + COMMAND_MOVE_LEFT + COMMAND_MOVE_RIGHT;

	/**
	 * 倉庫番をプレイする
	 * @param path ゲームファイルのパス
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
	 * ゲーム起動画面を表示する
	 */
	private void showSplash() {
		System.out.println("＿＿＿＿■■■＿＿＿＿＿＿＿＿＿＿＿■＿＿＿＿＿＿＿＿＿＿＿＿■■＿＿");
		System.out.println("＿＿＿■＿＿＿■＿＿＿＿＿■■■■■■■■■■＿＿＿■■■■■＿■＿＿");
		System.out.println("＿＿■＿■■■＿■＿＿＿＿■＿＿＿＿■＿＿＿＿＿＿＿＿■＿■＿■＿＿＿");
		System.out.println("■■＿＿＿＿＿＿＿■■＿＿■■■■■■■■■■＿■■■■■■■■■■■");
		System.out.println("＿＿■■■■■■■＿＿＿＿■＿■＿＿■＿＿■＿＿＿＿＿■＿■＿■＿＿＿");
		System.out.println("＿＿■＿＿＿＿＿■＿＿＿＿■＿■■■■■■■＿＿＿＿■＿＿■＿＿■＿＿");
		System.out.println("＿＿■■■■■■■＿＿＿＿■＿■＿＿■＿＿■＿＿■■■■■■■■■■■");
		System.out.println("＿＿■＿＿＿＿＿＿＿＿＿＿■＿■■■■■■■＿＿＿＿■＿＿■＿＿■＿＿");
		System.out.println("＿■＿■■■■■■＿＿＿＿■＿＿＿＿■＿＿＿＿＿＿＿■■■■■■■＿＿");
		System.out.println("■＿＿■＿＿＿＿■＿＿＿■＿■■■■■■■■■＿＿＿■＿＿■＿＿■＿＿");
		System.out.println("＿＿＿■■■■■■＿＿＿■＿＿＿＿＿■＿＿＿＿＿＿＿■■■■■■■＿＿");
		System.out.println("＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿");
		System.out.println("Please Enter Key... Game Start");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try { reader.readLine(); } catch (IOException e) { e.printStackTrace(); }
	}

	/**
	 * ゲーム終了後の結果を表示する
	 * @param storeKeeper
	 */
	private void showResult(StoreKeeper storeKeeper) {
		String message = storeKeeper.isCleared() ? "ゲームクリア！おめでとう！！" : "ゲームを中断しました！";
		System.out.println(message);
	}
	/**
	 * ゲーム画面を表示する
	 * @param storeKeeper
	 */
	private void render(StoreKeeper storeKeeper) {
		System.out.println(storeKeeper);
		System.out.println(MessageFormat.format("移動回数: {0}", storeKeeper.getStep()));
	}
	/**
	 * 上下左右の移動系のコンソール入力を、{@link Direction}オブジェクトに変換する<br>
	 * 入力が移動系ではない場合は、nullを返す
	 * @param command コンソール入力
	 * @return {@link Direction}オブジェクト
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
	 * コンソールから一文字取得する。キャンセル文字({@link ConsoleGame#COMMAND_CANCEL})が含まれている場合は-1を返す。
	 * @return コマンド文字
	 * @throws IOException
	 */
	private char requestCommand() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(
				"移動: (上->w, 左->a, 下->s, 右->d) + Enter"
				+ "\r\n"
				+ "戻す->u, リセット->@, 中断->_, 入力キャンセル->!を含める");
		String line = reader.readLine();
		if (!line.contains("" + COMMAND_CANCEL) && line.length() > 0) {
			return line.charAt(0);
		} else {
			return (char) -1;
		}
	}
}
