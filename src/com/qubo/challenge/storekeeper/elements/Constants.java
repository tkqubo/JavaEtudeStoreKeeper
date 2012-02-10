package com.qubo.challenge.storekeeper.elements;

/**
 * 定数を定義したインターフェース
 * @author Qubo
 */
public interface Constants {
	/**
	 * ゲームファイル内の、コメント行の先頭
	 */
	public static final String COMMENT_START = ";";
	/**
	 * プレーヤーを表す文字
	 */
	public static final char SYMBOL_PLAYER = 'p';
	/**
	 * 荷物置き場に乗ったプレーヤーを表す文字
	 */
	public static final char SYMBOL_PLAYER_ON_DEST = 'P';
	/**
	 * 荷物を表す文字
	 */
	public static final char SYMBOL_STUFF = 'o';
	/**
	 * 荷物置き場に乗った荷物を表す文字。全ての荷物がこの状態になればゲームクリア。
	 */
	public static final char SYMBOL_STUFF_ON_DEST = 'O';
	/**
	 * 壁を表す文字。プレーヤーも荷物も、壁上に移動させることはできない。
	 */
	public static final char SYMBOL_WALL = '#';
	/**
	 * 空白地帯を表す文字。プレーヤーと荷物をその上に移動させることができる。
	 */
	public static final char SYMBOL_EMPTY = ' ';
	/**
	 * 荷物置き場を表す文字。プレーヤーと荷物をその上に移動させることができる。
	 * 全ての荷物をここに置くことができればクリア。
	 */
	public static final char SYMBOL_DEST = '.';
	/**
	 * その他の文字。ゲームファイルに許容されない文字が入っている時にこれを使う。
	 */
	public static final char SYMBOL_ERROR = '?';
	static final String LINE_SEPARATOR = System.getProperty("line.separator");

}
