package fr.benoitsepe.colonie.main;

import java.util.concurrent.LinkedBlockingQueue;

import fr.benoitsepe.colonie.elements.Element;

public class Construction {
	
	private LinkedBlockingQueue<Element> queue;
	
	public Construction() {
		queue = new LinkedBlockingQueue<Element>();
	}
	
	
	public boolean offer(Element e) {
		return queue.offer(e);
	}
	
	public Element poll() {
		return queue.poll();
	}

}
