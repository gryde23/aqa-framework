package com.gryde.config;

import java.io.InputStream;
import java.util.Properties;

public final class ConfigManager {

    private static final Properties PROPERTIES = load();

    private ConfigManager(){}

    private static Properties load() {
        Properties props = new Properties();
        try (InputStream in = ConfigManager.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (in == null) {
                throw new IllegalStateException("config.properties не найден на classpath");
            }
            props.load(in);
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось загрузить config.properties");
        }
        return props;
    }

    public static String get(String key) {
        String override = System.getProperty(key);
        if (override != null) return override;

        String value = PROPERTIES.getProperty(key);
        if (value == null) throw new IllegalStateException("Нет ключа: " + key);

        return value;
    }

    public static String apiBaseUrl() {
        return get("api.base.url");
    }
}
