package com.qubo.challenge.storekeeper.elements;

import java.text.MessageFormat;

/**
 * ゲームマップの位置や移動量を表現したクラス
 * @author Qubo
 */
public class Position {
	/**
	 * 上への1セル分の移動を表す
	 */
	public static final Position UP = new Position(-1, 0);
	/**
	 * 下への1セル分の移動を表す
	 */
	public static final Position DOWN = new Position(1, 0);
	/**
	 * 左への1セル分の移動を表す
	 */
	public static final Position LEFT = new Position(0, -1);
	/**
	 * 右への1セル分の移動を表す
	 */
	public static final Position RIGHT = new Position(0, 1);

	private final int row;
	private final int col;
	/**
	 * 行の位置を取得する
	 * @return 行の位置
	 */
	public int getRow() {
		return row;
	}
	/**
	 * 列の位置を取得する
	 * @return 列の位置
	 */
	public int getCol() {
		return col;
	}

	/**
	 * 標準のコンストラクタ
	 * @param row 列の位置
	 * @param col 行の位置
	 */
	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}

	/**
	 * 自分自身に、{@code other}を移動量として追加した{@link Position}オブジェクトを、新規作成して返す
	 * @param other 移動量
	 * @return {@link Position}オブジェクト
	 */
	public Position add(Position other) {
		return new Position(this.row + other.row, this.col + other.col);
	}
	/**
	 * 自分自身と逆の移動量を持つ{@link Position}オブジェクトを、新規作成して返す
	 * @return {@link Position}オブジェクト
	 */
	public Position reverse() {
		return new Position(-row, -col);
	}

	/*
	 * (非 Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Position) {
			Position other = (Position) obj;
			return this.row == other.row && this.col == other.col;
		}
		return false;
	}
	/*
	 * (非 Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override public String toString() { return MessageFormat.format("[R{0}C{1}]", row, col); }
}
