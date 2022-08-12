package com.neoris.customerservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neoris.customerservice.dto.request.CustomerRequest;
import com.neoris.customerservice.dto.response.CustomerResponse;
import com.neoris.customerservice.entity.CustomerEntity;
import com.neoris.customerservice.entity.PersonEntity;
import com.neoris.customerservice.errorhandler.exception.ConflictException;
import com.neoris.customerservice.errorhandler.exception.CustomerNotExistsException;
import com.neoris.customerservice.repository.ICustomerRepository;
import com.neoris.customerservice.util.CustomerStatus;

@Service
public class CustomerService implements ICustomerService {

	@Autowired
	private ICustomerRepository customerRepository;
	
	@Override
	public CustomerResponse getCustomerById(Long id) {
		CustomerEntity customerEntity = customerRepository.findById(id)
				.orElseThrow(() -> new CustomerNotExistsException(String.format("Cliente %s not exists", id),
						"CUSTOMER000"));

		return CustomerResponse.builder()
				.clienteID(customerEntity.getCodigoCliente())
				.direccion(customerEntity.getPerson().getDireccion())
				.edad(customerEntity.getPerson().getEdad())
				.estado(customerEntity.getEstado().getStatus())
				.genero(customerEntity.getPerson().getGenero())
				.id(customerEntity.getId())
				.identificacion(customerEntity.getPerson().getIdentificacion())
				.nombre(customerEntity.getPerson().getNombre())
				.telefono(customerEntity.getPerson().getTelefono())
				.build();
	}

	@Override
	public CustomerResponse getCustomerByCustomerID(Long customerID) {
		CustomerEntity customerEntity = customerRepository.findByCodigoCliente(customerID)
				.orElseThrow(() -> new CustomerNotExistsException(String.format("Cliente %s not exists", customerID),
						"CUSTOMER001"));

		return CustomerResponse.builder()
				.clienteID(customerEntity.getCodigoCliente())
				.direccion(customerEntity.getPerson().getDireccion())
				.edad(customerEntity.getPerson().getEdad())
				.estado(customerEntity.getEstado().getStatus())
				.genero(customerEntity.getPerson().getGenero())
				.id(customerEntity.getId())
				.identificacion(customerEntity.getPerson().getIdentificacion())
				.nombre(customerEntity.getPerson().getNombre()
						)
				.telefono(customerEntity.getPerson().getTelefono())
				.build();
	}

	@Override
	public CustomerResponse saveCustomer(CustomerRequest customerRequest) {
		
		/** Validar contraseña **/
	if(null == customerRequest.getClave() || customerRequest.getClave().isEmpty()) {
		
		/** Se Lanza Excepción */
		throw new ConflictException("La contraseña no puede ser nula o vacia ", "CUSTOMER000");
		
	}

		/** Se valida si existe el cliente */
		customerRepository.findByCodigoCliente(customerRequest.getClienteID()).ifPresent(r -> {

			/** Se Lanza Excepción */
			throw new ConflictException("Ya existe el cliente " + customerRequest.getClienteID(), "CUSTOMER002");
		});

		CustomerEntity entity = CustomerEntity.builder()
				.clave(customerRequest.getClave())
				.codigoCliente(customerRequest.getClienteID())
				.estado(CustomerStatus.fromStatus(customerRequest.getEstado()))
				.person(PersonEntity.builder()
						.direccion(customerRequest.getDireccion())
						.edad(customerRequest.getEdad())
						.genero(customerRequest.getGenero())
						.identificacion(customerRequest.getIdentificacion())
						.nombre(customerRequest.getNombre())
						.telefono(customerRequest.getTelefono())
						.build())
				.build();

		entity = customerRepository.save(entity);
		
		return CustomerResponse.builder()
				.id(entity.getId())
				.clienteID(entity.getCodigoCliente())
				.estado(entity.getEstado().getStatus())
				.nombre(entity.getPerson().getNombre())
				.direccion(entity.getPerson().getDireccion())
				.edad(entity.getPerson().getEdad())
				.genero(entity.getPerson().getGenero())
				.identificacion(entity.getPerson().getIdentificacion())
				.telefono(entity.getPerson().getTelefono())
				.build();
	}

	@Override
	public CustomerResponse updateCustomer(CustomerRequest customerRequest, Long id) {

		//Se valida que exista el cliente
		CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(() -> 
			new ConflictException("No existe el cliente a modificar", "CUSTOMER003"));
		
		//Se valida el cambio del id del cliente
		if(!customerRequest.getClienteID().equals(customerEntity.getCodigoCliente())) {
			
			/** Se valida si existe el cliente */
			customerRepository.findByCodigoCliente(customerRequest.getClienteID()).ifPresent(r -> {

				/** Se Lanza Excepción */
				throw new ConflictException(String.format("El código cliente %s ya está asignado", customerRequest.getClienteID()),
						"CUSTOMER004");
			});
		}
		
		customerEntity.setCodigoCliente(customerRequest.getClienteID());
		customerEntity.setEstado(CustomerStatus.fromStatus(customerRequest.getEstado()));
		
		PersonEntity personDetail = PersonEntity.builder()
				.direccion(customerRequest.getDireccion())
				.edad(customerRequest.getEdad())
				.genero(customerRequest.getGenero())
				.identificacion(customerRequest.getIdentificacion())
				.nombre(customerRequest.getNombre())
				.telefono(customerRequest.getTelefono())
				.build();
		
		customerEntity.setPerson(personDetail);
		
		
		customerEntity = customerRepository.save(customerEntity);
			
		return CustomerResponse.builder()
		        .clienteID(customerEntity.getCodigoCliente())
		        .direccion(customerEntity.getPerson().getDireccion())
		        .edad(customerEntity.getPerson().getEdad())
		        .estado(customerEntity.getEstado().getStatus())
		        .genero(customerEntity.getPerson().getGenero())
		        .id(customerEntity.getId())
		        .identificacion(customerEntity.getPerson().getIdentificacion())
		        .nombre(customerEntity.getPerson().getNombre())
		        .telefono(customerEntity.getPerson().getTelefono())
				.build();
	}

	@Override
	public void deleteCustomer(Long id) {
		
		//Se valida que exista el cliente
				CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(() -> 
					new ConflictException("No existe el cliente a desactivar", "CUSTOMER003"));
				
         customerEntity.setEstado(CustomerStatus.FALSE);
         
         customerRepository.save(customerEntity);
	}
}
