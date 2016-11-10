package org.arcanic.ramm.service;

import org.arcanic.ramm.document.Bubble;
import org.arcanic.ramm.document.Memory;
import org.arcanic.ramm.repository.BubbleRepository;
import org.arcanic.ramm.util.Vector3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemoryService {

	@Autowired
	private BubbleRepository repository;

	public Memory getRoot() {
		final Memory memory = new Memory();
		memory.root = new Bubble();
		memory.root.content = "ROOT";
		memory.root.color = 13684944;
		memory.root.position = new Vector3();
		memory.children = this.repository.findAll();
		for (final Bubble child : memory.children) {
			child.color = 16765136;
			final int randX = (int) Math.floor((Math.random() - 0.5) * 100);
			final int randY = (int) Math.floor((Math.random() - 0.5) * 100);
			final int randZ = (int) Math.floor((Math.random() - 0.5) * 100);
			child.position = new Vector3(randX, randY, randZ);
		}
		return memory;
	}
}
