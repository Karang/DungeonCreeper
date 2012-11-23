#version 120
	
attribute vec4 vPosition;
attribute vec4 vColor;
attribute vec4 vNormal;
attribute vec2 vTexCoord;

varying vec4 color;
varying vec4 normal;
varying vec2 uvcoord;
varying vec4 light;

uniform mat4 Projection;
uniform mat4 View;
uniform mat4 Model;
uniform vec4 lightposition;
		
void main()
{
	gl_Position = Projection * View  * Model * vPosition;
	
	uvcoord = vTexCoord;
	color = vColor;
	normal = vNormal;
	light = vec4(1.0, 0.0, 0.0, 1.0) * distance(vPosition, lightposition);
} 
