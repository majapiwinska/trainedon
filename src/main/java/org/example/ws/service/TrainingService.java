package org.example.ws.service;

import org.example.ws.model.Block;
import org.example.ws.model.Training;

import java.util.Collection;

/**
 * Created by maja on 06.09.16.
 */
public interface TrainingService {

    Collection<Training> findAll();

    Training findOne(Long id);

    Training create(Training training);

    Training update(Training training);

    void delete(Long id);

    Training addBlockToTraining(Block block, Training training);
}
