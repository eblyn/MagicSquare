package solvers;

import java.util.Vector;

public  class ValidatorElements <T>{
	protected Vector<T> visited;
	
	public ValidatorElements() {
		visited = new Vector<T>();
	}
	
	public ValidatorElements(T firstValue) {
		visited = new Vector<T>();
		visited.add(firstValue);
	}

	public boolean validSwap(T newSquare) {
		for(int i = 0; i < visited.size(); i++) 
			if(newSquare.equals(visited.elementAt(i)))
				return false;
		return true;
	}
	
	public void addVisited(T square){
		visited.add(square);
	}
	
	public int visitedSize(){
		return visited.size();
	}
	
	public T getElement(int index){
		return visited.elementAt(index);
	}

	public void reset() {
		visited = new Vector<T>();
	}

	public void reset(T element) {
		visited = new Vector<T>();
		visited.add(element);
	}
}
