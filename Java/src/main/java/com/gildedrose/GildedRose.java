package com.gildedrose;

class GildedRose {
    Item[] items;
    int days = 1;
    boolean update = false;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        if(days == 0) {
            update = true;
            days = 1;
        }else {
            update = false;
            days--;
        }
        for (Item item: items) {
            if (!item.name.equals("Aged Brie")
                    && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (item.quality > 0) {
                    if(item.name.contains("Conjured")) {
                        item.quality = item.quality - 1;
                    }
                    if (!item.name.equals("Sulfuras, Hand of Ragnaros") && !isX(item.name)){
                        item.quality = item.quality - 1;
                    }else if(isX(item.name) && update) {
                        item.quality = item.quality - 1;
                    }
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;

                    if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
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

            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (!item.name.equals("Aged Brie")) {
                    if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (item.quality > 0) {
                            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
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
