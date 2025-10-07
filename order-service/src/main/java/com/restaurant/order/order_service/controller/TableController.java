package com.restaurant.order.order_service.controller;

import com.restaurant.order.order_service.model.dtos.tables.TableRequestDTO;
import com.restaurant.order.order_service.model.dtos.tables.TableResponseDTO;
import com.restaurant.order.order_service.model.dtos.tables.TableUpdateRequestDTO;
import com.restaurant.order.order_service.model.enums.TableStatus;
import com.restaurant.order.order_service.service.TableService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/table")
@Tag(name = "Tables", description = "Serviço de Mesas - Gerenciamento de Mesas")
@RequiredArgsConstructor
public class TableController {

    private final TableService tableService;

    @GetMapping
    @Operation(summary = "Lista todas as mesas")
    public ResponseEntity<List<TableResponseDTO>> getTables() {
        List<TableResponseDTO> tables = tableService.getAllTables();
        return ResponseEntity.ok(tables);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lista uma mesa pelo id")
    public ResponseEntity<TableResponseDTO> getTable(@PathVariable UUID id) {
        return ResponseEntity.ok(tableService.getTableById(id));
    }

    @PostMapping
    @Operation(summary = "Cria uma mesa")
    public ResponseEntity<TableResponseDTO> createTable(@RequestBody TableRequestDTO tableRequest) {
        TableResponseDTO table = tableService.createTable(tableRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(table);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza os dados de uma mesa", description = "Atualiza os dados de uma mesa")
    public ResponseEntity<TableResponseDTO> updateTable(@PathVariable UUID id, @RequestBody TableUpdateRequestDTO tableUpdateRequestDTO) {
        TableResponseDTO table = tableService.updateTable(id, tableUpdateRequestDTO);
        return ResponseEntity.ok(table);
    }

    @PatchMapping("/status/{id}")
    @Operation(summary = "Atualiza o status de uma mesa")
    public ResponseEntity<Void> updateTableStatus(@PathVariable UUID id, @RequestBody TableStatus tableStatus) {
        tableService.updateTableStatus(id, tableStatus);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui uma mesa")
    public ResponseEntity<TableResponseDTO> deleteTable(@PathVariable UUID id) {
        tableService.deleteTable(id);
        return ResponseEntity.noContent().build();
    }
}
