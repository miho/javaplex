package edu.stanford.math.plex_plus.math.miscellaneous;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.ArrayRealVector;
import org.apache.commons.math.linear.EigenDecomposition;
import org.apache.commons.math.linear.EigenDecompositionImpl;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.RealVector;
import org.apache.commons.math.util.MathUtils;

public class ApacheCommonsMathTest {
	public static void main(String[] args) throws InterruptedException {
		//testMathpbx02();
		test1();
	}
	
	public static void test1() throws InterruptedException {
		int n = 1000;
		//SparseRealMatrix matrix = new OpenMapRealMatrix(n, n);
		RealMatrix matrix = new Array2DRowRealMatrix(n, n);
		for (int i = 0; i < n; i++) { 
			matrix.setEntry(i, i, Math.random());
		}

		/*
		matrix.setEntry(n/2, n/2, 3);
		matrix.setEntry(n/2, n/2, 3);

		matrix.setEntry(0, n - 1, -7);
		matrix.setEntry(n - 1, 0, -7);
		*/
		
		//System.out.println(matrix);
		EigenDecomposition eigen = new EigenDecompositionImpl(matrix, MathUtils.SAFE_MIN);

		//System.out.println(ArrayUtility.toString(eigen.getRealEigenvalues()));
		//System.out.println(eigen.getEigenvector(0));
		//System.out.println(eigen.getEigenvector(1));
		System.out.println(eigen.getEigenvector(0).getL1Norm());
		System.out.println(eigen.getEigenvector(1).getL1Norm());
	}

	public static void testMathpbx02() {

		double[] mainTridiagonal = {
				7484.860960227216, 18405.28129035345, 13855.225609560746,
				10016.708722343366, 559.8117399576674, 6750.190788301587,
				71.21428769782159
		};
		double[] secondaryTridiagonal = {
				-4175.088570476366,1975.7955858241994,5193.178422374075,
				1995.286659169179,75.34535882933804,-234.0808002076056
		};

		// the reference values have been computed using routine DSTEMR
		// from the fortran library LAPACK version 3.2.1
		double[] refEigenValues = {
				20654.744890306974412,16828.208208485466457,
				6893.155912634994820,6757.083016675340332,
				5887.799885688558788,64.309089923240379,
				57.992628792736340
		};
		RealVector[] refEigenVectors = {
				new ArrayRealVector(new double[] {-0.270356342026904, 0.852811091326997, 0.399639490702077, 0.198794657813990, 0.019739323307666, 0.000106983022327, -0.000001216636321}),
				new ArrayRealVector(new double[] {0.179995273578326,-0.402807848153042,0.701870993525734,0.555058211014888,0.068079148898236,0.000509139115227,-0.000007112235617}),
				new ArrayRealVector(new double[] {-0.399582721284727,-0.056629954519333,-0.514406488522827,0.711168164518580,0.225548081276367,0.125943999652923,-0.004321507456014}),
				new ArrayRealVector(new double[] {0.058515721572821,0.010200130057739,0.063516274916536,-0.090696087449378,-0.017148420432597,0.991318870265707,-0.034707338554096}),
				new ArrayRealVector(new double[] {0.855205995537564,0.327134656629775,-0.265382397060548,0.282690729026706,0.105736068025572,-0.009138126622039,0.000367751821196}),
				new ArrayRealVector(new double[] {-0.002913069901144,-0.005177515777101,0.041906334478672,-0.109315918416258,0.436192305456741,0.026307315639535,0.891797507436344}),
				new ArrayRealVector(new double[] {-0.005738311176435,-0.010207611670378,0.082662420517928,-0.215733886094368,0.861606487840411,-0.025478530652759,-0.451080697503958})
		};

		// the following line triggers the exception
		EigenDecomposition decomposition =
			new EigenDecompositionImpl(mainTridiagonal, secondaryTridiagonal, MathUtils.SAFE_MIN);

		double[] eigenValues = decomposition.getRealEigenvalues();
		for (int i = 0; i < refEigenValues.length; ++i) {
			assert(Math.abs(refEigenValues[i] - eigenValues[i]) < 1.0e-3);
			if (refEigenVectors[i].dotProduct(decomposition.getEigenvector(i)) < 0) {
				assert(refEigenVectors[i].add(decomposition.getEigenvector(i)).getNorm() < 1.0e-5);
			} else {
				assert(refEigenVectors[i].subtract(decomposition.getEigenvector(i)).getNorm() < 1.0e-5);
			}
		}

	} 
}
