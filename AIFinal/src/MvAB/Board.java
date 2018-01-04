package MvAB;









import java.util.*;

import MvAB.PointsAndScores;
import MvAB.Point;


class Board {
	
	
	int boardSize=10;
	
	

    List<Point> availablePoints;
    Scanner scan = new Scanner(System.in);
    int[][] board = new int[boardSize][boardSize]; 

    List<PointsAndScores> rootsChildrenScore = new ArrayList<>();

    
    
    
    
    //Set this to some value if you want to have some specified depth limit for search
    int uptoDepth = -1;
    
    
    long iteration=0;
    

    public int alphaBetaMinimax(int alpha,int beta ,int depth , Point currentPointC , Point currentPointP,int turn) {  
        
    	iteration++;
    	System.out.println("\niteration number :[ " + iteration + " ]\n");
		
		///////deneme birkibirkirk
		if(beta<=alpha){ 
			System.out.println("-----------------------------");
        	System.out.println("Pruning at depth = "+depth );
        	
        	
			
			//
        	
        	System.out.println("the board for " + depth +  " is :");
        	displayBoard(currentPointC,currentPointP);
        	System.out.println("Turn was: "+ turn + " , Alpha was :" + alpha + ",  Beta was: " + beta +"\n");
        	System.out.println("Available no of moves for comp: " + getAvailableStates(currentPointC).size() + " , For player : "
        			+getAvailableStates(currentPointP).size()   );
        	//
        	System.out.println("-----------------------------");
			
        	if(turn == 1) 
        		return Integer.MAX_VALUE; 
        	else 
        		return Integer.MIN_VALUE; 
			
			
		}
		///////////////////////////////////////////////
		
		
		if(turn == 1) {
			if(isGameOver(currentPointC)) {
				return -1000;
			}
			else if(depth>=17) {
				//System.out.println("[[[[[[[[[   "+evaulateSituation(currentPointC)+"    ]]]]]]]]]");
				return evaulateSituation(currentPointC);
			}
		}
		
		else if(turn == 2) {
			if(isGameOver(currentPointP)) {
				return +1000;
			}
			else if(depth>=20) {
				//System.out.println("[[[[[[[[[   "+evaulateSituation(currentPointP)+"    ]]]]]]]]]");
				return evaulateSituation(currentPointP);
			}
		}
		
		
		
		
		/*
		if(   (depth >= 15)    ) {
			return 0;
			
		}
		
		
		
		if(turn == 1 && isGameOver(currentPointC)) {
			return -100;
		}
		else if(turn == 2 && isGameOver(currentPointP)) {
			return +100;
		}
		*/
		
		
		
		
        //List<Point> pointsAvailable = getAvailableStates();
        List<Point> pointsAvailable = new ArrayList<Point>();
        
        if(turn == 1) {
        	pointsAvailable = getAvailableStates(currentPointC);
        }
        else {
        	pointsAvailable = getAvailableStates(currentPointP);
        }
        
		
		//bunun anlamý yok
		if(pointsAvailable.isEmpty()) return 0;
		
		
		
		
		
		if(depth==0) rootsChildrenScore.clear(); 
		
		
		
		//yukarýsý farklý
        
        int maxValue = Integer.MIN_VALUE, minValue = Integer.MAX_VALUE;
         
        for (int i = 0; i < pointsAvailable.size(); ++i) {  
            Point point = pointsAvailable.get(i);   
			
			
			int currentScore=0;
            
			if (turn == 1) { 
            	
                placeAMove(point, 1); 
                
                //System.out.println("TURN 1 depth: " + depth + 
                	//		" currentScore point(x,y)->(" + point.x + "," + point.y + ") cPointP-->("+
                		//	currentPointP.x + "," + currentPointP.y +")");
                
                
                
                System.out.println("TURN 1 depth: " + depth + 
            			" currentScore point(x,y)->(" + point.x + "," + point.y + ") cPoint Player-->("+
            			currentPointP.x + "," + currentPointP.y +")");
                
                
                
                
                //currentScore = minimax(depth + 1, 2 , point , currentPointP);
				//currentScore = alphaBetaMinimax(alpha, beta, depth+1, 2);//yenisi ama deðiþtir
                currentScore= alphaBetaMinimax(alpha,beta,depth+1,point,currentPointP,2);
                
				maxValue = Math.max(maxValue, currentScore); 
				
				
				//Set alpha yeni eklenti
                alpha = Math.max(currentScore, alpha);
				
				
                
				
                if(depth == 0)System.out.println("Score for position "+(i+1)+" = "+currentScore + "which is ("+
                		point.x +"," + point.y + ")\n");
                
                //if(currentScore >= 0){ if(depth == 0) computersMove = point;} 
                
                //alptha betadan dolayý gerek olmammasý lazým if(currentScore == 1){board[point.x][point.y] = 0; break;} 
                
                //if(i == pointsAvailable.size()-1 && max < 0){if(depth == 0)computersMove = point;}
				
				//eklenmesi gereken aþþaðýda,yukarýdaki 4 lü yok
				if(depth == 0)
                    rootsChildrenScore.add(new PointsAndScores(currentScore, point));
            
			
			
			} 
			
			
			
            
            else if (turn == 2) {
                
            	System.out.println("TURN 2 depth : "+ depth + " currentScore cPointC(x,y)->(" + currentPointC.x + "," 
            				+ currentPointC.y + ") point-->(" + point.x + "," + point.y +")");
            	
            	placeAMove(point, 2); 
                
				//int currentScore = minimax(depth + 1, 1 ,  currentPointC , point);
                //currentScore = alphaBetaMinimax(alpha, beta, depth+1, 1); //yenisi ama deðiþtir
				currentScore = alphaBetaMinimax(alpha,beta,depth+1,currentPointC,point,1);
				
				
				
				minValue = Math.min(minValue, currentScore);
				
				//Set beta
                beta = Math.min(currentScore, beta);


				//aþþaðýdaki abprun da yok 
                //if(min == -1){board[point.x][point.y] = 0; break;}
            }
			
			
			board[point.x][point.y] = 0; //Reset this point
			
			//If a pruning has been done, don't evaluate the rest of the sibling states
            if(currentScore == Integer.MAX_VALUE || currentScore == Integer.MIN_VALUE) break;
			
			
        } 
        return turn == 1 ? maxValue : minValue;
    }  
	
	
    
