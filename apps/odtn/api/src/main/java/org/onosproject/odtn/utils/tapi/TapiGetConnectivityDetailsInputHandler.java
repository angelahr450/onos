/*
 * Copyright 2018-present Open Networking Foundation
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

package org.onosproject.odtn.utils.tapi;

import java.util.UUID;
import org.onosproject.yang.gen.v1.tapicommon.rev20181210.tapicommon.Uuid;
import org.onosproject.yang.gen.v1.tapiconnectivity.rev20181210.tapiconnectivity.getconnectivityservicedetails.DefaultGetConnectivityServiceDetailsInput;

/**
 * Utility class to deal with TAPI RPC input with DCS.
 */
public final class TapiGetConnectivityDetailsInputHandler
        extends TapiRpcInputHandler<DefaultGetConnectivityServiceDetailsInput> {

    public Uuid getId() {
        String idOrName = obj.serviceIdOrName();
        UUID uuid = UUID.fromString(idOrName);
        return Uuid.fromString(uuid.toString());
    }
}
