/*
 * Copyright 2009 Rene Treffer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
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
 *
 */
package com.avos.sasl.de;

import java.util.Map;

import com.avos.sasl.PlainSaslClient;
import com.avos.sasl.client.DigestMD5SaslClient;
import com.avos.security.auth.callback.CallbackHandler;
import com.avos.security.sasl.SaslClient;
import com.avos.security.sasl.SaslException;


public class SaslClientFactory implements
		com.avos.security.sasl.SaslClientFactory {

	@Override
	public SaslClient createSaslClient(String[] mechanisms,
			String authorizationId, String protocol, String serverName,
			Map<String, ?> props, CallbackHandler cbh) throws SaslException {
		for (String mech: mechanisms) {
			if ("PLAIN".equals(mech)) {
				return new PlainSaslClient(authorizationId, cbh);
			} else
			if ("DIGEST-MD5".equals(mech)) {
				return DigestMD5SaslClient.getClient(
					authorizationId,
					protocol,
					serverName,
					props,
					cbh
				);
			}
		}
		return null;
	}

	@Override
	public String[] getMechanismNames(Map<String, ?> props) {
		return new String[]{
			"PLAIN",
			"DIGEST-MD5"
		};
	}

}
