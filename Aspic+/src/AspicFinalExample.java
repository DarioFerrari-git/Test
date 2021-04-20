import java.io.IOException;

import org.tweetyproject.arg.aspic.reasoner.SimpleAspicReasoner;
import org.tweetyproject.arg.aspic.ruleformulagenerator.PlFormulaGenerator;
import org.tweetyproject.arg.aspic.syntax.AspicArgumentationTheory;
import org.tweetyproject.arg.aspic.syntax.StrictInferenceRule;
import org.tweetyproject.arg.dung.reasoner.AbstractExtensionReasoner;
import org.tweetyproject.arg.dung.semantics.Semantics;
import org.tweetyproject.commons.InferenceMode;
import org.tweetyproject.commons.ParserException;
import org.tweetyproject.logics.pl.parser.PlParser;
import org.tweetyproject.logics.pl.syntax.Negation;
import org.tweetyproject.logics.pl.syntax.PlFormula;
import org.tweetyproject.logics.pl.syntax.Proposition;

public class AspicFinalExample {
	public static void main(String[] args) throws ParserException, IOException{
		Proposition a = new Proposition("a");
		Proposition b = new Proposition("b");
		Proposition c = new Proposition("c");
		Proposition d = new Proposition("d");
		Proposition e = new Proposition("e");
		Proposition f = new Proposition("f");
		Proposition g = new Proposition("g");
		Proposition h = new Proposition("h");
		Proposition i = new Proposition("i");
		Proposition l = new Proposition("l");
		Proposition m = new Proposition("m");
		Proposition n = new Proposition("n");
		Proposition p = new Proposition("p");
		Proposition q = new Proposition("q");
		Proposition k = new Proposition("k");
		Proposition j = new Proposition("j");
		
		PlParser plparser = new PlParser();
		AspicArgumentationTheory<PlFormula> t1 = new AspicArgumentationTheory<>(new PlFormulaGenerator());
		t1.setRuleFormulaGenerator(new PlFormulaGenerator());
		SimpleAspicReasoner<PlFormula> ar = new SimpleAspicReasoner<PlFormula>(AbstractExtensionReasoner.getSimpleReasonerForSemantics(Semantics.PR));
		PlFormula pf1 = (PlFormula)plparser.parseFormula("a");		
		PlFormula pf2 = (PlFormula)plparser.parseFormula("b");		
		PlFormula pf3 = (PlFormula)plparser.parseFormula("f");		
		PlFormula pf4 = (PlFormula)plparser.parseFormula("m");		
		PlFormula pf5 = (PlFormula)plparser.parseFormula("p");		
		
		
		
		
		
		t1.addOrdinaryPremise(a);
		t1.addOrdinaryPremise(b);
		t1.addOrdinaryPremise(c);
		t1.addOrdinaryPremise(d);
		t1.addOrdinaryPremise(e);
		t1.addOrdinaryPremise(f);
		t1.addOrdinaryPremise(g);
		t1.addOrdinaryPremise(h);
		t1.addOrdinaryPremise(i);
		t1.addOrdinaryPremise(l);
		t1.addOrdinaryPremise(m);
		t1.addOrdinaryPremise(n);
		t1.addOrdinaryPremise(p);
		t1.addOrdinaryPremise(q);
		t1.addOrdinaryPremise(k);
		t1.addOrdinaryPremise(j);
		
        StrictInferenceRule<PlFormula> r2 = new StrictInferenceRule<>();
		
		r2.setConclusion(new Negation(a));
		r2.addPremise(c);
		t1.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(c));
		r2.addPremise(m);
		t1.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(m));
		r2.addPremise(l);
		t1.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(l));
		r2.addPremise(k);
		t1.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(k));
		r2.addPremise(m);
		t1.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(d));
		r2.addPremise(c);
		t1.addRule(r2);
			
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(d));
		r2.addPremise(g);
		t1.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(a));
		r2.addPremise(d);
		t1.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(b));
		r2.addPremise(d);
		t1.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(b));
		r2.addPremise(e);
		t1.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(e));
		r2.addPremise(d);
		t1.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(a));
		r2.addPremise(h);
		t1.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(e));
		r2.addPremise(h);
		t1.addRule(r2);
				
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(e));
		r2.addPremise(i);
		t1.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(j));
		r2.addPremise(i);
		t1.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(i));
		r2.addPremise(j);
		t1.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(n));
		r2.addPremise(i);
		t1.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(n));
		r2.addPremise(j);
		t1.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(f));
		r2.addPremise(n);
		t1.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(p));
		r2.addPremise(g);
		t1.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(p));
		r2.addPremise(n);
		t1.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(c));
		r2.addPremise(p);
		t1.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(q));
		r2.addPremise(p);
		t1.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(l));
		r2.addPremise(p);
		t1.addRule(r2);
		
		System.out.println(pf1 + "\t" + ar.query(t1,pf1,InferenceMode.CREDULOUS));		
		System.out.println(pf2 + "\t" + ar.query(t1,pf2,InferenceMode.CREDULOUS));		
		System.out.println(pf3 + "\t" + ar.query(t1,pf3,InferenceMode.CREDULOUS));		
		System.out.println(pf4 + "\t" + ar.query(t1,pf4,InferenceMode.CREDULOUS));		
		System.out.println(pf5 + "\t" + ar.query(t1,pf5,InferenceMode.CREDULOUS));		
		
		
	}
}
