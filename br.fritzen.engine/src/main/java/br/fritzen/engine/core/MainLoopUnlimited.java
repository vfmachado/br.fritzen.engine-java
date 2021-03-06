package br.fritzen.engine.core;

public abstract class MainLoopUnlimited extends MainLoop {

	private boolean isRunning = false;

	public void run() {

		long timer = System.currentTimeMillis();

		EngineLog.info("Initializing systems");
		this.init();

		isRunning = true;

		EngineLog.info("Application main loop started after " + (System.currentTimeMillis() - timer)  + "ms.");

		timer = System.nanoTime();
		long beforeTime = timer;
		long afterTime = timer;
		long deltaTime = 0;


		while (isRunning) {

			
			beforeTime =  System.nanoTime();
			
			this.update((float)deltaTime / 1_000_000_000);
			this.input();
			this.render();
			
			afterTime =  System.nanoTime();
			
			deltaTime = afterTime - beforeTime;
			
		}

		this.cleanUp();
	}

	/**
	 * The update method of application, it will be executed according to Engine.UPS
	 * in a second.
	 * 
	 * @param deltatime
	 *            how much time passed between the updates in MILLISECONDS
	 */
	protected abstract void update(float deltatime);

	protected abstract void render();

	private void cleanUp() {

	}

	public void stop() {
		isRunning = false;
	}


}
