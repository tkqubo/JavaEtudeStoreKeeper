package com.qubo.challenge.storekeeper.elements;

/**
 * ゲームマップ上のセルを表現したクラス
 * @author Qubo
 */
public class Cell {
	private FieldObject object;
	private final Terrain terrain;
	private final FieldObject originalObject;
	private final Position position;

	/**
	 * セルの{@link Terrain}オブジェクトを取得する
	 * @return セルの{@link Terrain}オブジェクト
	 */
	public Terrain getTerrain() { return terrain; }
	/**
	 * セルの現在の{@link FieldObject}オブジェクトを取得する
	 * @return セルの現在の{@link FieldObject}オブジェクト
	 */
	public FieldObject getObject() { return object; }
	/**
	 * セルの初期状態での{@link FieldObject}オブジェクトを取得する
	 * @return セルの初期状態での{@link FieldObject}オブジェクト
	 */
	public FieldObject getOriginalObject() { return originalObject; }
	/**
	 * セルの位置を取得する
	 * @return position
	 */
	public Position getPosition() {
		return position;
	}
	/**
	 * セルの現在の{@link FieldObject}オブジェクトを設定する
	 * @param object
	 */
	void setObject(FieldObject object) { this.object = object; }

	/**
	 * 標準のコンストラクタ
	 * @param field セルの地形
	 * @param object セル上のオブジェクト
	 * @param position セルの位置
	 */
	public Cell(Terrain field, FieldObject object, Position position) {
		this.terrain = field;
		this.object = object;
		this.originalObject = object;
		this.position = position;
	}

	/**
	 * セルの現在の{@link FieldObject}オブジェクトを初期状態に戻す
	 */
	void reset() { this.object = this.originalObject; }
	/**
	 * セルの状態を、文字表現として取得する
	 * @return 文字表現によるセルの状態
	 */
	public char toChar() {
		char ch;
		if (terrain == Terrain.Wall) {
			ch = Constants.SYMBOL_WALL;
		} else if (terrain == Terrain.Dest) {
			if (object == FieldObject.Player) {
				ch = Constants.SYMBOL_PLAYER_ON_DEST;
			} else if (object == FieldObject.Stuff) {
				ch = Constants.SYMBOL_STUFF_ON_DEST;
			} else {
				ch = Constants.SYMBOL_DEST;
			}
		} else if (terrain == null) {
			if (object == FieldObject.Player) {
				ch = Constants.SYMBOL_PLAYER;
			} else if (object == FieldObject.Stuff) {
				ch = Constants.SYMBOL_STUFF;
			} else {
				ch = Constants.SYMBOL_EMPTY;
			}
		} else {
			ch = Constants.SYMBOL_ERROR;
		}
		return ch;
	}

	/*
	 * (非 Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override public String toString() { return "" + toChar(); }
}
