package com.restaurant.order.order_service.service;

import com.restaurant.order.order_service.exceptions.TableNotFoundException;
import com.restaurant.order.order_service.model.Table;
import com.restaurant.order.order_service.model.dtos.tables.TableUpdateRequestDTO;
import com.restaurant.order.order_service.model.enums.TableStatus;
import com.restaurant.order.order_service.model.dtos.tables.TableRequestDTO;
import com.restaurant.order.order_service.model.dtos.tables.TableResponseDTO;
import com.restaurant.order.order_service.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TableService {

    private final TableRepository tableRepository;
    private final ModelMapper modelMapper;

    public TableResponseDTO createTable(TableRequestDTO tableRequestDTO) {
        Table table = modelMapper.map(tableRequestDTO, Table.class);
        tableRepository.save(table);
        return modelMapper.map(table, TableResponseDTO.class);
    }

    public TableResponseDTO updateTable(UUID tableId, TableUpdateRequestDTO tableUpdateRequestDTO) {
        Table table = tableRepository.findById(tableId)
                .orElseThrow(() -> new TableNotFoundException(tableId));

        Optional.of(tableUpdateRequestDTO.getNumber())
                .ifPresent(table::setNumber);

        Optional.of(tableUpdateRequestDTO.getCapacity())
                .ifPresent(table::setCapacity);

        tableRepository.save(table);
        return modelMapper.map(table, TableResponseDTO.class);
    }

    public void updateTableStatus(UUID tableId, TableStatus tableStatus) {
        Table table = tableRepository.findById(tableId)
                .orElseThrow(() -> new TableNotFoundException(tableId));
        table.setStatus(tableStatus);
        tableRepository.save(table);
    }

    public List<TableResponseDTO> getAllTables() {
        return tableRepository.findAll().stream()
                .map(table -> modelMapper.map(table, TableResponseDTO.class))
                .toList();
    }

    public TableResponseDTO getTableById(UUID tableId) {
        Table table = tableRepository.findById(tableId)
                .orElseThrow(() -> new TableNotFoundException(tableId));
        return modelMapper.map(table, TableResponseDTO.class);
    }

    public void deleteTable(UUID tableId) {
        Table table = tableRepository.findById(tableId)
                .orElseThrow(() -> new TableNotFoundException(tableId));
        tableRepository.delete(table);
    }
}
