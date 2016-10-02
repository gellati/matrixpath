package path;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class PathFinder{

    public PathFinder(){
    }
    

    public void printMostValuablePath(ArrayList<Path> pathList){
	Path path = new Path();
	int pathValue = 0;
	for(Path p: pathList){
	    if(p.getPathValue() > pathValue){
		pathValue = p.getPathValue();
		path = p;
	    }
	}
	System.out.println(path.getPathValue());
	path.printPath();
    }

    public Path getMostValuablePath(ArrayList<Path> pathList){
	Path path = new Path();
	int pathValue = 0;
	for(Path p: pathList){
	    if(p.getPathValue() > pathValue){
		pathValue = p.getPathValue();
		path = p;
	    }
	}
	System.out.println(path.getPathValue());
	return path;
    }

    

    /*
     * m - number of rows
     * n - number of columns
     */
    
    public ArrayList<String> robotPaths(int [][] A, int m, int n){
	ArrayList<String> pathList = new ArrayList<String>();
	getPaths(A, m, n, 0, 0, "", pathList);
	return pathList;
    }    

    public void getPaths(int [][] A, int m, int n, int i, int j, String path, ArrayList<String> pathList){
	if( i > m - 1 || j > n - 1){//wrong way
	    return;
	}
	else{
	    if(i < m && j < n){
		path += String.format(" (%d,%d,%d)", i , j, A[i][j]); // i row, j column
	    }
	    if( i == m - 1 && j == n - 1){ //reach the lower right corner point
		pathList.add(path);
	    }else {
		getPaths(A, m, n, i +1, j , path, pathList);
		getPaths(A, m, n, i , j +1, path, pathList);
	    }
	}
    }

    public ArrayList<Path> parsePaths(ArrayList<String> pathList){
	    //public static void parsePaths(ArrayList<String> pathList){

	Iterator<String> iterator = pathList.iterator();
	System.out.println(pathList.size());
	ArrayList<Path> paths = new ArrayList<Path>();

	while(iterator.hasNext()){
	    ArrayList<Node> path = new ArrayList<Node>();
	    //	    String [] nodes = iterator.next().split("\\s+");
	    String pathInfo = iterator.next().replaceAll("\\(|\\)", "");
	   
      	    String [] nodes = pathInfo.split(" "); //iterator.next().split(" ");
	    for(int i = 0; i < nodes.length; i++){
		if(nodes[i].equals("")){
		    continue;
		}
		String [] n = nodes[i].split(",");
		Node node = new Node(Integer.parseInt(n[0]), Integer.parseInt(n[1]), Integer.parseInt(n[2]));
		//	    System.out.println(nodes.length + " " + nodes[2]);
		path.add(node);
	    }
	    //	    System.out.println(path.size());
	    Path p = new Path(path);
	    p.printPath();
	    p.printPathValue();

	    paths.add(p);
	}
	//	System.out.println(paths.size());
	return paths;
    }
}
