package org.example.ws.service;

import org.example.ws.model.Training;
import org.example.ws.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by maja on 06.09.16.
 */

@Service
@Transactional(
        propagation = Propagation.SUPPORTS,
        readOnly = true)
public class TrainingServiceBean implements TrainingService {



   @Autowired
   private TrainingRepository trainingRepository;

    @Override
    public Collection<Training> findAll() {

        Collection<Training> trainings = trainingRepository.findAll();
        return trainings;
    }

    @Override
    public Training findOne(Long id) {
        Training training = trainingRepository.findOne(id);
        return training;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
    readOnly = false)
    public Training create(Training training) {

       if(training.getId() != null){
         //cannot create a training with specified ID value
           return null;
       }
        Training savedTraining = trainingRepository.save(training);
        return savedTraining;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            readOnly = false)
    public Training update(Training training) {
       Training trainingPersisted = findOne(training.getId());
       if(trainingPersisted ==  null){
           //cannot update training that doesn't exist
        return null;
       }
        Training updatedTraining = trainingRepository.save(training);
        return updatedTraining;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            readOnly = false)
    public void delete(Long id) {
       trainingRepository.delete(id);
    }
}
