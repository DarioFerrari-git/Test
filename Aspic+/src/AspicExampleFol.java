
import java.io.IOException;

import org.tweetyproject.arg.aspic.parser.AspicParser;
import org.tweetyproject.arg.aspic.reasoner.SimpleAspicReasoner;
import org.tweetyproject.arg.aspic.ruleformulagenerator.FolFormulaGenerator;
import org.tweetyproject.arg.aspic.syntax.AspicArgumentationTheory;
import org.tweetyproject.arg.dung.reasoner.AbstractExtensionReasoner;
import org.tweetyproject.arg.dung.semantics.Semantics;
import org.tweetyproject.commons.InferenceMode;
import org.tweetyproject.commons.ParserException;
import org.tweetyproject.logics.fol.parser.FolParser;
import org.tweetyproject.logics.fol.syntax.FolFormula;
import org.tweetyproject.logics.fol.syntax.FolSignature;

/**
 * Example code for using ASPIC with first-order-logic formulas.
 * 
 * @author Anna Gessler
 *
 */
public class AspicExampleFol {
	public static void main(String[] args) throws ParserException, IOException {
		// FOL Example
		FolParser folparser = new FolParser();
		FolSignature sig = folparser.parseSignature("Person = {alice,bob}\n" + 
				"type(snores(Person))\n" + 
				"type(professor(Person))\n" + 
				"type(accessDenied(Person))\n" + 
				"type(accessAllowed(Person))\n" + 
				"type(misbehaves(Person))");
		folparser.setSignature(sig);
		AspicParser<FolFormula> parser2 = new AspicParser<FolFormula>(folparser, new FolFormulaGenerator());
		parser2.setSymbolComma(";");
		
		AspicArgumentationTheory<FolFormula> at = parser2.parseBeliefBaseFromFile("ex5_fol.aspic");		
		SimpleAspicReasoner<FolFormula> ar = new SimpleAspicReasoner<FolFormula>(AbstractExtensionReasoner.getSimpleReasonerForSemantics(Semantics.CONFLICTFREE_SEMANTICS));
		FolFormula pf = (FolFormula)folparser.parseFormula("accessAllowed(bob)");	
		
		System.out.println(at.asDungTheory());
		System.out.println(pf + "\t" + ar.query(at,pf,InferenceMode.CREDULOUS));
	}
}
