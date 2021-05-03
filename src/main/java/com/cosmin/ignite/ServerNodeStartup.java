package com.cosmin.ignite;

import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;

import com.cosmin.ignite.util.CacheUtil;

public class ServerNodeStartup {
    	
	public final static String xmlConfigPath = CacheUtil.xmlConfig;
	
    public static void main(String[] args) throws IgniteException {
		// Mark this cluster member as server node, the default value = false.
        Ignition.setClientMode(false);
    	Ignition.start(xmlConfigPath);
    }
}