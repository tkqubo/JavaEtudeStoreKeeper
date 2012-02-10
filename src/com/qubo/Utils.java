package com.qubo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

/**
 * 便利関数を集めたクラス
 * @author Qubo
 */
public class Utils {
	/**
	 * {@link Utils#forEachLine(String)}用の内部クラス
	 * @author Qubo
	 */
	private static final class FileIterator implements Iterator<String> {
		private final BufferedReader bufferedReader;
		private String nextLine;

		private FileIterator(BufferedReader bufferedReader) {
			this.bufferedReader = bufferedReader;
			readNextLine();
		}

		@Override public void remove() { throw new UnsupportedOperationException(); }
		@Override
		public String next() {
			String line = nextLine;
			if (nextLine != null) {
				readNextLine();
			}
			return line;
		}
		private void readNextLine() {
			try {
				nextLine = bufferedReader.readLine();
				if (nextLine == null)
					bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
		@Override
		public boolean hasNext() {
			return nextLine != null;
		}
	}
	/**
	 * ファイル内のテキストを、行ごとにイテレートする{@link Iterable}オブジェクトを返す
	 * @param path ファイルのパス
	 * @return {@link Iterable}オブジェクト
	 * @throws FileNotFoundException ファイルが見つからなかった時に発生
	 */
	public static Iterable<String> forEachLine(String path) throws FileNotFoundException {
		final BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
		return new Iterable<String>() {
			@Override
			public Iterator<String> iterator() {
				return new FileIterator(bufferedReader);
			}
		};
	}
}
