package com.gildedrose;

class GildedRose {
    Item[] items;
    private int days = 1;

    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES = "Backstage passes";
    private static final String CONJURED = "Conjured";
    private static final String SULFURAS = "Sulfuras";

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        boolean update = false;
        if(days == 0) {
            update = true;
            days = 1;
        }else {
            days--;
        }
        for (Item item: items) {
            if (!item.name.contains(AGED_BRIE)
                    && !item.name.contains(BACKSTAGE_PASSES)) {
                if (item.quality > 0) {
                    if(item.name.contains(CONJURED)) {
                        item.quality = item.quality - 1;
                    }
                    if (!item.name.contains(SULFURAS) && !isX(item.name)){
                        item.quality = item.quality - 1;
                    }else if(isX(item.name) && update) {
                        item.quality = item.quality - 1;
                    }
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;

                    if (item.name.contains(BACKSTAGE_PASSES)) {
                        if (item.sellIn < 11) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1;
                            }
                        }

                        if (item.sellIn < 6) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1;
                            }
                        }
                    }
                }
            }

            if (!item.name.contains(SULFURAS)) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (!item.name.contains(AGED_BRIE)) {
                    if (!item.name.contains(BACKSTAGE_PASSES)) {
                        if (item.quality > 0) {
                            if (!item.name.contains(SULFURAS)) {
                                item.quality = item.quality - 1;
                            }
                        }
                    } else {
                        item.quality = 0;
                    }
                } else {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
                }
            }
        }
    }

    private boolean isX(String name) {
        return (name.contains("X") || name.contains("x"));
    }
}
