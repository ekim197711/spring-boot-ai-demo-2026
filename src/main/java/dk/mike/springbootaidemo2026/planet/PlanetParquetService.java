package dk.mike.springbootaidemo2026.planet;

import lombok.extern.slf4j.Slf4j;
import org.apache.parquet.example.data.Group;
import org.apache.parquet.example.data.simple.SimpleGroupFactory;
import org.apache.parquet.hadoop.ParquetReader;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.api.ReadSupport;
import org.apache.parquet.hadoop.example.ExampleParquetWriter;
import org.apache.parquet.hadoop.example.GroupReadSupport;
import org.apache.parquet.io.LocalInputFile;
import org.apache.parquet.io.LocalOutputFile;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.MessageTypeParser;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Service
@Slf4j
public class PlanetParquetService {

    private static final MessageType PLANET_SCHEMA = MessageTypeParser.parseMessageType("""
            message planet {
              required binary name (UTF8);
              required binary star_system (UTF8);
              required int32 order_from_star;
              required int32 moon_count;
              required boolean habitable;
            }
            """);

    public Path saveDummyPlanets(Path parquetPath) throws IOException {
        SimpleGroupFactory groupFactory = new SimpleGroupFactory(PLANET_SCHEMA);
        ParquetWriter<Group> writer = ExampleParquetWriter.builder(new LocalOutputFile(parquetPath))
                .withType(PLANET_SCHEMA)
                .build();
        try (writer) {
            for (Planet planet : dummyPlanets()) {
                writer.write(groupFactory.newGroup()
                        .append("name", planet.name())
                        .append("star_system", planet.starSystem())
                        .append("order_from_star", planet.orderFromStar())
                        .append("moon_count", planet.moonCount())
                        .append("habitable", planet.habitable()));
            }
        } catch (Exception exception) {
            throw new RuntimeException("Failed to write Parquet file", exception);
        }
        return parquetPath;
    }

    public List<Planet> readPlanets(Path parquetPath) throws IOException {
        ParquetReader<Group> reader = new ParquetReader.Builder<Group>(new LocalInputFile(parquetPath)) {
            @Override
            protected ReadSupport<Group> getReadSupport() {
                return new GroupReadSupport();
            }
        }.build();
        try (reader) {
            java.util.ArrayList<Planet> planets = new java.util.ArrayList<>();
            Group group;
            while ((group = reader.read()) != null) {
                planets.add(new Planet(
                        group.getString("name", 0),
                        group.getString("star_system", 0),
                        group.getInteger("order_from_star", 0),
                        group.getInteger("moon_count", 0),
                        group.getBoolean("habitable", 0)
                ));
            }
            return planets;
        } catch (Exception exception) {
            throw new RuntimeException("Failed to read Parquet file", exception);
        }
    }

    private List<Planet> dummyPlanets() {
        return List.of(
                new Planet("Mercury", "Solar System", 1, 0, false),
                new Planet("Venus", "Solar System", 2, 0, false),
                new Planet("Earth", "Solar System", 3, 1, true),
                new Planet("Mars", "Solar System", 4, 2, false),
                new Planet("Jupiter", "Solar System", 5, 95, false),
                new Planet("Saturn", "Solar System", 6, 146, false),
                new Planet("Uranus", "Solar System", 7, 28, false),
                new Planet("Neptune", "Solar System", 8, 16, false),
                new Planet("Kepler-452b", "Kepler-452", 1, 0, true),
                new Planet("Proxima Centauri b", "Proxima Centauri", 1, 0, true)
        );
    }


    public record Planet(String name, String starSystem, int orderFromStar, int moonCount, boolean habitable) {
    }
}
