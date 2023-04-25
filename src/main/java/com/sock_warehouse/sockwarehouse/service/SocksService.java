package com.sock_warehouse.sockwarehouse.service;

import com.sock_warehouse.sockwarehouse.constant.ComparisonOperators;
import com.sock_warehouse.sockwarehouse.dto.SocksDto;
import com.sock_warehouse.sockwarehouse.entity.Sock;
import com.sock_warehouse.sockwarehouse.exceptions.IncorrectParamsException;
import com.sock_warehouse.sockwarehouse.exceptions.NotFoundException;
import com.sock_warehouse.sockwarehouse.mapper.SocksMapper;
import com.sock_warehouse.sockwarehouse.repository.SocksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SocksService {
    private final SocksRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(SocksService.class);

    public SocksService(SocksRepository repository) {
        this.repository = repository;
    }

    /**
     * @param socksDto {@link SocksDto} Input parameter
     *                 <br> Is used mapper {@link SocksMapper#toDto(Sock)} </br>
     *                 <br> Is used repository {@link SocksRepository#save(Object)} </br>
     *                 <br> Is used repository {@link SocksRepository#findSocksByColorAndCottonPart(String, int)} </br>
     */
    public SocksDto socksIncome(SocksDto socksDto) {
        Optional<Sock> socks = repository.findSocksByColorAndCottonPart(socksDto.getColor(), socksDto.getCottonPart());
        boolean sockCheck = socks.isPresent();
        Sock sock;
        if (sockCheck) {
            sock = socks.get();
            sock.setQuantity(sock.getQuantity() + socksDto.getQuantity());
        } else {
            sock = SocksMapper.toEntity(socksDto);
        }
        logger.info("Метод socksIncome выполнен");
        return SocksMapper.toDto(repository.save(sock));
    }

    /**
     * @param socksDto {@link SocksDto} Input parameter
     *                 <br> Is used mapper {@link SocksMapper#toDto(Sock)} </br>
     *                 <br> Is used repository {@link SocksRepository#save(Object)} </br>
     *                 <br> Is used repository {@link SocksRepository#findSocksByColorAndCottonPart(String, int)} </br>
     */
    public SocksDto socksOutcome(SocksDto socksDto) {
        Sock socks = repository.findSocksByColorAndCottonPart(socksDto.getColor(), socksDto.getCottonPart())
                .orElseThrow(() -> new NotFoundException("Not found"));
        int quantity = socks.getQuantity();
        if (quantity < socksDto.getQuantity()) {
            throw new IncorrectParamsException("Отпуск превышает количество носков на складе");
        }
        socks.setQuantity(socks.getQuantity() - socksDto.getQuantity());
        repository.save(socks);
        logger.info("Метод socksOutcome выполнен");
        return SocksMapper.toDto(socks);
    }

    /**
     * @param color      String color Input parameter
     * @param operation  {@link ComparisonOperators} Input parameter
     * @param cottonPart int cottonPart Input parameter
     *                   <br> Is used mapper {@link SocksMapper#toDto(Sock)} </br>
     *                   <br> Is used repository {@link SocksRepository#findQuantityOfSocksByColorAndCottonPartGreaterThanCurrentNumber(String, Integer)} </br>
     *                   <br> Is used repository {@link SocksRepository#findQuantityOfSocksByColorAndCottonPartEqualsToCurrentNumber(String, Integer)} </br>
     *                   <br> Is used repository {@link SocksRepository#findQuantityOfSocksByColorAndCottonPartLessThanCurrentNumber(String, Integer)} </br>
     */
    public String getSocks(String color, ComparisonOperators operation, int cottonPart) {
        logger.info("Вызван метод getSocks");
        if (color.isEmpty() || cottonPart < 0 || cottonPart > 100) {
            throw new IncorrectParamsException("Incorrect param");
        } else
            switch (operation) {
                case moreThan: {
                    return repository.findQuantityOfSocksByColorAndCottonPartGreaterThanCurrentNumber(color, cottonPart);
                }
                case lessThan: {
                    return repository.findQuantityOfSocksByColorAndCottonPartLessThanCurrentNumber(color, cottonPart);
                }
                case equal: {
                    return repository.findQuantityOfSocksByColorAndCottonPartEqualsToCurrentNumber(color, cottonPart);
                }
            }
        throw new IncorrectParamsException("Incorrect param");
    }
}
