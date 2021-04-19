
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

/**
 * ASPIC example code that shows how to construct an ASPIC theory programmatically.
 * 
 * @author Matthias Thimm
 *
 */
public class AspicExample {
	public static void main(String[] args) throws ParserException, IOException{
		Proposition a = new Proposition("artillery");
		Proposition b = new Proposition("noevidence");
		Proposition c = new Proposition("technique");
		Proposition d = new Proposition("doctrine");
		Proposition e = new Proposition("aid");
		Proposition f = new Proposition("oil");
		Proposition g = new Proposition("humanitarian");
		Proposition h = new Proposition("strategic");
		Proposition i = new Proposition("casualties");
		
		PlParser plparser = new PlParser();
		AspicArgumentationTheory<PlFormula> t = new AspicArgumentationTheory<>(new PlFormulaGenerator());
		t.setRuleFormulaGenerator(new PlFormulaGenerator());
		SimpleAspicReasoner<PlFormula> ar = new SimpleAspicReasoner<PlFormula>(AbstractExtensionReasoner.getSimpleReasonerForSemantics(Semantics.GROUNDED_SEMANTICS));
		PlFormula pf = (PlFormula)plparser.parseFormula("oil");		
		
		t.addAxiom(a);
		t.addAxiom(d);
		t.addAxiom(e);
		t.addAxiom(f);
		t.addOrdinaryPremise(b);
		t.addOrdinaryPremise(c);
		
		DefeasibleInferenceRule<PlFormula> r1 = new DefeasibleInferenceRule<>();
		StrictInferenceRule<PlFormula> r2 = new StrictInferenceRule<>();
		
		r2.setConclusion(new Negation(b));
		r2.addPremise(a);
		t.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(c));
		r2.addPremise(b);
		t.addRule(r2);
		
		r1 = new DefeasibleInferenceRule<>();
		r1.setConclusion(i);
		r1.addPremise(d);
		r1.addPremise(c);
		t.addRule(r1);
        
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(i));
		r2.addPremise(b);
		t.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(g));
		r2.addPremise(i);
		t.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(h));
		r2.addPremise(g);
		t.addRule(r2);
		
		r2 = new StrictInferenceRule<>();
		r2.setConclusion(new Negation(g));
		r2.addPremise(h);
		t.addRule(r2);
		
		r1 = new DefeasibleInferenceRule<>();
		r1.setConclusion(g);
		r1.addPremise(e);
		t.addRule(r1);
		
		r1 = new DefeasibleInferenceRule<>();
		r1.setConclusion(h);
		r1.addPremise(f);
		t.addRule(r1);		
		
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
