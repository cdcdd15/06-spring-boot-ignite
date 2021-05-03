package com.cosmin.ignite.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cluster.ClusterGroup;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.lang.IgniteRunnable;
import org.springframework.stereotype.Service;

import com.cosmin.ignite.model.EmployeeDTO;
import com.cosmin.ignite.util.CacheUtil;

@Service
public class EmployeeIgniteService{
	public void insertDTOs(EmployeeDTO dto) {
//		IgniteConfiguration cfg = CacheUtil.createCacheConfiguration();
		Ignition.setClientMode(true);
		try (Ignite ignite = Ignition.start(CacheUtil.xmlConfig)) {
			IgniteCache<Integer, EmployeeDTO> cache = ignite.getOrCreateCache(CacheUtil.cacheName);
			cache.put(dto.getId(), dto);
		}
		System.out.println("end InsertDTOs");
	}

	public boolean processAllRecordsOnServerNodes() {
//		IgniteConfiguration cfg = CacheUtil.createCacheConfiguration();
		Ignition.setClientMode(true);
		try (Ignite ignite = Ignition.start(CacheUtil.xmlConfig)) {
			IgniteCache<Integer, EmployeeDTO> cache = ignite.getOrCreateCache(CacheUtil.cacheName);
			Set<Integer> keys = CacheUtil.fetchAllKeysFromCache(ignite, cache);
			ClusterGroup clusterGroup = ignite.cluster().forRemotes();
			ignite.compute(clusterGroup).withName("event-task").run(new IgniteRunnable() {
				@Override
				public void run() {
					Map<Integer, EmployeeDTO> dtos = cache.getAll(keys);
					for (Integer key : keys) {
						EmployeeDTO dto = cache.get(key);
						dto.setSalary(dto.getSalary() + dto.getSalary() / 10);
						System.out.println(
								"Executing Employee job for name=" + dto.getName() + " and salary=" + dto.getSalary());
//						employeeList.add(dto);
						cache.put(key, dto);
					}
				}
			});
		}
		System.out.println("end ProcessDTOs");
		return true;
	}

	public List<EmployeeDTO> findAll() {
		List<EmployeeDTO> employeeList = null;
		Ignition.setClientMode(true);
		try (Ignite ignite = Ignition.start(CacheUtil.xmlConfig)) {
			IgniteCache<Integer, EmployeeDTO> cache = ignite.getOrCreateCache(CacheUtil.cacheName);
			Set<Integer> keys = CacheUtil.fetchAllKeysFromCache(ignite, cache);
			Map<Integer, EmployeeDTO> map = cache.getAll(keys);
			employeeList = new ArrayList(map.values());
		}
		return employeeList;
	}

	public boolean deleteCache() {
		Ignition.setClientMode(true);
		try (Ignite ignite = Ignition.start(CacheUtil.xmlConfig)) {
			IgniteCache<Integer, EmployeeDTO> cache = ignite.getOrCreateCache(CacheUtil.cacheName);
			cache.destroy();
		}
		return true;
	}
}