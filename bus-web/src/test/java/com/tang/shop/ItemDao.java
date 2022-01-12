package com.tang.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author he
 * @since 2022-01.12-22:49
 */
public class ItemDao {
    private static List<String> items = new ArrayList<>();

    static {
        items.add("1");
        items.add("2");
        items.add("3");
        items.add("4");
    }

    public static Optional<String> getItemById(String id) {
        return items.stream().filter(s -> s.equals(id)).findFirst();
    }
}
