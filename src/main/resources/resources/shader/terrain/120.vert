#version 120
	
attribute vec4 vPosition;
attribute float vlava;
attribute vec4 vNormal;
attribute vec2 vTexCoord;
attribute float vwater;

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
	
	vec4 lavaColor = vec4(1.0, 0.9, 0.9, 1.0);
	vec4 waterColor = vec4(0.9, 0.9, 1.0, 1.0);
	
	vec4 ambient = vec4(0.33, 0.33, 0.33, 1.0);
	
	vec4 diffuse = lavaColor*vlava + waterColor*vwater;
	diffuse = clamp(diffuse, 0.0, 1.0);
	
	uvcoord = vTexCoord;
	color = ambient + diffuse;
	normal = vNormal;
	
	eyePos = Camera;
} 
