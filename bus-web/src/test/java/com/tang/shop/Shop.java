package com.tang.shop;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author he
 * @since 2022-01.12-22:49
 */
public class Shop {
    private List<String> items = new ArrayList<>();

    public List<String> getItems(String id) {
        Optional<String> itemById = ItemDao.getItemById(id);
        itemById.ifPresent(s -> items.add(s));
        return new ArrayList<>(items);
    }

    public List<String> getItems(List<String> ids) {
        items.addAll(ids.stream().map(ItemDao::getItemById).filter(Optional::isPresent).map(Optional::get).collect(
            Collectors.toList()));
        return new ArrayList<>(items);
    }

    public boolean isExit(String path) {
        File file = new File(path);
        return file.exists();
    }
}
