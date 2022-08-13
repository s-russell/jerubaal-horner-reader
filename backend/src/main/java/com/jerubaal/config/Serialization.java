package com.jerubaal.config;

import javax.enterprise.inject.Produces;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;

public class Serialization {

    @Produces
    public Jsonb configJsonb() {
        var conf = new JsonbConfig();

        return JsonbBuilder.create(conf);
    }
}
