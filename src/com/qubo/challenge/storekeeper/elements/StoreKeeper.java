package com.qubo.challenge.storekeeper.elements;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * 倉庫番ゲームを表現したクラス
 * @author Qubo
 */
public class StoreKeeper implements Iterable<Iterable<Cell>> {
	private final Cell[][] cells;
	private final Stack<Move> history;

	/**
	 * 標準のコンストラクタ
	 * @param cells 二次元配列のセル情報
	 */
	public StoreKeeper(Cell[][] cells) {
		this.cells = cells;
		this.history = new Stack<Move>();
	}

	/**
	 * ゲームをクリアしているかどうかを取得。
	 * 判定は、「全ての『荷物』が『荷物置き場セル』上に乗っているかどうか」で判断される。
	 * @return ゲームをクリアしているかどうか
	 */
	public boolean isCleared() {
		for (Cell[] row : cells) {
			for (Cell cell : row) {
				if (cell.getTerrain() == Terrain.Dest && cell.getObject() != FieldObject.Stuff)
					return false;
			}
		}
		return true;
	}
	/**
	 * ゲームをリセットし、全ての配置を初期状態に戻す
	 */
	public void reset() {
		history.clear();
		for (Cell[] row : cells) {
			for (Cell cell : row) {
				cell.reset();
			}
		}
	}
	/**
	 * プレーヤーを与えられた方向に進める
	 * @param direction 方向
	 * @return
	 * 移動が成功したかどうかを返す。
	 * 例えば壁に移動しようとした場合、あるいは荷物が邪魔をしている場合に{@code false}を返す。
	 */
	public boolean move(Direction direction) {
		Move move = getMove(direction.toPosition());
		if (move != null) {
			history.push(move);
			doMove(move, false);
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 直前の移動を取り消す
	 * @return
	 * 成功したかどうかを返す。
	 * 例えば、これ以上取り消しできない場合（初期状態など）で{@code false}を返す。
	 */
	public boolean undo() {
		if (!history.isEmpty()) {
			Move move = history.pop();
			doMove(move, true);
			return true;
		} else {
			return false;
		}
	}
	/**
	 * ゲームマップの{@code position}上のセルを取得する
	 * @param position
	 * @return ゲームマップの{@code position}上のセル
	 */
	public Cell getCell(Position position) {
		return cells[position.getRow()][position.getCol()];
	}
	/**
	 * 現在のステップ数を取得する
	 * @return 現在のステップ数
	 */
	public int getStep() { return history.size(); }

	/**
	 * 内部用関数。プレーヤーを与えられた方向に進める。
	 * @param move
	 * @param undoing 取り消し操作による移動か通常操作による移動かを返す
	 */
	private void doMove(Move move, boolean undoing) {
		Position posPlayer = getPlayerPosition();
		if (move.shouldPushStuff()) {
			Position posNext = posPlayer.add(move.getDelta());
			Position posPrev = posPlayer.add(move.getDelta().reverse());

			Position pivot = !undoing ? posNext : posPlayer;
			Position first = !undoing ? posNext.add(move.getDelta()) : posPrev;
			Position second = !undoing ? posPlayer : posNext;
			swap(pivot, first);
			swap(pivot, second);
		} else {
			Position posDestination = posPlayer.add(!undoing ? move.getDelta() : move.getDelta().reverse());
			swap(posPlayer, posDestination);
		}
	}
	/**
	 * 二点のセル上のオブジェクトを交換する。
	 * @param pos1
	 * @param pos2
	 */
	private void swap(Position pos1, Position pos2) {
		Cell cell1 = getCell(pos1);
		Cell cell2 = getCell(pos2);
		FieldObject temp = cell1.getObject();
		cell1.setObject(cell2.getObject());
		cell2.setObject(temp);
	}
	/**
	 * プレーヤーが{@code delta}だけ移動するための{@link Move}オブジェクトを返す。
	 * 移動できない場合は{@code null}を返す。
	 * @param delta 移動差分
	 * @return プレーヤーが{@code delta}だけ移動するための{@link Move}オブジェクト
	 */
	private Move getMove(Position delta) {
		Position player = getPlayerPosition();
		Position destination = player.add(delta);
		Cell cell = getCell(destination);
		Terrain field = cell.getTerrain();
		FieldObject object = cell.getObject();

		if (field != Terrain.Wall) {
			if (object == null) {
				return new Move(delta, false);
			} else if (object == FieldObject.Stuff) {
				destination = destination.add(delta);
				field = getCell(destination).getTerrain();
				if (field != Terrain.Wall) {
					return new Move(delta, true);
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	/**
	 * 現在のプレーヤーの位置を返す
	 * @return 現在のプレーヤーの位置
	 */
	private Position getPlayerPosition() {
		for (Cell[] row : cells) {
			for (Cell cell : row) {
				if (cell.getObject() == FieldObject.Player) {
					return cell.getPosition();
				}
			}
		}
		return null;
	}

	/*
	 * (非 Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Cell[] row : cells) {
			for (Cell cell : row) {
				char ch = cell.toChar();
				builder.append(ch);
			}
			builder.append(Constants.LINE_SEPARATOR);
		}
		return builder.toString();
	}
	/*
	 * (非 Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Iterable<Cell>> iterator() {
		final Iterator<Cell[]> rowIterator = Arrays.asList(cells).iterator();
		return new Iterator<Iterable<Cell>>() {
			@Override
			public Iterable<Cell> next() {
				if (!rowIterator.hasNext()) throw new NoSuchElementException();
				final Iterator<Cell> cellIterator = Arrays.asList(rowIterator.next()).iterator();
				return new Iterable<Cell>() {
					@Override
					public Iterator<Cell> iterator() {
						return new Iterator<Cell>() {
							@Override
							public Cell next() {
								if (!cellIterator.hasNext()) throw new NoSuchElementException();
								return cellIterator.next();
							}
							@Override public boolean hasNext() { return cellIterator.hasNext(); }
							@Override public void remove() { throw new UnsupportedOperationException(); }
						};
					}
				};
			}
			@Override public boolean hasNext() { return rowIterator.hasNext(); }
			@Override public void remove() { throw new UnsupportedOperationException(); }
		};
	}
}
