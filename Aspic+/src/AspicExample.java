
import java.io.IOException;

import org.tweetyproject.arg.aspic.parser.AspicParser;
import org.tweetyproject.arg.aspic.reasoner.SimpleAspicReasoner;
import org.tweetyproject.arg.aspic.ruleformulagenerator.PlFormulaGenerator;
import org.tweetyproject.arg.aspic.syntax.AspicArgumentationTheory;
import org.tweetyproject.arg.aspic.syntax.DefeasibleInferenceRule;
import org.tweetyproject.arg.aspic.syntax.InferenceRule;
import org.tweetyproject.arg.aspic.util.RandomAspicArgumentationTheoryGenerator;
import org.tweetyproject.arg.dung.reasoner.AbstractExtensionReasoner;
import org.tweetyproject.arg.dung.reasoner.SimpleCompleteReasoner;
import org.tweetyproject.arg.dung.reasoner.SimpleGroundedReasoner;
import org.tweetyproject.arg.dung.semantics.Semantics;
import org.tweetyproject.arg.dung.syntax.Argument;
import org.tweetyproject.arg.dung.syntax.Attack;
import org.tweetyproject.arg.dung.syntax.DungTheory;
import org.tweetyproject.commons.InferenceMode;
import org.tweetyproject.commons.ParserException;
import org.tweetyproject.logics.pl.parser.PlParser;
import org.tweetyproject.logics.pl.syntax.Negation;
import org.tweetyproject.logics.pl.syntax.Proposition;
import org.tweetyproject.logics.pl.syntax.PlFormula;


public class AspicExample {
	public static void main(String[] args) throws ParserException, IOException{
		Proposition a = new Proposition("a");
		Proposition b = new Proposition("b");
		Proposition c = new Proposition("c");
		Proposition d = new Proposition("d");
		Proposition e = new Proposition("e");
		
		PlParser plparser = new PlParser();
		AspicArgumentationTheory<PlFormula> t = new AspicArgumentationTheory<>(new PlFormulaGenerator());
		t.setRuleFormulaGenerator(new PlFormulaGenerator());
		SimpleAspicReasoner<PlFormula> ar = new SimpleAspicReasoner<PlFormula>(AbstractExtensionReasoner.getSimpleReasonerForSemantics(Semantics.GROUNDED_SEMANTICS));  
		PlFormula pf = (PlFormula)plparser.parseFormula("e");		
		
		
		DefeasibleInferenceRule<PlFormula> r1 = new DefeasibleInferenceRule<>();
		r1.setConclusion(a);
		r1.addPremise(b);
		r1.addPremise(c);
		t.addRule(r1);
		

		
		r1 = new DefeasibleInferenceRule<>();
		r1.setConclusion(new Negation(d));
		r1.addPremise(a);
		t.addRule(r1);
		
		r1 = new DefeasibleInferenceRule<>();
		r1.setConclusion(new Negation(e));
		r1.addPremise(d);
		t.addRule(r1);
		
		r1 = new DefeasibleInferenceRule<>();
		r1.setConclusion(e);
		r1.addPremise(b);
		t.addRule(r1);
		
		
		t.addAxiom(b);
		t.addAxiom(c);
		
	    t.addOrdinaryPremise(d);
	    
		
	//	System.out.println(t);
		System.out.println();
		
		
		
		AbstractExtensionReasoner reasoner = new SimpleGroundedReasoner();
		
		DungTheory aaf = t.asDungTheory();
		System.out.println(reasoner.getModels(aaf));
		
		
	/*	
		for(Argument arg: aaf)
			System.out.println(arg);
		
		System.out.println();
		
		for(Attack att: aaf.getAttacks())
			System.out.println(att);	
		
		
		
		System.out.println(pf + "\t" + ar.query(t,pf,InferenceMode.CREDULOUS));		
		*/
	/*	
		RandomAspicArgumentationTheoryGenerator gen = new RandomAspicArgumentationTheoryGenerator(10,30,2,0.3);
	    Proposition p=new Proposition("A0");
		AspicArgumentationTheory<PlFormula> t2= gen.next();
	    System.out.println(t2);
	    
	    SimpleAspicReasoner<PlFormula> r9=new SimpleAspicReasoner<>(new SimpleGroundedReasoner());
	    System.out.println(p+"\t"+r9.query(t2, p, InferenceMode.CREDULOUS));
	 */   
	}
}
