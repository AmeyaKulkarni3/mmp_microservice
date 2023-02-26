package com.ameya.schedulemicroservice.controller;

import com.ameya.schedulemicroservice.dto.PartnerDto;
import com.ameya.schedulemicroservice.service.PartnerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/partners")
public class PartnerController {

	@Autowired
	private PartnerService partnerService;
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<PartnerDto> addPartner(@RequestBody PartnerDto dto){
		
		return ResponseEntity.ok(partnerService.createPartner(dto));
	}
}
