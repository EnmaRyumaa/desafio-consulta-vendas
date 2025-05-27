package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleMinDTO> getReport(LocalDate minDate, LocalDate maxDate, String name, Pageable page) {
		return repository.getReport(minDate, maxDate, name, page);
	}

	public Page<SaleMinDTO> getReport(Pageable page) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate minDate = today.minusYears(1L);
		return repository.getReport(minDate, today, page);
	}

	public List<SaleMinDTO> getSummary(LocalDate minDate, LocalDate maxDate) {
		return repository.getSummary(minDate, maxDate);
	}

	public List<SaleMinDTO> getSummary() {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate minDate = today.minusYears(1L);
		return repository.getSummary(minDate, today);
	}
}
