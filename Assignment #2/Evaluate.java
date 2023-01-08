
public class Evaluate {

    private char[][] gameBoard; // Game board
    private int adjacentTilestoWin; // Required tiles to win
    private int sizeOfBoard; // size of the board

    public Evaluate(int size, int tilesToWin, int maxLevels) {

        this.adjacentTilestoWin = tilesToWin;
        this.sizeOfBoard = size;
        // Creates a gameboard of size (size x size)
        gameBoard = new char[size][size];

        // Initializes the gameboard such that every entry stores the character 'e'
        // indicating that every position of the board is empty
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                gameBoard[row][col] = 'e';
            }
        }

    }

    // Returns an empty dictionary of size 6563
    public Dictionary createDictionary() {
        Dictionary dic = new Dictionary(6563);
        return dic;
    }

    // Uses string representation of a given game state as key to check if the game
    // state is found in the dictionary,
    // Returning the Record Object if it is found, and null if it is not found
    public Record repeatedState(Dictionary dict) {
        String gameState = StringGameState(this.gameBoard);

        return dict.get(gameState);
    }

    // Inserts a Record Object containing the game state, score, and level into the
    // dictionary
    public void insertState(Dictionary dict, int score, int level) {
        String gameState = StringGameState(this.gameBoard);

        Record rec = new Record(gameState, score, level);

        dict.put(rec);
    }

    // Stores a character into the gameboard at the specified row and column of the
    // gameboard
    public void storePlay(int row, int col, char symbol) {
        this.gameBoard[row][col] = symbol;
    }

    // Checks to see if the square is empty at the specified row and column of the
    // gameboard
    public boolean squareIsEmpty(int row, int col) {
        if (this.gameBoard[row][col] == 'e')
            return true;
        else
            return false;
    }

    // Checks to see if the square contains a computer tile at the specified row and
    // column of the gameboard
    public boolean tileOfComputer(int row, int col) {
        if (this.gameBoard[row][col] == 'c')
            return true;
        else
            return false;
    }

    // Checks to see if the square contains a human tile at the specified row and
    // column of the gameboard
    public boolean tileOfHuman(int row, int col) {
        if (gameBoard[row][col] == 'h')
            return true;
        else
            return false;
    }

    // Checks to see if there are the required number of adjacent tiles of type
    // symbol in the same row, column, or
    // diagonal of the game board
    public boolean wins(char symbol) {

        // Iterates through every square of the game board
        for (int row = 0; row < this.sizeOfBoard; row++) {
            for (int col = 0; col < this.sizeOfBoard; col++) {
                if (this.gameBoard[row][col] == symbol) {
                    if (rowWin(row, col, symbol) || columnWin(row, col, symbol) || diagonalWin(row, col, symbol))
                        return true;
                }
            }
        }

        return false;
    }

    // Checks to see if there are any empty tiles on the gameboard and if there are,
    // the game is not a draw
    public boolean isDraw() {
        for (int row = 0; row < this.sizeOfBoard; row++) {
            for (int col = 0; col < this.sizeOfBoard; col++) {
                if (this.gameBoard[row][col] == 'e')
                    return false;
            }
        }

        return true;

    }

    public int evalBoard() {

        if (wins('c'))
            return 3;
        else if (wins('h'))
            return 0;
        else if (isDraw())
            return 2;
        else
            return 1;
    }

    // Checks to see there are the required number of adjacent tiles of type symbol
    // in the same row
    private boolean rowWin(int row, int col, char symbol) {

        // counter that keeps track of how many tiles of the same symbol were found in
        // the same row
        int counter = 0;

        // Checks the right side of the row from where the character is located
        for (int column = col; column < this.sizeOfBoard; column++) {
            if (this.gameBoard[row][column] == symbol)
                counter++;
            else
                break;
        }

        // If it found that the number of tiles of the same symbol found on the right
        // side of the row from
        // where the character is located is at least the number of adjacent tiles to
        // win then it returns true

        if (counter >= this.adjacentTilestoWin)
            return true;
        else
            counter = 0;

        // Checks the left side of the row from where the character is located
        for (int column = col; column >= 0; column--) {
            if (this.gameBoard[row][column] == symbol)
                counter++;
            else
                break;
        }

        // If it found that the number of tiles of the same symbol found on the left
        // side of the row from
        // where the character is located is at least the number of adjacent tiles to
        // win then it returns true

        if (counter >= this.adjacentTilestoWin)
            return true;
        else
            return false;

    }

    // Checks to see there are the required number of adjacent tiles of type symbol
    // in the same column

    private boolean columnWin(int row, int col, char symbol) {

        // counter that keeps track of how many tiles of the same symbol were found in
        // the same column
        int counter = 0;

        // Checks the upper side of the column from where the character is located
        for (int rowOfBoard = row; rowOfBoard < this.sizeOfBoard; rowOfBoard++) {
            if (this.gameBoard[rowOfBoard][col] == symbol)
                counter++;
            else
                break;
        }

        // If it found that the number of tiles of the same symbol found on the upper
        // side of the column from
        // where the character is located is at least the number of adjacent tiles to
        // win then it returns true
        if (counter >= this.adjacentTilestoWin)
            return true;
        else
            counter = 0;

        // Checks the lower side of the column from where the character is located
        for (int rowOfBoard = row; rowOfBoard >= 0; rowOfBoard--) {
            if (this.gameBoard[rowOfBoard][col] == symbol)
                counter++;
            else
                break;
        }

        // If it found that the number of tiles of the same symbol found on the lower
        // side of the column from
        // where the character is located is at least the number of adjacent tiles to
        // win then it returns true
        if (counter >= this.adjacentTilestoWin)
            return true;
        else
            return false;

    }

    // Checks to see there are the required number of adjacent tiles of type symbol
    // in the same diagonal
    private boolean diagonalWin(int row, int col, char symbol) {

        // counter that keeps track of how many tiles of the same symbol were found in
        // the same diagonal
        int counter = 0;

        // Checks the lower right side of the diagonal from where the character is
        // located
        for (int rowOfBoard = row, colOfBoard = col; rowOfBoard < this.sizeOfBoard
                && colOfBoard < this.sizeOfBoard; rowOfBoard++, colOfBoard++) {
            if (this.gameBoard[rowOfBoard][colOfBoard] == symbol)
                counter++;
            else
                break;
        }

        // If it found that the number of tiles of the same symbol found on the lower
        // right side of the diagonal from
        // where the character is located is at least the number of adjacent tiles to
        // win then it returns true

        if (counter >= this.adjacentTilestoWin)
            return true;
        else
            counter = 0;

        // Checks the upper right side of the diagonal from where the character is
        // located
        for (int rowOfBoard = row, colOfBoard = col; rowOfBoard >= 0
                && colOfBoard < this.sizeOfBoard; rowOfBoard--, colOfBoard++) {
            if (this.gameBoard[rowOfBoard][colOfBoard] == symbol)
                counter++;
            else
                break;
        }

        // If it found that the number of tiles of the same symbol found on the upper
        // right side of the diagonal from
        // where the character is located is at least the number of adjacent tiles to
        // win then it returns true

        if (counter >= this.adjacentTilestoWin)
            return true;
        else
            counter = 0;

        // Checks the lower left side of the diagonal from where the character is
        // located
        for (int rowOfBoard = row, colOfBoard = col; rowOfBoard < this.sizeOfBoard
                && colOfBoard >= 0; rowOfBoard++, colOfBoard--) {
            if (this.gameBoard[rowOfBoard][colOfBoard] == symbol)
                counter++;
            else
                break;
        }

        // If it found that the number of tiles of the same symbol found on the lower
        // left side of the diagonal from
        // where the character is located is at least the number of adjacent tiles to
        // win then it returns true

        if (counter >= this.adjacentTilestoWin)
            return true;
        else
            counter = 0;

        // Checks the upper left side of the diagonal from where the character is
        // located

        for (int rowOfBoard = row, colOfBoard = col; rowOfBoard >= 0 && colOfBoard >= 0; rowOfBoard--, colOfBoard--) {
            if (this.gameBoard[rowOfBoard][col] == symbol)
                counter++;
            else
                break;
        }

        // If it found that the number of tiles of the same symbol found on the upper
        // left side of the diagonal from
        // where the character is located is at least the number of adjacent tiles to
        // win then it returns true

        if (counter >= this.adjacentTilestoWin)
            return true;
        else
            return false;

    }

    // private helper method that provides a string representation of a given game
    // state
    private String StringGameState(char[][] board) {
        String concatenatedString = "";

        for (int col = 0; col < this.sizeOfBoard; col++) {
            for (int row = 0; row < this.sizeOfBoard; row++) {
                concatenatedString += board[row][col];
            }
        }

        return concatenatedString;

    }
}
