package com.mars.model;

import com.mars.customview.UICheckBox;

import java.util.ArrayList;

public class Item
{
    //===========================================Variable===========================================
    //-------------------------------------------Constant-------------------------------------------
    public static final int NONE = 0;
    public static final int INTERMEDIATE = 1;
    public static final int CHECKED = 2;
    //----------------------------------------------------------------------------------------------
    //-------------------------------------------Instance-------------------------------------------
    int level;
    int state = NONE;
    String title;
    Item parent;
    ArrayList<Item> children;
    //Listens to changes in the item
    ItemListener listener;
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //===========================================Constructor========================================
    public Item(String title)
    {
        this.title = title;
        this.children = new ArrayList<>();
    }
    //==============================================================================================
    //=============================================Method===========================================
    //---------------------------------------------Getter-------------------------------------------
    public int getLevel() {
        return level;
    }
    public int getState() {
        return state;
    }
    public String getTitle() {
        return title;
    }
    public ItemListener getListener() { return listener; }

    public Item getParent() {
        return parent;
    }

    //----------------------------------------------------------------------------------------------
    //---------------------------------------------Setter-------------------------------------------
    public void setLevel(int level) {
        this.level = level;
    }
    public void setState(int state) {
        if(this.state!=state)
        {
            this.state = state;
            if(listener!=null)
            {
                listener.onChangeState(state);
            }
            if(state==CHECKED)
            {
                changeChildrenState(CHECKED);
                if(parent!=null)
                {
                    if(parent.isChildrenState(CHECKED))
                    {
                        parent.setState(CHECKED);
                    }
                    else
                    {
                        parent.setState(INTERMEDIATE);
                    }
                }
            }
            if(state==NONE)
            {
                changeChildrenState(NONE);
                if(parent!=null) {
                    if(parent.isChildrenState(NONE))
                    {
                        parent.setState(NONE);
                    }
                    else
                    {
                        parent.setState(INTERMEDIATE);
                    }

                }

            }
            if(state==INTERMEDIATE)
            {

                if(parent!=null) {
                    parent.setState(INTERMEDIATE);
                }

            }
        }


    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setListener(ItemListener listener) {
        this.listener = listener;
    }

    public void setParent(Item parent) {
        this.parent = parent;
    }

    //to avoid memory leaks
    public void removeListener(){this.listener = null;}
    //----------------------------------------------------------------------------------------------
    //---------------------------------------------Children-----------------------------------------
    public ArrayList<Item> getChildren() {
        return children;
    }
    public void setChildren(ArrayList<Item> children) {
        this.children = children;
    }
    //Add additional Children
    public int addChild(Item item)
    {
        //Avoid null pointer exception
        if(children==null)
        {
            children = new ArrayList<>();
        }
        //Avoid duplicates
        if(!children.contains(item))
        {
            children.add(item);
        }
        item.setParent(this);
        return children.size();
    }
    //Remove a specific child
    public int removeChild(Item item)
    {
        item.setParent(null);
        if(children==null)
        {
            children = new ArrayList<>();
            //Returns -1 meaning the list has not yet been initialized
            return -1;
        }
        else
        {
            if(children.contains(item))
            {
                children.remove(item);
                return children.size();
            }
            else
            {
                //Returns -2 meaning the list does not contain the item to be removed
                return  -2;
            }
        }

    }
    //Change the state of all Children
    public void changeChildrenState(int state)
    {
        for(Item child:children)
        {
            child.setState(state);
        }
    }
    public boolean isChildrenState(int state)
    {
        for(Item i:children)
        {
            if(i.getState()!=state)
            {
                return false;
            }
        }
        return true;
    }
    //----------------------------------------------------------------------------------------------
    //==============================================================================================

}
