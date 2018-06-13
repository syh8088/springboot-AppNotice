package com.example.api.repositories;

import com.example.api.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findBySportsIdAndIdNotInAndStartDatetimeBetweenOrderByIdDesc(long sportsId, Long[] exceptIds, LocalDateTime searchStartDateTime, LocalDateTime searchEndDateTime);

}
