package com.br.moviesbattle.repository;

import com.br.moviesbattle.model.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {

    Game findByUserIdAndIsFinishFalse(Long id);

    Game findByUserIdAndIdAndIsFinishFalse(Long userId, Long id);
}
