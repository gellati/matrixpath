
public class Node{

    int xpos;
    int ypos;
    int value;

    public Node(){
    }

    public Node(int i, int j, int v){
	xpos = i;
	ypos = j;
	value = v;
    }

    public int addOne(int i){
	return i;
    }

    public void setX(int i){
	this.xpos = i;
    }

    public int getX(){
	return xpos;
    }

    public void setY(int j){
	this.ypos = j;
    }

    public int getY(){
	return ypos;
    }

    public void setValue(int v){
	this.value = value;
    }

    public int getValue(){
	return value;
    }
}
