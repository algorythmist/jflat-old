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

public class BooleanToStringConverter implements ToStringConverter<Boolean> {

	private String yesString = "Y";
	private String noString = "N";

	public BooleanToStringConverter() {
		this("Y", "N");
	}

	public BooleanToStringConverter(String yesString, String noString) {
		super();
		this.yesString = yesString;
		this.noString = noString;
	}

	@Override
	public String convert(Boolean value) {
		if (value == null) {
			return null;
		}
		return value ? yesString : noString;
	}

	public String getYesString() {
		return yesString;
	}

	public void setYesString(String yesString) {
		this.yesString = yesString;
	}

	public String getNoString() {
		return noString;
	}

	public void setNoString(String noString) {
		this.noString = noString;
	}

}
