package fr.karang.dungeoncreeper;

import java.io.File;

import org.spout.api.util.config.ConfigurationHolderConfiguration;
import org.spout.api.util.config.yaml.YamlConfiguration;

public class DungeonConfig extends ConfigurationHolderConfiguration {

	public DungeonConfig(File dataFolder) {
		super(new YamlConfiguration(new File(dataFolder, "config.yml")));
	}

}
