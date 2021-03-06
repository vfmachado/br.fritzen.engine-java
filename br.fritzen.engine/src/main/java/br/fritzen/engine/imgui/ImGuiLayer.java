package br.fritzen.engine.imgui;

import br.fritzen.engine.Application;
import br.fritzen.engine.core.EngineLog;
import br.fritzen.engine.core.input.Input;
import br.fritzen.engine.core.layers.Layer;
import br.fritzen.engine.events.Event;
import br.fritzen.engine.events.EventCategory;
import br.fritzen.engine.events.EventType;
import br.fritzen.engine.events.key.KeyEvent;
import imgui.Context;
import imgui.IO;
import imgui.ImGui;
import imgui.impl.LwjglGlfw;
import imgui.impl.LwjglGlfw.GlfwClientApi;
import uno.glfw.GlfwWindow;


public class ImGuiLayer extends Layer {

	private LwjglGlfw lwjglGlfw;
	
	private GlfwWindow window;
    
	private ImGui imgui = ImGui.INSTANCE;
	
	private IO io;
	
	private Context ctx;
	
	
	public ImGuiLayer() {
		
		super("ImGuiLayer");
		
		window = GlfwWindow.from(Application.getWindow().getNativeWindow());
	
		ctx = new Context(null);
		
		lwjglGlfw = new LwjglGlfw(window, true, GlfwClientApi.OpenGL, null);
		
		io = imgui.getIo();
		
		io.setWantCaptureKeyboard(true);
		
	}
	
	
	public void begin() {
	
		//reseting 'constant'
		GUI.TRUE[0] = true;
		
		lwjglGlfw.newFrame();
		imgui.newFrame();		
		
	}
	
	
	public void end() {
		
		imgui.render();
	    lwjglGlfw.renderDrawData(imgui.getDrawData());
	     
	}
	
	
	
	@Override
	public void onAttach() {
		
	}

	
	@Override
	public void onDetach() {
		this.ctx.shutdown();
	}

	
	@Override
	public void onUpdate(float deltatime) {
		
	}

	
	@Override
	public void onEvent(Event e) {
		
		if (e.getEventCategory() == EventCategory.Keyboard) {
			
			KeyEvent ke = (KeyEvent) e;
				try {
				
				if (e.getEventType() == EventType.KeyPressedEvent) {
					io.getKeysDown()[ke.getKeyCode()] = true;
					
					io.setKeyCtrl(io.getKeysDown()[Input.KEY_LEFT_CONTROL] || io.getKeysDown()[Input.KEY_RIGHT_CONTROL]);
					io.setKeyShift(io.getKeysDown()[Input.KEY_LEFT_SHIFT] || io.getKeysDown()[Input.KEY_RIGHT_SHIFT]);
					io.setKeyAlt(io.getKeysDown()[Input.KEY_LEFT_ALT] || io.getKeysDown()[Input.KEY_RIGHT_ALT]);
					io.setKeySuper(io.getKeysDown()[Input.KEY_LEFT_SUPER] || io.getKeysDown()[Input.KEY_RIGHT_SUPER]);
					
				} else if (e.getEventType() == EventType.KeyReleasedEvent) {
					
					io.getKeysDown()[ke.getKeyCode()] = false;
			
				} else if (e.getEventType() == EventType.KeyTypedEvent) {
		
					if (ke.getKeyCode() > 0 && ke.getKeyCode() < 0X1000) {
						io.addInputCharacter((char)ke.getKeyCode());
					}
					
				}
			} catch (Exception exc) {
				EngineLog.warning("KeyEvent code not recognized by imgui " + (char)ke.getKeyCode());
			}
		}
				
	}

	
	@Override
	public void onImGuiRender() {
		
	}
	
}
