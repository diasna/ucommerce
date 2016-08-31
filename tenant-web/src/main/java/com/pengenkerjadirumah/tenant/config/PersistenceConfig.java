package com.pengenkerjadirumah.tenant.config;

import com.pengenkerjadirumah.persistence.Tenant;
import com.pengenkerjadirumah.persistence.repository.TenantRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by rin on 8/16/2016.
 */

@Configuration
@EntityScan(basePackageClasses = Tenant.class)
@EnableJpaRepositories(basePackageClasses = TenantRepository.class)
public class PersistenceConfig {
}
