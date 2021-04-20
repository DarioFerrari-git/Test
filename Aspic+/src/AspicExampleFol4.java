
import java.io.IOException;

import org.tweetyproject.arg.aspic.parser.AspicParser;
import org.tweetyproject.arg.aspic.reasoner.SimpleAspicReasoner;
import org.tweetyproject.arg.aspic.ruleformulagenerator.FolFormulaGenerator;
import org.tweetyproject.arg.aspic.syntax.AspicArgumentationTheory;
import org.tweetyproject.arg.dung.reasoner.AbstractExtensionReasoner;
import org.tweetyproject.arg.dung.semantics.Semantics;
import org.tweetyproject.arg.dung.syntax.Argument;
import org.tweetyproject.arg.dung.syntax.Attack;
import org.tweetyproject.arg.dung.syntax.DungTheory;
import org.tweetyproject.commons.InferenceMode;
import org.tweetyproject.commons.ParserException;
import org.tweetyproject.logics.fol.parser.FolParser;
import org.tweetyproject.logics.fol.syntax.FolFormula;
import org.tweetyproject.logics.fol.syntax.FolSignature;


public class AspicExampleFol4 {
	public static void main(String[] args) throws ParserException, IOException {
		// FOL Example
		FolParser folparser = new FolParser();
		FolSignature sig = folparser.parseSignature(
				"type(infoionP)\n" + 				
                "type(healthinfo)\n" +
                "type(Pdonotwanttopublish)\n" +	
                "type(iaffectPfunction)\n" +
                "type(nopublication)\n" +
                "type(publication)\n" +
                "type(privateinfo)\n" +
                "type(ihaspublicsignificant)\n" +
				"type(Pcabinetminister)\n");
		folparser.setSignature(sig);
		AspicParser<FolFormula> parser2 = new AspicParser<FolFormula>(folparser, new FolFormulaGenerator());
		parser2.setSymbolComma(";");
		
		AspicArgumentationTheory<FolFormula> at = parser2.parseBeliefBaseFromFile("ex5_fol4.aspic");		
		SimpleAspicReasoner<FolFormula> ar = new SimpleAspicReasoner<FolFormula>(AbstractExtensionReasoner.getSimpleReasonerForSemantics(Semantics.GROUNDED_SEMANTICS));
		FolFormula pf = (FolFormula)folparser.parseFormula("nopublication");	
		
		System.out.println(at.asDungTheory());
		System.out.println();
		
		DungTheory aaf = at.asDungTheory();
		for(Argument arg: aaf)
			System.out.println(arg);
		
		
		for(Attack att: aaf.getAttacks())
			System.out.println(att);
		
		System.out.println();
		
		System.out.println(pf + "\t" + ar.query(at,pf,InferenceMode.CREDULOUS));
	}
}
