package com.qubo.challenge.storekeeper;

import java.io.IOException;

/**
 * コンソールプログラムのエントリポイントが定義されたクラス
 * @author Qubo
 */
public class Main {
	/**
	 * サンプル用ゲームファイル
	 */
	private static final String GAMEMAP1 = "gamemap_1.txt";
	/**
	 * エントリポイント。
	 * 1番目の引数をゲームファイル名として、{@link ConsoleGame#play(String)}を実行する。
	 * 引数が与えられていない場合は、デフォルトの{@link Main#GAMEMAP1}をゲームファイル名とする。
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String path = args.length > 0 ? args[0] : GAMEMAP1;
			ConsoleGame game = new ConsoleGame();
			game.play(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
