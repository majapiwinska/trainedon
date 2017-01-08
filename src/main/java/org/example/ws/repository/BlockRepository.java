package org.example.ws.repository;

import org.example.ws.model.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by maja on 07.09.16.
 */

@Repository
@Transactional
public interface BlockRepository extends JpaRepository<Block, Long>{
}
