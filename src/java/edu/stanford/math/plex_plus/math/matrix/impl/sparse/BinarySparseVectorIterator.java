/**
 * 
 */
package edu.stanford.math.plex_plus.math.matrix.impl.sparse;

import edu.stanford.math.plex_plus.math.matrix.interfaces.BinaryAbstractVectorIterator;
import edu.stanford.math.plex_plus.utility.ExceptionUtility;
import gnu.trove.iterator.TIntIterator;

/**
 * @author atausz
 *
 */
public class BinarySparseVectorIterator implements BinaryAbstractVectorIterator {
	private final TIntIterator iterator;
	private int currentIndex;
	
	public BinarySparseVectorIterator(BinarySparseVector vector) {
		ExceptionUtility.verifyNonNull(vector);
		this.iterator = vector.indices.iterator();
	}
	/* (non-Javadoc)
	 * @see edu.stanford.math.plex_plus.math.matrix.interfaces.BinaryAbstractVectorIterator#advance()
	 */
	public void advance() {
		this.currentIndex = this.iterator.next();
	}

	/* (non-Javadoc)
	 * @see edu.stanford.math.plex_plus.math.matrix.interfaces.BinaryAbstractVectorIterator#hasNext()
	 */
	public boolean hasNext() {
		return this.iterator.hasNext();
	}

	/* (non-Javadoc)
	 * @see edu.stanford.math.plex_plus.math.matrix.interfaces.BinaryAbstractVectorIterator#index()
	 */
	public int index() {
		return this.currentIndex;
	}

	/* (non-Javadoc)
	 * @see edu.stanford.math.plex_plus.math.matrix.interfaces.BinaryAbstractVectorIterator#remove()
	 */
	public void remove() {
		this.iterator.remove();
	}
	
	public boolean value() {
		return true;
	}

}
