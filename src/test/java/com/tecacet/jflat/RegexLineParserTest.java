/*
 Copyright 2008 TecAceT Ltd.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

package com.tecacet.jflat;

import static org.junit.Assert.assertArrayEquals;

import java.io.IOException;

import org.junit.Test;

public class RegexLineParserTest {

	@Test
	public void testRegexLineParser() throws IOException {
		RegexLineParser lineParser = new RegexLineParser("\\s+");
		String[] tokens = lineParser.parseLine("I am    only joking ");
		assertArrayEquals( new String[] {"I","am","only", "joking"}, tokens );
	}
	
	@Test
	public void testLimit() throws IOException {
		RegexLineParser lineParser = new RegexLineParser("\\s+", 2);
		String[] tokens = lineParser.parseLine("I am    only joking ");
		
		assertArrayEquals( new String[] {"I","am    only joking "}, tokens );
		lineParser.setLimit(3);
		tokens = lineParser.parseLine("I am    only joking ");
		assertArrayEquals( new String[] {"I","am","only joking "}, tokens );
	}

}
