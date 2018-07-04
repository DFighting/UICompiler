package SynAnalysis;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

import Utility.utility;
import Symbol.*;
import Inter.node;

public class Action {
	
	public static Hashtable<Integer, State> ToState=new Hashtable<Integer,State>();
	
	public static final int action[][]={
			{1,	2,	3,	4,	5,	6,	7,	8,	8,	9,	10,	-1,	11,	-1,	-1,	-1,	-1,	-1,	-1,	-1},
			{-1,-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	12,	-1,	-1,	-1,	-1,	-1,	27},
			{-1,-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	13,	-1,	-1,	-1,	-1,	-1},
			{-1,-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	14,	-1,	-1,	-1,	-1},
			{-1,-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1},
			{-1,-1,	-1,	-1,	-1,	-1,	-1,	16,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	15,	16,	-1,	-1},
			{-1,-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	17, -1},
			{-1,-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1},
			{-1,-1,	-1,	-1,	-1,	-1,	24,	-1,	-1,	9,  10,	-1,	11,	-1,	-1,	-1,	-1,	-1,	-1,	-1},
			{-1,-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1},
			{25, 2,	3,	4,	5,	6,	7,	8,	8,	9,	10,	-1,	11,	-1,	-1,	-1,	-1,	-1,	-1,	-1},
			{-1,-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1, -1,	-1,	-1,	-1, -1,	-1},
			{-1,18,	3,	4,   5,  6,	 7,	 8,	8,	9,	10,	-1,	11,	-1,	-1,	-1,	-1, -1,	-1,	-1},
			{-1,-1,	19,	4,	5,	6,	7,	8,	8,	9,	10,	-1,	11,	-1,	-1,	-1,	-1,	-1,	-1,	-1},
			{-1,-1,	-1,	20,	5,  6,	7,  8,	8,	9,	10,	-1,	11,	-1,	-1,	-1,	-1,	-1,	-1,	-1},
			{-1,-1,	-1,	-1,	21,	6,	7,	8,	8,	9,	10,	-1,	11,	-1,	-1,	-1,	-1,	-1,	-1,	-1},
			{-1,-1,	-1,	-1,	-1,	22,	7,	8,	8,	9,	10,	-1,	11,	-1,	-1,	-1,	-1,	-1,	-1,	-1},
			{-1,-1,	-1,	-1,	-1,	-1,	23,	-1,	-1,	9,	10,	-1,	11,	-1, -1,	-1,	-1,	-1,	-1,	-1},
			{-1,-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	13,	-1,	-1,	-1,	-1,	-1},
			{-1,-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	14,	-1,	-1,	-1,	-1},
			{-1,-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1},
			{-1,-1,	-1,	-1,	-1,	-1,	-1,	16,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	16,	-1,	-1},
			{-1,-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	17,	-1},
			{-1,-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1},
			{-1,-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1, -1,	-1,	-1},
			{-1,-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	26,	-1,	12,	-1,	-1,	-1,	-1,	-1,	-1},
			{-1,-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1},
	};
	
	public static void Initial_ToState()
	{
		ToState.put(0, State.I0);
		ToState.put(1, State.I1);
		ToState.put(2, State.I2);
		ToState.put(3, State.I3);
		ToState.put(4, State.I4);
		ToState.put(5, State.I5);
		ToState.put(6, State.I6);
		ToState.put(7, State.I7);
		ToState.put(8, State.I8);
		ToState.put(9, State.I9);
		ToState.put(10, State.I10);
		ToState.put(11, State.I11);
		ToState.put(12, State.I12);
		ToState.put(13, State.I13);
		ToState.put(14, State.I14);
		ToState.put(15, State.I15);
		ToState.put(16, State.I16);
		ToState.put(17, State.I17);
		ToState.put(18, State.I18);
		ToState.put(19, State.I19);
		ToState.put(20, State.I20);
		ToState.put(21, State.I21);
		ToState.put(22, State.I22);
		ToState.put(23, State.I23);
		ToState.put(24, State.I24);
		ToState.put(25, State.I25);
		ToState.put(26, State.I26);
	}
	
