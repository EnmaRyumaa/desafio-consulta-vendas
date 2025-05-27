package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SellerMinDTO;
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

	public Page<SaleMinDTO> getReport(String minDateStr, String maxDateStr, String sellerName, Pageable pageable) {
		LocalDate maxDate = (maxDateStr != null) ?
				LocalDate.parse(maxDateStr) :
				LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

		LocalDate minDate = (minDateStr != null) ?
				LocalDate.parse(minDateStr) :
				maxDate.minusYears(1L);

		String name = (sellerName != null) ? sellerName : "";

		return repository.getReport(minDate, maxDate, name, pageable);
	}

	public List<SellerMinDTO> getSummary(String minDateStr, String maxDateStr) {
		LocalDate maxDate = (maxDateStr != null) ?
				LocalDate.parse(maxDateStr) :
				LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

		LocalDate minDate = (minDateStr != null) ?
				LocalDate.parse(minDateStr) :
				maxDate.minusYears(1L);

		return repository.getSummary(minDate, maxDate);
	}
}
