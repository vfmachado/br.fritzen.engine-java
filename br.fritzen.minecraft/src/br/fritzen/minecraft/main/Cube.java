package br.fritzen.minecraft.main;

import br.fritzen.engine.renderer.Buffer.IndexBuffer;
import br.fritzen.engine.renderer.Buffer.VertexArray;
import br.fritzen.engine.renderer.Buffer.VertexBuffer;
import br.fritzen.engine.utils.EngineBuffers;

public class Cube {

	private static Cube instance = null;
	private VertexArray vao;

	static int lines = 16;
	static int columns = 16;
	static int height = 16;
	static int count = lines * columns * height;
	
	int mat[][][] = new int[lines][columns][height];
	static int quantity = 0;
	
	private Cube() {
		
		for (int i = 0; i < lines; i++) {
			for (int j = 0; j < columns; j++) {
				for (int k = 0; k < height; k++) {
					
					//if ((k >= Math.abs(i - j)))
					mat[i][k][j] = 1;
				}
			}
		}
		
		mat[2][0][1] = 0;
		mat[2][1][1] = 0;
		
		float[] positions = {
				
			//top
			0, 1, 0,
			1, 1, 0,
			0, 1, 1,
			1, 1, 1,
			
			//bottom
			0, 0, 0,
			1, 0, 0,
			0, 0, 1,
			1, 0, 1,
			
			
			//front
			0, 1, 1,
			1, 1, 1,
			0, 0, 1,
			1, 0, 1,
			
			//back
			0, 1, 0,
			1, 1, 0,
			0, 0, 0,
			1, 0, 0,
			
			//side left
			0, 1, 1,
			0, 1, 0,
			0, 0, 1,
			0, 0, 0,
			
			
			//side right
			1, 1, 1,
			1, 1, 0,
			1, 0, 1,
			1, 0, 0,
			
			
        };
		
		float[] texCoords = {
			0, 0,
			1, 0,
			0, 1,
			1, 1,
			
			2, 0,
			3, 0,
			2, 1,
			3, 1,
			
			1, 0,
			2, 0,
			1, 1,
			2, 1,
			
			2, 0,
			1, 0,
			2, 1,
			1, 1,
			
			2, 0,
			1, 0,
			2, 1,
			1, 1,
			
			1, 0,
			2, 0,
			1, 1,
			2, 1,
			
		};
		
		this.vao = VertexArray.create();
		
		VertexBuffer vbo = VertexBuffer.create(EngineBuffers.createFloatBuffer(positions), positions.length * 4);
		this.vao.addVB(vbo, 0, 3);
		
		VertexBuffer vboTextures = VertexBuffer.create(EngineBuffers.createFloatBuffer(texCoords), texCoords.length * 4);
		this.vao.addVB(vboTextures, 1, 2);
		
		float[] transform = new float[lines * columns * height * 3];
		int index = 0;
		
		for (int i = 0; i < lines; i++) {
			for (int j = 0; j < columns; j++) {
				for (int k = 0; k < height; k++) {
					
					if (mat[i][j][k] == 1) {
						
						try {
							if (mat[i+1][j][k] == 1 && mat[i-1][j][k] == 1 && mat[i][j+1][k] == 1 && mat[i][j-1][k] == 1 && mat[i][j][k+1] == 1 && mat[i][j][k-1] == 1)
								continue;
						} catch (IndexOutOfBoundsException e) {}
						
						transform[index++] = i - lines/2;
						transform[index++] = k;
						transform[index++] = j - columns/2;
						quantity++;
						
					}
				}
			}
		}
		
		System.out.println("Number of blocks:"  + quantity);
		
		VertexBuffer vboTransforms = VertexBuffer.create(EngineBuffers.createFloatBuffer(transform), transform.length * 4);
		this.vao.addInstancedVB(vboTransforms, 2, 3, 3, 0);
		
		
		int[] indices = { 
				0, 1, 2, 
				1, 3, 2,
				
				4, 5, 6,
				5, 7, 6,
				
				8, 9, 10,
				9, 11, 10,
				
				12, 13, 14,
				13, 15, 14,
				
				16, 17, 18,
				17, 19, 18,
				
				20, 21, 22,
				21, 23, 22
				
		};
		
		IndexBuffer ibo = IndexBuffer.create(EngineBuffers.createIntBuffer(indices), indices.length);
		this.vao.setIB(ibo);
		
	}


	public static VertexArray getVao() {
		
		if (instance == null)
			instance = new Cube();
		
		return instance.vao;
	}
	
}
