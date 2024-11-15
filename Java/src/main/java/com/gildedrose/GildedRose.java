package com.gildedrose;

class GildedRose {
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES = "Backstage passes";
    private static final String CONJURED = "Conjured";
    private static final String SULFURAS = "Sulfuras";
    Item[] items;
    private int days = 1;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        boolean canUpdateItemXQuality = false;
        if (days == 0) {
            canUpdateItemXQuality = true;
            days = 1;
        } else {
            days--;
        }
        for (Item item : items) {

            //Sulfuras not sold, quality does not change, no change needed
            if (isSulfuras(item.name)) {
                continue;
            }

            if (!isAgedBrie(item.name) && !isBackstagePasses(item.name)) {
                if (isItemQualityPositive(item)) {
                    reduceQuality(item, canUpdateItemXQuality);
                }
            } else {
                increaseQuality(item);
            }

            //sell in reduces by 1 for all but Sulfuras
            item.sellIn = item.sellIn - 1;

            if (item.sellIn < 0) {
                updateQualityForItemsPastSellIn(item);
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

    private void reduceQuality(Item item, boolean canUpdateItemXQuality) {
        if (isConjured(item.name)) {
            //conjured item quality will reduce by 2
            item.quality = item.quality - 1;
        }
        if (!isX(item.name) || (isX(item.name) && canUpdateItemXQuality)) {
            //reduce all eligible items quality daily or item "x/X" by 1 every 2 days
            item.quality = item.quality - 1;
        }
    }

    private void increaseQuality(Item item) {
        if (isItemQualityBelowMax(item)) {
            //Aged brie + 1
            //Backstage passes can go upto +3 based on demand
            item.quality = item.quality + 1;

            if (isBackstagePasses(item.name)) {
                if (item.sellIn < 11) {
                    if (isItemQualityBelowMax(item)) {
                        item.quality = item.quality + 1;
                    }
                }

                if (item.sellIn < 6) {
                    if (isItemQualityBelowMax(item)) {
                        item.quality = item.quality + 1;
                    }
                }
            }
        }
    }

    private void updateQualityForItemsPastSellIn(Item item) {
        if (!isAgedBrie(item.name)) {
            if (!isBackstagePasses(item.name)) {
                if (isItemQualityPositive(item)) {
                    item.quality = item.quality - 1;
                }
            } else {
                item.quality = 0;
            }
        } else {
            if (isItemQualityBelowMax(item)) {
                item.quality = item.quality + 1;
            }
        }
    }

    private boolean isItemQualityPositive(Item item) {
        return item.quality > 0;
    }

    private boolean isItemQualityBelowMax(Item item) {
        return item.quality < 50;
    }
}
