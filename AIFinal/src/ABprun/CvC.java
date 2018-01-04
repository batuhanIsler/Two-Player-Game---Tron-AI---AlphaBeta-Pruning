package ABprun;






import java.util.Random;

import ABprun.*;



//alpha beta computer versus computer ( free space important one)


public class CvC {

    public static void main(String[] args) {
        Board b = new Board();
        
        //Random rand = new Random();
        
        
        Point currentPointCo = new Point();
        Point currentPointCt = new Point(0,0);
        b.placeAMove(currentPointCt, 2);
        
        
        //b.displayBoard();

        
        
        /*if(choice == 1){
            //Point p = new Point(rand.nextInt(3), rand.nextInt(3));
            Point p = new Point(0,0);
        	b.placeAMove(p, 1);
            b.displayBoard();
            currentPointC = p;
        }
        
        */
        
        
        Point p = new Point(2,2);
    	b.placeAMove(p, 1);
        
        currentPointCo = p;
        b.displayBoard(currentPointCo,currentPointCt);
        /*
        
        if(choice == 2) {
    		System.out.println("Your move: ");
    		currentPointP = b.takeHumanInput(currentPointP);
    		 b.displayBoard();
    	}
        
        */
        //boolean cont = true;
		
		
		
		
		
		
		
		
		
		//oyun baslagici assagisi
		
		
		
        
        int i = 0;
        while (i<1000) {
        	
        	//!b.gameOver()
        	System.out.println("Continue?");
        	String choice = b.scan.next();
        	
        	
        	
        	
        	
        	if (b.isGameOver(currentPointCo)) {
            	System.out.println("Computer  1 lost!!");
            	break;
            }
        	long startTime = System.currentTimeMillis();
            
            
            //eski b.minimax(0, 1 , currentPointC , currentPointP);  
			//yeni degistir b.alphaBetaMinimax(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 1);
			//yeni geldi
			b.alphaBetaMinimax(Integer.MIN_VALUE,Integer.MAX_VALUE,0,currentPointCo,currentPointCt,1);
			//yeni bitti
			
			
			
			//eklenti baslagici
			for (PointsAndScores pas : b.rootsChildrenScore) 
                System.out.println("Point: " + pas.point + " Score: " + pas.score);
			//eklenti bitis
			
			
			
            
            //eski b.placeAMove(b.computersMove, 1);
			b.placeAMove(b.returnBestMove(), 1);
            
			
            
            currentPointCo = b.returnBestMove();
            
            b.displayBoard(currentPointCo,currentPointCt);
            
            long endTime = System.currentTimeMillis();
            System.out.println("It took " + (endTime - startTime) + " milliseconds");
            
            /*
            if (b.isGameOver(currentPointC)) {
            	System.out.println("Computer lost!!");
            	break;
            }
            */
            
            
            
            
            if (b.isGameOver(currentPointCt)) {
            	System.out.println("Computer 2 lost!!");
            	break;
            }
            
            
            System.out.println("Continue?");
        	String choiceTwo = b.scan.next();
        	

        	startTime = System.currentTimeMillis();
        	
            //System.out.println("Your move: ");
            
            //Point userMove = new Point(b.scan.nextInt(), b.scan.nextInt());
            b.alphaBetaMinimax(Integer.MIN_VALUE,Integer.MAX_VALUE,0,currentPointCt,currentPointCo,1);
            
            for (PointsAndScores pas : b.rootsChildrenScore) 
                System.out.println("Point: " + pas.point + " Score: " + pas.score);
            
            
            b.placeAMove(b.returnBestMove(), 2);
            
            currentPointCt = b.returnBestMove();
            b.displayBoard(currentPointCo,currentPointCt);
            
            
            
            
            
            
            endTime = System.currentTimeMillis();
            System.out.println("It took " + (endTime - startTime) + " milliseconds");
            

            //b.placeAMove(userMove, 2); //2 for O and O is the user
            
            
            /*
            if (b.isGameOver(currentPointP)) {
            	System.out.println("Player lost!!");
            	break;
            }
            */
            
            
            
            
            i++;
        }
        
        
        
        
    
    
    
    
    }

    
}



