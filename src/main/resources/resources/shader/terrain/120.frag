#version 120

uniform sampler2D Diffuse;
uniform sampler2D Bump;

varying vec4 color;
varying vec4 normal;
varying vec2 uvcoord;
varying vec3 eyePos;

void main() {
	vec3 lightPos = eyePos; // debug
	
	float distSqr = dot(lightPos, lightPos);
	vec3 lVec = lightPos * inversesqrt(distSqr);
	vec3 vVec = normalize(eyePos);
	
	vec4 base = texture2D(Diffuse, uvcoord);
	vec4 bump = normalize(texture2D(Bump, uvcoord) * 2.0 - 1.0);

	float diffuse = max(dot(normal, bump), 0.0);
	vec4 vDiffuse = color * diffuse;
	vDiffuse.a = color.a;
	
	gl_FragColor =  vec4(diffuse, diffuse, diffuse, 1.0) * base;
} 