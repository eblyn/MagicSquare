package magicsquare;

public class Square {
	protected Values values;
	
	public Square(Values values) {
		this.values = values;
	} 
	
	@Override
	public Square clone(){
		return new Square(values.clone());
	}
	
	public String toString() {
		String str = "";
		for(int i = 0; i < values.n(); i ++){
			for(int j = 0; j < values.n(); j++){
				str += values.elementAt(i, j);
				str += (j == values.n()-1) ? '\n' : " ";
			}
		}
		return str;
	}
	
	@Override
	public boolean equals(Object square) {
		try{
			return values.equals(((Square)square).values);
		} catch(Exception e) {
			return super.equals(square);
		}
	}
	
	public int elementAt(int pos) { 
		return values.elementAt(getX(pos), getY(pos)); 
	}
	
	public int getX(int pos) {
		return (int)pos/values.n();	
	}
	
	public int getY(int pos) { 	
		return pos%values.n(); 	
	}
	
	public Values getValues() {
		return values;
	}

	public void swap(int pos1, int pos2) {
		values.getSwaper().swap(getX(pos1), getY(pos1), getX(pos2), getY(pos2));	
	}
	
	public Swaper getSwaper() {
		return values.getSwaper();
	}
}
