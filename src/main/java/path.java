import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class path{
    
    public static void main(String [] args){
	System.out.println("hello");

	/*
	int [][] A = {{1, 3, 1, 3, 2, 3},
		      {3, 1, 5, 1, 6, 7},
		      {5, 1, 5, 3, 71, 13},
		      {1, 6, 2, 2, 82, 9}
	};
	*/

	
	int [][] A = {{1, 3, 2, 3},
		      {3, 4, 6, 7},
		      {5, 3, 71, 13}
	};
		
	/*
	int [][] A = {{0, 0, 0, 0},
		      {0, 0, 0, 0},
		      {0, 0, 1, 0}
	};
	*/
	for(int i = 0; i < A.length; i++){
	    for(int j = 0; j < A[0].length; j++){
		System.out.print(A[i][j] + " ");
	    }
	    System.out.println();
	}

	System.out.println(A.length); // number of rows
	System.out.println(A[0].length);  // number of columns
	
	ArrayList<String> paths = robotPaths(A, A.length, A[0].length); // rows, columns
	
	Iterator<String> iterator = paths.iterator();

	/*
	while(iterator.hasNext()){
	    System.out.println(iterator.next());
	    //	    System.out.println(iterator.next());
	}
	*/
	System.out.println(paths.size());	
	ArrayList<Path> pathList = parsePaths(paths);
	getMostValuablePath(pathList);

	GUI gui = new GUI();
	//       	gui.drawGrid(A);


	

	//	calculatePathsSum(pathList);

    }

    public static void getMostValuablePath(ArrayList<Path> pathList){
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

    /*
     * m - number of rows
     * n - number of columns
     */
    
    public static ArrayList<String> robotPaths(int [][] A, int m, int n){
	ArrayList<String> pathList = new ArrayList<String>();
	getPaths(A, m, n, 0, 0, "", pathList);
	return pathList;
    }    

    public static void getPaths(int [][] A, int m, int n, int i, int j, String path, ArrayList<String> pathList){
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

    public static ArrayList<Path> parsePaths(ArrayList<String> pathList){
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
