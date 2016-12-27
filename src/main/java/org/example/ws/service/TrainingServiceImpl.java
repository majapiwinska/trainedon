package org.example.ws.service;

import org.example.ws.model.Block;
import org.example.ws.model.Training;
import org.example.ws.repository.BlockRepository;
import org.example.ws.repository.TrainingRepository;
import org.example.ws.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

/**
 * Created by maja on 06.09.16.
 */

@Service
@Transactional(
        propagation = Propagation.SUPPORTS,
        readOnly = true)
public class TrainingServiceImpl implements TrainingService {

   @Autowired
   private BlockRepository blockRepository;

    @Autowired
    private UserRepository userRepository;
   //
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

    @Override
    @Transactional (propagation = Propagation.REQUIRED,
            readOnly = false)
    public Training addBlockToTraining(Block block, Training training){
     //check if block exists
        if(blockRepository.findOne(block.getId()) == null || trainingRepository.findOne(training.getId()) == null)
            return null; // zle i do poprawy API DESIGN
        training.getBlocks().add(block);
        return trainingRepository.save(training);
    }


    @Override
    @Transactional (propagation = Propagation.REQUIRED,
            readOnly = false)
    public List<Training> findTrainingsByUserId(Principal principal) {
        String tmpName = principal.getName();
    return null;
    }

    @Override
    public Collection<Training> findByTags(String tag) {
        Collection<Training> trainings = trainingRepository.findByTags(tag);
        if(trainings == null) {
            System.out.print("There's no training tagged with " + tag);

        }
        return trainings;
    }


}
