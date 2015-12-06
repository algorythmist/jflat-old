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
package com.tecacet.jflat.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Like java.util.Properties but respects spaces
 * 
 * @author Dimitri Papaioannou
 * 
 */
public class MapReader {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    
    private boolean convertToLowerCase = false;
    
    /**
     * Read a properties file into a map
     * 
     * @param is
     * @return
     * @throws IOException
     */
    public Map<String, String> readMap(InputStream is) throws IOException {
        Map<String, String> map = new LinkedHashMap<String, String>();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = br.readLine();
        while (line != null) {
            // skip comments
            if (line.startsWith("#") || line.trim().equals("")) {
                line = br.readLine();
                continue;
            }
            String[] nextLineAsTokens = line.split("=");
            if (nextLineAsTokens.length < 2) {
                log.warn("Line does not contain an assignment: {} ", line);
                line = br.readLine();
                continue;
            }
            if (nextLineAsTokens.length > 2) {
                log.warn("Ambiguous assignement: {}", line);
                line = br.readLine();
                continue;
            }
            String property = nextLineAsTokens[0].trim();
            if (convertToLowerCase) {
                property = property.toLowerCase();
            }
            map.put(property, nextLineAsTokens[1].trim());
            line = br.readLine();
        }
        br.close();
        return map;
    }

    public boolean isConvertToLowerCase() {
        return convertToLowerCase;
    }

    public void setConvertToLowerCase(boolean convertToLowerCase) {
        this.convertToLowerCase = convertToLowerCase;
    }

}
