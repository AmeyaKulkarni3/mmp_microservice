package com.ameya.schedulemicroservice.service;

import java.util.List;

import com.ameya.schedulemicroservice.dto.TierDto;
import com.ameya.schedulemicroservice.exception.tier.NoSuchTierException;
import com.ameya.schedulemicroservice.exception.tier.TierAlreadyExistsException;

public interface TierService {
	
	TierDto addTier(TierDto tier) throws TierAlreadyExistsException;
	
	TierDto getTierById(int id) throws NoSuchTierException;
	
	List<TierDto> getAllTier();

	TierDto updateTier(TierDto tierDto) throws NoSuchTierException;

	String deleteTier(int id) throws NoSuchTierException;
	
	String addAllTiers(List<TierDto> dtos);

}
