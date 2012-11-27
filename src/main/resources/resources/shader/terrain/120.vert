#version 120
	
attribute vec4 vPosition;
attribute vec4 vColor;
attribute vec4 vNormal;
attribute vec2 vTexCoord;

varying vec4 color;
varying vec4 normal;
varying vec2 uvcoord;
varying vec3 eyePos;

uniform mat4 Projection;
uniform mat4 View;
uniform mat4 Model;
uniform vec3 Camera;
		
void main()
{
	gl_Position = Projection * View  * Model * vPosition;
	
	uvcoord = vTexCoord;
	color = vColor;
	normal = vNormal;
	
	eyePos = Camera;
} 
