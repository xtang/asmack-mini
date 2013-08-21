package com.avos.avoscloud.jivesoftware.smackx.ping;

import org.xmlpull.v1.XmlPullParser;

import com.avos.avoscloud.jivesoftware.smack.packet.IQ;
import com.avos.avoscloud.jivesoftware.smack.provider.IQProvider;
import com.avos.avoscloud.jivesoftware.smackx.ping.packet.Ping;

public class PingProvider implements IQProvider {
    
    public IQ parseIQ(XmlPullParser parser) throws Exception {
        // No need to use the ping constructor with arguments. IQ will already
        // have filled out all relevant fields ('from', 'to', 'id').
        return new Ping();
    }

}
