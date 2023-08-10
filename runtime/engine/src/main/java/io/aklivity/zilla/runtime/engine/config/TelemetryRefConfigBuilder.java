/*
 * Copyright 2021-2023 Aklivity Inc.
 *
 * Aklivity licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.aklivity.zilla.runtime.engine.config;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public final class TelemetryRefConfigBuilder<T> implements ConfigBuilder<T>
{
    private final Function<TelemetryRefConfig, T> mapper;

    private List<MetricRefConfig> metrics;

    TelemetryRefConfigBuilder(
        Function<TelemetryRefConfig, T> mapper)
    {
        this.mapper = mapper;
    }

    public MetricRefConfigBuilder<TelemetryRefConfigBuilder<T>> metric()
    {
        return new MetricRefConfigBuilder<>(this::metric);
    }

    public TelemetryRefConfigBuilder<T> metric(
        MetricRefConfig metric)
    {
        if (metrics == null)
        {
            metrics = new LinkedList<>();
        }
        metrics.add(metric);
        return this;
    }

    @Override
    public T build()
    {
        return mapper.apply(new TelemetryRefConfig(metrics));
    }
}
