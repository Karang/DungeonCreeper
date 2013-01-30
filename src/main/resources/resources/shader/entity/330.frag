#version 330

in vec2 uvcoord;

uniform sampler2D Diffuse;
uniform vec4 teamColor;

layout(location=0) out vec4 outputColor;

void main()
{
	vec4 tex = texture(Diffuse, uvcoord);
	
	if (tex.r==1.0 && tex.g==0.0 && tex.b==1.0) {
		outputColor = teamColor;
	} else {
		outputColor = tex;
	}
} 