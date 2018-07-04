package SynAnalysis;

public class State {
	
	private String conclude=null;
	private String generate=null;
	private int number=0;
	public static int sum=0;
	public String getConclude() {
		return conclude;
	}
	public String getGenerate() {
		return generate;
	}
	
	public State(String conclude,String generate,int number)
	{
		this.conclude=conclude;
		this.generate=generate;
		this.setNumber(number);
	}
	
	public final static State
	I0=new State("Bool", ".bool",0),
	I1=new State("Bool bool", "bool. bool.||join",1),
	I2=new State("bool join", "join. join.&&equality",2),
	I3=new State("join equality equality", "equality. equality.==rel equality.!=rel ",3),
	I4=new State("equality", "rel.",4),
	I5=new State("rel rel rel rel rel expr expr", "expr.<expr expr.<=expr expr.>expr expr.>=expr expr. expr.+term expr.-term",5),
	I6=new State("expr term term", "term. term.*unary term./unary",6),
	I7=new State("term", "unary.",7),
	I8=new State("term term", "!.unary -.unary",8),
	I9=new State("unary", "factor.",9),
	I10=new State("factor", "(.bool)",10),
	I11=new State("factor", "basic.",11),
	I12=new State("bool", "bool||.join",12),
	I13=new State("join", "join&&.equality",13),
	I14=new State("equality equality", "equality==.rel equality!=.rel",14),
	I15=new State("rel rel rel rel", "expr<.expr expr<=.expr expr>.expr expr>=.expr",15),
	I16=new State("expr expr", "expr+.term expr-.term",16),
	I17=new State("term term", "term*.unary term/.unary",17),
	I18=new State("bool join", "bool||join. join.&&equality",18),
	I19=new State("join equality equality", "join&&equality. equality.==rel equality.!=rel",19),
	I20=new State("equality equality", "equality==rel. equality!=rel.",20),
	I21=new State("rel rel rel rel expr expr", "expr<expr. expr<=expr. expr>expr. expr>=expr. expr.+term expr.-term",21),
	I22=new State("expr expr term term", "expr+term. expr-term. term.*unary term./unary",22),
	I23=new State("term term", "term*unary. term/unary.",23),
	I24=new State("term term", "!unary. -unary.",24),
	I25=new State("factor bool", "(bool.) bool.||join",25),
	I26=new State("factor", "(bool).",26);
	
	public static void print_state()
	{
		state(State.I0);
		state(State.I1);
		state(State.I2);
		state(State.I3);
		state(State.I4);
		state(State.I5);
		state(State.I6);
		state(State.I7);
		state(State.I8);
		state(State.I9);
		state(State.I10);
		state(State.I11);
		state(State.I12);
		state(State.I13);
		state(State.I14);
		state(State.I15);
		state(State.I16);
		state(State.I17);
		state(State.I18);
		state(State.I19);
		state(State.I20);
		state(State.I21);
		state(State.I22);
		state(State.I23);
		state(State.I24);
		state(State.I25);
		state(State.I26);;
	}
	
	public static void state(State state)
	{
		int count=0;
		String[] conc=state.getConclude().split(" ");
		String[] gene=state.getGenerate().split(" ");
		if(conc.length!=gene.length)
			System.out.println("Error");
		else
			for(int i=0;i<conc.length;i++){
				if(count++==0)
					System.out.println("I"+state.getNumber()+": ");
				System.out.print("  "+conc[i]+"-->"+gene[i]);
				sum++;
				System.out.print("\n");
			}
		
		System.out.print("\n");
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	
}
