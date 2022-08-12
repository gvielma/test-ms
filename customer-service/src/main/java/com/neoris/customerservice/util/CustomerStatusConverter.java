package com.neoris.customerservice.util;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class CustomerStatusConverter implements AttributeConverter<CustomerStatus, String> {

	@Override
	public String convertToDatabaseColumn(CustomerStatus customerStatus) {
		if (customerStatus == null) {
			return null;
		}
		return customerStatus.getStatus();
	}

	@Override
	public CustomerStatus convertToEntityAttribute(String status) {
		if (status == null) {
			return null;
		}
		return Stream.of(CustomerStatus.values()).filter(c -> c.getStatus().equals(status)).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

}
