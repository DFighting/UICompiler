package LexAnalysis;

public enum Arithmetic {
	ADD("+",1),SUB("-",2),MUL("*",3),DIV("/",4);
	private String name=null;
	@SuppressWarnings("unused")
	private int index;
	private Arithmetic(String name,int index)
	{
		this.name=name;
		this.index=index;
	}
	
	public static int Search(String name)
	{
		for(Arithmetic temp:Arithmetic.values())
			if(temp.name.equals(name))
				return name.charAt(0);
		return 0;	
	}
}
