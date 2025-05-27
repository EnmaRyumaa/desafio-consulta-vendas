package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.lang.annotation.Native;
import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("""
    SELECT new com.devsuperior.dsmeta.dto.SaleMinDTO(
        s.id, s.date, s.amount, s.seller.name)
    FROM Sale s
    WHERE s.date BETWEEN :minDate AND :maxDate
      AND LOWER(s.seller.name) LIKE LOWER(CONCAT('%', :name, '%'))
    """)
    Page<SaleMinDTO> getReport(@Param("minDate") LocalDate minDate,
                               @Param("maxDate") LocalDate maxDate,
                               @Param("name") String name,
                               Pageable pageable);
    @Query("""
    SELECT new com.devsuperior.dsmeta.dto.SaleMinDTO(
        s.id, s.date, s.amount, s.seller.name)
    FROM Sale s 
    WHERE s.date BETWEEN :minDate AND :maxDate 
    """)
    Page<SaleMinDTO> getReport(@Param("minDate") LocalDate minDate,
                         @Param("maxDate") LocalDate maxDate,
                         Pageable pageable);


    List<SaleMinDTO> getSummary(@Param("minDate") LocalDate minDate,
                               @Param("maxDate") LocalDate maxDate);

    List<SaleMinDTO> getSummary();

}
