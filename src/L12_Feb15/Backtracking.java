package L12_Feb15;

/**
 * @author Garima Chhikara
 * @email garima.chhikara@codingblocks.com
 * @date 15-Feb-2020
 *
 */

public class Backtracking {

	public static void main(String[] args) {

		// QueenCombinationBoxRespect(new boolean[4], 0, 2, 0, "");
		// coinChange(new int[] { 2, 3, 5, 6 }, 0, 10, "");

		// QueenCombinationBoxRespect2D(new boolean[2][3], 0, 0, 2, 0, "");

		// NQueen(new boolean[4][4], 0, 0, 4, 0, "");

		// char[][] board = { { 'o', 'x', 'o', 'o' }, { 'o', 'o', 'o', 'o' }, { 'o',
		// 'x', 'o', 'o' },
		// { 'o', 'o', 'x', 'o' } };

		// blockedMaze(board, 0, 0, "", new boolean[board.length][board[0].length]);

		String word = "SEA";

		// char[][] board = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A',
		// 'D', 'A', 'E' } };
		//
		// boolean res = false;
		//
		// for (int i = 0; i < board.length; i++) {
		// for (int j = 0; j < board[0].length; j++) {
		//
		// if (board[i][j] == word.charAt(0))
		// res = res || WordSearch2(board, i, j, word, 0, new
		// boolean[board.length][board[0].length]);
		//
		// }
		// }
		//
		// System.out.println(res);

		// NQueen2(new boolean[4][4], 0, "");
		KQueen(new boolean[4][4], 0, 3, 0, "");

	}

	public static void QueenCombinationBoxRespect(boolean[] board, int col, int tq, int qpsf, String ans) {

		if (qpsf == tq) {
			System.out.println(ans);
			return;
		}

		if (col == board.length) {
			return;
		}

		// place
		board[col] = true;
		QueenCombinationBoxRespect(board, col + 1, tq, qpsf + 1, ans + " b" + col);
		board[col] = false;

		// not place
		QueenCombinationBoxRespect(board, col + 1, tq, qpsf, ans);
	}

	public static void coinChange(int[] denom, int idx, int amount, String ans) {

		if (amount == 0) {
			System.out.println(ans);
			return;
		}

		if (idx == denom.length || amount < 0) {
			return;
		}

		// exclude
		coinChange(denom, idx + 1, amount, ans);
		// include
		coinChange(denom, idx, amount - denom[idx], ans + denom[idx]);

	}

	public static void QueenCombinationBoxRespect2D(boolean[][] board, int row, int col, int tq, int qpsf, String ans) {

		if (qpsf == tq) {
			System.out.println(ans);
			return;
		}

		if (col == board[0].length) {
			row++;
			col = 0;
		}

		if (row == board.length) {
			return;
		}

		// place
		board[row][col] = true;
		QueenCombinationBoxRespect2D(board, row, col + 1, tq, qpsf + 1, ans + "[" + row + "-" + col + "]");
		board[row][col] = false;

		// not place
		QueenCombinationBoxRespect2D(board, row, col + 1, tq, qpsf, ans);
	}

	static int count = 0;

	public static void NQueen(boolean[][] board, int row, int col, int tq, int qpsf, String ans) {

		if (qpsf == tq) {
			System.out.println(++count + " : " + ans);
			return;
		}

		if (col == board[0].length) {
			row++;
			col = 0;
		}

		if (row == board.length) {
			return;
		}

		// place
		// if (isItSafeToPlaceQueen(board, row, col)) {
		if (isItSafeToPlaceKnight(board, row, col)) {
			board[row][col] = true;
			NQueen(board, row, col + 1, tq, qpsf + 1, ans + "[" + row + "-" + col + "]");
			board[row][col] = false;
		}

		// not place
		NQueen(board, row, col + 1, tq, qpsf, ans);
	}

	public static boolean isItSafeToPlaceQueen(boolean[][] board, int row, int col) {

		// vertically up
		int r = row - 1;
		int c = col;

		while (r >= 0) {
			if (board[r][c]) {
				return false;
			}
			r--;
		}

		// horizontally left
		r = row;
		c = col - 1;

		while (c >= 0) {
			if (board[r][c]) {
				return false;
			}
			c--;
		}

		// diagonally left
		r = row - 1;
		c = col - 1;
		while (r >= 0 && c >= 0) {
			if (board[r][c]) {
				return false;
			}
			r--;
			c--;
		}

		// diagonally right
		r = row - 1;
		c = col + 1;
		while (r >= 0 && c < board[0].length) {
			if (board[r][c]) {
				return false;
			}
			r--;
			c++;
		}

		return true;

	}

