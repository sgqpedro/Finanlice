package com.finanlice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.finanlice.model.ReceitaModel;


public interface ReceitaRepository extends JpaRepository<ReceitaModel, Long> {
}
