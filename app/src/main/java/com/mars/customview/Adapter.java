package com.mars.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;

import androidx.recyclerview.widget.RecyclerView;

import com.mars.R;
import com.mars.model.Item;
import com.mars.model.ItemListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Adapter extends BaseExpandableListAdapter
{

    private Context context;
    private ExpandableListView lv;
    private ArrayList<Item> items;
    private ParentAdapter adapter;
    private ImageButton ib_collapse;
    private HashMap<Integer,ImageButton> imagebuttons;



    public Adapter(ParentAdapter adapter, final ImageButton ib_collapse, ExpandableListView lv, Context context, ArrayList<Item> items)
    {
        this.context = context;
        this.items = items;
        this.lv = lv;
        this.adapter = adapter;
        this.ib_collapse = ib_collapse;
        this.imagebuttons = new HashMap<>();
    }

    @Override
    public int getGroupCount() {
        return items.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return items.get(i).getChildren().size();
    }

    @Override
    public Object getGroup(int i) {
        return items.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return items.get(i).getChildren().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int i, boolean b, View view, final ViewGroup viewGroup)
    {
        final Item item = ((Item)getGroup(i));

        String listTitle = item.getTitle();
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_header, null);
        }
        final UICheckBox ui_checkbox = view.findViewById(R.id.ui_checkbox);
        ui_checkbox.setStatus(item.getState());
        ui_checkbox.setTypeface(null, Typeface.BOLD);
        ui_checkbox.setText(listTitle);
        final ImageButton ib_more = view.findViewById(R.id.ib_more);
        imagebuttons.put(i,ib_more);
        ib_more.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View view)
        {
            boolean isExpanded = lv.isGroupExpanded(i);

            if(isExpanded)
            {
                lv.collapseGroup(i);
            }
            else
            {
                lv.expandGroup(i);
            }


            ib_more.setImageDrawable(context.getResources().getDrawable( lv.isGroupExpanded(i)?R.drawable.less:R.drawable.more));
            ib_collapse.setImageDrawable(ib_collapse.getContext().getResources().getDrawable( isAllCollapsed()?R.drawable.less:R.drawable.more));


        }

    });

        item.setListener(new ItemListener() {
            @Override
            public void onChangeState(int state) {
                    ui_checkbox.setStatus(state);        ;
            }
        });

        ui_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                if(item!=null)
                {
                    item.setState(item.getState()==Item.CHECKED?Item.NONE:Item.CHECKED);
                }
                if(!lv.isGroupExpanded(i))
                {
                    adapter.notifyDataSetChanged();
                }

            }
        });



        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        Item p = ((Item)getGroup(i));
        final Item item = p.getChildren().get( i1);
            String listTitle = item.getTitle();
            if (view == null) {
                LayoutInflater layoutInflater = (LayoutInflater) this.context.
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = layoutInflater.inflate(R.layout.child_item, null);
            }

            final UICheckBox ui_checkbox = view.findViewById(R.id.ui_checkbox);
            ui_checkbox.setTypeface(null, Typeface.BOLD);
            ui_checkbox.setText(listTitle);
            ui_checkbox.setStatus(item.getState());
            item.setListener(new ItemListener() {
            @Override
            public void onChangeState(int state) {
                    ui_checkbox.setStatus(state);        ;
                    notifyDataSetChanged();
                }
            });
            ui_checkbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(item!=null)
                    {
                        item.setState(item.getState()==Item.CHECKED?Item.NONE:Item.CHECKED);
                    }

                }
            });




            return view;

    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    private boolean isAllCollapsed() {
        for (int ctr = 0; ctr < items.size(); ctr++)
        {
            if(!lv.isGroupExpanded(ctr))
            {
                return false;
            }
        }
        return true;
    }

    public void updateMoreButtons()
    {
        Log.i("UPDATING MORE","UPDATING BUTTONS");
        int ctr = 0;
        for(ImageButton ib:imagebuttons.values())
        {
            Log.i("UPDATING MORE",""+ctr+":"+lv.isGroupExpanded(ctr));
            ib.setImageDrawable(context.getResources().getDrawable( lv.isGroupExpanded(ctr)?R.drawable.less:R.drawable.more));
            ctr++;
        }


    }


}
