package cz.yorick;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.toml.TomlFactory;
import com.fasterxml.jackson.dataformat.toml.TomlMapper;
import com.mojang.serialization.DynamicOps;
import cz.yorick.api.FileTypeInitializer;
import cz.yorick.ops.JacksonOps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Reader;
import java.io.Writer;

public class TomlResources implements FileTypeInitializer<JsonNode> {
	public static final String MOD_ID = "toml-resources";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final TomlFactory FACTORY = new TomlFactory();
	public static final TomlMapper MAPPER = new TomlMapper();

	static {
		LOGGER.info("Toml resources are now available.");
	}

	@Override
	public JsonNode read(Reader reader) throws Exception {
		return MAPPER.readTree(reader);
	}

	@Override
	public void write(Writer writer, JsonNode node) throws Exception {
		MAPPER.writeTree(FACTORY.createGenerator(writer), node);
	}

	@Override
	public String getExtension() {
		return "toml";
	}

	@Override
	public DynamicOps<JsonNode> getOps() {
		return JacksonOps.INSTANCE;
	}
}