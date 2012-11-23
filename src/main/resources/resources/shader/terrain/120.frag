#version 120

uniform sampler2D Diffuse;

varying vec4 color;
varying vec2 uvcoord;
varying vec4 light;

void main()
{
	gl_FragColor = texture2D(Diffuse, uvcoord) * light;
} 