
import java.io.IOException;

import org.tweetyproject.arg.aspic.parser.AspicParser;
import org.tweetyproject.arg.aspic.reasoner.SimpleAspicReasoner;
import org.tweetyproject.arg.aspic.ruleformulagenerator.PlFormulaGenerator;
import org.tweetyproject.arg.aspic.syntax.AspicArgumentationTheory;
import org.tweetyproject.arg.aspic.syntax.DefeasibleInferenceRule;
import org.tweetyproject.arg.aspic.syntax.StrictInferenceRule;
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


public class AspicExample3 {
	public static void main(String[] args) throws ParserException, IOException{
		Proposition a = new Proposition("a");
		Proposition b = new Proposition("b");
		Proposition c = new Proposition("c");
		Proposition d = new Proposition("d");
		Proposition e = new Proposition("e");
		
		PlParser plparser = new PlParser();
		AspicArgumentationTheory<PlFormula> t1 = new AspicArgumentationTheory<>(new PlFormulaGenerator());
		t1.setRuleFormulaGenerator(new PlFormulaGenerator());
		SimpleAspicReasoner<PlFormula> ar = new SimpleAspicReasoner<PlFormula>(AbstractExtensionReasoner.getSimpleReasonerForSemantics(Semantics.GR));
		PlFormula pf1 = (PlFormula)plparser.parseFormula("c");		
		
		t1.addOrdinaryPremise(a);
		t1.addOrdinaryPremise(b);
		t1.addOrdinaryPremise(c);
		t1.addOrdinaryPremise(d);
		
		StrictInferenceRule<PlFormula> r2 = new StrictInferenceRule<>();
		
		r2.setConclusion(new Negation(b));
		r2.addPremise(a);
		t1.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(c));
		r2.addPremise(a);
		t1.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(c));
		r2.addPremise(b);
		t1.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(b));
		r2.addPremise(c);
		t1.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(d));
		r2.addPremise(b);
		t1.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(d));
		r2.addPremise(c);
		t1.addRule(r2);
		
		System.out.println(pf1 + "\t" + ar.query(t1,pf1,InferenceMode.CREDULOUS));		
		
		
		DungTheory aaf = t1.asDungTheory();
		
		AbstractExtensionReasoner reasoner = new SimpleGroundedReasoner();
		System.out.println(reasoner.getModels(aaf));
		System.out.println();
	  
		
		AspicArgumentationTheory<PlFormula> t2 = new AspicArgumentationTheory<>(new PlFormulaGenerator());
		t2.setRuleFormulaGenerator(new PlFormulaGenerator());
		PlFormula pf2 = (PlFormula)plparser.parseFormula("c");		
		
		t2.addOrdinaryPremise(a);
		t2.addOrdinaryPremise(b);
		t2.addOrdinaryPremise(c);
		t2.addOrdinaryPremise(d);
		
	    r2 = new StrictInferenceRule<>();
		
		r2.setConclusion(new Negation(d));
		r2.addPremise(a);
		t2.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(b));
		r2.addPremise(d);
		t2.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(a));
		r2.addPremise(b);
		t2.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(d));
		r2.addPremise(c);
		t2.addRule(r2);
		
        System.out.println(pf2 + "\t" + ar.query(t2,pf2,InferenceMode.CREDULOUS));		
			
		DungTheory aaf2 = t2.asDungTheory();
		
        System.out.println(reasoner.getModels(aaf2));
		System.out.println();
	  

		AspicArgumentationTheory<PlFormula> t3 = new AspicArgumentationTheory<>(new PlFormulaGenerator());
		t3.setRuleFormulaGenerator(new PlFormulaGenerator());
		PlFormula pf3 = (PlFormula)plparser.parseFormula("c");		
		
		t3.addOrdinaryPremise(a);
		t3.addOrdinaryPremise(b);
		t3.addOrdinaryPremise(c);
		t3.addOrdinaryPremise(d);
		t3.addOrdinaryPremise(e);
		
	    r2 = new StrictInferenceRule<>();
		
		r2.setConclusion(new Negation(d));
		r2.addPremise(a);
		t3.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(a));
		r2.addPremise(d);
		t3.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(a));
		r2.addPremise(b);
		t3.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(b));
		r2.addPremise(a);
		t3.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(c));
		r2.addPremise(a);
		t3.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(c));
		r2.addPremise(b);
		t3.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(e));
		r2.addPremise(c);
		t3.addRule(r2);
		
        System.out.println(pf3 + "\t" + ar.query(t3,pf3,InferenceMode.CREDULOUS));		
			
		DungTheory aaf3 = t3.asDungTheory();
		
        System.out.println(reasoner.getModels(aaf3));
		System.out.println();
	  
	}
}
