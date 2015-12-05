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
package com.tecacet.util.introspection;

import static org.junit.Assert.assertNotNull;

import java.util.Currency;

import org.junit.Test;

import com.tecacet.jflat.om.Customer;
import com.tecacet.jflat.om.Order;

public class DefaultBeanFactoryTest {

    @Test
    public void testCreateBeanOK() throws BeanCreationException {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        Order order = (Order) factory.createBean(Order.class);
        assertNotNull(order);
    }
    
    @Test
	public void testCreateBean() {
		BeanFactory beanFactory = new DefaultBeanFactory();
		Customer customer = (Customer) beanFactory.createBean(Customer.class);
		assertNotNull(customer);
	}
	
	@Test(expected=BeanCreationException.class)
	public void testNoDefaultConstructor() {
		BeanFactory beanFactory = new DefaultBeanFactory();
		beanFactory.createBean(Currency.class);
	}


}
