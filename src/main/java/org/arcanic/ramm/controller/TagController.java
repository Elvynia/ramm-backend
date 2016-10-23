package org.arcanic.ramm.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.arcanic.ramm.document.Tag;
import org.arcanic.ramm.repository.TagRepository;
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
@RequestMapping("/tag")
public class TagController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TagController.class);

	@Autowired
	private TagRepository repository;

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Tag create(@RequestBody final Tag tag, final HttpServletResponse response) {
		TagController.LOGGER.debug("Creating tag with name '{}'.", tag.name);
		Tag result = null;
		if (tag.getId() == null || this.repository.findOne(tag.getId()) == null) {
			result = repository.save(tag);
			response.setStatus(HttpStatus.CREATED.value());
		} else {
			TagController.LOGGER.error("Cannot create tag, id already exists.");
			result = this.repository.findOne(tag.getId());
			response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
		}
		return result;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Tag read(@PathVariable final String id, final HttpServletResponse response) {
		TagController.LOGGER.debug("Reading tag with id '{}'.", id);
		Tag result = this.repository.findOne(id);
		if (id != null && result != null) {
			result = repository.findOne(id);
		} else {
			response.setStatus(HttpStatus.NOT_FOUND.value());
		}
		return result;
	}

	@RequestMapping(method = RequestMethod.PUT, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Tag update(@RequestBody final Tag tag, final HttpServletResponse response) {
		Tag result = this.repository.findOne(tag.getId());
		if (tag.getId() != null && result != null) {
			TagController.LOGGER.debug("Updating tag with name '{}'.", tag.name);
			result = repository.save(tag);
		} else {
			TagController.LOGGER.error("Cannot update tag, id not found.");
			response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
		}
		return result;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable final String id, final HttpServletResponse response) {
		if (id != null && this.repository.findOne(id) != null) {
			TagController.LOGGER.debug("Deleting tag with id '{}'.", id);
			repository.delete(id);
			response.setStatus(HttpStatus.NO_CONTENT.value());
		} else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
		}
	}

	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Tag> list() {
		TagController.LOGGER.debug("Fetching all tags in database.");
		return repository.findAll();
	}
}
