package utils.reflection;

import contract.Client;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ClientUtils {

    public static void main(String[] args) throws Exception {

        Class<?> clazz = Client.class;

        // --- Inspect fields ---
        System.out.println("Fields:");
        for (Field field : clazz.getDeclaredFields()) {
            System.out.printf("  %s %s %s%n",
                    Modifier.toString(field.getModifiers()),
                    field.getType().getSimpleName(),
                    field.getName());
        }

        // --- Inspect constructors ---
        System.out.println("\nConstructors:");
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            System.out.printf("  %s %s(%s)%n",
                    Modifier.toString(constructor.getModifiers()),
                    constructor.getName(),
                    getParams(constructor.getParameterTypes()));
        }

        // --- Inspect methods ---
        System.out.println("\nMethods:");
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.printf("  %s %s %s(%s)%n",
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

        System.out.println("\nReflection call result: Industry = " + industryValue);
    }

    private static String getParams(Class<?>[] paramTypes) {
        if (paramTypes.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (Class<?> p : paramTypes)
            sb.append(p.getSimpleName()).append(", ");
        return !sb.isEmpty() ? sb.substring(0, sb.length() - 2) : "";
    }
}
