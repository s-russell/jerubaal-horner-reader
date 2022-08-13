package com.jerubaal.reader;

import com.jerubaal.reader.ReadingPlanBuilder.Reading;
import com.jerubaal.reader.ReadingPlanBuilder.ReadingPlan;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("reading")
public class ReadingResource {

    @Inject
    Jsonb jsonb;

    @Inject
    ReadingPlan plan;

    @GET
    @Path("{readingNumber}")
    @Produces(APPLICATION_JSON)
    public String getReading(@PathParam("readingNumber") int readingNumber) {
        return jsonb.toJson(
                plan.reading(readingNumber)
                        .stream()
                        .map(readingToMap)
                        .collect(toList())
        );
    }

    private Function<Reading, Map<String, Object>> readingToMap = reading -> Map.of(
            "book", reading.book().name(),
            "chapter", reading.chapterNumber()
    );
}