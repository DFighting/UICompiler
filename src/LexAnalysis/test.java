package LexAnalysis;

import Symbol.Token;
import Utility.SourceCode;

public class test {
	
	public static void main(String[] args) throws Exception 
	{
		SourceCode test=new SourceCode();
		test.setPath("code.txt");
		test.Input();
		test.setSourceCode(test.getSourceCode()+"@");
		Lexer lexer=new Lexer();
		Token re=lexer.lexer(test);
		System.out.println("Symbol Table is:");
		int index=1;
		while(re.getToken_name()!="@")
		{
		    System.out.print("No "+(index++)+": ");	
			lexer.getSymbolRecord().print();
			re=lexer.lexer(test);
		}		
	}

}
