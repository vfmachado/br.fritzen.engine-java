package br.fritzen.engine.core.layers;

import br.fritzen.engine.events.Event;

public abstract class Layer {

	private String name;
	
	
	public Layer(String name) {
		this.name = name;
	}
	
	
	public String getName() {
		return this.name;
	}
	
	
	public void onAttach() {
		
	}
	
	public void onDetach() {
		
	}
	
	public void onUpdate(float deltatime) {
		
	}
	
	public void onEvent(Event e) {
		
	}
	
	public void onImGuiRender() {
		
	}
	
	public void onRender() {
		
	}
	
	
}
