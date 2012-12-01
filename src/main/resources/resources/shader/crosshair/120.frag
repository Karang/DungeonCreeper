#version 120

varying vec2 uvcoord;
uniform float angle; // angle is given between 0 and 1
						
void main() {
	float x = uvcoord.x - 0.5;
	float y = uvcoord.y - 0.5;
	float dist = x*x + y*y;
	
	float a = atan(y/x) * 0.16;
	if (x > 0.0 && y < 0.0) {
		a = a + 1.0;
	} else if (x < 0.0) {
		a = a + 0.5;
	}
	
	if (0.25 > dist  && dist > 0.04 && a < angle) { // between the circles of radius 0.5 and 0.2
		gl_FragColor = vec4(1.0, 1.0, 1.0, 0.5);
	} else {
		gl_FragColor = vec4(0.0, 0.0, 0.0, 0.0); // transparent
	}
} 