package org.example.ws.service;

import org.example.ws.model.Block;

import java.util.Collection;

/**
 * Created by maja on 07.09.16.
 */
public interface BlockService {

    Block findOne(Long id);

    Collection<Block> findAll();

    Block create(Block block);

    Block update(Block block);

    void delete(Long id);
}
