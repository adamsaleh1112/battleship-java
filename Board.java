public class Board {
	private String[][] shipBoard = new String[11][11];
	private String[][] attackBoard = new String[11][11];

	public Board() {
		shipBoard[0] = new String[] {" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
		shipBoard[1] = new String[] {"A", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"};
		shipBoard[2] = new String[] {"B", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"};
		shipBoard[3] = new String[] {"C", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"};
		shipBoard[4] = new String[] {"D", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"};
		shipBoard[5] = new String[] {"E", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"};
		shipBoard[6] = new String[] {"F", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"};
		shipBoard[7] = new String[] {"G", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"};
		shipBoard[8] = new String[] {"H", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"};
		shipBoard[9] = new String[] {"I", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"};
		shipBoard[10] = new String[] {"J", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"};

		attackBoard[0] = new String[] {" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
		attackBoard[1] = new String[] {"A", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"};
		attackBoard[2] = new String[] {"B", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"};
		attackBoard[3] = new String[] {"C", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"};
		attackBoard[4] = new String[] {"D", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"};
		attackBoard[5] = new String[] {"E", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"};
		attackBoard[6] = new String[] {"F", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"};
		attackBoard[7] = new String[] {"G", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"};
		attackBoard[8] = new String[] {"H", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"};
		attackBoard[9] = new String[] {"I", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"};
		attackBoard[10] = new String[] {"J", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"};
	}

	public boolean addShip(String ship, int startrow, int startcol, boolean direction) {
		int size = 0;


		if (ship.equals("C")) {
			size = 5;
		}
		else if (ship.equals("B")) {
			size = 4;
		}
		else if (ship.equals("D")) {
			size = 3;
		}
		else if (ship.equals("S")) {
			size = 3;
		}
		else {
			size = 2;
		}


		if (direction) { // horizontal
			if (size+startcol > 11) {
				System.out.println("Ship cannot be placed here, try again.");
				return false;
			}

			for (int i = startrow; i < startrow+1; i++) {
				for (int j = startcol; j < startcol+size; j++) {
					shipBoard[i][j] = ship;
				}
			}
		}
		else { // vertical
			if (size+startrow > 11) {
				System.out.println("Ship cannot be placed here, try again.");
				return false;
			}

			for (int j = startcol; j<startcol+1; j++) {
				for (int i = startrow; i<startrow+size; i++) {
					shipBoard[i][j] = ship;
				}
			}
		}
		return true;
	}

	public boolean fire(int row, int col) {
		if (shipBoard[row][col].equals("-")) {
			attackBoard[row][col] = "M";
			return false;
		}
		else {
			shipBoard[row][col] = shipBoard[row][col].toLowerCase();
			attackBoard[row][col] = "X";
			return true;
		}
	}

	public boolean gameOver() {
		int num = sunkShips();
		if (num == 5) {
			return true;
		}
		return false;
	}

	public int sunkShips() {
		int sunknum = 0;
		String[] ships = {"C", "B", "D", "S", "P"};

		for (String ship : ships) {
			boolean sunk = true;
			for (int i = 1; i < 11; i++) { // starting at 1 not 0 to avoid it confusing the row labels for a ship
				for (int j = 1; j < 11; j++) {
					if (shipBoard[i][j].equals(ship)) {
						sunk = false;
					}
				}
			}

			if (sunk) {
				sunknum++;
				if (ship.equals("C")) {
					System.out.println("\u001B[33mThe carrier has been sunk.\u001B[0m");
				} else if (ship.equals("B")) {
					System.out.println("\u001B[33mThe battleship has been sunk.\u001B[0m");
				} else if (ship.equals("D")) {
					System.out.println("\u001B[33mThe destroyer has been sunk.\u001B[0m");
				} else if (ship.equals("S")) {
					System.out.println("\u001B[33mThe submarine has been sunk.\u001B[0m");
				} else if (ship.equals("P")) {
					System.out.println("\u001B[33mThe patroller has been sunk.\u001B[0m");
				}
			}
		}
		return sunknum;
	}

	public void printAttackBoard() {
		System.out.println("");
		for (int i = 0; i < attackBoard.length; i++) {
			for (int j = 0; j < attackBoard[i].length; j++) {
				if (attackBoard[i][j] == "-") {
					System.out.print("\u001B[34m" + attackBoard[i][j] + " ");
				}
				else if (attackBoard[i][j] == "M") {
					System.out.print("\u001B[94m" + attackBoard[i][j] + " ");
				}
				else if (attackBoard[i][j] == "X") {
					System.out.print("\u001B[31m" + attackBoard[i][j] + " ");
				}
				else {
					System.out.print("\u001B[0m" + attackBoard[i][j] + " ");
				}
			}
			System.out.println("");
		}
		System.out.println("");
	}

	public void printShipBoard() {
		System.out.println("");
		for (int i = 0; i < shipBoard.length; i++) {
			for (int j = 0; j < shipBoard[i].length; j++) {
				if (shipBoard[i][j] == "-") {
					System.out.print("\u001B[34m" + shipBoard[i][j] + " ");
				}
				else {
					System.out.print("\u001B[0m" + shipBoard[i][j] + " ");
				}
			}
			System.out.println("");
		}
		System.out.println("");
	}
}
