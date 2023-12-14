package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        Item item = new Item("우산", 10000 , 10);

        Item savedItem = itemRepository.save(item);

        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        Item item1 = new Item("우산", 10000 , 10);
        Item item2 = new Item("신발", 50000 , 32);

        itemRepository.save(item1);
        itemRepository.save(item2);

        List<Item> all = itemRepository.findAll();

        assertThat(all.size()).isEqualTo(2);
        assertThat(all).contains(item1, item2);
    }

    @Test
    void updateItem() {
        Item item = new Item("우산", 10000 , 10);

        Item savedItem = itemRepository.save(item);
        Item item2 = new Item("신발", 50000 , 32);

        itemRepository.update(savedItem.getId(), item2);
        Item findItem = itemRepository.findById(item.getId());

        assertThat(findItem.getItemName()).isEqualTo(item2.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(item2.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(item2.getQuantity());
    }
}