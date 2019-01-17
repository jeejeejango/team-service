package org.jeejeejango.config;

import org.jeejeejango.entity.Team;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.configuration.MutableConfiguration;

/**
 * @author jeejeejango
 * @since 19/11/2018 5:17 AM
 */
@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cacheManager -> {
            javax.cache.configuration.Configuration<Object, Object> configuration = new MutableConfiguration<>();
            cacheManager.createCache(Team.class.getName(), configuration);
        };
    }


}
