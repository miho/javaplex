package edu.stanford.math.plex_plus.structures;

import org.apache.commons.math.fraction.Fraction;

import edu.stanford.math.plex_plus.datastructures.GenericFormalSum;
import edu.stanford.math.plex_plus.datastructures.IntFormalSum;
import edu.stanford.math.plex_plus.homology.ArraySimplex;
import edu.stanford.math.plex_plus.homology.Simplex;
import edu.stanford.math.plex_plus.math.structures.GenericFreeModule;
import edu.stanford.math.plex_plus.math.structures.IntFreeModule;
import edu.stanford.math.plex_plus.math.structures.ModularIntField;
import edu.stanford.math.plex_plus.math.structures.RationalField;


public class GenericFreeModuleTest {

	public static void main(String[] args) {
		GenericFreeModule<Fraction, String> M = new GenericFreeModule<Fraction, String>(RationalField.getInstance());
		GenericFormalSum<Fraction, String> sum = M.multiply(5, M.add(M.add("b", "b"), "a"));
		System.out.println(sum.toString());
		
		GenericFreeModule<Fraction, Simplex> C = new  GenericFreeModule<Fraction, Simplex>(RationalField.getInstance());
		GenericFormalSum<Fraction, Simplex> chain = C.add(new ArraySimplex(new int[]{0, 1}), new ArraySimplex(new int[]{2, 1}));
		chain = C.add(chain, chain);
		System.out.println(chain);
		
		IntFreeModule<Simplex> D = new IntFreeModule<Simplex>(ModularIntField.getInstance(3));
		IntFormalSum<Simplex> chain2 = D.add(new ArraySimplex(new int[]{0, 1}), new ArraySimplex(new int[]{2, 1}));
		chain2 = D.add(chain2, chain2);
		chain2 = D.add(chain2, new ArraySimplex(new int[]{0, 1}));
		System.out.println(chain2);
	}
}
