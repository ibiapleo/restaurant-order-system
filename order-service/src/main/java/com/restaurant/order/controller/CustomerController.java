package com.restaurant.order.controller;

import com.restaurant.order.model.dtos.customers.CustomerRequestDTO;
import com.restaurant.order.model.dtos.customers.CustomerResponseDTO;
import com.restaurant.order.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/customer")
@Tag(name = "Customers", description = "Serviço do Cliente - Gerenciamento de Clientes")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    @Operation(summary = "Lista todos os clientes cadastrados")
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {
        List<CustomerResponseDTO> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lista um cliente pelo id")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable UUID id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PostMapping
    @Operation(summary = "Cria um cliente")
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) {
        CustomerResponseDTO customer = customerService.createCustomer(customerRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza os dados de um cliente", description = "Atualiza os dados de um cliente")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(@PathVariable UUID id, @RequestBody CustomerRequestDTO customerRequestDTO) {
        CustomerResponseDTO customer = customerService.updateCustomer(id, customerRequestDTO);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um cliente")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
