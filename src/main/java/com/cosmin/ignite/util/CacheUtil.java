package com.cosmin.ignite.util;

import java.util.HashSet;
import java.util.Set;

import javax.cache.Cache.Entry;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.ScanQuery;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;

import com.cosmin.ignite.MyLifecycleBean;
import com.cosmin.ignite.model.EmployeeDTO;

public class CacheUtil {
	public final static String cacheName = "cosminCache";
	public static IgniteConfiguration createCacheConfiguration() {
		// se da ca arg cashe name
		CacheConfiguration<?, ?> cacheCfg = new CacheConfiguration(cacheName);
		cacheCfg.setIndexedTypes(Integer.class, EmployeeDTO.class);

		IgniteConfiguration cfg = new IgniteConfiguration();
		cfg.setLifecycleBeans(new MyLifecycleBean());
		cfg.setPeerClassLoadingEnabled(true);
		cfg.setIgniteInstanceName("ignite-cluster-node");
		cfg.setCacheConfiguration(cacheCfg);
		
		return cfg;
	}
	public static Set<Integer> fetchAllKeysFromCache(Ignite ignite, IgniteCache<Integer, EmployeeDTO> cache){
		Set<Integer> keys = new HashSet<>();
		try (QueryCursor<Entry<Integer, EmployeeDTO>> qryCursor = cache.query(new ScanQuery<>(null))) {
		    qryCursor.forEach(
		            entry -> keys.add(entry.getKey())
		            );
		}
		return keys;
	}
}
