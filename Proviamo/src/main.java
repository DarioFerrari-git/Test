import java.util.Vector;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vector<String> Args=new Vector<String>();
		Args.add("a");
		Args.add("b");
		Args.add("c");
		Args.add("d");
		Args.add("e");
		Args.add("f");
		Args.add("g");
		Args.add("h");
		Args.add("i");
		Args.add("l");
		Args.add("m");
		Args.add("n");
		Args.add("o");
		Args.add("p");
		
		Vector<String[]> atts= new Vector<String[]>();
		
		atts.add(new String[] {"a","b"});
		atts.add(new String[] {"b","c"});
		atts.add(new String[] {"c","b"});
		atts.add(new String[] {"c","d"});
		atts.add(new String[] {"d","m"});
		atts.add(new String[] {"m","l"});
		atts.add(new String[] {"l","m"});
		
		atts.add(new String[] {"a","e"});
		atts.add(new String[] {"e","f"});
		atts.add(new String[] {"f","g"});
		atts.add(new String[] {"g","f"});
		atts.add(new String[] {"g","l"});
		
		atts.add(new String[] {"a","i"});
		atts.add(new String[] {"h","i"});
		atts.add(new String[] {"g","h"});
		atts.add(new String[] {"n","h"});
		
		atts.add(new String[] {"p","n"});
		atts.add(new String[] {"n","o"});
		atts.add(new String[] {"o","p"});
		
		
		System.out.println(new Dung(Args,atts).Grounded());
	}

}
