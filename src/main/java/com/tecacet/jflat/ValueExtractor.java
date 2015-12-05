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

/**
 * 
 * @author Dimitri Papaioannou
 *
 * This interface is used by a File Writer to extract a column value from a bean.
 * Suppose that you want to map a currency ($) and an amount (10,000) to a single value that looks like this: $10,000
 * Then you can use a value extractor like this:
 * 
 * <verbatim>
 *  String getValue(Order order) { return order.getCurrency().getSymbol() + order.getAmount() }
 * </verbatim>
 * 
 */
public interface ValueExtractor<T> {

    String getValue(T bean);
}
