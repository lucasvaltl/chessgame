/**
	<h1>Chessgame</h1>
	 * Purpose:
	 * This game enables you to play chess with a friend in the console
	 * <p>
	 * Description:
	 * The game uses unicode to display the characters in the console. PLayers can move the figures 
	 * and then the board is shown again after each move. Player input is given in standard chess notation
	 * e.g. e6 to e5.
	 * 
	 * @author Lucas
	 * @version $Date: 2016/10/03 10:24:11 $
	*/

import java.util.Random;
import java.util.Scanner;

public class Chessgame {
	public enum Chessmen {
		WHITE_KING, WHITE_QUEEN, WHITE_ROOK, WHITE_BISHOP, WHITE_KNIGHT, WHITE_PAWN, BLACK_KING, BLACK_QUEEN, BLACK_ROOK, BLACK_BISHOP, BLACK_KNIGHT, BLACK_PAWN, EMPTY
	}

	public static void printBoard(Chessmen[][] chessboard) {
		String[] labels = new String[] { "a", "b", "c", "d", "e", "f", "g", "h" };
		for (int h = 0; h <= 7; h++) {
			System.out.print("\t" + labels[h]);
		}
		// prints new line to start first row of chessboard matrix
		System.out.print("\n");
		// checks whether you are at the right end of the matrix
		int k = 0;
		// loops over matrix, prints corresponding row number at the start of
		// each row
		for (int i = 0; i < 8; i++) {
			if (k == 0) {
				System.out.print("\n\n" + i + "\t");
			}
			for (int j = 0; j < 8; j++) {

				switch (chessboard[i][j]) {
				case WHITE_KING: {
					System.out.print("\u265A\t");
					k = j;
					if (j == 7)
						k = 0;
					break;
				}
				case WHITE_QUEEN: {
					System.out.print("\u265B\t");
					k = j;
					if (j == 7)
						k = 0;
					break;
				}
				case WHITE_ROOK: {
					System.out.print("\u265C\t");
					k = j;
					if (j == 7)
						k = 0;
					break;
				}
				case WHITE_BISHOP: {
					System.out.print("\u265D\t");
					k = j;
					if (j == 7)
						k = 0;
					break;
				}
				case WHITE_KNIGHT: {
					System.out.print("\u265E\t");
					k = j;
					if (j == 7)
						k = 0;
					break;
				}
				case WHITE_PAWN: {
					System.out.print("\u265F\t");
					k = j;
					if (j == 7)
						k = 0;
					break;
				}
				case BLACK_KING: {
					System.out.print("\u2654\t");
					k = j;
					if (j == 7)
						k = 0;
					break;
				}
				case BLACK_QUEEN: {
					System.out.print("\u2655\t");
					k = j;
					if (j == 7)
						k = 0;
					break;
				}
				case BLACK_ROOK: {
					System.out.print("\u2656\t");
					k = j;
					if (j == 7)
						k = 0;
					break;
				}
				case BLACK_BISHOP: {
					System.out.print("\u2657\t");
					k = j;
					if (j == 7)
						k = 0;
					break;
				}
				case BLACK_KNIGHT: {
					System.out.print("\u2658\t");
					k = j;
					if (j == 7)
						k = 0;
					break;
				}
				case BLACK_PAWN: {
					System.out.print("\u2659\t");
					k = j;
					if (j == 7)
						k = 0;
					break;
				}
				case EMPTY: {
					System.out.print("  " + "\t");
					k = j;
					if (j == 7)
						k = 0;
					break;
				}
				}
			}
		}
		System.out.println("\n");
	}

