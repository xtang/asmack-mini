/**
 * All rights reserved. Licensed under the Apache License, Version 2.0 (the "License");
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

package org.jivesoftware.smackx;

import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.smackx.entitycaps.provider.CapsExtensionProvider;
import org.jivesoftware.smackx.ping.provider.PingProvider;
import org.jivesoftware.smackx.provider.DataFormProvider;
import org.jivesoftware.smackx.provider.DiscoverInfoProvider;
import org.jivesoftware.smackx.provider.DiscoverItemsProvider;

/**
 * Since dalvik on Android does not allow the loading of META-INF files from the
 * filesystem, you have to register every provider manually.
 *
 * The full list of providers is at:
 * http://fisheye.igniterealtime.org/browse/smack/trunk/build/resources/META-INF/smack.providers?hb=true
 *
 * @author Florian Schmaus fschmaus@gmail.com
 *
 */
public class ConfigureProviderManager {

    public static void configureProviderManager() {
        ProviderManager pm = ProviderManager.getInstance();

        //  Service Discovery # Items
        pm.addIQProvider("query","http://jabber.org/protocol/disco#items", new DiscoverItemsProvider());
        //  Service Discovery # Info
        pm.addIQProvider("query","http://jabber.org/protocol/disco#info", new DiscoverInfoProvider());
        //  Data Forms
        pm.addExtensionProvider("x","jabber:x:data", new DataFormProvider());
        // XEP-0199 XMPP Ping
        pm.addIQProvider("ping", "urn:xmpp:ping", new PingProvider());

        // XEP-0115 Entity Capabilities
        pm.addExtensionProvider("c", "http://jabber.org/protocol/caps", new CapsExtensionProvider());
    }
}
