package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void sulfurasTest() {
        Item[] items = new Item[] { new Item("Sulfuras", 5, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Sulfuras", app.items[0].name);
        assertEquals(5, app.items[0].sellIn);
        assertEquals(80, app.items[0].quality);
    }

    @Test
    void conjuredTest() {
        Item[] items = new Item[] { new Item("Conjured item 1", 10, 8) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Conjured item 1", app.items[0].name);
        assertEquals(9, app.items[0].sellIn);
        assertEquals(6, app.items[0].quality);
    }

    @Test
    void agedBrieTest() {
        Item[] items = new Item[] { new Item("Aged Brie cheese", 6, 45) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Aged Brie cheese", app.items[0].name);
        assertEquals(5, app.items[0].sellIn);
        assertEquals(46, app.items[0].quality);
    }

    @Test
    void xItem() {
        Item[] items = new Item[] { new Item("Exxon gift card", 10, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Exxon gift card", app.items[0].name);
        assertEquals(9, app.items[0].sellIn);
        assertEquals(10, app.items[0].quality);

        app.updateQuality();
        assertEquals("Exxon gift card", app.items[0].name);
        assertEquals(8, app.items[0].sellIn);
        assertEquals(9, app.items[0].quality);
    }

    @Test
    void backstagePassesTest() {
        Item[] items = new Item[] { new Item("Backstage passes to Band", 10, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to Band", app.items[0].name);
        assertEquals(9, app.items[0].sellIn);
        assertEquals(7, app.items[0].quality);
    }

    @Test
    void itemTest() {
        Item[] items = new Item[] { new Item("Eggs", 10, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Eggs", app.items[0].name);
        assertEquals(9, app.items[0].sellIn);
        assertEquals(4, app.items[0].quality);
    }

    @Test
    void itemQuality50Test() {
        Item[] items = new Item[] { new Item("Backstage passes", 10, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes", app.items[0].name);
        assertEquals(9, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }


}
