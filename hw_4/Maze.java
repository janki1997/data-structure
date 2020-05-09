package lab4;

import java.util.ArrayList;
import java.util.Stack;

import lab4.PairInt;

public class Maze implements GridColors {

    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    // ADD METHOD FOR PROBLEM 1 HERE
    
    	public boolean findMazePath(int x, int y) 
    	{
    		
    		if ((x > maze.getNCols() - 1 || x < 0) || (y > maze.getNRows() - 1 || y < 0) || (maze.getColor(x, y) == BACKGROUND || maze.getColor(x, y) == TEMPORARY))
    		{
    			
    			
    			return false;
    			
    			
    		} 
    	else if (maze.getColor(x, y) == NON_BACKGROUND)
    	{
    			maze.recolor(x, y, TEMPORARY);
    			
    			if ((x == (maze.getNCols() - 1) && y == (maze.getNRows() - 1)) || this.findMazePath(x-1,y) || this.findMazePath(x+1,y) || this.findMazePath(x,y-1) || this.findMazePath(x,y+1)) 
    			{
    				
    				
    				maze.recolor(x, y, PATH);
    				return true;
    				
    				
    			}
    		}
    		
    		
    		return this.findMazePath(x-1,y) || this.findMazePath(x+1,y) || this.findMazePath(x,y-1) || this.findMazePath(x,y+1);
	}
    	
     
    // ADD METHOD FOR PROBLEM 2 HERE
    /**
     * Helper function of findAllMazePaths.
     * @param x
     * @param y
     * @param res
     * @param trace
     */

    	
    	 public ArrayList < ArrayList < PairInt >> findAllMazePaths ( int x, int y) {
        	 ArrayList < ArrayList < PairInt >> Final = new ArrayList < >();
        	 Stack < PairInt > trace = new Stack < >();
        	 findMazePathStackBased (0 ,0 , Final , trace );
        	 return Final ;
        }
        
        public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> Final, Stack<PairInt> trace) 
        {
        	
        	if((x > maze.getNCols() - 1 || y > maze.getNRows() - 1) || (x < 0 || y < 0) || (maze.getColor(x, y) == BACKGROUND || maze.getColor(x, y) == TEMPORARY))
        	
        	{
        		
        		return;
        		
        	}
        	
        	else if(x == maze.getNCols()-1 && y == maze.getNRows()-1)
        	{
        		
        		trace.push(new PairInt(x,y));
        		ArrayList<PairInt> cells  = new ArrayList<PairInt>();
        		cells.addAll(trace);
        		Final.add(cells);
        		trace.pop(); 
        		maze.recolor(x, y, NON_BACKGROUND);
                return;
                
        	}
        	
        	else
        		
        	{
        		trace.push(new PairInt(x,y));
        		maze.recolor(x, y, TEMPORARY);
        		findMazePathStackBased(x + 1, y, Final, trace);
        		findMazePathStackBased(x - 1, y, Final, trace);
        		findMazePathStackBased(x, y + 1, Final, trace);
        		findMazePathStackBased(x, y - 1, Final, trace);
        		maze.recolor(x, y, NON_BACKGROUND);
        		trace.pop();
        		return;
        		
        	}
        }


   
    // ADD METHOD FOR PROBLEM 3 HERE
    /**
     * Returns the shortest path that can be taken to reach the end of the maze.
     * @param x
     * @param y
     * @return
     */

    
    public ArrayList<PairInt> findMazePathMin(int x, int y)
    {
    	
    	ArrayList<ArrayList<PairInt>> Final = this.findAllMazePaths(0, 0);
		ArrayList<PairInt> small = new ArrayList<>();
		int length = Final.size();
		if (!Final.isEmpty()) 
		{
			
			int[] index = new int[length];
			int Ii = 0; 
			
			for (int i = 0; i < length ; i++) 
			{
				
				index[i] = Final.get(i).size();
				
			}
			
			
			int Iie = index[0];
			for (int j = 0; j < length; j++)
			{
				
				if (Iie > index[j]) 
					

				{
					
					Iie = index[j];
					Ii = j;
		}
				}
//			}
			small = Final.get(Ii);

		}
		return small;

	}

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/