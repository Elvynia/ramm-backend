package org.arcanic.ramm.controller;

import org.arcanic.ramm.document.Bubble;
import org.arcanic.ramm.document.Memory;
import org.arcanic.ramm.repository.BubbleRepository;
import org.arcanic.ramm.service.MemoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/memory")
public class MemoryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MemoryController.class);

	@Autowired
	private MemoryService service;

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public Memory get() {
		LOGGER.debug("Building and sending root memory.");
		return this.service.getRoot();
	}
}
