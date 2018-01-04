package Minimax;



//import java.util.*;



public class Game {

    public static void main(String[] args) {
        Board b = new Board();
        //Random rand = new Random();
        
        
        Point currentPointC = new Point();
        Point currentPointP = new Point(4,4);
        b.placeAMove(currentPointP, 2);
        
        
        //b.displayBoard(currentPointC,currentPointP);

        System.out.println("Select turn:\n\n1. Computer 2. User: ");
        int choice = b.scan.nextInt();
        
        
        /*if(choice == 1){
            //Point p = new Point(rand.nextInt(3), rand.nextInt(3));
            Point p = new Point(0,0);
        	b.placeAMove(p, 1);
            b.displayBoard();
            currentPointC = p;
        }
        
        */
        
        
        Point p = new Point(0,0);
    	b.placeAMove(p, 1);
    	b.displayBoard(currentPointC,currentPointP);
        currentPointC = p;
        
        
        
        if(choice == 2) {
    		System.out.println("Your move: ");
    		currentPointP = b.takeHumanInput(currentPointP);
    		b.displayBoard(currentPointC,currentPointP);
    	}
        
        
        //boolean cont = true;
        
        int i = 0;
        while (i<100) {
        	
        	//!b.gameOver()
        	
        	
        	///////////////////////////
        	long startTime = System.currentTimeMillis();
        	//code
        	
        	//////////////////////////////
        	
        	//long startTimeTwo = System.currentTimeMillis();
        	
        	
        	
        	
        	if (b.isGameOver(currentPointC)) {
            	System.out.println("Computer lost!!");
            	break;
            }
            
            
            b.minimax(0, 1 , currentPointC , currentPointP);  
            
            b.placeAMove(b.computersMove, 1);
            
            
            
            currentPointC = b.computersMove;
            b.displayBoard(currentPointC,currentPointP);
            
            long endTime = System.currentTimeMillis();
            System.out.println("It took " + (endTime - startTime) + " milliseconds");
            
            /*
            if (b.isGameOver(currentPointC)) {
            	System.out.println("Computer lost!!");
            	break;
            }
            */
            
            
            
            
            if (b.isGameOver(currentPointP)) {
            	System.out.println("Player lost!!");
            	break;
            }
            
            ////////////////////////////////
            //long endTime = System.nanoTime();
            //System.out.println("Took "+(endTime - startTime) + " ns"); 
            /////////////////////////////
            
            
        	
            System.out.println("Your move: ");
            
            //Point userMove = new Point(b.scan.nextInt(), b.scan.nextInt());
            currentPointP = b.takeHumanInput(currentPointP);
            
            //b.displayBoard(currentPointC,currentPointP);
            

            //b.placeAMove(userMove, 2); //2 for O and O is the user
            b.displayBoard(currentPointC,currentPointP);
            
            
            /*
            if (b.isGameOver(currentPointP)) {
            	System.out.println("Player lost!!");
            	break;
            }
            */
            
            
        	
        	
            
            
            i++;
        }
        
        /*
        
        if (b.hasXWon()) 
            System.out.println("Unfortunately, you lost!");
        else if (b.hasOWon()) 
            System.out.println("You win!"); //Can't happen
        else 
            System.out.println("It's a draw!");
        */
    
    
    
    
    
    
    }

    
}



