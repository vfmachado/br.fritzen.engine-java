#version 330


void main() {
	
	//gl_FragDepth = gl_FragCoord.z;
	gl_FragColor = vec4(gl_FragCoord.z, gl_FragCoord.z, gl_FragCoord.z, 1);
	
}