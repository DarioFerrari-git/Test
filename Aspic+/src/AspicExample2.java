
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


public class AspicExample2 {
	public static void main(String[] args) throws ParserException, IOException{
		Proposition a = new Proposition("a");
		Proposition b = new Proposition("b");
		Proposition c = new Proposition("c");
		
		PlParser plparser = new PlParser();
		AspicArgumentationTheory<PlFormula> t = new AspicArgumentationTheory<>(new PlFormulaGenerator());
		t.setRuleFormulaGenerator(new PlFormulaGenerator());
		SimpleAspicReasoner<PlFormula> ar = new SimpleAspicReasoner<PlFormula>(AbstractExtensionReasoner.getSimpleReasonerForSemantics(Semantics.GR));
		PlFormula pf = (PlFormula)plparser.parseFormula("c");		
		
		t.addOrdinaryPremise(a);
		t.addOrdinaryPremise(b);
		t.addOrdinaryPremise(c);
		
		DefeasibleInferenceRule<PlFormula> r1 = new DefeasibleInferenceRule<>();
		StrictInferenceRule<PlFormula> r2 = new StrictInferenceRule<>();
		
		r2.setConclusion(new Negation(b));
		r2.addPremise(a);
		t.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(a));
		r2.addPremise(b);
		t.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(c));
		r2.addPremise(b);
		t.addRule(r2);
		
		System.out.println(pf + "\t" + ar.query(t,pf,InferenceMode.CREDULOUS));		
		System.out.println(t);
		System.out.println();	
		
		DungTheory aaf = t.asDungTheory();
		System.out.println(aaf);
		AbstractExtensionReasoner reasoner = new SimpleGroundedReasoner();
		System.out.println(reasoner.getModels(aaf));
		
		System.out.println("Argument");
		for(Argument arg: aaf)
			System.out.println(arg);
		System.out.println();
		
		System.out.println("Attack");
		for(Attack att: aaf.getAttacks())
			System.out.println(att);	
		

	   // SimpleAspicReasoner<PlFormula> r9=new SimpleAspicReasoner<>(new SimpleGroundedReasoner());
	   // System.out.println(p+"\t"+r9.query(t2, p, InferenceMode.CREDULOUS));
	   
	}
}
