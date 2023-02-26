package com.ameya.schedulemicroservice.service.impl;

import java.util.ArrayList;

import com.ameya.schedulemicroservice.dto.PartnerDto;
import com.ameya.schedulemicroservice.entity.Partner;
import com.ameya.schedulemicroservice.repository.PartnerRepository;
import com.ameya.schedulemicroservice.service.PartnerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartnerServiceImpl implements PartnerService {
	
	@Autowired
	private PartnerRepository partnerRepository;

	@Override
	public PartnerDto createPartner(PartnerDto dto) {
		
		Partner partner = new Partner();
		partner.setName(dto.getName());
		partner.setTheaters(new ArrayList<>());
		
		Partner saved = partnerRepository.save(partner);
		
		PartnerDto returnValue = new PartnerDto();
		returnValue.setId(saved.getId());
		returnValue.setName(saved.getName());
		
		return returnValue;
	}

}
