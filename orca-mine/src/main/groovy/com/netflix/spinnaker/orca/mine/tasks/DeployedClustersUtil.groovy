/*
 * Copyright 2015 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.orca.mine.tasks

class DeployedClustersUtil {
  static List<Map> toKatoAsgOperations(String asgOperationDescription, Map stageContext) {
    stageContext.deployedClusterPairs.findAll { it.canaryStage == stageContext.canaryStageId }.collect {
      [it.canaryCluster, it.baselineCluster].collect {
        [
          (asgOperationDescription): [
            serverGroupName: it.serverGroup, asgName: it.serverGroup, regions: [it.region], credentials: it.accountName
          ]
        ]
      }
    }.flatten()
  }
}
