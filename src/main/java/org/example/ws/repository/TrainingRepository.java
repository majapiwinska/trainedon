package org.example.ws.repository;

import org.example.ws.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by maja on 06.09.16.
 */

@Repository
@Transactional
public interface TrainingRepository extends JpaRepository<Training, Long> {

    Collection<Training> findByTags(String tags);
}
