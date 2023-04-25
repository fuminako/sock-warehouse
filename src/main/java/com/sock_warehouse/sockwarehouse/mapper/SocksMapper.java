package com.sock_warehouse.sockwarehouse.mapper;

import com.sock_warehouse.sockwarehouse.dto.SocksDto;
import com.sock_warehouse.sockwarehouse.entity.Sock;

public class SocksMapper {

    public static SocksDto toDto(Sock sock) {
        return new SocksDto(sock.getId(), sock.getCottonPart(), sock.getColor(), sock.getQuantity());
    }

    public static Sock toEntity(SocksDto socksDto) {
        return new Sock(socksDto.getId(), socksDto.getCottonPart(), socksDto.getColor(), socksDto.getQuantity());
    }
}
