package com.avos.smack.ping;

import org.xmlpull.v1.XmlPullParser;

import com.avos.smack.packet.IQ;
import com.avos.smack.ping.packet.Ping;
import com.avos.smack.provider.IQProvider;

public class PingProvider implements IQProvider {
    
    public IQ parseIQ(XmlPullParser parser) throws Exception {
        // No need to use the ping constructor with arguments. IQ will already
        // have filled out all relevant fields ('from', 'to', 'id').
        return new Ping();
    }

}
