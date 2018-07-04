package LexAnalysis;

import Symbol.Tag;

public enum Other {
	GE(">=",1),G(">",2),LE("<=",3),L("<",4),EQ("==",5),ASS("=",6);
	private String name=null;
	private int index;
	
	private Other(String name,int index)
	{
		this.name=name;
		this.index=index;
	}
	
	public static  int Search(String name)
	{
		for(Other temp:Other.values())
			if(temp.name.equals(name))
				return Tag(temp.index);
		return 0;	
	}
	
	private static int Tag(int index)
	{
		switch (index) {
		case 1:
			return Tag.GE;
		case 2:
			return '>';
		case 3:
			return Tag.LE;
		case 4 :
			return '<';
		case 5 :
			return Tag.EQ;
		case 6 :
			return Tag.ASS;
		default:
			return 0;
		}
	}


}
