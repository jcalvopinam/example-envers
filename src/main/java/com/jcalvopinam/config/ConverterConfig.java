/*
 * MIT License
 *
 * Copyright (c) 2020 JUAN CALVOPINA M
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.jcalvopinam.config;

import com.jcalvopinam.converter.OrderDTOtoOrderConverter;
import com.jcalvopinam.converter.OrderToOrderDTOConverter;
import com.jcalvopinam.converter.PersonDTOtoPersonConverter;
import com.jcalvopinam.converter.PersonToPersonDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author juan.calvopina
 */
@Configuration
public class ConverterConfig implements WebMvcConfigurer {

    private final PersonToPersonDTOConverter personToPersonDTOConverter;
    private final PersonDTOtoPersonConverter personDTOtoPersonConverter;
    private final OrderToOrderDTOConverter orderToOrderDTOConverter;
    private final OrderDTOtoOrderConverter orderDTOtoOrderConverter;

    @Autowired
    public ConverterConfig(final PersonToPersonDTOConverter personToPersonDTOConverter,
                           final PersonDTOtoPersonConverter personDTOtoPersonConverter,
                           final OrderToOrderDTOConverter orderToOrderDTOConverter,
                           final OrderDTOtoOrderConverter orderDTOtoOrderConverter) {
        this.personToPersonDTOConverter = personToPersonDTOConverter;
        this.personDTOtoPersonConverter = personDTOtoPersonConverter;
        this.orderToOrderDTOConverter = orderToOrderDTOConverter;
        this.orderDTOtoOrderConverter = orderDTOtoOrderConverter;
    }

    @Override
    public void addFormatters(final FormatterRegistry registry) {
        registry.addConverter(personToPersonDTOConverter);
        registry.addConverter(personDTOtoPersonConverter);
        registry.addConverter(orderToOrderDTOConverter);
        registry.addConverter(orderDTOtoOrderConverter);
    }

}
