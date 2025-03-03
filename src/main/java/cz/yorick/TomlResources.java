package cz.yorick;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.toml.TomlFactory;
import com.fasterxml.jackson.dataformat.toml.TomlMapper;
import cz.yorick.api.resources.SimpleResources;
import cz.yorick.ops.JacksonOps;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Reader;
import java.io.Writer;

public class TomlResources implements ModInitializer {
	public static final String MOD_ID = "toml-resources";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final TomlFactory FACTORY = new TomlFactory();
	public static final TomlMapper MAPPER = new TomlMapper();

	static {
		SimpleResources.registerOps("toml", JacksonOps.INSTANCE, TomlResources::readToml, TomlResources::writeToml);
		LOGGER.info("Toml resources are now available.");
	}

	@Override
	public void onInitialize() {
	}

	private static JsonNode readToml(Reader reader) throws Exception {
		return MAPPER.readTree(reader);
	}

	private static void writeToml(Writer writer, JsonNode node) throws Exception {
		MAPPER.writeTree(FACTORY.createGenerator(writer), node);
	}

	public static void ensureRegistered() {
	}
}