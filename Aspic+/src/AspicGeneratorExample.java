
import org.tweetyproject.arg.aspic.reasoner.DirectionalAspicReasoner;
import org.tweetyproject.arg.aspic.reasoner.ModuleBasedAspicReasoner;
import org.tweetyproject.arg.aspic.reasoner.SimpleAspicReasoner;
import org.tweetyproject.arg.aspic.reasoner.RandomAspicReasoner;
import org.tweetyproject.arg.aspic.syntax.AspicArgumentationTheory;
import org.tweetyproject.arg.aspic.util.RandomAspicArgumentationTheoryGenerator;
import org.tweetyproject.arg.dung.reasoner.AbstractExtensionReasoner;
import org.tweetyproject.arg.dung.semantics.Semantics;
import org.tweetyproject.commons.InferenceMode;
import org.tweetyproject.logics.pl.syntax.Proposition;
import org.tweetyproject.logics.pl.syntax.PlFormula;

/**
 * Exemplary code illustrating the use of the ASPIC theory generator.
 * Furthermore this code show a small performance comparison between
 * the naive ASPIC reasoner, the module based reasoner, and the random reasoner.
 * 
 * @author Matthias Thimm
 *
 */
public class AspicGeneratorExample {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {		 
		int repetitions = 50;
		int numberAtoms = 35;
		int numberFormulas = 100;
		int maxLiteralsInPremises = 2;
		double percentageStrictRules = 0.2;
		
		SimpleAspicReasoner<PlFormula> naiveReasoner = new SimpleAspicReasoner<PlFormula>(AbstractExtensionReasoner.getSimpleReasonerForSemantics(Semantics.GR));
		ModuleBasedAspicReasoner<PlFormula> moduleBasedReasoner = new ModuleBasedAspicReasoner<PlFormula>(AbstractExtensionReasoner.getSimpleReasonerForSemantics(Semantics.GR));
		DirectionalAspicReasoner<PlFormula> dirReasoner = new DirectionalAspicReasoner<PlFormula>(AbstractExtensionReasoner.getSimpleReasonerForSemantics(Semantics.GR));
		RandomAspicReasoner<PlFormula> randomReasoner = new RandomAspicReasoner<PlFormula>(AbstractExtensionReasoner.getSimpleReasonerForSemantics(Semantics.GR),600,100);
		
		long totalNaive = 0;
		long totalModulebased = 0;
		long totalDirectional = 0;
		long totalRandom = 0;
		long correctRandom = 0;
		long correctDirectional = 0;
		RandomAspicArgumentationTheoryGenerator gen = new RandomAspicArgumentationTheoryGenerator(numberAtoms, numberFormulas, maxLiteralsInPremises, percentageStrictRules);
		for(int i = 0; i < repetitions; i++) {
			AspicArgumentationTheory<PlFormula> theory = gen.next();
			System.out.println(i + "\t" + theory);
			PlFormula query = new Proposition("A1");
			// Naive
			long millis = System.currentTimeMillis();
			boolean answer = naiveReasoner.query(theory,query,InferenceMode.CREDULOUS);
			totalNaive += System.currentTimeMillis()-millis;
			// Module
			millis = System.currentTimeMillis();
			moduleBasedReasoner.query(theory,query,InferenceMode.CREDULOUS);
			totalModulebased += System.currentTimeMillis()-millis;
			// Directional
			millis = System.currentTimeMillis();
			if(dirReasoner.query(theory,query,InferenceMode.CREDULOUS) == answer)
				correctDirectional++;
			totalDirectional += System.currentTimeMillis()-millis;
			// Random
			millis = System.currentTimeMillis();
			if(randomReasoner.query(theory,query,InferenceMode.CREDULOUS) == answer)
				correctRandom++;
			totalRandom += System.currentTimeMillis()-millis;
		}	
		System.out.println();
		System.out.println("Runtime naive reasoner: " + totalNaive + "ms");
		System.out.println("Runtime module-based reasoner: " +  totalModulebased+ "ms");
		System.out.println("Runtime directional reasoner: " +  totalDirectional+ "ms");
		System.out.println("Runtime random reasoner: " +  totalRandom + "ms");
		System.out.println("Accuracy directional reasoner: " +(new Double(correctDirectional)/(repetitions)));
		System.out.println("Accuracy random reasoner: " +(new Double(correctRandom)/(repetitions)));
	}	
	
}