package com.zhaisoft.lib.utils;

import java.util.HashMap;
import java.util.Map;

public class MapCache {
	static volatile MapCache instance;

	private class CacheInfo {
		public Object obj;
		public int ref;
		public String toString() {
			return ""+obj;
		}
	}

	private Map<String, CacheInfo> cache;

	private static final int PUT = 0;
	private static final int GET = 1;
	private static final int FREE = 2;

	public MapCache() {
		cache = new HashMap<String, CacheInfo>();
	}

	public static MapCache getInstance() {
		if (instance == null) {
			synchronized(MapCache.class) {
				if (instance == null)
					instance = new MapCache();
			}
		}
		return instance;
	}

	/**
	 * put objects with the same key, the lasts will be ignore...
	 * 
	 * @param key
	 * @param obj
	 */
	public void put(String key, Object obj) {
		operateMap(key, PUT, obj);
	}

	private synchronized Object operateMap(String key, int action, Object data) {
		if (cache == null)
			cache = new HashMap<String, CacheInfo>();
		CacheInfo info = cache.get(key);
		if (info == null) {
			if (action == PUT) {
				info = new CacheInfo();
				info.obj = data;
				info.ref = 0;
				cache.put(key, info);
 			}
			return null;
		}

		if (action == GET || action == PUT) {
			info.ref++;
 			return info.obj;
		} else {
			info.ref--;
 			if (info.ref < 0) {
				cache.remove(key);
 			}
			return null;
		}
	}

	/**
	 * 
	 * @param key
	 * @return the value of the mapping with the specified key, or null if no
	 *         mapping for the specified key is found.
	 */
	public Object get(String key) {
		return operateMap(key, GET, null);
	}

	public void free(String key) {
		operateMap(key, FREE, null);
	}

	public void clear() {
		if (cache != null)
			cache.clear();
		cache = null;
	}
	
	public String toString() {
		return "" + cache;
	}
	
}
