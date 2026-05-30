package com.empresa.members.repository;

import com.empresa.members.enums.CargoMembro;
import com.empresa.members.model.Membro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembroRepository extends JpaRepository<Membro, Long> {
    List<Membro> findByCargo(CargoMembro cargo);
}
