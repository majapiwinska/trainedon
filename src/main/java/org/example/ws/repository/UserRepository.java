package org.example.ws.repository;

import org.example.ws.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by maja on 19.10.16.
 */

@Repository
@Transactional
public interface UserRepository  extends JpaRepository<User, Long> {
    User getUserByEmail(String email);
}