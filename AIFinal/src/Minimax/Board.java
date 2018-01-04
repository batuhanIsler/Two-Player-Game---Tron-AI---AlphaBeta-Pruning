package Minimax;


import java.util.*;


public class Board {
	List<Point> availablePoints;
	Scanner scan = new Scanner(System.in);
	int boardSize=5;
	int[][] board = new int[boardSize][boardSize];
	
	public Board() {}
	
	
	//---------------------------------
	
	public boolean isGameOver( Point currentPoint) {
		
		// 1 for computer 2 for person
		
		//if( getAvailableStates(currentState).isEmpty() ) {
			//return true;
		//}
		
		if(getAvailableStates(currentPoint).isEmpty()) {
			return true;
		}
		
		
		return false;
		
	}
	
	
	/*
	public boolean hasXwon() {
		return false;
	}
	
	public boolean hasOwon() {
		return false;
	}
	
	*/
	
	
	
	
	
	//----------------------------------------
	
	public List<Point> getAvailableStates( Point currentPoint ) {
        availablePoints = new ArrayList<>();
        int i = currentPoint.x;
        int j = currentPoint.y;
        
        //left
        if( j!=0 && board[i][j-1]==0 ) {
        	availablePoints.add(new Point(i,j-1));
        }
        //up
        //j=currentPoint.y;
        
        if(i!=0 && board[i-1][j]==0) {
        	availablePoints.add(new Point(i-1,j));
        }
        
        //right
        //i=currentPoint.x;
        
        if(j!=(boardSize-1) && board[i][j+1]==0) {
        	availablePoints.add(new Point(i,j+1));
        }
        
        //down
        //i=currentPoint.x;
        
        if(i!=(boardSize-1) && board[i+1][j]==0) {
        	availablePoints.add(new Point(i+1,j));
        }
        
        
        /*
        if(currentPoint.x == 1 && currentPoint.y == 1) {
        	 
            System.out.println("bas");
            for(Point k:availablePoints) {
            	System.out.println("den k.x -->" + k.x + " k.y-->" + k.y);
            }
            //right and down needs to be change
            System.out.println("bit");
            
        }*/
        
        /*
        System.out.println("bas");
        for(Point k:availablePoints) {
        	System.out.println("den k.x -->" + k.x + " k.y-->" + k.y);
        }
        //right and down needs to be change
        System.out.println("bit");
        */
        return availablePoints;
    }
	
	public void placeAMove(Point point, int player) {
        board[point.x][point.y] = player;   //player = 1 for X, 2 for O
    }
	
	public Point takeHumanInput(Point currentPoint) {
        System.out.println("Your move [w,a,s,d] :  ");
        char move = scan.next().charAt(0);
        
        System.out.println("----->" + move + "\n");
        
        int x = currentPoint.x;
        int y = currentPoint.y;
        
        if(move == 'w') {
        	
        	Point point = new Point(x-1,y);
        	placeAMove(point,2);
        	return point;
        	//displayBoard();
        	//System.out.println("x: " + point.x + "  y: " + point.y);
        	//displayBoard();
        	//System.out.println("\n---->x : " + (x-1) + "  y--> " + y + "x' --> "+ point.x  + "  x''--> " + currentPoint.x);
        }
        else if(move == 'a') {
        	Point point = new Point(x,y-1);
        	placeAMove(point,2);
        	return point;
        }
        else if(move == 's') {
        	Point point = new Point(x+1,y);
        	placeAMove(point,2);
        	return point;
        }
        else if(move == 'd') {
        	Point point = new Point(x,y+1);
        	placeAMove(point,2);
        	return point;
        }
        else {
        	System.out.println("erroooor!!!!!!!!!!");
        	Point point = new Point();
        	return point;
        }
        
        //Point point = new Point(x, y);
        //placeAMove(point, 2); 
        
        
        
    }
	
	public void displayBoard(Point currentPointC,Point currentPointP) {
        System.out.println();

        for (int i = 0; i < boardSize; ++i) {
            for (int j = 0; j < boardSize; ++j) {
            	if(i==currentPointC.x && j == currentPointC.y) {
            		System.out.print("C" + " ");
            	}
            	else if(i== currentPointP.x && j==currentPointP.y) {
            		System.out.print("P" + " ");
            	}
            	else {
            		System.out.print(board[i][j] + " ");
            	}
                
            }
            System.out.println();

        }
    } 
	
	
	
	
	
	Point computersMove; 
	//public int minimax(int depth, int turn , Point currentPointC , Point currentPointP)
	
	
	long iteration =0;
	
	public int minimax(int depth, int turn , Point currentPointC , Point currentPointP) {  
        //System.out.println("girdi");
		//displayBoard();
		//if (hasXWon()) return +1; 
        //if (hasOWon()) return -1;
		iteration++;
		System.out.println("\niteration number :[ " + iteration + " ]\n");
		
		if(turn == 1 && isGameOver(currentPointC)) {
			return -1;
		}
		else if(turn == 2 && isGameOver(currentPointP)) {
			return +1;
		}
		
		else if(depth >=17) {
			return 0;
		}
		
		
		
        //List<Point> pointsAvailable = getAvailableStates();
        List<Point> pointsAvailable = new ArrayList<Point>();
        
        if(turn == 1) {
        	pointsAvailable = getAvailableStates(currentPointC);
        }
        else {
        	pointsAvailable = getAvailableStates(currentPointP);
        }
        //if (pointsAvailable.isEmpty()) return 0; 
        
        
        /*
        System.out.println("-----------------------1111---------");
        for(Point k:pointsAvailable) {
        	System.out.println("x -> " + k.x + " y -> " + k.y +"\n\n");
        }
        
        System.out.println("-----------------------222---------");
        */
        
        
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
         
        for (int i = 0; i < pointsAvailable.size(); ++i) {  
            Point point = pointsAvailable.get(i);   
            if (turn == 1) { 
                placeAMove(point, 1); 
                
                System.out.println("TURN 1 depth: " + depth + 
                			" currentScore point(x,y)->(" + point.x + "," + point.y + ") cPointP-->("+
                			currentPointP.x + "," + currentPointP.y +")");
                
                int currentScore = minimax(depth + 1, 2 , point , currentPointP);
                
                //int currentScore = minimax(depth + 1, 2);
                max = Math.max(currentScore, max);
                
                if(depth == 0)System.out.println("Score for position "+(i+1)+" = "+currentScore + "which is ("+
                		point.x +"," + point.y + ")\n");
                
                if(currentScore >= 0){ if(depth == 0) computersMove = point;} 
                
                if(currentScore == 1){board[point.x][point.y] = 0; break;} 
                
                if(i == pointsAvailable.size()-1 && max < 0){if(depth == 0)computersMove = point;}
            } 
            
            else if (turn == 2) {
                
            	System.out.println("TURN 2 depth : "+ depth + " currentScore cPointC(x,y)->(" + currentPointC.x + "," 
            				+ currentPointC.y + ") point-->(" + point.x + "," + point.y +")");
            	
            	placeAMove(point, 2); 
                int currentScore = minimax(depth + 1, 1 ,  currentPointC , point);
                min = Math.min(currentScore, min); 
                if(min == -1){board[point.x][point.y] = 0; break;}
            }
            board[point.x][point.y] = 0; //Reset this point
        } 
        return turn == 1?max:min;
    }  
	
	
	
	
	
	
	
	
	
	
}
