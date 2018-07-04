package LexAnalysis;

import Symbol.Tag;

public enum Logic {
	AND("&",1),OR("|",2),NOT("!",3),CAND("&&",4),COR("||",5),CNOT("!=",6);
	private String name=null;
	private int index;
	
	private Logic(String name,int index)
	{
		this.name=name;
		this.index=index;
	}
	
	public static int Search(String name)
	{
		for(Logic temp:Logic.values())
			if(temp.name.equals(name))
				if(temp.index<4)
				   return name.charAt(0);
				else 
					return Tag(temp.index);
				
		return 0;	
	}
	
	private static int Tag(int index)
	{
		switch (index) {
		case 4 :
			return Tag.AND;
		case 5 :
			return Tag.OR;
		case 6 :
			return Tag.NE;
		default:
			return 0;
		}
	}

}
