/*
 Copyright 2008 TecaceT Ltd.

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

public class FixedWidthLineMerger implements LineMerger {

    private int[] widths;
    private String format;
    private final String EMPTY_STRING = "";

    public FixedWidthLineMerger(int[] widths) {
        super();
        this.widths = widths;
        this.format = buildFormat();
    }

    @Override
    public String makeLine(String[] elements) throws LineMergerException{
        convertNullsToEmptyStrings(elements);
        validateFields(elements);
        return String.format(format, (Object[]) elements);
    }

    private String buildFormat() {
        StringBuffer sb = new StringBuffer();
        for (int width : widths) {
            String format = "%-" + width + "s"; //This means a left-justified string of <width> characters
            sb.append(format);
        }
        return sb.toString();
    }

    private void validateFields(String[] elements) throws LineMergerException {
        if (elements.length > widths.length) {
            throw new TooManyFieldsException(elements.length, widths.length);
        }

        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == null) {
                continue; 
            }
            if (elements[i].length() > widths[i]) {
                throw new FieldTooWideException(elements[i], widths[i]);
            }
        }
    }
    
    private void convertNullsToEmptyStrings(String[] elements) {
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == null) {
                elements[i] = EMPTY_STRING;
            }
        }
    }
       
}
