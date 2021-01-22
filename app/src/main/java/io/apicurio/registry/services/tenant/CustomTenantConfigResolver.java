/*
 * Copyright 2021 Red Hat
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

package io.apicurio.registry.services.tenant;

import io.apicurio.registry.mt.metadata.TenantMetadataDto;
import io.apicurio.registry.rest.Headers;
import io.apicurio.registry.storage.RegistryStorage;
import io.apicurio.registry.types.Current;
import io.quarkus.oidc.OidcTenantConfig;
import io.quarkus.oidc.TenantConfigResolver;
import io.vertx.ext.web.RoutingContext;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CustomTenantConfigResolver implements TenantConfigResolver {

	@Inject
	@Current
	RegistryStorage registryStorage;

	@Override
	public OidcTenantConfig resolve(RoutingContext context) {

		final String tenantId = context.request().getHeader(Headers.TENANT_ID);

		if (null == tenantId) {
			// resolve to default tenant configuration
			return null;
		}

		final TenantMetadataDto registryTenant = registryStorage.getTenantMetadata(tenantId);
		final OidcTenantConfig config = new OidcTenantConfig();

		config.setTenantId(registryTenant.getTenantId());
		config.setAuthServerUrl(registryTenant.getAuthServerUrl());
		config.setClientId(registryTenant.getClientId());

		return config;
	}
}