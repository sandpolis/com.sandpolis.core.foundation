//============================================================================//
//                                                                            //
//                         Copyright © 2015 Sandpolis                         //
//                                                                            //
//  This source file is subject to the terms of the Mozilla Public License    //
//  version 2. You may not use this file except in compliance with the MPL    //
//  as published by the Mozilla Foundation.                                   //
//                                                                            //
//============================================================================//
package com.sandpolis.core.foundation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.sandpolis.core.foundation.Result.Outcome;

/**
 * A {@link Set} of {@link Outcome}s representing the overall result of some
 * larger action which may consist of multiple components.
 *
 * @author cilki
 * @since 4.0.0
 */
public final class OutcomeSet extends HashSet<Outcome> {

	private static final long serialVersionUID = 1L;

	/**
	 * Add an outcome to the set.
	 *
	 * @param outcome The outcome to add
	 */
	public void add(Outcome.Builder outcome) {
		add(outcome.build());
	}

	/**
	 * Get the current result.
	 *
	 * @return The overall outcome of the {@code OutcomeSet}
	 */
	public boolean getResult() {
		for (Outcome outcome : this)
			if (!outcome.getResult())
				return false;
		return true;
	}

	/**
	 * Get one of the failed outcomes.
	 *
	 * @return A failed outcome or {@code null} if all outcomes succeeded
	 */
	public Outcome getOneFailed() {
		return stream().filter(outcome -> !outcome.getResult()).findAny().orElse(null);
	}

	/**
	 * Get all failed outcomes.
	 *
	 * @return A list of all failed outcomes in the {@code OutcomeSet}
	 */
	public List<Outcome> getFailed() {
		return stream().filter(outcome -> !outcome.getResult()).collect(Collectors.toList());
	}

	/**
	 * Get all passed outcomes.
	 *
	 * @return A list of all passed outcomes in the {@code OutcomeSet}
	 */
	public List<Outcome> getPassed() {
		return stream().filter(outcome -> outcome.getResult()).collect(Collectors.toList());
	}

	/**
	 * Get the total duration.
	 *
	 * @return The cumulative duration of all outcomes in the {@code OutcomeSet}.
	 */
	public long getTime() {
		return stream().mapToLong(Outcome::getTime).sum();
	}

}
