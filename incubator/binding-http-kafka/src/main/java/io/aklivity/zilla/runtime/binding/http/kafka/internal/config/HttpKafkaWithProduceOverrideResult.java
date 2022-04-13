/*
 * Copyright 2021-2022 Aklivity Inc
 *
 * Licensed under the Aklivity Community License (the "License"); you may not use
 * this file except in compliance with the License.  You may obtain a copy of the
 * License at
 *
 *   https://www.aklivity.io/aklivity-community-license/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */
package io.aklivity.zilla.runtime.binding.http.kafka.internal.config;

import org.agrona.DirectBuffer;

import io.aklivity.zilla.runtime.binding.http.kafka.internal.types.KafkaHeaderFW;

public final class HttpKafkaWithProduceOverrideResult
{
    public final DirectBuffer name;
    public final DirectBuffer value;

    HttpKafkaWithProduceOverrideResult(
        DirectBuffer name,
        DirectBuffer value)
    {
        this.name = name;
        this.value = value;
    }

    public void header(
        KafkaHeaderFW.Builder builder)
    {
        builder.nameLen(name.capacity())
               .name(name, 0, name.capacity())
               .valueLen(value.capacity())
               .value(value, 0, value.capacity());
    }
}
