package org.arcanic.ramm.service;

import org.arcanic.ramm.document.Bubble;
import org.arcanic.ramm.document.Memory;
import org.arcanic.ramm.repository.BubbleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemoryService {
	
	@Autowired
	private BubbleRepository repository;

	public Memory getRoot() {
		Memory memory = new Memory();
		memory.root = new Bubble();
		memory.root.content = "ROOT";
		memory.root.color = "0xD0D0D0";
		memory.children = this.repository.findAll();
		for (final Bubble child : memory.children) {
			child.color = "0xFFD0D0";
		}
		return memory;
	}
}
