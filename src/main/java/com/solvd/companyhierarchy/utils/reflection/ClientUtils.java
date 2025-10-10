package com.solvd.companyhierarchy.utils.reflection;

import com.solvd.companyhierarchy.contract.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ClientUtils {

    private static final Logger log = LogManager.getLogger(ClientUtils.class);

    public static void main(String[] args) throws Exception {

        Class<?> clazz = Client.class;

        // --- Inspect fields ---
        log.info("Fields:");
        for (Field field : clazz.getDeclaredFields()) {
            log.info("  {} {} {}\n",
                    Modifier.toString(field.getModifiers()),
                    field.getType().getSimpleName(),
                    field.getName());
        }

        // --- Inspect constructors ---
        log.info("\nConstructors:");
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            log.info("  {} {}({})\n",
                    Modifier.toString(constructor.getModifiers()),
                    constructor.getName(),
                    getParams(constructor.getParameterTypes()));
        }

        // --- Inspect methods ---
        log.info("\nMethods:");
        for (Method method : clazz.getDeclaredMethods()) {
            log.info("  {} {} {}({})\n",
                    Modifier.toString(method.getModifiers()),
                    method.getReturnType().getSimpleName(),
                    method.getName(),
                    getParams(method.getParameterTypes()));
        }

        // --- Create object dynamically ---
        Constructor<?> constructor = clazz.getConstructor(String.class, String.class);
        Object client = constructor.newInstance("OpenAI", "Technology");

        // --- Call setter dynamically ---
        Method setIndustry = clazz.getMethod("setIndustry", String.class);
        setIndustry.invoke(client, "AI Research");

        // --- Call getter dynamically ---
        Method getIndustry = clazz.getMethod("getIndustry");
        Object industryValue = getIndustry.invoke(client);

        log.info("\nReflection call result: Industry = {}", industryValue);
    }

    private static String getParams(Class<?>[] paramTypes) {
        if (paramTypes.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (Class<?> p : paramTypes)
            sb.append(p.getSimpleName()).append(", ");
        return !sb.isEmpty() ? sb.substring(0, sb.length() - 2) : "";
    }
}
