package com.jfloresl.usuarios.repository;
import java.util.List;
import java.util.UUID;

import com.jfloresl.usuarios.entities.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jfloresl.usuarios.entities.User;
@Repository
public interface PhoneRepository extends JpaRepository<Phone,Long>{
    List<Phone> findByUserId(UUID id);
}
