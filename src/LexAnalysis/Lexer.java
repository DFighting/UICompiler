package LexAnalysis;

import java.io.IOException;

import Symbol.*;
import Utility.SourceCode;
public class Lexer {
	
	private int current,predict;
	private int line=1;
	@SuppressWarnings("unused")
	private int state=0;
	private AttributeKey attributeKey=new AttributeKey();
	private SymbolRecord symbolRecord=new SymbolRecord();
	
	public AttributeKey getAttributeKey() {
		return attributeKey.getKey();
	}
	private void setAttributeKey(Token result) {
		this.attributeKey.setToken(result);
	//Result , whose tag is predefined, maybe setLine(0) 
		this.attributeKey.setLine(line);
	}
	public SymbolRecord getSymbolRecord() {
		SymbolRecord temp=new SymbolRecord();
		temp.setName(symbolRecord.getName());
		temp.setValue(symbolRecord.getValue());
		temp.setTag(symbolRecord.getTag());
		temp.setLine(symbolRecord.getLine());
		return temp;
	}
	private void setSymbolRecord(Token result) {
		this.symbolRecord.setName(result.getToken_name());
		this.symbolRecord.setValue(result.getToken_name());
		this.symbolRecord.setTag(result.getTag());
		this.symbolRecord.setLine(line);	
	}
	
	public Token lexer(SourceCode sourceCode) throws IOException
	{
		String code=sourceCode.getSourceCode();
		Token result=new Token();
		current=predict=0;
		int len=code.length();
		if(len==1)
			if(code.charAt(0)!='@')
			{
				System.out.println("UnRecognized End!");
				return result;
			}else
			{
				result.setTag(Tag.EOF);
				result.setToken_name("@");
				return result;
			}
		while(current+1<len)
		{	
			predict=current;
			char temp=code.charAt(current);
			// terminal symbols,State(1)
			if ((temp == ' ') || (temp == '\t')||(temp == '\r')) {
			current++;
			continue;
		} else if((temp == '{') || (temp == '}') || (temp == '(') || 
				(temp == ')')||(temp == ';')||(temp=='[')||(temp==']')||(temp==',')){
			result.setToken_name(Character.toString(temp));
			result.setTag(temp);
			current++;
			break;
		} else if (temp == '\n') {
		    line = line + 1;
			current++;
			continue; 
		}
		// Keyboard:Letter or Digit,State(3)
		else if (Character.isLetterOrDigit(temp)) {
			state = 3;
			Handle_Keyboard(result,code);
			break;
		} // Instruction Character,State(2)
		else {
			state = 2; 
			Handle_Instruction(result,code);
			break;}
		}
		code=""+code.substring(current);
		sourceCode.setSourceCode(code);
		
		this.setAttributeKey(result);
		this.setSymbolRecord(result);
		return result;
	}
	//state(3)
	private  boolean Handle_Keyboard(Token result,String code)
	{
		boolean re=false;
		predict = current;
		char temp=code.charAt(predict++);
		String token_name="";
		
		if (Character.isDigit(temp)) {
			int v = 0;
			do {
				v = 10 * v + Character.digit(temp, 10);
				token_name+=temp;
				temp=code.charAt(predict++);
			} while (Character.isDigit(temp));
			if (temp != '.') {
				result.setToken_name(token_name);
				result.setTag(Tag.NUM);
				current = predict-1;
				return true;
			}
			float x = v;
			float d = 10;
			for (;;) {
				temp=code.charAt(predict++);
				if (!Character.isDigit(temp))
					break;
				x = x + Character.digit(temp, 10) / d;
				d = d * 10;
			}
			token_name=Float.toString(x);
			result.setToken_name(token_name);
			result.setTag(Tag.REAL);
			current = predict-1;
			return true;
		}

		if (Character.isLetter(temp)) {
			 while (Character.isLetterOrDigit(temp)){
				token_name+=temp;
				temp=code.charAt(predict++);
			 }
			current = predict - 1;
		}
		switch(token_name){
		case "int":
		case "float":
		case "char":
		case "bool":{
			result.setToken_name(token_name);
			result.setTag(Tag.Type);
			re=true;
			break;}
		case "if":
		{
			result.setToken_name(token_name);
			result.setTag(Tag.IF);
			re=true;
			break;
		}
		case "return":
		{
			result.setToken_name(token_name);
			result.setTag(Tag.RETURN);
			re=true;
			break;
		}
		case "void":
		{
			result.setToken_name(token_name);
			result.setTag(Tag.VOID);
			re=true;
			break;
		}
		case "else":{
			result.setToken_name(token_name);
			result.setTag(Tag.ELSE);
			re=true;
			break;
		}
		case "do":{
			result.setToken_name(token_name);
			result.setTag(Tag.DO);
			re=true;
			break;
		}
		case "while":{
			result.setToken_name(token_name);
			result.setTag(Tag.WHILE);
			re=true;
			break;
		}
		case "break":
		{
			result.setToken_name(token_name);
			result.setTag(Tag.BREAK);
			re=true;
			break;
		}
		case "true":
			result.setToken_name(token_name);
			result.setTag(Tag.TRUE);
			re=true;
			break;
		case "false":
			result.setToken_name(token_name);
			result.setTag(Tag.FALSE);
			re=true;
			break;
		default:{
			result.setToken_name(token_name);
			result.setTag(Tag.STRING);
			re=true;
			break;}   
		}
		return re;
		
	}
	//state(2)
	private  boolean Handle_Instruction(Token result,String code)
	{
		String alone=Character.toString(code.charAt(current));
		String doubl=alone+code.charAt(current+1);
		
		if(Arithmetic.Search(doubl)!=0)
		{
			result.setTag(Arithmetic.Search(doubl));
			result.setToken_name(doubl);
			current=current+2;
			return true;
		}else if(Logic.Search(doubl)!=0)
		{
			result.setTag(Logic.Search(doubl));
			result.setToken_name(doubl);
			current=current+2;
			return true;
		}else if(Other.Search(doubl)!=0)
		{
			result.setTag(Other.Search(doubl));
			result.setToken_name(doubl);
			current=current+2;
			return true;
		}else if(Arithmetic.Search(alone)!=0)
		{
			result.setTag(Arithmetic.Search(alone));
			result.setToken_name(alone);
			current=current+1;
		}else if(Logic.Search(alone)!=0)
		{
			result.setTag(Logic.Search(alone));
			result.setToken_name(alone);
			current=current+1;
		}else if(Other.Search(alone)!=0)
		{
			result.setTag(Other.Search(alone));
			result.setToken_name(alone);
			current=current+1;
		}else 
			return false;
		return false;
	}
	

}