	public static void createBoard(Chessmen[][] chessboard) {

		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j <= 7; j++) {
				if ((i == 0) && (j == 0))
					chessboard[i][j] = Chessmen.BLACK_ROOK;
				else if ((i == 0) && (j == 1))
					chessboard[i][j] = Chessmen.BLACK_KNIGHT;
				else if ((i == 0) && (j == 2))
					chessboard[i][j] = Chessmen.BLACK_BISHOP;
				else if ((i == 0) && (j == 3))
					chessboard[i][j] = Chessmen.BLACK_QUEEN;
				else if ((i == 0) && (j == 4))
					chessboard[i][j] = Chessmen.BLACK_KING;
				else if ((i == 0) && (j == 5))
					chessboard[i][j] = Chessmen.BLACK_BISHOP;
				else if ((i == 0) && (j == 6))
					chessboard[i][j] = Chessmen.BLACK_KNIGHT;
				else if ((i == 0) && (j == 7))
					chessboard[i][j] = Chessmen.BLACK_ROOK;
				else if ((i == 1 && j <= 7))
					chessboard[i][j] = Chessmen.BLACK_PAWN;
				else if ((i > 1 && i < 6) && (j < 8))
					chessboard[i][j] = Chessmen.EMPTY;
				else if ((i == 6) && (j < 8))
					chessboard[i][j] = Chessmen.WHITE_PAWN;
				if ((i == 7) && (j == 0))
					chessboard[i][j] = Chessmen.WHITE_ROOK;
				else if ((i == 7) && (j == 1))
					chessboard[i][j] = Chessmen.WHITE_KNIGHT;
				else if ((i == 7) && (j == 2))
					chessboard[i][j] = Chessmen.WHITE_BISHOP;
				else if ((i == 7) && (j == 3))
					chessboard[i][j] = Chessmen.WHITE_QUEEN;
				else if ((i == 7) && (j == 4))
					chessboard[i][j] = Chessmen.WHITE_KING;
				else if ((i == 7) && (j == 5))
					chessboard[i][j] = Chessmen.WHITE_BISHOP;
				else if ((i == 7) && (j == 6))
					chessboard[i][j] = Chessmen.WHITE_KNIGHT;
				else if ((i == 7) && (j == 7)) {
					chessboard[i][j] = Chessmen.WHITE_ROOK;
					break;
				}
			}
		}
	}

	public static void move(Chessmen[][] chessboard, String move) {

		String[] components = move.split(" ");

		// converts user input into positions and stores them in variables.
		// catches exception
		try {

			int xaxisold = boardtoMatrix(components[0].charAt(0));
			int yaxisold = boardtoMatrix(components[0].charAt(1));
			int xaxisnew = boardtoMatrix(components[2].charAt(0));
			int yaxisnew = boardtoMatrix(components[2].charAt(1));
			
			//validates user move using isvalid method
			if (isValid(chessboard, yaxisold, xaxisold, yaxisnew, xaxisnew)) {

				// moves piece to new field
				chessboard[yaxisnew][xaxisnew] = chessboard[yaxisold][xaxisold];
				// removes piece from old field
				chessboard[yaxisold][xaxisold] = Chessmen.EMPTY;
			} else {
				System.out.println("Invalid move. This piece is unable to move to this position!");
			}
		} catch (ArrayIndexOutOfBoundsException exception) {
			System.out.println("This is not a valid chess move. Try something like e6 to e5");
		}
	}

	public static boolean isValid(Chessmen[][] chessboard, int yaxisold, int xaxisold, int yaxisnew, int xaxisnew) {
		// create variable to guide through the validation process - no immediate use
		boolean validated;
		Chessmen currentpiece = chessboard[yaxisold][xaxisold];
		Chessmen destinationpiece = chessboard[yaxisnew][xaxisnew];

		// check if move is out of bounds:
		if ((xaxisnew < 0) || (xaxisnew > 7)) {
			System.out.println("Move is out of bounds!");
			return false;
		} else if ((yaxisnew < 0) || (yaxisnew > 7)) {
			System.out.println("Move is out of bounds!");
			return false;
		} else
			validated = true;

		// check if piece on destination is the same color or not
		// Define color of piece. true = white
		boolean currentpiececolor;
		if (currentpiece == Chessmen.WHITE_KING)
			currentpiececolor = true;
		else if (currentpiece == Chessmen.WHITE_QUEEN)
			currentpiececolor = true;
		else if (currentpiece == Chessmen.WHITE_ROOK)
			currentpiececolor = true;
		else if (currentpiece == Chessmen.WHITE_BISHOP)
			currentpiececolor = true;
		else if (currentpiece == Chessmen.WHITE_KNIGHT)
			currentpiececolor = true;
		else if (currentpiece == Chessmen.WHITE_PAWN)
			currentpiececolor = true;
		else
			currentpiececolor = false;
		// Define color of piece on destination. true = white
		boolean destinationpiececolor;
		if (destinationpiece == Chessmen.WHITE_KING)
			destinationpiececolor = true;
		else if (destinationpiece == Chessmen.WHITE_QUEEN)
			destinationpiececolor = true;
		else if (destinationpiece == Chessmen.WHITE_ROOK)
			destinationpiececolor = true;
		else if (destinationpiece == Chessmen.WHITE_BISHOP)
			destinationpiececolor = true;
		else if (destinationpiece == Chessmen.WHITE_KNIGHT)
			destinationpiececolor = true;
		else if (destinationpiece == Chessmen.WHITE_PAWN)
			destinationpiececolor = true;
		else
			destinationpiececolor = false;
		// check if destination of move is empty or occupied by piece of same
		// color
		if (destinationpiece == Chessmen.EMPTY) {
			validated = true;
		} else if ((currentpiececolor == destinationpiececolor)) {
			return false;
		} else {
			validated = true;
		}

		switch (currentpiece) {
		case WHITE_KING: {
			boolean toofarxaxis = ((Math.abs(xaxisnew - xaxisold)) > 1);
			boolean toofaryaxis = ((Math.abs(yaxisnew - yaxisold)) > 1);
			if (toofarxaxis || toofaryaxis)
				return false;
			else
				return true;

		}
		case WHITE_QUEEN: {
			if (((Math.abs(xaxisnew - xaxisold)) == 0) || ((Math.abs(yaxisnew - yaxisold)) == 0)||(Math.abs(xaxisnew - xaxisold) == (Math.abs(yaxisnew - yaxisold))))
				return true;
			else {
				return false;
			}

		}
		case WHITE_ROOK: {
			if (((Math.abs(xaxisnew - xaxisold)) == 0) || ((Math.abs(yaxisnew - yaxisold)) == 0))
				return true;
			else
				return false;
		}
		case WHITE_BISHOP: {
			if (Math.abs(xaxisnew - xaxisold) == (Math.abs(yaxisnew - yaxisold)))
				return true;
			else
				return false;
		}
		case WHITE_KNIGHT: {
			if (((Math.abs(xaxisnew - xaxisold)) == 1) && ((Math.abs(yaxisnew - yaxisold)) == 2))
				return true;

			else if (((Math.abs(xaxisnew - xaxisold)) == 2) && ((Math.abs(yaxisnew - yaxisold)) == 1))
				return true;
			else
				return false;
		}
		case WHITE_PAWN: {

			if (((Math.abs(xaxisnew - xaxisold)) == 0) && ((yaxisnew - yaxisold) > -2) && ((yaxisnew - yaxisold) < 0))
				return true;
			else {
				return false;
			}
		}
		case BLACK_KING: {
			boolean toofarxaxis = ((Math.abs(xaxisnew - xaxisold)) > 1);
			boolean toofaryaxis = ((Math.abs(xaxisnew - xaxisold)) > 1);
			if (toofarxaxis || toofaryaxis)
				return false;
			else
				return true;
		}
		case BLACK_QUEEN: {
			if (((Math.abs(xaxisnew - xaxisold)) == 0) || ((Math.abs(yaxisnew - yaxisold)) == 0)||(Math.abs(xaxisnew - xaxisold) == (Math.abs(yaxisnew - yaxisold))))
				return true;
			else {
				return false;
			}
		}
		case BLACK_ROOK: {
			if (((Math.abs(xaxisnew - xaxisold)) == 0) || ((Math.abs(yaxisnew - yaxisold)) == 0))
				return true;
			else
				return false;
		}
		case BLACK_BISHOP: {
			if (Math.abs(xaxisnew - xaxisold) == (Math.abs(yaxisnew - yaxisold)))
				return true;
			else
				return false;
		}
		case BLACK_KNIGHT: { 
			if (((Math.abs(xaxisnew - xaxisold)) == 1) && ((Math.abs(yaxisnew - yaxisold)) == 2))
				return true;

			else if (((Math.abs(xaxisnew - xaxisold)) == 2) && ((Math.abs(yaxisnew - yaxisold)) == 1))
				return true;
			else
				return false;
		}
		case BLACK_PAWN: {

			if (((Math.abs(xaxisnew - xaxisold)) == 0) && ((yaxisnew - yaxisold) > 0) && ((yaxisnew - yaxisold) < 2))
				return true;
			else {
				return false;

			}
		}
		case EMPTY: {

			System.out.println("You are trying to move nothing!");
			return false;

		}
		default: {

			System.out.println("Something weird just happened...try moving one of your figures");
			return false;

		}
		}

	}

	public static int boardtoMatrix(char position) {
		int matrixnumber = 0;
		switch (position) {
		case 'a': {
			matrixnumber = 0;
			break;
		}
		case 'b': {
			matrixnumber = 1;
			break;
		}
		case 'c': {
			matrixnumber = 2;
			break;
		}
		case 'd': {
			matrixnumber = 3;
			break;
		}
		case 'e': {
			matrixnumber = 4;
			break;
		}
		case 'f': {
			matrixnumber = 5;
			break;
		}
		case 'g': {
			matrixnumber = 6;
			break;
		}
		case 'h': {
			matrixnumber = 7;
			break;
		}
		case '0': {
			matrixnumber = 0;
			break;
		}
		case '1': {
			matrixnumber = 1;
			break;
		}
		case '2': {
			matrixnumber = 2;
			break;
		}
		case '3': {
			matrixnumber = 3;
			break;
		}
		case '4': {
			matrixnumber = 4;
			break;
		}
		case '5': {
			matrixnumber = 5;
			break;
		}
		case '6': {
			matrixnumber = 6;
			break;
		}
		case '7': {
			matrixnumber = 7;
			break;
		}

		}
		return matrixnumber;
	}

	public static void main(String[] args) {

		// Excercise5.createBoard();
		Chessmen[][] chessboard = new Chessmen[8][8];
		createBoard(chessboard);

		System.out.println(
				"\n\n\n Welcome to chess. The board has already been set up for you.\n Please find a partner to play and then,\n press enter to be assigned a color.");
		Scanner input = new Scanner(System.in);
		// print blank line to make it more readable
		String nextline = input.nextLine();
		Random randomizer = new Random();
		boolean randomcolor = randomizer.nextBoolean();
		if (randomcolor)
			System.out.println(
					" Congratulations. You were assigned white.\n You may start. Enter a move in standard chess\n notation, for example e5 to e6\n and press enter.\n"
							+ " You can enter end game at any time\n in order to quit the game");
		else
			System.out.println(
					" Congratulations. You were assigned black.\n Your opponent may start. Enter a move in standard chess\n notation, for example e5 to e6\n and press enter."
							+ " You can enter end game at any time\n in order to quit the game");
		printBoard(chessboard);

		// Starts an infinite loop until someone exits the game
		while (true) {

			String moveinput = input.nextLine();
			if (moveinput.equals("end game")) {
				System.out.println("Thank you for playing");
				input.close();
				System.exit(0);
			}
			move(chessboard, moveinput);
			printBoard(chessboard);
		}

	}
}