	public static void Shift(Stack<Integer> state,Stack<SymbolRecord> TempSymbols,Stack<node> ast,ArrayList<String> temp,State I,int line,StringBuilder inter)
	{
		String[] conc=I.getConclude().split(" ");
		String[] gene=I.getGenerate().split(" ");
		for(int i=0;i<gene.length;i++)
			if(gene[i].charAt(gene[i].length()-1)=='.')
			{
				int len=Length(gene[i]);
				String te="";
				for(int j=len;j>0;j--)
					te+=temp.get(temp.size()-j);
				if(te.equals(gene[i].substring(0, gene[i].length()-1)))
				{
					TypeCheck(I,TempSymbols,temp,i,line,inter);
					//System.out.println("Generate is :"+conc[i]+"-->"+gene[i]);
					ArrayList<node> childs=new ArrayList<node>();
					if(conc[i].equals("Bool"))
				         break;
										
					for(int j=len;j>0;j--){
						temp.remove(temp.size()-j);
						childs.add(ast.pop());
					}
					temp.add(conc[i]);
					node concnode=new node("",conc[i],null);
					for(node tempnode:childs)
						concnode.Add(tempnode);
						
					for(int j=0;j<len;j++)
						state.pop();			
					state.push(action[state.peek()][utility.Convert(conc[i],line)]);
					ast.push(concnode);
					return;
				}					
			}
	}
		
