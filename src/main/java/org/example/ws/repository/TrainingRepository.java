package org.example.ws.repository;

import org.example.ws.model.Training;
import org.example.ws.model.User;
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
    Collection<Training> findByTrainer(String trainer);
    Collection<Training> findByUser(User user);
}
