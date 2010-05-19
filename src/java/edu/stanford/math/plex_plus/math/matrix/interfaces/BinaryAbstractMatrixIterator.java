package edu.stanford.math.plex_plus.math.matrix.interfaces;

public interface BinaryAbstractMatrixIterator {
	public boolean hasNext();
	public void advance();
	public void remove();
	public int row();
	public int column();
	public boolean value();
}
