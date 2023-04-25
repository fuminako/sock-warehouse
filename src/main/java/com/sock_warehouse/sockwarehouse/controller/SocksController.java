package com.sock_warehouse.sockwarehouse.controller;

import com.sock_warehouse.sockwarehouse.constant.ComparisonOperators;
import com.sock_warehouse.sockwarehouse.dto.SocksDto;
import com.sock_warehouse.sockwarehouse.service.SocksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/socks")
public class SocksController {

    private final SocksService service;

    private static final Logger logger = LoggerFactory.getLogger(SocksService.class);

    public SocksController(SocksService service) {
        this.service = service;
    }

    @Operation(summary = "Регистрирация прихода носков на склад", operationId = "socksIncome",
            responses = {@ApiResponse(responseCode = "200", description = "Удалось добавить приход",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SocksDto.class)
                    )),
                    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
                    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")})
    @PostMapping("/income")
    public ResponseEntity<SocksDto> socksIncome(@RequestBody SocksDto socksDto) {
        logger.info("Контроллер - socksIncome");
        return ResponseEntity.ok(service.socksIncome(socksDto));
    }

    @Operation(summary = "Регистрирация отпуска носков со склада", operationId = "socksOutcome",
            responses = {@ApiResponse(responseCode = "200", description = "Удалось уменьшить приход",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SocksDto.class)
                    )),
                    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
                    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")})
    @PostMapping("/outcome")
    public ResponseEntity<SocksDto> socksOutcome(@RequestBody SocksDto socksDto) {
        logger.info("Контроллер - socksOutcome");
        return ResponseEntity.ok(service.socksOutcome(socksDto));
    }

    @Operation(summary = "Общее количество носков на складе, соответствующих переданным в параметрах критериям запроса",
            operationId = "getSocks",
            responses = {@ApiResponse(responseCode = "200", description = "Запрос выполнен",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SocksDto.class)
                    )),
                    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
                    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")})
    @GetMapping
    public ResponseEntity<String> getSocks(@RequestParam String color,
                                           @RequestParam ComparisonOperators operation,
                                           @RequestParam int cottonPart) {
        logger.info("Контроллер - getSocks");
        return ResponseEntity.ok(service.getSocks(color, operation, cottonPart));
    }
}
