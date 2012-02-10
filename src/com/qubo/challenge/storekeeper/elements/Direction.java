package com.qubo.challenge.storekeeper.elements;

/**
 * 上下左右の進行方向を表す列挙型
 * @author Qubo
 */
public enum Direction {
	/**
	 * 上
	 */
	Up { @Override Position toPosition() { return Position.UP; } },
	/**
	 * 下
	 */
	Down { @Override Position toPosition() { return Position.DOWN; } },
	/**
	 * 右
	 */
	Right { @Override Position toPosition() { return Position.RIGHT; } },
	/**
	 * 左
	 */
	Left { @Override Position toPosition() { return Position.LEFT; } },
	;
	/**
	 * 進行方向への1セル分の移動を表す{@link Position}オブジェクトを返す。
	 * @return
	 */
	abstract Position toPosition();
}
