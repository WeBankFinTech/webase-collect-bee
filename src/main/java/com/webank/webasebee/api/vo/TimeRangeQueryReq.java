/**
 * Copyright 2014-2019 the original author or authors.
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
package com.webank.webasebee.api.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * TimeRangeQueryReq
 *
 * @author maojiayu
 * @data Dec 21, 2018 11:06:21 AM
 *
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper=true)
public class TimeRangeQueryReq extends CommonPageReq {

    /** @Fields beginTime : valid date format is accepted, and various kinds could be parsed. */
    private String beginTime;

    /** @Fields endTime : valid date format is accepted, and various kinds could be parsed. */
    private String endTime;

}