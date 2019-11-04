package br.fritzen.pong;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import br.fritzen.engine.renderer.Renderer2D;
import br.fritzen.engine.renderer.Texture2D;

public class Ball {

	private Vector3f position;
	
	private Vector3f direction;
	
	private Vector2f size;
	
	private Vector4f color;
	
	private Texture2D texture; 
	
	private float speed;
	
	
	public Ball(float x, float y, float sx, float sy) {
	
		this.position = new Vector3f(x, y, 0);
		direction = new Vector3f(0.5f, 0.3f, 0);
		this.size = new Vector2f(sx, sy);
		this.color = new Vector4f(0.2f, 0.3f, 0.8f, 1.0f);
		this.texture = Texture2D.create("textures/ball.png");
		this.speed = 100f;
	}
	
	
	public void draw() {
		
		Renderer2D.drawQuad(position, size, texture);
		
	}


	private Vector3f tmpDir = new Vector3f();
	public void update(float deltatime) {
		
		if (position.x <= 0f || position.x > 100f) {
			direction.mul(-1, 1, 1);
			if (position.x < 0f) {
				position.x = 0f;
			} else {
				position.x = 100f;
			}
			//position.add(direction.mul(deltatime/100f, tmpDir));
			//System.out.println("Inverter X");
		}
		
		if (position.y <= 0f || position.y >= 100f) {
			
			direction.mul(1, -1, 1);
			if (position.y < 0f) {
				position.y = 0f;
			} else {
				position.y = 100f;
			}
			//position.add(direction.mul(deltatime/100f, tmpDir));
			//System.out.println("Inverter Y");
		}
			
		position.add(direction.mul(deltatime * speed, tmpDir));
		//System.out.println(position);
	}
	
}
