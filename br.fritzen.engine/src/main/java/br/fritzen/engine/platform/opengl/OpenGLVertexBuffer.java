package br.fritzen.engine.platform.opengl;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL15;

import br.fritzen.engine.core.EngineLog;
import br.fritzen.engine.renderer.Buffer;

public class OpenGLVertexBuffer implements Buffer.VertexBuffer {
	
	private int vbo;
	
	
	public OpenGLVertexBuffer(int size) {
		
		this.vbo = GL15.glGenBuffers();
		
		if (this.vbo == 0) {
			EngineLog.warning("Problem creating OpenGL VBO");
		}
		
		bind();
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, size, GL15.GL_DYNAMIC_DRAW);
		
	}
	
	
	public OpenGLVertexBuffer(FloatBuffer data, int size) {
	
		this.vbo = GL15.glGenBuffers();
		
		if (this.vbo == 0) {
			EngineLog.warning("Problem creating OpenGL VBO");
		}
		
		bind();
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, data, GL15.GL_STATIC_DRAW);
		
	}
	
	
	public void setData(FloatBuffer data, int size) {
		bind();
		GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, 0, data);
	}
	
	
	@Override
	public void bind() {
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.vbo);
	}
	
	
	@Override
	public void unbind() {
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	
}
