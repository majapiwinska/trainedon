package org.example.web.api;

import org.example.ws.model.Block;
import org.example.ws.model.Training;
import org.example.ws.service.BlockService;
import org.example.ws.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by maja on 30.08.16.
 */
@Controller
public class TrainingController {


    @Autowired
    private TrainingService trainingService;
    @Autowired
    private BlockService blockService;


    @RequestMapping(
            value = "/api/trainings",
            method = RequestMethod.GET
    )
    public String getTrainings(Model model){

        Collection<Training> trainings = trainingService.findAll();
        model.addAttribute("trainings", trainings);

        return "index";
    }

    @RequestMapping(
            value = "/form/{id}",
            method = RequestMethod.GET
    )
    public String getTraining(Model model, @PathVariable("id") Long id){
        Training training = trainingService.findOne(id);
        model.addAttribute("training", training);
        return "trainingresult";

    }

    @RequestMapping(value = "/form",
            method = RequestMethod.GET)
    public String createTrainingView(@ModelAttribute Training training, Model model){

        Collection<Block> blocks = blockService.findAll();
        model.addAttribute("blocks", blocks);
        return "form";    }


    @RequestMapping(
            value = "/form",
            method = RequestMethod.POST
    )
    public String createTraining(Training training, Model model){

        Training savedTraining = trainingService.create(training);

        model.addAttribute("training", savedTraining);
        return "trainingresult";

    }

    @RequestMapping(
            value = "/new_block/{trainingId}",
            method = RequestMethod.POST
    )
    public String createBlockAndAddToTraining(Block block, Model model, @PathVariable("trainingId") Long trainingId){
        Block savedBlock = blockService.create(block);
        Training training = trainingService.findOne(trainingId);
        Training savedTraining = trainingService.addBlockToTraining(block, training);

        model.addAttribute("block", savedBlock);
        model.addAttribute("training", savedTraining);

        return "redirect:/form/"+trainingId;
    };

    @RequestMapping(
            value = "/update/{id}",
            method = RequestMethod.GET
    )
    public String updateTrainingView(Model model, @PathVariable("id") Long id){
        Training updatedTraining = trainingService.findOne(id);
        model.addAttribute("training", updatedTraining);
        return "updateform";    }

    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    public String updateTraining(Training training, Model model){

        Training updatedTraining = trainingService.update(training);
        model.addAttribute("training", updatedTraining);
        if(updatedTraining == null){
            return "There's no such training session!";
        }
        return "trainingresult";
    }

    @RequestMapping(
            value = "/delete/{id}",
            method = RequestMethod.GET
    )
    public String deleteForm(@PathVariable("id") Long id, Model model){
        Training deleteTraining = trainingService.findOne(id);
        model.addAttribute("training", deleteTraining);

        return "deleteform";
    }

    @RequestMapping(
            value = "/delete",
            method = RequestMethod.DELETE
    )
    public String deleteTraining(Training training, Model model){
         Long id = training.getId();
         trainingService.delete(id);
        Collection<Training> trainings = trainingService.findAll();
        model.addAttribute("trainings", trainings);
        return "index";
    }



}