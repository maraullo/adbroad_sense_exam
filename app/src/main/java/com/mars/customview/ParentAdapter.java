package com.mars.customview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mars.R;
import com.mars.model.Item;
import com.mars.model.ItemListener;

import java.util.ArrayList;

public class ParentAdapter extends RecyclerView.Adapter<ParentViewHolder>
{

    Adapter adapter;
    ArrayList<Item> items;
    Context context;
    boolean isExpanded = false;

    public ParentAdapter(Context context,ArrayList<Item> items)
    {
        Log.i("ITEMS",items.size()+"");
        this.items = items;
        this.context = context;
    }


    @NonNull
    @Override
    public ParentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.parent_item,parent,false);

        return new ParentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ParentViewHolder holder, final int position)
    {
        items.get(position).setListener(new ItemListener() {
            @Override
            public void onChangeState(int state) {
                holder.getUi_checkbox().setStatus(state);
            }
        });
        holder.getUi_checkbox().setText(items.get(position).getTitle());
        holder.getUi_checkbox().setStatus(items.get(position).getState());
        holder.getUi_checkbox().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Item item = items.get(position);
                    if(item!=null)
                    {
                            item.setState(item.getState()==Item.CHECKED?Item.NONE:Item.CHECKED);
                    }

            }
        });

        holder.getIb_more().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                boolean result = true;
                for (int ctr = 0; ctr < items.get(0).getChildren().size(); ctr++)
                {
                    if(!holder.getExp_lv_item().isGroupExpanded(ctr))
                    {

                        result = false;
                        break;
                    }
                }

                for(int ctr = 0; ctr<items.get(position).getChildren().size();ctr++)
                {
                    if(result)
                    {
                        holder.getExp_lv_item().collapseGroup(ctr);
                    }
                    else
                    {
                        holder.getExp_lv_item().expandGroup(ctr);
                    }
                }
                isExpanded = !isExpanded;
                holder.getIb_more().setImageDrawable(context.getResources().getDrawable(isExpanded?R.drawable.less:R.drawable.more));
                adapter.updateMoreButtons();
            }
        });
        adapter =new Adapter(this,holder.getIb_more(),holder.getExp_lv_item(),context,items.get(position).getChildren());
        holder.getExp_lv_item().setAdapter(adapter);

    }


    @Override
    public int getItemCount() {
        return items.size();
    }

}
