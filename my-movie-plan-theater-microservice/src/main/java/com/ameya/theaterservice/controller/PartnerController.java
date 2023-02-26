package com.ameya.theaterservice.controller;

import com.ameya.theaterservice.dto.PartnerDto;
import com.ameya.theaterservice.service.PartnerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/partners")
public class PartnerController {

	@Autowired
	private PartnerService partnerService;
	
	@Autowired
	private Environment env;
	
	@PostMapping
	public ResponseEntity<PartnerDto> addPartner(@RequestBody PartnerDto dto){
		
		RestTemplate rt = new RestTemplate();
		
		rt.postForEntity(env.getProperty("add.partner.url"), dto, PartnerDto.class);
		
		return ResponseEntity.ok(partnerService.createPartner(dto));
	}
}
