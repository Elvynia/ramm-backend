package org.arcanic.ramm.controller;

import java.util.List;

import org.arcanic.ramm.document.Memory;
import org.arcanic.ramm.repository.MemoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/memory")
public class MemoryController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MemoryController.class);
	
	@Autowired
	private MemoryRepository repository;

	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Memory> list() {
		MemoryController.LOGGER.debug("Fetching all memories in database.");
		return repository.findAll();
	}
}
