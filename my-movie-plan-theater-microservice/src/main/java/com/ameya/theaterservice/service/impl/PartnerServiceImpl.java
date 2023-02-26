package com.ameya.theaterservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ameya.theaterservice.dto.PartnerDto;
import com.ameya.theaterservice.dto.TheaterDto;
import com.ameya.theaterservice.entity.Partner;
import com.ameya.theaterservice.entity.Theater;
import com.ameya.theaterservice.repository.PartnerRepository;
import com.ameya.theaterservice.service.PartnerService;

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