    long iteration2 =0;
	
    Point computersMove;
    
	public int minimax(int depth, int turn , Point currentPointC , Point currentPointP) {  
        //System.out.println("girdi");
		//displayBoard();
		//if (hasXWon()) return +1; 
        //if (hasOWon()) return -1;
		iteration2++;
		System.out.println("\niteration2 number :[ " + iteration2 + " ]\n");
		
		if(turn == 1 && isGameOver(currentPointC)) {
			return -1;
		}
		else if(turn == 2 && isGameOver(currentPointP)) {
			return +1;
		}
		
		else if(depth >=6) {
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
    
    
    
    
    
    
    
    
    
    
	
	
	public int evaulateSituation(Point currentPoint) {
		int tempScore=0;
		//System.out.println("laaaaaa");
		
		List<Point> tempStates = getAvailableStates(currentPoint);
		tempScore+= tempStates.size();
		//for(Point i:tempStates) {
			//tempScore+= getAvailableStates(i).size();
		//}
		//System.out.println("{{{{{ current point : " +currentPoint.x + " , " + currentPoint.y +"  tempScore: "+tempScore);
	
		return tempScore;
	}
	
	
	
	
	
	
	
	
	
	
    
    
    
    
    public boolean isGameOver(Point currentPoint) {
        //Game is over is someone has won, or board is full (draw)
    	if(getAvailableStates(currentPoint).isEmpty()) {
			return true;
		}
		
		
		return false;
    }

    
    

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

    public Point returnBestMove() {
        int MAX = -100000;
        int best = -1;

        for (int i = 0; i < rootsChildrenScore.size(); ++i) {
            if (MAX < rootsChildrenScore.get(i).score) {
                MAX = rootsChildrenScore.get(i).score;
                best = i;
            }
        }

        return rootsChildrenScore.get(best).point;
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

        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; ++j) {
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
	
    
    public void resetBoard() {
        for (int i = 0; i < boardSize; ++i) {
            for (int j = 0; j < boardSize; ++j) {
                board[i][j] = 0;
            }
        }
    } 
}