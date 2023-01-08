

public class test {
    public static void main(String[] args) {

        /* 
          Dictionary dic = new Dictionary(8543);
          
         // int num = Dictionary.hashFunction("563456");
        //  System.out.println(num);
          
         Record rec = new Record("4523", 10, 4);
          Record rec2 = new Record("45273", 20, 4);
          
          
          
          
          
          System.out.println("Record put " + dic.put (rec));
          System.out.println("Record put " + dic.put (rec2));
          // dic.remove("4523");
          dic.remove("45273");
          
          System.out.println ("Return rec2: " + dic.get("4523").getScore() );
         System.out.println("# of Records: " + dic.numRecords());
         */

         char[][] board2 = { { 'h', 'h', 'e', 'c' },
                            { 'h', 'e', 'c', 'c' },
                            { 'h', 'c', 'c', 'e' },
                            { 'c', 'h', 'c', 'e' },
        };

        char[][] board = { { 'c', 'h', 'c' },
                           { 'h', 'h', 'h' },
                           { 'e', 'e', 'c' },
        
        };

        Evaluate eval = new Evaluate(4, 4, 0);

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                eval.storePlay(row, col, board2[row][col]);
            }
        }

       // System.out.println("Won = " + eval.diagonalWin(1, 2, 'h'));
        System.out.println("Computer Won = " + eval.wins('c'));
        System.out.println("Human Won = " + eval.wins('h'));
        System.out.println("Draw  = " + eval.isDraw());
        System.out.println("Game State: " + eval.evalBoard()); 

    }
}