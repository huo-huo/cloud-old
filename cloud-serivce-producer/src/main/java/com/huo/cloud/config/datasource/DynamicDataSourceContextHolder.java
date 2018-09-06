package com.huo.cloud.config.datasource;

import java.util.ArrayList;
import java.util.List;

public class DynamicDataSourceContextHolder {

	/**
	 * Maintain variable for every thread, to avoid effect other thread
	 */
	private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

	/**
	 * To switch DataSource
	 *
	 * @param key the key
	 */
	public static void setDataSource(String key) {
		CONTEXT_HOLDER.set(key);
	}

	/**
	 * Get current DataSource
	 *
	 * @return data source key
	 */
	public static String getDataSource() {
		return CONTEXT_HOLDER.get();
	}

	/**
	 * To set DataSource as default
	 */
	public static void clearDataSource() {
		CONTEXT_HOLDER.remove();
	}

}
