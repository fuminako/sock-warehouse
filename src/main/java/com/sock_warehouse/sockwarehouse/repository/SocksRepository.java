package com.sock_warehouse.sockwarehouse.repository;

import com.sock_warehouse.sockwarehouse.entity.Sock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocksRepository extends JpaRepository<Sock, Long> {
    Optional<Sock> findSocksByColorAndCottonPart(String color, int cottonPart);

    @Query("SELECT SUM(s.quantity) FROM Sock s WHERE s.color = :color and s.cottonPart < :cottonPart")
    String findQuantityOfSocksByColorAndCottonPartLessThanCurrentNumber(@Param("color") String color,
                                                                     @Param("cottonPart") Integer cottonPart);

    @Query("SELECT SUM(s.quantity) FROM Sock s WHERE s.color = :color and s.cottonPart > :cottonPart")
    String findQuantityOfSocksByColorAndCottonPartGreaterThanCurrentNumber(@Param("color") String color,
                                                                        @Param("cottonPart") Integer cottonPart);

    @Query("SELECT SUM(s.quantity) FROM Sock s WHERE s.color = :color and s.cottonPart = :cottonPart")
    String findQuantityOfSocksByColorAndCottonPartEqualsToCurrentNumber(@Param("color") String color,
                                                                     @Param("cottonPart") Integer cottonPart);
}
