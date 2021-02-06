//============================================================================//
//                                                                            //
//                         Copyright © 2015 Sandpolis                         //
//                                                                            //
//  This source file is subject to the terms of the Mozilla Public License    //
//  version 2. You may not use this file except in compliance with the MPL    //
//  as published by the Mozilla Foundation.                                   //
//                                                                            //
//============================================================================//
package com.sandpolis.core.foundation.util;

import static com.sandpolis.core.foundation.util.TextUtil.compareVersion;
import static com.sandpolis.core.foundation.util.TextUtil.formatByteCount;
import static com.sandpolis.core.foundation.util.TextUtil.formatByteCountSI;
import static com.sandpolis.core.foundation.util.TextUtil.parseJavaVersion;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TextUtilTest {

	@Test
	@DisplayName("Format byte counts")
	void testFormatByteCount() {

		assertEquals("0 B", formatByteCount(0L));
		assertEquals("27 B", formatByteCount(27L));
		assertEquals("999 B", formatByteCount(999L));
		assertEquals("1000 B", formatByteCount(1000L));
		assertEquals("1023 B", formatByteCount(1023L));
		assertEquals("1.0 KiB", formatByteCount(1024L));
		assertEquals("1.7 KiB", formatByteCount(1728L));
		assertEquals("108.0 KiB", formatByteCount(110592L));
		assertEquals("6.8 MiB", formatByteCount(7077888L));
		assertEquals("432.0 MiB", formatByteCount(452984832L));
		assertEquals("27.0 GiB", formatByteCount(28991029248L));
		assertEquals("1.7 TiB", formatByteCount(1855425871872L));
		assertEquals("8.0 EiB", formatByteCount(9223372036854775807L));
	}

	@Test
	@DisplayName("Format byte counts in SI system")
	void testFormatByteCountSI() {

		assertEquals("0 B", formatByteCountSI(0L));
		assertEquals("27 B", formatByteCountSI(27L));
		assertEquals("999 B", formatByteCountSI(999L));
		assertEquals("1.0 kB", formatByteCountSI(1000L));
		assertEquals("1.0 kB", formatByteCountSI(1023L));
		assertEquals("1.0 kB", formatByteCountSI(1024L));
		assertEquals("1.7 kB", formatByteCountSI(1728L));
		assertEquals("110.6 kB", formatByteCountSI(110592L));
		assertEquals("7.1 MB", formatByteCountSI(7077888L));
		assertEquals("453.0 MB", formatByteCountSI(452984832L));
		assertEquals("29.0 GB", formatByteCountSI(28991029248L));
		assertEquals("1.9 TB", formatByteCountSI(1855425871872L));
		assertEquals("9.2 EB", formatByteCountSI(9223372036854775807L));
	}

	@Test
	@DisplayName("Parse Java version text")
	void testParseJavaVersion() {

		assertEquals("14.0.2", parseJavaVersion("""
				openjdk 14.0.2 2020-07-14
				OpenJDK Runtime Environment (build 14.0.2+12)
				OpenJDK 64-Bit Server VM (build 14.0.2+12, mixed mode)
				"""));
	}

	@Test
	@DisplayName("Compare version strings")
	void testCompareVersion() {

		assertEquals(0, compareVersion("1.0.0", "1.0.0"));
		assertTrue(compareVersion("1.0.0", "1.0.1") < 0);
		assertTrue(compareVersion("1.0.1", "1.0.0") > 0);
	}
}
