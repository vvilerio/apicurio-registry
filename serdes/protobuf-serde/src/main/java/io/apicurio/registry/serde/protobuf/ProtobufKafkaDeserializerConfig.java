/*
 * Copyright 2021 Red Hat
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.apicurio.registry.serde.protobuf;

import static io.apicurio.registry.serde.SerdeConfig.*;

import java.util.Map;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Type;

import io.apicurio.registry.serde.config.BaseKafkaSerDeConfig;

/**
 * @author Fabian Martinez
 */
public class ProtobufKafkaDeserializerConfig extends BaseKafkaSerDeConfig {

    public static final String SPECIFIC_RETURN_CLASS_DOC =
        "A class generated by Protocol buffers that the message value should be deserialized to";

    public static final String DERIVE_CLASS_FROM_SCHEMA = "apicurio.protobuf.derive.class";
    public static final String DERIVE_CLASS_FROM_SCHEMA_DOC =
        "Whether to derive the class based on `java_outer_classname` and `java_multiple_files` from the Protobuf schema.";

    private static ConfigDef configDef() {
        ConfigDef configDef = new ConfigDef()
                .define(DESERIALIZER_SPECIFIC_KEY_RETURN_CLASS, Type.CLASS, null, Importance.MEDIUM, SPECIFIC_RETURN_CLASS_DOC)
                .define(DESERIALIZER_SPECIFIC_VALUE_RETURN_CLASS, Type.CLASS, null, Importance.MEDIUM, SPECIFIC_RETURN_CLASS_DOC)
                .define(DERIVE_CLASS_FROM_SCHEMA, Type.BOOLEAN, false, Importance.MEDIUM, DERIVE_CLASS_FROM_SCHEMA_DOC);
        return configDef;
    }

    private boolean isKey;

    /**
     * Constructor.
     * @param originals
     */
    public ProtobufKafkaDeserializerConfig(Map<?, ?> originals, boolean isKey) {
        super(configDef(), originals);
        this.isKey = isKey;

    }

    public Class<?> getSpecificReturnClass() {
        if (isKey) {
            return this.getClass(DESERIALIZER_SPECIFIC_KEY_RETURN_CLASS);
        } else {
            return this.getClass(DESERIALIZER_SPECIFIC_VALUE_RETURN_CLASS);
        }
    }

    public boolean deriveClass() {
        return this.getBoolean(DERIVE_CLASS_FROM_SCHEMA);
    }

}
