package com.mars.presenter;

import com.mars.model.Item;

import java.util.ArrayList;
import java.util.UUID;

public class ItemInitializer
{
    public ArrayList<Item> generateData(int level)
    {
        ArrayList<Item> topLevel = new ArrayList<>();
        Item item = new Item("All");
        topLevel.add(item);
        for(int ctr = 0; ctr <level;ctr++)
        {

            Item parent = new Item(UUID.randomUUID().toString());
            item.addChild(parent);

            for(int ctr2 = 0; ctr2 < level; ctr2++)
            {
                Item i = new Item("Item "+(ctr2+1));
                parent.addChild(i);
            }
        }
        return topLevel;

    }

}
