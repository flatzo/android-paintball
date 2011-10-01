import java.io.IOException;

import com.badlogic.gdx.tools.imagepacker.TexturePacker;
import com.badlogic.gdx.tools.imagepacker.TexturePacker.Settings;

public class pack {
	public static void main(String[] args) throws IOException {
		Settings settings = new Settings();
		settings.padding = 2;
		settings.maxWidth = 512;
		settings.maxHeight = 512;
		settings.incremental = true;
		TexturePacker.process(settings, "tiledmap", "generated");
	}
}
