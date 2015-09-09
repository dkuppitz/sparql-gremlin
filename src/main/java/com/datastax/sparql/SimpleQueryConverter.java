/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.datastax.sparql;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Daniel Kuppitz (http://gremlin.guru)
 */
class SimpleQueryConverter {

    private final static Pattern EDGE_PREFIX_PATTERN = Pattern.compile("(\\p{Blank}*\\?\\p{Alpha}\\p{Alnum}*\\p{Blank}+)(\\p{Alnum}+)(.*)");
    private final static Pattern PROPERTY_PREFIX_PATTERN = Pattern.compile("(\\p{Blank}*\\?\\p{Alpha}\\p{Alnum}*\\p{Blank}+)::(\\p{Alnum}+)(.*)");

    public static String convert(final String simpleQuery) {
        String query = simpleQuery;
        Matcher matcher;
        matcher = EDGE_PREFIX_PATTERN.matcher(query);
        if (matcher.find()) {
            query = matcher.replaceAll("$1e:$2$3");
        }
        matcher = PROPERTY_PREFIX_PATTERN.matcher(query);
        if (matcher.find()) {
            query = matcher.replaceAll("$1v:$2$3");
        }
        return query;
    }
}
