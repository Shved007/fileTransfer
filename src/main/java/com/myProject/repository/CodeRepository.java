package com.myProject.repository;

import com.myProject.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CodeRepository extends JpaRepository<Code,Long> {

    boolean existsByVerificationCode(String code);

    Optional<Code> findByVerificationCode(String code);

    List<Code> findByMessageId(Long id);

    List<Code> findByMessageIdIn(List<Long> ids);
    

}