	public static boolean isItSafeToPlaceKnight(boolean[][] board, int row, int col) {

		int[] rowArr = { -1, -2, -2, -1 };
		int[] colArr = { 2, 1, -1, -2 };

		for (int i = 0; i < rowArr.length; i++) {

			int nr = row + rowArr[i];
			int nc = col + colArr[i];

			if (nr >= 0 && nr < board.length && nc >= 0 && nc < board[0].length) {

				if (board[nr][nc]) {
					return false;
				}
			}

		}

		return true;

	}

	public static void blockedMaze(char[][] board, int row, int col, String ans, boolean[][] visited) {

		if (row == board.length - 1 && col == board[0].length - 1) {
			System.out.println(ans);
			return;
		}

		if (row < 0 || col < 0 || row >= board.length || col >= board[0].length || board[row][col] == 'x'
				|| visited[row][col]) {
			return;
		}

		visited[row][col] = true;

		blockedMaze(board, row - 1, col, ans + "T", visited);
		blockedMaze(board, row, col - 1, ans + "L", visited);
		blockedMaze(board, row + 1, col, ans + "D", visited);
		blockedMaze(board, row, col + 1, ans + "R", visited);

	}

	// https://leetcode.com/problems/word-search/

	public static void WordSearch(char[][] board, int row, int col, String word, int idx, boolean[][] visited) {

		if (row < 0 || col < 0 || row >= board.length || col >= board[0].length || visited[row][col]) {
			return;
		}

		if (board[row][col] != word.charAt(idx)) {
			return;
		}

		if (idx == word.length() - 1) {
			System.out.println("Word Found");
			return;
		}

		visited[row][col] = true;

		WordSearch(board, row - 1, col, word, idx + 1, visited); // T
		WordSearch(board, row + 1, col, word, idx + 1, visited); // D
		WordSearch(board, row, col - 1, word, idx + 1, visited); // L
		WordSearch(board, row, col + 1, word, idx + 1, visited); // R

		visited[row][col] = false;

	}

	public static boolean WordSearch2(char[][] board, int row, int col, String word, int idx, boolean[][] visited) {

		if (row < 0 || col < 0 || row >= board.length || col >= board[0].length || visited[row][col]) {
			return false;
		}

		if (board[row][col] != word.charAt(idx)) {
			return false;
		}

		if (idx == word.length() - 1) {
			// System.out.println("Word Found");
			return true;
		}

		visited[row][col] = true;

		boolean t = WordSearch2(board, row - 1, col, word, idx + 1, visited); // T
		boolean d = WordSearch2(board, row + 1, col, word, idx + 1, visited); // D
		boolean l = WordSearch2(board, row, col - 1, word, idx + 1, visited); // L
		boolean r = WordSearch2(board, row, col + 1, word, idx + 1, visited); // R

		visited[row][col] = false;

		return t || d || l || r;

	}

	public static void NQueen2(boolean[][] board, int row, String ans) {

		if (row == board.length) {
			System.out.println(++count + " " + ans);
			return;
		}

		for (int col = 0; col < board[0].length; col++) {

			if (isItSafeToPlaceQueen(board, row, col)) {
				board[row][col] = true;
				NQueen2(board, row + 1, ans + "[" + row + "-" + col + "] ");
				board[row][col] = false;
			}
		}

	}

	public static void KQueen(boolean[][] board, int row, int tq, int qpsf, String ans) {

		if (qpsf == tq) {
			System.out.println(++count + " " + ans);
			return;
		}

		if (row == board.length) {
			// System.out.println(++count + " " + ans);
			return;
		}

		for (int col = 0; col < board[0].length; col++) {

			if (isItSafeToPlaceQueen(board, row, col)) {
				board[row][col] = true;
				KQueen(board, row + 1, tq, qpsf + 1, ans + "[" + row + "-" + col + "] ");
				board[row][col] = false;
			}

		}

		KQueen(board, row + 1, tq, qpsf, ans);

	}
}
