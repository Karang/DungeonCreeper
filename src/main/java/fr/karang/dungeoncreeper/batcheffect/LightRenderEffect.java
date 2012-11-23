package fr.karang.dungeoncreeper.batcheffect;

import org.spout.api.math.Vector4;
import org.spout.api.render.effect.RenderEffect;
import org.spout.api.render.effect.SnapshotRender;

public class LightRenderEffect implements RenderEffect {

	public void preRender(SnapshotRender snapshotRender) {
		snapshotRender.getMaterial().getShader().setUniform("lightPos", new Vector4(0,0,0,0));
	}

	public void postRender(SnapshotRender snapshotRender) {
		
	}

}
