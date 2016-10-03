package org.example.ws.service;

import org.example.ws.model.Block;
import org.example.ws.repository.BlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by maja on 07.09.16.
 */

@Service
@Transactional(
        propagation = Propagation.SUPPORTS,
        readOnly = true)
public class BlockServiceBean implements BlockService {

    @Autowired
    private BlockRepository blockRepository;


    @Override
    public Block findOne(Long id) {
        Block block= blockRepository.findOne(id);
        return block;
    }

    @Override
    public Collection<Block> findAll() {
        Collection<Block> blocks = blockRepository.findAll();
        return blocks;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            readOnly = false)
    public Block create(Block block) {
        if(block.getId() != null){
            return null;
        }
        Block savedBlock = blockRepository.save(block);
        return savedBlock;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            readOnly = false)
    public Block update(Block block) {
        Block blockPersisted = findOne(block.getId());
        if(blockPersisted ==  null){
            //cannot update training that doesn't exist
            return null;
        }
        Block updatedBlock = blockRepository.save(block);
        return updatedBlock;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            readOnly = false)
    public void delete(Long id) {
        blockRepository.delete(id);
    }
}
