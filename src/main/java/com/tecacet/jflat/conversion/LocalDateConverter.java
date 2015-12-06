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
package com.tecacet.jflat.conversion;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LocalDateConverter implements DataConverter<String, LocalDate>{

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private final DateTimeFormatter[] formatters;
    
    public LocalDateConverter(String dateFormatString) {
        super();
        this.formatters = new DateTimeFormatter[1];
        this.formatters[0] = DateTimeFormat.forPattern(dateFormatString);   		
    }

    public LocalDateConverter(String[] dateFormatStrings) {
        super();
        this.formatters = new DateTimeFormatter[dateFormatStrings.length];
        for (int i=0;i<dateFormatStrings.length;i++) {
        	formatters[i] = DateTimeFormat.forPattern(dateFormatStrings[i]);
        }
    }
    
    @Override
    public LocalDate convert(String from) {
    	if (from == null) {
    		return null;
    	}
        for (DateTimeFormatter formatter : formatters) {
        	try {
        		return (LocalDate) LocalDate.parse(from, formatter);
        	} catch (Exception e) {
        		log.trace(e.getMessage());
        	}
        }
        String message = String.format("Error converting %s to LocalDate", from);
        throw new IllegalArgumentException(message);
    }

    
    
}
