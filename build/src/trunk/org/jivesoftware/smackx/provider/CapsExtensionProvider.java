/*
 * Copyright 2009 Jonas dahl.
 * Copyright 2011-2013 Florian Schmaus
 *
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

package org.jivesoftware.smackx.provider;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.provider.PacketExtensionProvider;
import org.jivesoftware.smackx.entitycaps.packet.CapsExtension;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class CapsExtensionProvider implements PacketExtensionProvider {
    private static final int MAX_DEPTH = 10;

    public PacketExtension parseExtension(XmlPullParser parser) throws XmlPullParserException, IOException,
            XMPPException {
        String hash = null;
        String version = null;
        String node = null;
        int depth = 0;
        while (true) {
            if (parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equalsIgnoreCase("c")) {
                hash = parser.getAttributeValue(null, "hash");
                version = parser.getAttributeValue(null, "ver");
                node = parser.getAttributeValue(null, "node");
            }

            if (parser.getEventType() == XmlPullParser.END_TAG && parser.getName().equalsIgnoreCase("c")) {
                break;
            } else {
                parser.next();
            }

            if (depth < MAX_DEPTH) {
                depth++;
            } else {
                throw new XMPPException("Malformed caps element");
            }
        }

        if (hash != null && version != null && node != null) {
            return new CapsExtension(node, version, hash);
        } else {
            throw new XMPPException("Caps elment with missing attributes");
        }
    }
}
