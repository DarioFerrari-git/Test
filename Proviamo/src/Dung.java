import java.awt.Dimension;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

public class Dung {
   Vector<String> arg;
   Vector<String[]> att;
public Dung(Vector<String> arg, Vector<String[]> att){
	this.arg=arg;
	this.att=att;
}
public String toString(){
	String x="";
	for (int i=0;i<att.size();i++) {
		x= x+"["+att.get(i)[0]+","+att.get(i)[1]+"]";
	}
	
return "{"+arg +",["+x+"]"+"}";
}
public String Grounded(){
	HashMap<String,Vector<String>> attacchi=new HashMap<String,Vector<String>>();
	Vector<String>notattackedarg=new Vector<String>();
	Vector<String>grounded_extension=new Vector<String>();
	
	for(int j=0; j<arg.size();j++) {
		boolean notattacked=true;
		Vector<String>attaccanti=new Vector<String>();
		
	for(int i=0; i<att.size();i++) {
	    if(arg.get(j)==att.get(i)[1]) { 
	    	notattacked=false;
	    	attaccanti.add(att.get(i)[0]);
	}
	}
	if(notattacked)notattackedarg.add(arg.get(j));
	attacchi.put(arg.get(j), attaccanti);
	}
	
	grounded_extension=notattackedarg;
	//System.out.println(grounded_extension);
	if(!grounded_extension.isEmpty()) {
	for(String i:attacchi.keySet()) {
		//System.out.print((i));
		boolean giust=false;
		boolean giust2=true;
		boolean giust3=true;
		boolean doppioattacco=false;
		HashMap<String,Boolean> bho1=new HashMap<String,Boolean>();
		if(attacchi.get(i).isEmpty()) {giust2=true;
		}
		for(String s:attacchi.get(i)) {	
			if(s==i) {giust3=false;
			bho1.put(s, giust3);
			continue;}
			if(grounded_extension.contains(s)) {giust3=false;
			bho1.put(s, giust3);
			continue; }
			//System.out.print("<-"+s);
			HashMap<String,Boolean> bho=new HashMap<String,Boolean>();		
			for(String k:attacchi.get(s)) {
			
			if(i==k&&attacchi.get(s).size()==1) {			
				giust3=false;
				bho1.put(s, giust3);
				 break;
			}
			if(i==k&&attacchi.get(s).size()>1){
				//System.out.print("<--"+k+" ");
				continue;}		
			giust=false;
			//System.out.print("<--"+k);
			if(grounded_extension.contains(k)) {giust=true;}
			bho.put(k, giust);
			}
			//System.out.print(bho);
			for(String a:bho.keySet()){
				
				boolean esistepos=false;
			    if(bho.get(a)==true) {esistepos=true;}
			    else {
				for(String n:attacchi.get(a)) {
					
					boolean almeno1=false;
					if(n!=s) {
					//	System.out.print("<---"+n);
					if(n==i) {giust3=false;break;}
					if(grounded_extension.contains(n)) {almeno1=true;};
					if(almeno1) {//System.out.print(" no");
					giust3=false;
					break;}
					else {
					for(String m:attacchi.get(n)) {
					//	System.out.print("<----1"+m);
						    boolean almeno1_=false;
						    if(m==i) {giust3=false;break;}
							if(grounded_extension.contains(m)) {almeno1_=true;};
							if(almeno1_) {giust3=true;break;}
							else {
							for(String o:attacchi.get(m)) {
							//	System.out.print("<----2"+o);
								boolean almeno1__=false;
								if(o==n) {giust3=false;break;}
								if(o==a) {giust3=false;break;}
								if(o==i) {giust3=false;break;}
								if(grounded_extension.contains(o)) {almeno1__=true;};
								if(almeno1__) {giust3=false;break;}
								else {
									for(String p:attacchi.get(o)) {
										if(p==m) {giust3=false;break;}
										if(p==n) {giust3=false;break;}
										if(p==a) {giust3=false;break;}	
								//		System.out.print("<----3"+p);
										boolean almeno1___=false;
										if(p==i) {giust3=false;break;}
										if(grounded_extension.contains(p)) {almeno1___=true;};
										if(almeno1__) {giust3=true;break;}
								}
							}
							}
							
						}			
				        }
					}	
				}
			        }
			    }
			    if(esistepos) {giust3=true;break;}
			}
			//System.out.print(bho);
			bho1.put(s, giust3);	
		}
		//System.out.print("<->"+bho1);
		for(String b:bho1.keySet()) {
			if(bho1.get(b)==false)giust2=false;
		}
		//System.out.print(doppioattacco);
		if(giust2&&!grounded_extension.contains(i)&&!doppioattacco) {grounded_extension.add(i);
		//System.out.print("Inserisco "+i);
		}
		//if(autoatt)grounded_extension.remove(i);
		//System.out.println();
	}
	}
	//System.out.println(grounded_extension);
//	System.out.println(attacchi);
return "{"+grounded_extension+"}";
}
}






