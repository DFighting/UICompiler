package Symbol;

public class Type extends Token {
	
	private int width;
	
	public Type(String name,int Tag,int width)
	{
		super(name,Tag);
		this.setWidth(width);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public static String printType(int tag){
		String result="";
		switch(tag){
		case(Tag.VOID):
			result="void";
		    break;
		case(Tag.NUM):
			result="int";
		    break;
		case(Tag.REAL):
			result="float";
		    break;
		case(Tag.CHAR):
		    result="char";
		    break;
		case(Tag.TRUE):
		case(Tag.FALSE):
			result="boolean";
		    break;
		case(Tag.BASIC):
			result="basic";
		    break;
		default:
			break;
		}
		return result;
	}
	
	public static final Type
	INT =new Type("int", Tag.NUM, 4),
	FLOAT=new Type("float", Tag.REAL, 4),
	CHAR=new Type("char", Tag.BASIC, 1),
	BOOL=new Type("bool", Tag.BASIC, 1);

}
