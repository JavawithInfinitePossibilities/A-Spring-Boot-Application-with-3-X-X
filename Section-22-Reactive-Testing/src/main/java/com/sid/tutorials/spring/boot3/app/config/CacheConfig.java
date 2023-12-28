/**
 * 
 */
package com.sid.tutorials.spring.boot3.app.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import org.springframework.context.annotation.Configuration;

/**
 * @author kunmu
 *
 */
@Configuration
public class CacheConfig {

	public Config cacheConfig() {
		return new Config().setInstanceName("hazal-instance")
				.addMapConfig(new MapConfig().setName("student-cache").setTimeToLiveSeconds(3000));

	}
}
