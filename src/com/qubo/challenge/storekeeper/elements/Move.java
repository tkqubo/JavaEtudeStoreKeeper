package com.qubo.challenge.storekeeper.elements;

import java.text.MessageFormat;

/**
 * プレーヤーの「移動」を表現したクラス
 * @author Qubo
 */
class Move {
	private final boolean shouldPushStuff;
	private final Position delta;

	/**
	 * 移動量を表した{@link Position}オブジェクトを取得する
	 * @return
	 */
	Position getDelta() {
		return delta;
	}
	/**
	 * 移動時に荷物を動かしているかどうかを取得する
	 * @return
	 */
	boolean shouldPushStuff() {
		return shouldPushStuff;
	}

	/**
	 * 標準のコンストラクタ
	 * @param delta 移動量
	 * @param shouldPushStuff 荷物を動かしているかどうか
	 */
	Move(Position delta, boolean shouldPushStuff) {
		this.delta = delta;
		this.shouldPushStuff = shouldPushStuff;
	}

	/*
	 * (非 Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return MessageFormat.format("move({0}{1})", delta, shouldPushStuff ? ", PUSH" : "");
	}
}
