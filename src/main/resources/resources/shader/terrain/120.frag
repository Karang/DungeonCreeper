#version 120

uniform sampler2D Diffuse;
uniform sampler2D Bump;

varying vec4 color;
varying vec2 uvcoord;
varying vec3 eyePos;

void main() {
	vec3 lightPos = eyePos; // debug
	
	float distSqr = dot(lightPos, lightPos);
	float att = clamp(1.0 - sqrt(distSqr), 0.0, 1.0);
	
	vec3 lVec = lightPos * inversesqrt(distSqr);
	vec3 vVec = normalize(eyePos);
	
	vec4 base = texture2D(Diffuse, uvcoord);
	
	vec3 bump = normalize(texture2D(Bump, uvcoord).xyz * 2.0 - 1.0);

	vec4 vAmbient = color;

	float diffuse = max(dot(lVec, bump), 0.0);
	
	vec4 vDiffuse = color * diffuse;
	
	gl_FragColor = (vAmbient * base + vDiffuse * base) * att;
} 