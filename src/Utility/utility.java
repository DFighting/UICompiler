package Utility;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import Inter.node;
import Symbol.Environment;
import Symbol.SymbolRecord;
import Symbol.Tag;
import Symbol.Type;

public class utility {

	public static String Extract(String source,char condition,int line)
	{
		String reString="";
		int rcount=0;
		int lcount=1;
		int i=1;
		char Rcondition;
		if(condition=='(')
			Rcondition=')';
		else if(condition=='{')
			Rcondition='}';
		else if(condition=='[')
			Rcondition=']';
		else 
			Rcondition='0';
		while(lcount!=rcount){
			if(i==source.length())
				break;
			char c=source.charAt(i++);
			if(c==condition)
				lcount++;
			if(c==Rcondition)
				rcount++;
		}
		if(rcount!=lcount)
			throw new Error("Syntax Error in line "+line+", maybe need a corresponding notation of "+condition);
		reString=source.substring(1,i-1);
		return reString;
	}

	public static int Convert(String input,int line)
	{
		int result;
		switch(input){
		case("bool"):      result=0; break;
		case("join"):      result=1;break;
		case("equality"):  result=2;break;
		case("rel"):       result=3;break;
		case("expr"):      result=4;break;
		case("term"):      result=5;break;
		case("unary"):     result=6;break;
		case("-"):         result=7;break;
		case("!"):         result=8;break;
		case("factor"):    result=9;break;
		case("("):         result=10;break;
		case(")"):         result=11;break;
		case("basic"):     result=12;break;
		case("||"):        result=13;break;
		case("&&"):        result=14;break;
		case("=="):
		case("!="):        result=15;break;
		case("<"):
		case("<="):
		case(">"):
		case(">="):        result=16;break;
		case("+"):         result=17;break;
		case("*"):
		case("/"):         result=18;break;
		case("@"):         result=19;break;
		default:
			throw new Error("Syntax Error in line "+line+" , maybe no this input:"+input);
		}
		return result;					
	}

	public static String Instranction(SourceCode code)
	{
		String result="";
		String codeString=code.getSourceCode();
		for(int i=0;i<codeString.length();i++)
			if(codeString.charAt(i)==';')
				break;
			else 
				result+=codeString.charAt(i);
		return result;
	}
	
	public static void Copy(SymbolRecord target,SymbolRecord source){
		target.setLine(source.getLine());
		target.setName(source.getName());
		target.setTag(source.getTag());
		target.setType(source.getType().getToken_name(), source.getType().getWidth());
		target.getType().setTag(source.getTag());
		target.setValue(source.getValue());
	}
	
	public static void Copy(Type target,Type source)
	{
		target.setTag(source.getTag());
		target.setToken_name(source.getToken_name());
		target.setWidth(source.getWidth());
	}

	public static void Copy(Environment target,Environment source){
		
	}
	public static Type type(int tag)
	{
		switch (tag) {
		case Tag.NUM:
			return Type.INT;
		case Tag.REAL:
			return Type.FLOAT;
		case Tag.TRUE:
			return Type.BOOL;
		case Tag.FALSE:
			return Type.BOOL;
		case Tag.CHAR:
			return Type.CHAR;
		default:
			return null;
		}
	}
	
	public static int ToInt(String s)
	{
		int re=0;
		int len=s.length();
		for(int i=len-1;i>=0;i--)
			re+=((int)s.charAt(i)-48)*Math.pow(10, len-i-1);
		return re;
	}
 
	public static String ExtractVarible(String args){
		StringBuilder re=new StringBuilder();
		String[] parama=args.split(";");
		for(int i=0;i<parama.length;i++)
		{
			String[] temp=parama[i].split(" ");
			re.append(temp[temp.length-1]+" ");
		}
		
		return re.toString();
	}

	public static void  ChildADD(node root,DefaultMutableTreeNode node){
		ArrayList<node > childs=root.getChilds();
		for(int i=0;i<childs.size();i++){
			DefaultMutableTreeNode temp=new DefaultMutableTreeNode(childs.get(i).getInterString());
			node.add(temp);	
			if(childs.get(i).getChilds().size()!=0)
				ChildADD(childs.get(i), temp);
		}
	}
	
	public static void Remove(ArrayList<node> nodes,node removed){
		for(int i=nodes.size()-1;i>=0;i--)
			if(nodes.get(i).equals(removed))
				nodes.remove(i);
	}
	
	public static String ToString(StringBuilder stringBuilder){
		for(int i=0;i<stringBuilder.capacity();i++)
			stringBuilder.charAt(i);
		return stringBuilder.toString();
	}
}
