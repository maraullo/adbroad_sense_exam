package com.mars.customview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;

import com.mars.R;

public class UICheckBox extends AppCompatCheckBox
{
    public static final int NONE = 0;
    public static final int INTERMEDIATE = 1;
    public static final int CHECKED = 2;
    int status;

    public UICheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public int getStatus() {
        return status;
    }


    public void setStatus(int status) {
        this.status = status;
        switch (status)
        {
            case NONE:
                setButtonDrawable(R.drawable.x);
                break;
            case INTERMEDIATE:
                setButtonDrawable(R.drawable.circle);
                break;
            case CHECKED:
                setButtonDrawable(R.drawable.selected);
                break;
        }


        Log.i("STATUS",status+"");
    }

    @Nullable
    @Override
    public Drawable getButtonDrawable()
    {



        return super.getButtonDrawable();
    }
}
