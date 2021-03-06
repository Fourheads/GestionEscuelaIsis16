/**
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package webapp.prototyping;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.value.Blob;
import org.apache.isis.applib.value.Clob;
import org.apache.isis.core.metamodel.services.devutils.DeveloperUtilitiesServiceDefault;

/**
 * These overrides are simply to 'move' the action underneath the 
 * {@link fixture.simple.SimpleObjectsFixturesService prototyping} menu.
 */
@Hidden
@DomainService(menuOrder = "200")
public class DeveloperUtilities extends DeveloperUtilitiesServiceDefault {

    @MemberOrder(name="Prototyping", sequence="200")
    @Override
    public Clob downloadMetaModel() {
        return super.downloadMetaModel();
    }
    
    @MemberOrder(name="Prototyping", sequence="92")
    @Override
    public Blob downloadLayouts() {
        return super.downloadLayouts();
    }

    @MemberOrder(name="Prototyping", sequence="94")
    @Override
    public void refreshServices() {
        super.refreshServices();
    }
}

