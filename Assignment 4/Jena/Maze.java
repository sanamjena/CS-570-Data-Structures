package Maze;

import java.util.ArrayList;
import java.util.Stack;

public class Maze implements GridColors{
	
	 /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
    	findAllMazePaths(0,0);
    	findMazePathMin(0,0);
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
    public boolean findMazePath(int x, int y) {
        // COMPLETE HERE FOR PROBLEM 1

    	if(x>maze.getNCols()-1||y>maze.getNRows()-1||x<0||y<0) return false;
    	
    	if(maze.getColor(x, y)!=NON_BACKGROUND) return false;
 	
    	if(x==maze.getNCols()-1&&y==maze.getNRows()-1) {
    		maze.recolor(x, y, PATH);
    		return true;
    	}else{
    		maze.recolor(x, y, PATH);

    		boolean b1,b2,b3,b4;
    		b1=findMazePath(x-1,y);
    		    		
    		b2=findMazePath(x+1,y);
    		    		
    		b3=findMazePath(x,y-1);
    		    		
    		b4=findMazePath(x,y+1);

    		if((b1||b2||b3||b4)==false){
    			maze.recolor(x, y, TEMPORARY);
    			return false;
    		}else return true;
    	}
    }

    // ADD METHOD FOR PROBLEM 2 HERE
    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace){
    	if(x>maze.getNCols()-1||y>maze.getNRows()-1||x<0||y<0){
    		return;
    	}
    	if(maze.getColor(x, y)!=NON_BACKGROUND){
    		return;
    	}
    	if(x==maze.getNCols()-1&&y==maze.getNRows()-1){
    		PairInt now =new PairInt(x,y);
    		ArrayList<PairInt> thisPath =new ArrayList<PairInt>();
    		trace.push(now);
    		thisPath.addAll(trace);
    		result.add(thisPath);
    		trace.pop();
    		return;
    	}
    	else{
    		PairInt now =new PairInt(x,y);
    		maze.recolor(x, y, PATH);
    		trace.push(now);
    		findMazePathStackBased(x-1,y,result,trace);
    		findMazePathStackBased(x+1,y,result,trace);
    		findMazePathStackBased(x,y-1,result,trace);
    		findMazePathStackBased(x,y+1,result,trace);
    		trace.pop();
    		maze.recolor(x, y, NON_BACKGROUND);
    	}
    }
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y){
    	ArrayList <ArrayList<PairInt>> result = new ArrayList < >();
    	Stack <PairInt> trace = new Stack < >();
    	findMazePathStackBased (0 ,0 , result , trace );
    	if (result.size()!=0){
    		System.out.println("All the paths are");
    		System.out.println(result.get(0));
    		for(int i=1;i<result.size();i++){
    			System.out.println(result.get(i));
    		}
    	}
    	return result ;
    }
    
    // ADD METHOD FOR PROBLEM 3 HERE
    public boolean findMin(int x,int y,Stack<PairInt> path,ArrayList<PairInt> result){
    	
    	if(x>maze.getNCols()-1||y>maze.getNRows()-1||x<0||y<0) return false;
    	if(maze.getColor(x, y)!=NON_BACKGROUND) return false;
    	if(x==maze.getNCols()-1&&y==maze.getNRows()-1) {
    		
    		path.push(new PairInt(x,y));
    		if(path.size()<result.size()||result.size()==0){
    			result.clear();
    			result.addAll(path);
    		}
    		path.pop();
    		return true;
    	}else{
    		maze.recolor(x, y, PATH);
    		path.push(new PairInt(x,y));
    		boolean b1,b2,b3,b4;
    		b1=findMin(x-1,y,path,result);
    		b2=findMin(x+1,y,path,result);
    		b3=findMin(x,y-1,path,result);
    		b4=findMin(x,y+1,path,result);
    		maze.recolor(x, y, NON_BACKGROUND);
			path.pop();
    		if(b1|b2|b3|b4){
    			
    			return true;
    		}else return false;
    	}
    }
    public ArrayList<PairInt> findMazePathMin(int x, int y){
    	ArrayList <PairInt> result=new ArrayList < >();
    	Stack <PairInt> path = new Stack < >();
    	findMin(0,0,path,result);
    	if(result.size()!=0){
    		System.out.println("\n The shortest path is");
    		System.out.println(result);
    		}
    	return result;
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