package org.example.web.api;

import org.example.ws.model.Block;
import org.example.ws.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

/**
 * Created by maja on 07.09.16.
 */
@Controller
public class BlockController {

    @Autowired
    private BlockService blockService;

    @RequestMapping(
            value = "/blocks",
            method = RequestMethod.GET
            )
    public String getBlocks(Model model){
        Collection<Block> blocks = blockService.findAll();
        model.addAttribute("blocks", blocks);
        return "/block/blocks";
            }

    @RequestMapping(
            value = "/blocks/{id}",
            method = RequestMethod.GET
    )
    public String getBlock(Model model, @PathVariable("id") Long id){
        Block block = blockService.findOne(id);
        model.addAttribute("block", block );
        return "/block/blockresult";
    }

    @RequestMapping(
            value = "/blockForm",
            method = RequestMethod.GET
    )
    public String getCreateBlockView(@ModelAttribute Block block){

        return "/block/blockForm";
    }

    @RequestMapping(
            value = "/blockForm",
            method = RequestMethod.POST
    )
    public String handleCreateBlockForm(Block block, Model model){

        Block savedBlock= blockService.create(block);
        model.addAttribute("block", savedBlock);
        return "/block/blockresult";

    }



    @RequestMapping(
            value = "/update/block/{id}",
            method = RequestMethod.GET
    )
    public String getUdateBlockView(Model model, @PathVariable("id") Long id){
        Block updatedBlock = blockService.findOne(id);
        model.addAttribute("block", updatedBlock);
        return "/block/update_block";


    }

    @RequestMapping(
            value = "/update_block",
            method = RequestMethod.POST
    )
    public String handleUpdateBlockForm(Block block, Model model){

        Block updatedBlock = blockService.update(block);
        model.addAttribute("block", updatedBlock);
        if(updatedBlock == null){
            return "There's no such training session!";
        }
        return "/block/blockresult";
    }

    @RequestMapping(
            value = "/delete/block/{id}",
            method = RequestMethod.GET
    )
    public String getDeleteBlockView(@PathVariable("id") Long id, Model model){
        Block deleteBlock = blockService.findOne(id);
        model.addAttribute("block", deleteBlock);

        return "/block/delete_block_form";
    }

    @RequestMapping(
            value = "/delete_block",
            method = RequestMethod.DELETE
    )
    public String handleDeleteBlockForm(Block block, Model model){
        Long id = block.getId();
        blockService.delete(id);
        Collection<Block> blocks = blockService.findAll();
        model.addAttribute("blocks", blocks);
        return "/block/blocks";
    }

}
