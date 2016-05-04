import java.util.ArrayList;
import java.util.List;

public class Path{
    ArrayList<Node> path;
    int pathValue;

    public Path(){
	path = null;
    }

    public Path(ArrayList<Node> p){
	path = p;
	int sum = 0;
	for(Node n: path){
	    sum += n.getValue();
	}
	setPathValue(sum);
    }

    public void setPath(ArrayList<Node> p){
	path = p;
	int sum = 0;
	for(Node n: path){
	    sum += n.getValue();
	}
	setPathValue(sum);
    }

    public ArrayList<Node> getPath(){
	return path;
    }

    public void setPathValue(int v){
	pathValue = v;
    }

    public int getPathValue(){
	return pathValue;
    }

    public Node getNode(int i){
	return path.get(i);
    }

    public void printPathValue(){
	if(path == null){
	    System.out.println("no path value");
	}
	else{
	    System.out.println(pathValue);
	}
    }

    public void printPath(){
	if(path == null){
	    System.out.println("no path");
	}
	else{
	    for(Node n: path){
		System.out.print("(" + n.getX() + "," + n.getY() + ")"); 
	    }
	    System.out.println();
	}
    }

    
}