    private static void TypeCheck(State I,Stack<SymbolRecord> TempSymbols,ArrayList<String> temp,int location,int line,StringBuilder inter)
    {
    	String gene=I.getGenerate().split(" ")[location];
    	String intercode=null;	
    	SymbolRecord t1=new SymbolRecord();
		SymbolRecord t2=new SymbolRecord();
	    SymbolRecord temprecord=new SymbolRecord();
    	int len=Length(gene);
    	int number=utility.ToInt(temp.get(0));
    	if(len==1)
    		return;
    	else if((len==2)&&(gene=="!unary."))
    	{
    		if(TempSymbols.peek().getType().getToken_name()!="bool")
    			throw new Error("Type Error in line "+line);
    		else{
    			t1=TempSymbols.pop();
    			temprecord.setName("t"+number++);
    			temprecord.setTag(Tag.TEMP);
    			temprecord.setType(t1.getType());
    			intercode=temprecord.getName()+"="+temp.get(temp.size()-2)+t1.getName();
    			TempSymbols.push(temprecord);
    			return;
    		}	
    	}else{
    		switch(I.getNumber()){
    		case(18):
    		case(19):
    			t1=TempSymbols.pop();
    		    t2=TempSymbols.pop();
    		    if((t1.getTypeName()=="bool")&&(t2.getTypeName()=="bool")){
        			temprecord.setName("t"+number++);temprecord.setTag(Tag.TEMP);
        			temprecord.setType(Type.BOOL);
        			intercode=temprecord.getName()+"="+t2.getName()+temp.get(temp.size()-2)+t1.getName();
        			TempSymbols.push(temprecord);
        			break;	
    		    }  	
    		    else 
    		    	throw new Error("Type Check Error in line "+line);
    		case(20):
    			t1=TempSymbols.pop();
		        t2=TempSymbols.pop();
		        if((t1.getTypeName()=="bool")||((t1.getTypeName()=="char")&&(t2.getTypeName()=="char"))){
	    			temprecord.setName("t"+number++);temprecord.setTag(Tag.TEMP);
	    			temprecord.setType(Type.BOOL);
	    			TempSymbols.push(temprecord);
	    			intercode=temprecord.getName()+"="+t2.getName()+temp.get(temp.size()-2)+t1.getName();
		        	break;
		        }
		        else
		        	throw new Error("Type Check Error in line "+line);
    		case(21):
    			t1=TempSymbols.pop();
	            t2=TempSymbols.pop();
	            if((t1.getTypeName()=="bool")||(t2.getTypeName()=="bool"))
	            		throw new Error("Type Error in line "+line+", maybe Illegal Compare of Two Elements");
	            else {
	            	temprecord.setName("t"+number++);temprecord.setTag(Tag.TEMP);
            		temprecord.setType(Type.BOOL);
            		TempSymbols.push(temprecord);
	    			intercode=temprecord.getName()+"="+t2.getName()+temp.get(temp.size()-2)+t1.getName();
	            	break;
	            }
	           
    		case(22):
    		case(23):
    			t1=TempSymbols.pop();
                t2=TempSymbols.peek();
           
                if((t1.getTypeName()=="bool")||(t2.getTypeName()=="bool"))
            		throw new Error("Type Error in line "+line+", maybe Illegal Calculate of Two Elements");
                else {
                	TempSymbols.pop();
                	temprecord.setName("t"+number++);temprecord.setTag(Tag.TEMP);
                  
                	if((t1.getTypeName()=="float")||(t2.getTypeName()=="float")){
                    	temprecord.setType(Type.FLOAT);
        		    	TempSymbols.push(temprecord);
        		    	if(t2.getTypeName()=="int")
        		    	  intercode=temprecord.getName()+"=(float)"+t2.getName()+temp.get(temp.size()-2)+t1.getName();
        		    	else if(t1.getTypeName()=="int")
        		    		intercode=temprecord.getName()+"="+t2.getName()+temp.get(temp.size()-2)+"(float)"+t1.getName();
        		    	else 
        		    		intercode=temprecord.getName()+"=(float)"+t2.getName()+temp.get(temp.size()-2)+t1.getName();	
        		    	break;
	            	}else{
            		intercode=temprecord.getName()+"="+t2.getName()+temp.get(temp.size()-2)+t1.getName();
                	  if(((t1.getTypeName()=="char")&&(t2.getTypeName()=="char"))){
                		  temprecord.setType(Type.CHAR);
    		    	      TempSymbols.push(temprecord);}
	            	  else if((t1.getTypeName()=="int")&&(t2.getTypeName()=="int")){
	            		  temprecord.setType(Type.INT);
	    		    	  TempSymbols.push(temprecord);
	            	  }
	            	  else
	                	  throw new Error("Type Error in line "+line+", maybe Illegal Calculate of Two Elements");
	            	   break;
	            		}
	            }
    		case(24):
    		case(26):
    			t1=TempSymbols.pop();
    			temprecord.setName("t"+number++);temprecord.setTag(Tag.TEMP);
    		    temprecord.setType(t1.getType());
    		    if(I.getNumber()==24)
    		    	intercode=temprecord.getName()+"="+temp.get(temp.size()-2)+t1.getName();
    		    else 
    		    	intercode=temprecord.getName()+"="+temp.get(temp.size()-3)+t1.getName()+temp.get(temp.size()-1);
    		    TempSymbols.push(temprecord);
    		    break;   			
	      }
    		
    		if(intercode!=null)
    		    inter.append(intercode+";\n");
    		
    		if(number>0)
    			temp.set(0, ""+number);	
      } 
    }
	
    public static int Length(String s)
	{
		int len=0;
		int location=0;
		String temp="";
		String other="";
		while(location<s.length())
		{
			 char c=s.charAt(location++);
			 if(Character.isLetter(c))
				 temp+=c;
			 else
				 other+=c;
			 switch(temp){
				 case("bool"):case("join"):case("equality"):case("rel"):case("expr"):case("term"):
				 case("unary"):case("factor"):case("basic"):
					 len++;temp="";break;
					 default:break;
			 }
			 
			switch(other){
			case("||"):case("&&"):case("=="):case("!="):case("<"):case("<="):case(">="):case(">"):case("+"):
			case("-"):case("*"):case("/"):case("!"):case("("):case(")"):
				len++;other="";break;
				default:
					break;
			}
			
	 }
		return len;
	}
}
