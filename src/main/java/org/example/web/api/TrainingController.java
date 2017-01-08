package org.example.web.api;

import org.example.ws.model.Block;
import org.example.ws.model.Training;
import org.example.ws.model.User;
import org.example.ws.service.BlockService;
import org.example.ws.service.TrainingService;
import org.example.ws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

/**
 * Created by maja on 30.08.16.
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Controller
public class TrainingController {


    @Autowired
    private TrainingService trainingService;
    @Autowired
    private BlockService blockService;
    @Autowired
    private UserService userService;

    @RequestMapping(
            value = "/allTrainings",
            method = RequestMethod.GET
    )
    public String getTrainings(Model model){

        Collection<Training> trainings = trainingService.findAll();
        model.addAttribute("trainings", trainings);

        return "/allTrainings";
    }

    @RequestMapping(
            value = "/training/{id}",
            method = RequestMethod.GET
    )
    public String getTraining(Model model, @PathVariable("id") Long id, Principal principal) {
        Training training = trainingService.findOne(id);
        model.addAttribute("training", training);
        String currentUser = principal.getName();
        if (training.getTrainer().equals(currentUser)) {
            return "/training/trainingResult";
        } else {
            return "/training/trainingResultForViewOnly";
        }
    }

    @RequestMapping(value = "/form/{userId}",
            method = RequestMethod.GET)
    public String getCreateTrainingView(@ModelAttribute Training training, Model model, @PathVariable("userId") Long userId){
        User user  = userService.findUserById(userId);

        Collection<Block> blocks = blockService.findAll();

        model.addAttribute("user", user);
        model.addAttribute("blocks", blocks);
        return "/form";
    }


    @RequestMapping(
            value = "/form",
            method = RequestMethod.POST
    )
    public String handleCreateTrainingForm(Training training, Model model){
        String userEmail = training.getTrainer();
        User user = userService.findUserByEmail(userEmail);
        //user.getRoles().add("ROLE_OWNER");
        userService.update(user);
        training.setUser(user);
        Training savedTraining = trainingService.create(training);
        userService.addTrainingToUser(user, training);
        model.addAttribute("training", savedTraining);
        return "/training/trainingResult";

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

        return "redirect:/training/"+trainingId;
    };

    @RequestMapping(
            value = "/update/{id}",
            method = RequestMethod.GET
    )
    public String getUpdateTrainingView(Model model, @PathVariable("id") Long id){
        Training updatedTraining = trainingService.findOne(id);
        model.addAttribute("training", updatedTraining);
        return "/training/updateTraining";    }

    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    public String handleUpdateTrainingForm(Training training, Model model){

        Training updatedTraining = trainingService.update(training);
        model.addAttribute("training", updatedTraining);
        if(updatedTraining == null){
            return "There's no such training session!";
        }
        return "/training/trainingResult";
    }

    @RequestMapping(
            value = "/delete/{id}",
            method = RequestMethod.GET
    )
    public String getDeleteTrainingView(@PathVariable("id") Long id, Model model){
        Training deleteTraining = trainingService.findOne(id);
        model.addAttribute("training", deleteTraining);

        return "/training/deleteForm";
    }

    @RequestMapping(
            value = "/delete",
            method = RequestMethod.POST
    )
    public String handleDeleteTrainingForm(Training training, Model model, Principal principal){
         Long id = training.getId();
         trainingService.delete(id);
        Collection<Training> trainings = trainingService.findAll();
        model.addAttribute("trainings", trainings);

       User currentUser = userService.findUserByEmail(principal.getName());
       model.addAttribute("user", currentUser);
       List<String> roles = currentUser.getRoles();
        if(roles.contains("ADMIN")){
            return "/admin/adminHomePage";
        }else {
            return "/allTrainings";
        }
    }


    @RequestMapping(
            value = "/principal",
            method = RequestMethod.GET
    )
    public String myTrainings(Model model, Principal principal){

        return "/myTrainings";
    }

    @RequestMapping(
            value = "/search",
            method = RequestMethod.POST
    )
    public String handleFindTrainingsByTagFom(Model model, String tag){
        System.out.println(tag);

        Collection<Training> trainings = trainingService.findByTags(tag);
        model.addAttribute("trainings", trainings);
        return "/searchResult";
    }


    @RequestMapping(
            value = "/saveChanges/{id}",
            method = RequestMethod.POST
    )
    public String changeBlocksOrderInTraining(@RequestBody List<Block> blockList, Model model, @PathVariable("id") Long trainingId){
        Training training = trainingService.findOne(trainingId);
        training.setBlocks(blockList);
        trainingService.update(training);

        model.addAttribute("blocks", blockList);
        model.addAttribute("training", training);
        return "/training/trainingResult";
    }
}