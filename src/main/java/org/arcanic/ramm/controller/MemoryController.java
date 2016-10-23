package org.arcanic.ramm.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.arcanic.ramm.document.Memory;
import org.arcanic.ramm.repository.MemoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/memory")
public class MemoryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MemoryController.class);

	@Autowired
	private MemoryRepository repository;

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Memory create(@RequestBody final Memory memory, final HttpServletResponse response) {
		MemoryController.LOGGER.debug("Creating memory with name '{}'.", memory.content);
		Memory result = null;
		if (memory.getId() == null || this.repository.findOne(memory.getId()) == null) {
			result = repository.save(memory);
			response.setStatus(HttpStatus.CREATED.value());
		} else {
			MemoryController.LOGGER.error("Cannot create memory, id already exists.");
			result = this.repository.findOne(memory.getId());
			response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
		}
		return result;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Memory read(@PathVariable final String id, final HttpServletResponse response) {
		MemoryController.LOGGER.debug("Reading tag with id '{}'.", id);
		Memory result = this.repository.findOne(id);
		if (id != null && result != null) {
			result = repository.findOne(id);
		} else {
			response.setStatus(HttpStatus.NOT_FOUND.value());
		}
		return result;
	}

	@RequestMapping(method = RequestMethod.PUT, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Memory update(@RequestBody final Memory memory, final HttpServletResponse response) {
		Memory result = this.repository.findOne(memory.getId());
		if (memory.getId() != null && result != null) {
			MemoryController.LOGGER.debug("Updating memory with name '{}'.", memory.content);
			result = repository.save(memory);
		} else {
			MemoryController.LOGGER.error("Cannot update memory, id not found.");
			response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
		}
		return result;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable final String id, final HttpServletResponse response) {
		if (id != null && this.repository.findOne(id) != null) {
			MemoryController.LOGGER.debug("Deleting memory with id '{}'.", id);
			repository.delete(id);
			response.setStatus(HttpStatus.NO_CONTENT.value());
		} else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
		}
	}

	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Memory> list() {
		MemoryController.LOGGER.debug("Fetching all memories in database.");
		return repository.findAll();
	}
}
