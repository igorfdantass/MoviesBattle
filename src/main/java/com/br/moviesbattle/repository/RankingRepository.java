package com.br.moviesbattle.repository;

import com.br.moviesbattle.model.Ranking;
import org.springframework.data.repository.CrudRepository;

public interface RankingRepository extends CrudRepository<Ranking, Long> {

    Iterable<Ranking> findTop10ByOrderByTotalPointDesc();
}
