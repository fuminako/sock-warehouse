package com.sock_warehouse.sockwarehouse.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SocksDto {

    private long id;

    private int cottonPart;

    private String color;

    private int quantity;
}
