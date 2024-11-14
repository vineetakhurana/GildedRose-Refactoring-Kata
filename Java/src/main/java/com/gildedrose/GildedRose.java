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
        boolean canUpdateItemXQuality = false;
        if(days == 0) {
            canUpdateItemXQuality = true;
            days = 1;
        }else {
            days--;
        }
        for (Item item: items) {
            String name = item.name;
            if (!isAgedBrie(name)
                    && !isBackstagePasses(name)) {
                if (item.quality > 0) {
                    if(isConjured(name)) {
                        item.quality = item.quality - 1;
                    }
                    if (!isSulfuras(name) && !isX(name)){
                        item.quality = item.quality - 1;
                    }else if(isX(name) && canUpdateItemXQuality) {
                        item.quality = item.quality - 1;
                    }
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;

                    if (isBackstagePasses(name)) {
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

            if (!isSulfuras(name)) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (!isAgedBrie(name)) {
                    if (!isBackstagePasses(name)) {
                        if (item.quality > 0) {
                            if (!isSulfuras(name)) {
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

    private boolean isAgedBrie(String name) {
        return name.contains(AGED_BRIE);
    }

    private boolean isSulfuras(String name) {
        return name.contains(SULFURAS);
    }

    private boolean isBackstagePasses(String name) {
        return name.contains(BACKSTAGE_PASSES);
    }

    private boolean isConjured(String name) {
        return name.contains(CONJURED);
    }
}
