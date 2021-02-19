/**
 * Copyright 2019 Huawei Technologies Co.,Ltd.
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use
 * this file except in compliance with the License.  You may obtain a copy of the
 * License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 **/

package com.obs.services.model;

/**
 * Response to a request for obtaining the bucket location
 */
public class BucketLocationResponse extends HeaderResponse {
    private String location;

    public BucketLocationResponse(String location) {
        this.location = location;
    }

    /**
     * Obtain the bucket location.
     *
     * @return Bucket location
     */
    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "BucketLocationResponse [location=" + location + "]";
    }

}


