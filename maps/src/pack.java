import java.io.IOException;

import com.badlogic.gdx.tools.imagepacker.TexturePacker;
import com.badlogic.gdx.tools.imagepacker.TexturePacker.Settings;

public class pack {
	public static void main(String[] args) throws IOException {
		Settings settings = new Settings();
		settings.padding = 0;
		settings.maxWidth = 1024;
		settings.maxHeight = 1024;
		settings.incremental = false;
		TexturePacker.process(settings, "D:/Eclipse_workspace/ourscureil/tmx/superforest", "D:/Eclipse_workspace/ourscureil/tmx");
	}
}
