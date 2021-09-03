package com.crasoftinc.jmatrailers.repository;

import com.crasoftinc.jmatrailers.data.TrailersEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

public interface TrailersRepository extends PagingAndSortingRepository<TrailersEntity, String>, MongoRepository<TrailersEntity, String> {
}