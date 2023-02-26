package com.ameya.schedulemicroservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ameya.schedulemicroservice.dto.TierDto;
import com.ameya.schedulemicroservice.entity.Tier;
import com.ameya.schedulemicroservice.exception.ExceptionConstants;
import com.ameya.schedulemicroservice.exception.tier.NoSuchTierException;
import com.ameya.schedulemicroservice.exception.tier.TierAlreadyExistsException;
import com.ameya.schedulemicroservice.repository.TierRepository;
import com.ameya.schedulemicroservice.service.TierService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@PropertySource("classpath:TierValidationMessages.properties")
@Transactional
public class TierServiceImpl implements TierService {

	@Autowired
	TierRepository tierRepository;

	@Autowired
	Environment env;

	@Override
	public TierDto addTier(TierDto tier) throws TierAlreadyExistsException {

		Tier saved = tierRepository.findByName(tier.getName());
		if (saved != null) {
			throw new TierAlreadyExistsException(env.getProperty(ExceptionConstants.TIER_ALREADY_EXISTS.toString()));
		}
		Tier t = new Tier();
		t.setName(tier.getName());
		t.setPrice(tier.getPrice());
		t.setPriority(tier.getPriority());
		t.setNoOfSeats(tier.getNoOfSeats());
		t.setRows(tier.getRows());
		t.setCols(tier.getCols());
		t.setSeatsBooked(tier.getSeatsBooked());

		Tier savedTier = tierRepository.save(t);

		TierDto dto = new TierDto();
		dto.setId(savedTier.getId());
		dto.setName(savedTier.getName());
		dto.setPrice(savedTier.getPrice());
		dto.setPriority(savedTier.getPriority());
		dto.setNoOfSeats(savedTier.getNoOfSeats());
		dto.setRows(savedTier.getRows());
		dto.setCols(savedTier.getCols());
		dto.setSeatsBooked(savedTier.getSeatsBooked());

		return dto;
	}
	
	private TierDto dataTransfer(Tier t) {
		TierDto dto = new TierDto();
		dto.setId(t.getId());
		dto.setName(t.getName());
		dto.setPrice(t.getPrice());
		dto.setPriority(t.getPriority());
		dto.setNoOfSeats(t.getNoOfSeats());
		dto.setRows(t.getRows());
		dto.setCols(t.getCols());
		dto.setSeatsBooked(t.getSeatsBooked());
		return dto;
	}

	@Override
	public TierDto getTierById(int id) throws NoSuchTierException {

		Tier saved = tierRepository.findById(id).orElseThrow(
				() -> new NoSuchTierException(env.getProperty(ExceptionConstants.TIER_NOT_FOUND.toString())));
		
		TierDto dto = dataTransfer(saved);
		
		return dto;
	}

	@Override
	public List<TierDto> getAllTier() {
		
		List<Tier> tiers = (List<Tier>) tierRepository.findAll();
		
		List<TierDto> dtos = new ArrayList<>();
		
		for(Tier t : tiers) {
			TierDto dto = dataTransfer(t);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public TierDto updateTier(TierDto tierDto) throws NoSuchTierException {
		
		Tier saved = tierRepository.findById(tierDto.getId()).orElseThrow(
				() -> new NoSuchTierException(env.getProperty(ExceptionConstants.TIER_NOT_FOUND.toString())));
		
		if(tierDto.getName() != null && saved.getName() != tierDto.getName()) {
			saved.setName(tierDto.getName());
		}
		if(tierDto.getPrice() != 0 && tierDto.getPrice() != saved.getPrice()) {
			saved.setPrice(tierDto.getPrice());
		}
		if(tierDto.getPriority() != 0 && tierDto.getPriority() != saved.getPriority()) {
			saved.setPriority(tierDto.getPriority());
		}
		if(tierDto.getNoOfSeats() != 0 && tierDto.getNoOfSeats() != saved.getNoOfSeats()) {
			saved.setNoOfSeats(tierDto.getNoOfSeats());
		}
		
		Tier updated = tierRepository.save(saved);
		
		TierDto dto = dataTransfer(updated);
		
		return dto;
	}

	@Override
	public String deleteTier(int id) throws NoSuchTierException {
		
		Tier saved = tierRepository.findById(id).orElseThrow(
				() -> new NoSuchTierException(env.getProperty(ExceptionConstants.TIER_NOT_FOUND.toString())));
		tierRepository.delete(saved);
		return "Tier Deleted Successfully!";
	}

	@Override
	public String addAllTiers(List<TierDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

}
