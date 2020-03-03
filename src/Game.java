import java.util.Scanner;

public class Game {
	
	private static int playerShips = 0;
	private static int computerShips = 0;
	private static char[][] table = new char[10][10];
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
								
		startGame();

	}
	
	private static void startGame() {
		System.out.println("Welcome to Battle Ships!");
		getPlayerShips();
		getComputerShips();
		play();
	}
	
	private static void showTable() {
		System.out.println("   0123456789");
		for(int i = 0; i < table.length; i++) {
			System.out.print(i + " |");
			for(int j = 0; j < table[i].length; j++) {
				if(table[i][j] == '2') {
					System.out.print('\0');
				} else if(table[i][j] == '1') {
					System.out.print('@');
				} else if(table[i][j] == '*') {
					System.out.print('\0');
				} else {
					System.out.print(table[i][j]);
				}
			}
			System.out.println("| " + i);
		}
		System.out.println("   0123456789");
		System.out.println();
		System.out.println("Your ships: " + playerShips + " | Computer ships: " + computerShips);
	}
	
	private static void getPlayerShips() {
		while(playerShips < 5) {
			int counter = playerShips +1;
			System.out.println("Enter X coordinate for your " + counter + ". ship: ");
			int x = input.nextInt();
			System.out.println("Enter Y coordinate for your " + counter + ". ship: ");
			int y = input.nextInt();
			try {
			if(table[x][y] == '\0') {
				table[x][y] = '1';
				System.out.println("Ship added");
				playerShips++;
			}
			} catch (Exception e) {
				System.out.println("Coordinates not exist or reserved");
			}
		}
	}
	
	private static void getComputerShips() {
		while(computerShips < 5) {
			int x = (int) (Math.random()*10);
			int y = (int) (Math.random()*10);
			if(table[x][y] == '\0') {
				table[x][y] = '2';
				computerShips++;
				System.out.println("Computer added a ship");
			}
			
		}
	}
	
	private static void play() {
		while(true) {
			playerGuess();
			computerGuess();			
		}
	}
	
	private static void playerGuess() {
		System.out.println("Your turn!");
		showTable();
		while(true) {
			System.out.println("Enter X coordinate: ");
			int x = input.nextInt();
			System.out.println("Enter Y coordinate: ");
			int y = input.nextInt();
			try {
				if(table[x][y] == '2') {
					System.out.println("BOOM! You sunk a ship!");
					table[x][y] = '!';
					computerShips--;
					break;
				} else if(table[x][y] == '1') {
					System.out.println("You sunk your own ship!");
					table[x][y] = 'x';
					playerShips--;
					break;
				} else {
					System.out.println("You missed!");
					table[x][y] = '-';
					break;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Wrong coordinates");
			}
		}
		if(computerShips == 0) gameOver();
	}
	
	private static void computerGuess() {
		System.out.println("Computer's turn");
		while(true) {
			int x = (int) (Math.random()*10);
			int y = (int) (Math.random()*10);
			if(table[x][y] == '1') {
				System.out.println("The computer sunk your ship!");
				table[x][y] = 'x';
				playerShips--;
				break;
			} else if(table[x][y] == '2') {
				System.out.println("The computer sunk its own ship!");
				table[x][y] = '!';
				computerShips--;
				break;
			} else if(table[x][y] == '*') {
				continue;
			} else {
				System.out.println("The computer missed!");
				table[x][y] = '*';
				break;
			}
		}
		if(playerShips == 0) gameOver();
	}
	
	private static void gameOver() {
		input.close();
		if(playerShips > computerShips) {
			System.out.println("You won!");
			System.out.println("Your ships: " + playerShips + " | Computer ships: " + computerShips);
		} else {
			System.out.println("The computer won!");
			System.out.println("Your ships: " + playerShips + " | Computer ships: " + computerShips);
		}
		System.exit(0);
	}
}