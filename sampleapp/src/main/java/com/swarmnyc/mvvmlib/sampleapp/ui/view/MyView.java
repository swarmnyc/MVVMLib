package com.swarmnyc.mvvmlib.sampleapp.ui.view;

import android.content.Context;
import android.databinding.Observable;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.swarmnyc.mvvmlib.sampleapp.viewmodel.BindableViewSubViewModel;
import com.swarmnyc.mvvmlib.view.MvvmView;

public class MyView extends MvvmView<BindableViewSubViewModel> {

    private Paint paint;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setViewModel(BindableViewSubViewModel viewModel) {
        super.setViewModel(viewModel);
        viewModel.getValueText().addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                invalidate();
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (this.getViewModel() != null) {
            if (paint == null) {
                paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                paint.setColor(Color.BLUE);
                paint.setStyle(Paint.Style.FILL);
                paint.setTextSize(this.getMeasuredHeight()/2);
            }

            canvas.drawText(getViewModel().getValueText().get(), 0, this.getMeasuredHeight(), paint);
        }
    }
}
