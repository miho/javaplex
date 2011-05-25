package edu.stanford.math.plex4.homology.barcodes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnnotatedBarcodeCollection<T extends Comparable<T>, G> extends PersistenceInvariantDescriptor<Interval<T>, G> {
	protected boolean useLeftClosedDefault = true;
	protected boolean useRightClosedDefault = false;

	/**
	 * This function sets the property that determines whether the left endpoint
	 * of created intervals is closed or not.
	 * 
	 * @param value
	 */
	public void setLeftClosedDefault(boolean value) {
		this.useLeftClosedDefault = value;
	}

	/**
	 * This function sets the property that determines whether the right endpoint
	 * of created intervals is closed or not.
	 * 
	 * @param value
	 */
	public void setRightClosedDefault(boolean value) {
		this.useRightClosedDefault = value;
	}

	/**
	 * This function adds the specified finite interval (start, end} at the
	 * supplied dimension. The curly braces are meant to indicate that the
	 * closedness of the end points depends on the default state of the class.
	 * 
	 * @param dimension the dimension to add to
	 * @param start the starting point of the interval
	 * @param end the ending point of the interval
	 * @param generatingCycle the generating cycle
	 */
	public void addInterval(int dimension, T start, T end, G generatingCycle) {
		this.addInterval(dimension, Interval.makeInterval(start, end, useLeftClosedDefault, useRightClosedDefault, false, false), generatingCycle);
	}

	/**
	 * This function adds the specified semi-infinite interval {start, infinity}
	 * at the supplied dimension. The curly braces are meant to indicate that the
	 * closedness of the end points depends on the default state of the class.
	 * 
	 * @param dimension the dimension to add to
	 * @param start the starting point of the interval
	 * @param generatingCycle the generating cycle
	 */
	public void addRightInfiniteInterval(int dimension, T start, G generatingCycle) {
		this.addInterval(dimension, Interval.makeInterval(start, null, useLeftClosedDefault, useRightClosedDefault, false, true), generatingCycle);
	}

	/**
	 * This function adds the specified semi-infinite interval {-infinity, end}
	 * at the supplied dimension. The curly braces are meant to indicate that the
	 * closedness of the end points depends on the default state of the class.
	 * 
	 * @param dimension the dimension to add to
	 * @param end the ending point of the interval
	 * @param generatingCycle the generating cycle
	 */
	public void addLeftInfiniteInterval(int dimension, T end, G generatingCycle) {
		this.addInterval(dimension, Interval.makeInterval(null, end, useLeftClosedDefault, useRightClosedDefault, true, false), generatingCycle);
	}

	public AnnotatedBarcodeCollection<T, G> getInfiniteIntervals() {
		AnnotatedBarcodeCollection<T, G> result = new AnnotatedBarcodeCollection<T, G>();

		for (Integer dimension: this.intervals.keySet()) {
			List<Interval<T>> intervalList = this.intervals.get(dimension);
			List<G> generatorList = this.generators.get(dimension);

			for (int i = 0; i < intervalList.size(); i++) {
				Interval<T> interval = intervalList.get(i);
				G generator = generatorList.get(i);

				if (interval.isInfinite()) {
					result.addInterval(dimension, interval, generator);
				}
			}
		}

		return result;
	}

	public Map<Integer, Integer> getBettiNumbersMap(T point) {
		Map<Integer, Integer> result = new HashMap<Integer, Integer>();

		for (Integer dimension: this.intervals.keySet()) {
			List<Interval<T>> intervalList = this.intervals.get(dimension);
			int count = 0;

			for (Interval<T> interval: intervalList) {
				if (interval.containsPoint(point)) {
					count++;
				}
			}

			if (count > 0) {
				result.put(dimension, count);
			}
		}

		return result;
	}

	public AnnotatedBarcodeCollection<T, G> filterByMaxDimension(int maxDimension) {
		AnnotatedBarcodeCollection<T, G> result = new AnnotatedBarcodeCollection<T, G>();
		result.useLeftClosedDefault = this.useLeftClosedDefault;
		result.useRightClosedDefault = this.useRightClosedDefault;
		
		for (Integer dimension: this.intervals.keySet()) {
			if (dimension > maxDimension) {
				continue;
			}

			List<Interval<T>> intervalList = this.intervals.get(dimension);
			List<G> generatorList = this.generators.get(dimension);

			for (int i = 0; i < intervalList.size(); i++) {
				Interval<T> interval = intervalList.get(i);
				G generator = generatorList.get(i);

				result.addInterval(dimension, interval, generator);
			}
		}

		return result;
	}
}
