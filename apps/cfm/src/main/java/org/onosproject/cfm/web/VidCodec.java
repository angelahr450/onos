/*
 * Copyright 2017-present Open Networking Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onosproject.cfm.web;

import static org.onlab.util.Tools.nullIsIllegal;

import java.util.ArrayList;
import java.util.List;

import org.onlab.packet.VlanId;
import org.onosproject.codec.CodecContext;
import org.onosproject.codec.JsonCodec;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Encode and decode to/from JSON to Vid object.
 */
public class VidCodec extends JsonCodec<VlanId> {

    @Override
    public ObjectNode encode(VlanId vid, CodecContext context) {
        return context.mapper().createObjectNode().put("vid", vid.toString());
    }

    @Override
    public ArrayNode encode(Iterable<VlanId> vids, CodecContext context) {
        ArrayNode an = context.mapper().createArrayNode();
        vids.forEach(vid -> {
            an.add(encode(vid, context));
        });
        return an;
    }

    @Override
    public VlanId decode(ObjectNode json, CodecContext context) {
        if (json == null || !json.isObject()) {
            return null;
        }

        JsonNode vidNode = json.get("vid");

        int vid = (nullIsIllegal(vidNode.asInt(), "vid is required"));
        if (vid < 0 || vid > 4095) {
            throw new IllegalArgumentException("VID values must be between 0 and 4095");
        }
        return VlanId.vlanId((short) vid);
    }

    @Override
    public List<VlanId> decode(ArrayNode json, CodecContext context) {
        List<VlanId> vidList = new ArrayList<>();
        json.forEach(node -> vidList.add(decode((ObjectNode) node, context)));
        return vidList;
    }
}
