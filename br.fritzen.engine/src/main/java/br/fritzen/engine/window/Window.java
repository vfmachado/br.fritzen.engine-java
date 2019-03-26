package br.fritzen.engine.window;

/**
 * 
 * @author fritz
 *
 */
public abstract class Window {

	protected int width;
	
	protected int height;
	
	protected String title;
	
	
	protected abstract void init();
	
	public abstract void cleanUp();

	public abstract void onUpdate();
	
	public abstract long getNativeWindow();
	
	public abstract void setVSync(boolean enable);
	
	public abstract boolean isVSync();
	
	
	public Window(int width, int height, String title) {
		
		this.width = width;
		this.height = height;
		this.title = title;
		
		init();
		
	}

	
	public int getWidth() {
		return this.width;
	}
	
	
	public int getHeight() {
		return this.height;
	}
	
	
	public String getTitle() {
		return this.title;
	}
	
}
