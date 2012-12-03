#version 120

uniform sampler2D Diffuse;
uniform vec4 teamColor;

varying vec2 uvcoord;
						
void main() {
	vec4 tex = texture2D(Diffuse, uvcoord);
	
	if (tex.r==1.0 && tex.g==0.0 && tex.b==1.0) {
		gl_FragColor = teamColor;
	} else {
		gl_FragColor = tex;
	}
}