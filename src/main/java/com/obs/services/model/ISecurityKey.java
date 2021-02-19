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
 */
package com.obs.services.model;

public interface ISecurityKey {
    /**
     * Returns the access key for this securityKey.
     */
    public String getAccessKey();

    /**
     * Returns the secret key for this securityKey.
     */
    public String getSecretKey();

    /**
     * Returns the security token for this securityKey.
     */
    public String getSecurityToken();
}
