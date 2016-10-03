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
        return "blocks";
            }

    @RequestMapping(
            value = "/blocks/{id}",
            method = RequestMethod.GET
    )
    public String getBlock(Model model, @PathVariable("id") Long id){
        Block block = blockService.findOne(id);
        model.addAttribute("block", block );
        return "blockresult";
    }

    @RequestMapping(
            value = "/block_form",
            method = RequestMethod.GET
    )
    public String createBlock(@ModelAttribute Block block){

        return "block_form";
    }

    @RequestMapping(
            value = "/block_form",
            method = RequestMethod.POST
    )
    public String createFormView(Block block, Model model){

        Block savedBlock= blockService.create(block);
        model.addAttribute("block", savedBlock);
        return "blockresult";

    }


    @RequestMapping(
            value = "/update/block/{id}",
            method = RequestMethod.GET
    )
    public String updateBlockForm(Model model, @PathVariable("id") Long id){
        Block updatedBlock = blockService.findOne(id);
        model.addAttribute("block", updatedBlock);
        return "update_block";


    }

    @RequestMapping(
            value = "/update_block",
            method = RequestMethod.POST
    )
    public String updateBlock(Block block, Model model){

        Block updatedBlock = blockService.update(block);
        model.addAttribute("block", updatedBlock);
        if(updatedBlock == null){
            return "There's no such training session!";
        }
        return "blockresult";
    }

    @RequestMapping(
            value = "/delete/block/{id}",
            method = RequestMethod.GET
    )
    public String deleteForm(@PathVariable("id") Long id, Model model){
        Block deleteBlock = blockService.findOne(id);
        model.addAttribute("block", deleteBlock);

        return "delete_block_form";
    }

    @RequestMapping(
            value = "/delete_block",
            method = RequestMethod.DELETE
    )
    public String deleteBlock(Block block, Model model){
        Long id = block.getId();
        blockService.delete(id);
        Collection<Block> blocks = blockService.findAll();
        model.addAttribute("blocks", blocks);
        return "blocks";
    }

}
