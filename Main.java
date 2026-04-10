import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Board game = new Board();

		Scanner keyboard = new Scanner(System.in);

		System.out.println("\u001B[0mWelcome to BATTLESHIP!");
		System.out.println("Player 1 please place your ships now.");
		System.out.println("Player 2 look away!");
		game.printShipBoard();

		placeShips(keyboard, game);
		
		// STARTING GAME 
		
		boolean over = game.gameOver();
		
		int moves = 0;
		
		while (over == false){
		    game.printAttackBoard();
		    System.out.println("\u001B[0mPlayer 2's turn to attack.");
		    System.out.print("Enter attack coordinates (rows are A-J and columns are 1-10):");
		    String attackcoords = keyboard.nextLine().toUpperCase();
		    int targetRow = getRow(attackcoords);
		    int targetCol = getCol(attackcoords);
		    
		    game.fire(targetRow, targetCol);
		    
		    //game.sunkShips(); already called in gameOver in board
		    
		    over = game.gameOver();
		    moves++;
		}
		game.printAttackBoard();
		System.out.println("\u001B[0mGame over! Player 2 wins!");
		System.out.println("Player 2 won in " + moves + " moves.");
	}

	public static int getCol(String input) {
		String col = input.substring(1);
		int colnum = Integer.parseInt(col);
		return colnum;
	}

	public static int getRow(String input) {
		String row = input.substring(0, 1);
		int rownum = 0;
		if (row.equals("A")) {
			rownum = 1;
		}
		else if (row.equals("B")) {
			rownum = 2;
		}
		else if (row.equals("C")) {
			rownum = 3;
		}
		else if (row.equals("D")) {
			rownum = 4;
		}
		else if (row.equals("E")) {
			rownum = 5;
		}
		else if (row.equals("F")) {
			rownum = 6;
		}
		else if (row.equals("G")) {
			rownum = 7;
		}
		else if (row.equals("H")) {
			rownum = 8;
		}
		else if (row.equals("I")) {
			rownum = 9;
		}
		else {
			rownum = 10;
		}
		return rownum;
	}

	public static void placeShips(Scanner input, Board game) {
		String[] shipnames = {"carrier", "battleship", "destroyer", "submarine", "patroller"};
		String[] ships = {"C", "B", "D", "S", "P"};
		int[] sizes = {5, 4, 3, 3, 2};
        boolean accepted = false;
        
		for (int i = 0; i < 5; i++) {
		    while(accepted == false){
    			System.out.println("\u001B[0mGet ready to place your " + shipnames[i]);
    			System.out.print("Enter both row (A-J) and column (1-10) without spaces: ");
    			String rowcol = input.nextLine().toUpperCase();
    			System.out.print("Now enter the orientation of the ship. Horizontal (true) or vertical (false): ");
    			boolean orientation = input.nextBoolean();
    			
    			input.nextLine();
    			accepted = game.addShip(ships[i], getRow(rowcol), getCol(rowcol), orientation);
		    }
		    
		    game.printShipBoard();
		    System.out.println("\u001B[0m Ship added!");
		    accepted = false;
		}
		System.out.println("All ships added!");

	}
}
