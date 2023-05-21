package com.brodygaudel.gestioncomptes.query.repositories;

import com.brodygaudel.gestioncomptes.query.entities.Compter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompterRepository extends JpaRepository<Compter, Long> {
}
