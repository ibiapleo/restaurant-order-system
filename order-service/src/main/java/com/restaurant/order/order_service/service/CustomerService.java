package com.restaurant.order.order_service.service;

import com.restaurant.order.order_service.exceptions.CustomerNotFoundException;
import com.restaurant.order.order_service.model.Customer;
import com.restaurant.order.order_service.model.dtos.customers.CustomerRequestDTO;
import com.restaurant.order.order_service.model.dtos.customers.CustomerResponseDTO;
import com.restaurant.order.order_service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO) {
        Customer customer = modelMapper.map(customerRequestDTO, Customer.class);
        customerRepository.save(customer);
        return modelMapper.map(customer, CustomerResponseDTO.class);
    }

    public CustomerResponseDTO updateCustomer(UUID customerId, CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));

        Optional.ofNullable(customerRequestDTO.getName())
                .ifPresent(customer::setName);

        Optional.ofNullable(customerRequestDTO.getEmail())
                .ifPresent(customer::setEmail);

        Optional.ofNullable(customerRequestDTO.getPhone())
                .ifPresent(customer::setPhone);

        customerRepository.save(customer);
        return modelMapper.map(customer, CustomerResponseDTO.class);
    }

    public List<CustomerResponseDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customer -> modelMapper.map(customer, CustomerResponseDTO.class))
                .toList();
    }

    public CustomerResponseDTO getCustomerById(UUID customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
        return modelMapper.map(customer, CustomerResponseDTO.class);
    }

    public void deleteCustomer(UUID customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));

        customerRepository.delete(customer);
    }

}