package com.ms.checkbox;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class CheckBoxGroupView extends View implements View.OnTouchListener {

    private List<CheckText> checkTexts = new ArrayList<>(0);
    private SparseArray<CheckText> checkedTexts = new SparseArray<>(0);
    private int mNotSelect = -1;
    private int disableColor;
    //未选中状态文本的颜色(默认)
    private int checkedTextColor;
    //选中状态文本的颜色
    private int unCheckedTextColor;
    //选中边框填充颜色
    private int checkedStrokeColor;
    //未选中边框填充颜色(默认)
    private int unCheckedStrokeColor;
    //选中填充颜色
    private int checkedFillColor;
    //未选中填充颜色(默认)
    private int unCheckedFillColor;
    //文本之间的间隔距离
    private int textGapWidth;
    //宽度
    private int groupWidth;
    //高度
    private int groupHeight;
    //换行的行高间距
    private int lineHeight;
    //图标的宽度
    private int drawableWidth;
    //图标的高度
    private int drawableHeight;
    //图标与文本之间的间距
    private int drawTextGapWidth;
    //圆角半径
    private int radius;
    //最多选择的个数
    private int maxCheckedNum;
    private boolean repeatCheck;

    private int textPaddingLeft;
    private int textPaddingTop;
    private int textPaddingRight;
    private int textPaddingBottom;

    //选中的图标
    private Drawable checkedDrawable;
    private int checkedRes;
    //默认的图标
    private Drawable unCheckedDrawable;
    private int unCheckedRes;

    //单选
    private static final int SIMPLE = 1;

    //多选
    private static final int MULTI = 2;

    //选中模式
    private int checkModel;

    private Paint textPaint;
    private Paint strokePaint;
    private Paint fillPaint;

    private int downCheckedIndex;

    private CheckedChangeListener listener;
    private final Context mContext;

    public CheckBoxGroupView(Context context) {
        this(context, null);
    }

    public CheckBoxGroupView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckBoxGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initConfig(context, attrs);
    }

    private void initConfig(Context context, AttributeSet attrs) {

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CheckBoxGroupView);

        int textSize = ta.getDimensionPixelSize(R.styleable.CheckBoxGroupView_cb_textSize, 14);
        int textPadding = ta.getDimensionPixelSize(R.styleable.CheckBoxGroupView_cb_textPadding, 0);
        textPaddingLeft = ta.getDimensionPixelSize(R.styleable.CheckBoxGroupView_cb_textPaddingLeft, -1);
        textPaddingTop = ta.getDimensionPixelSize(R.styleable.CheckBoxGroupView_cb_textPaddingTop, -1);
        textPaddingRight = ta.getDimensionPixelSize(R.styleable.CheckBoxGroupView_cb_textPaddingRight, -1);
        textPaddingBottom = ta.getDimensionPixelSize(R.styleable.CheckBoxGroupView_cb_textPaddingBottom, -1);
        textGapWidth = ta.getDimensionPixelSize(R.styleable.CheckBoxGroupView_cb_textGapWidth, 0);
        groupWidth = ta.getDimensionPixelSize(R.styleable.CheckBoxGroupView_cb_groupWidth, 0);
        groupHeight = ta.getDimensionPixelSize(R.styleable.CheckBoxGroupView_cb_groupHeight, 0);
        lineHeight = ta.getDimensionPixelSize(R.styleable.CheckBoxGroupView_cb_lineHeight, 0);
        disableColor = ta.getColor(R.styleable.CheckBoxGroupView_cb_disableColor, Color.GRAY);
        checkedTextColor = ta.getColor(R.styleable.CheckBoxGroupView_cb_checkedTextColor, Color.GREEN);
        unCheckedTextColor = ta.getColor(R.styleable.CheckBoxGroupView_cb_unCheckedTextColor, Color.GRAY);
        checkedStrokeColor = ta.getColor(R.styleable.CheckBoxGroupView_cb_checkedStrokeColor, Color.RED);
        unCheckedStrokeColor = ta.getColor(R.styleable.CheckBoxGroupView_cb_unCheckedStrokeColor, Color.GRAY);
        checkedFillColor = ta.getColor(R.styleable.CheckBoxGroupView_cb_checkedFillColor, Color.RED);
        unCheckedFillColor = ta.getColor(R.styleable.CheckBoxGroupView_cb_unCheckedFillColor, Color.GRAY);
        int strokeWidth = ta.getDimensionPixelSize(R.styleable.CheckBoxGroupView_cb_strokeWidth, 0);
        checkedDrawable = ta.getDrawable(R.styleable.CheckBoxGroupView_cb_checkedDrawable);
        checkedRes = ta.getResourceId(R.styleable.CheckBoxGroupView_cb_checkedRes, 0);
        unCheckedDrawable = ta.getDrawable(R.styleable.CheckBoxGroupView_cb_unCheckedDrawable);
        unCheckedRes = ta.getResourceId(R.styleable.CheckBoxGroupView_cb_unCheckedRes, 0);
        drawTextGapWidth = ta.getDimensionPixelOffset(R.styleable.CheckBoxGroupView_cb_drawTextGapWidth, 0);
        drawableHeight = ta.getDimensionPixelOffset(R.styleable.CheckBoxGroupView_cb_drawableHeight, 0);
        drawableWidth = ta.getDimensionPixelOffset(R.styleable.CheckBoxGroupView_cb_drawableWidth, 0);
        radius = ta.getDimensionPixelSize(R.styleable.CheckBoxGroupView_cb_radius, 5);
        boolean textBold = ta.getBoolean(R.styleable.CheckBoxGroupView_cb_textBold, false);
        repeatCheck = ta.getBoolean(R.styleable.CheckBoxGroupView_cb_repeatCheck, true);
        maxCheckedNum = ta.getInteger(R.styleable.CheckBoxGroupView_cb_maxCheckedNum, -1);
        checkModel = ta.getInt(R.styleable.CheckBoxGroupView_cb_checkModel, SIMPLE);

        ta.recycle();

        if (textPaddingLeft == -1) {
            textPaddingLeft = textPadding;
        }

        if (textPaddingTop == -1) {
            textPaddingTop = textPadding;
        }

        if (textPaddingRight == -1) {
            textPaddingRight = textPadding;
        }

        if (textPaddingBottom == -1) {
            textPaddingBottom = textPadding;
        }

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(textSize);
        textPaint.setColor(unCheckedTextColor);
        textPaint.setFakeBoldText(textBold);

        strokePaint = new Paint();
        strokePaint.setAntiAlias(true);
        strokePaint.setTextSize(strokeWidth);
        strokePaint.setColor(unCheckedStrokeColor);
        strokePaint.setStyle(Paint.Style.STROKE);

        fillPaint = new Paint();
        fillPaint.setAntiAlias(true);
        fillPaint.setTextSize(strokeWidth);
        fillPaint.setColor(unCheckedFillColor);
        fillPaint.setStyle(Paint.Style.FILL);

        setOnTouchListener(this);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(measureHeightByWithLayout(width), MeasureSpec.AT_MOST);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        measureHeightByWithLayout(w);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int lastDownX = (int) event.getX();
        int lastDownY = (int) event.getY();
        if (isEnabled())
            updateTextChecked(lastDownX, lastDownY, event.getAction());
        return checkTexts.size() > 0 && isEnabled();
    }

    /**
     * 更新选择的状态
     *
     * @param touchX 触摸的X坐标
     * @param touchY 触摸的Y坐标
     */
    private synchronized void updateTextChecked(int touchX, int touchY, int action) {
        boolean hasExchange = false;
        int curIndex = -1;
        for (int index = 0; index < checkTexts.size(); index++) {
            if (index == mNotSelect)
                continue;
            CheckText text = checkTexts.get(index);
            if (text.isChecked && !repeatCheck) {
                continue;
            }
            if (text.inRange(touchX, touchY)) {
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        if (checkedTexts.get(index) == null) {
                            text.setChecked(true);
                            if (checkModel == SIMPLE)
                                cleanRadioChecked();
                            checkedTexts.put(index, text);
                            hasExchange = true;
                            curIndex = index;
                            downCheckedIndex = -1;
                        } else {
                            curIndex = -1;
                            downCheckedIndex = index;
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (checkedTexts.get(index) == null) {
                            text.setChecked(true);
                            if (checkModel == SIMPLE)
                                cleanRadioChecked();
                            checkedTexts.put(index, text);
                            curIndex = index;
                            hasExchange = true;
                        } else {
                            curIndex = -1;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (downCheckedIndex == index) {
                            if (checkedTexts.get(index) != null) {
                                text.setChecked(false);
                                checkedTexts.delete(index);
                                hasExchange = true;
                            }
                        }
                        break;
                }
            }
        }

        if (checkedTexts.size() > maxCheckedNum && curIndex != -1) {
            checkTexts.get(curIndex).setChecked(false);
            checkedTexts.delete(curIndex);
        }

        if (hasExchange) {
            requestInvalidate();
        }

        if (listener != null && hasExchange) {
            if (curIndex < 0)
                listener.onCheckedChange(curIndex, "");
            else
                listener.onCheckedChange(curIndex, checkTexts.get(curIndex).getText());
        }
    }

    private void cleanRadioChecked() {

        for (int index = 0; index < checkedTexts.size(); index++) {
            CheckText text = checkedTexts.get(checkedTexts.keyAt(index));
            text.setChecked(false);
        }
        checkedTexts.clear();
    }

    /**
     * 重新计算每个文本的位置
     */
    private synchronized int measureHeightByWithLayout(int width) {

        if (checkTexts == null || checkTexts.size() <= 0)
            return 0;

        //计算所有文本中的最大高度
        int maxHeight = computerMaxTextHeight();

        //上一次换行停留的位置index
        int priorColIndex = 0;
        //上一行停留的位置
        int priorRawXPosion = 0;
        //当前最新的行数
        int curRow = 0;
        //开始计算每个文本的位置和宽高
        for (int index = 0; index < checkTexts.size(); index++) {
            CheckText text = checkTexts.get(index);
            Rect rect = new Rect();
            textPaint.getTextBounds(text.getText(), 0, text.getText().length(), rect);
            text.setTextWidth(rect.width());
            text.setTextHeight(maxHeight);
            if (groupWidth != 0) {
                text.setWidth(groupWidth);
            } else {
                text.setWidth(rect.width() + textPaddingLeft + textPaddingRight + drawableWidth + drawTextGapWidth);
            }
            if (groupHeight != 0) {
                text.setHeight(groupHeight);
            } else {
                if (drawableHeight < maxHeight + textPaddingBottom + textPaddingTop)
                    text.setHeight(maxHeight + textPaddingBottom + textPaddingTop);
                else
                    text.setHeight(drawableHeight);
            }
            //判断总长度是否超过了view的宽度,超过则自动换行
            int colWidth = curTextColWidth(index, priorColIndex);
            if (colWidth > width - getPaddingRight()) {
                curRow++;
                priorRawXPosion += checkTexts.get(priorColIndex).getHeight();
                colWidth = text.getWidth() + getPaddingLeft();
                priorColIndex = index;
            }
            text.setCenterX(colWidth - text.getWidth() / 2);
            text.setCenterY(getPaddingTop() + priorRawXPosion + text.getHeight() / 2 + curRow * lineHeight);
        }

        if (checkTexts.size() <= 0) {
            return 0;
        } else {
            CheckText checkText = checkTexts.get(checkTexts.size() - 1);
            return checkText.getCenterY() + checkText.getHeight() / 2 + getPaddingBottom();
        }
    }

    //计算文本的最大高度
    private int computerMaxTextHeight() {
        int maxheight = 0;
        for (CheckText text : checkTexts) {
            Rect rect = new Rect();
            textPaint.getTextBounds(text.getText(), 0, text.getText().length(), rect);
            if (maxheight < rect.height()) {
                maxheight = rect.height();
            }
        }
        return maxheight;
    }

    /**
     * 计算当前列的总文本长度
     *
     * @param targetIndex   目标的位置
     * @param priorColIndex 上一次换行停留的位置index
     */
    private int curTextColWidth(int targetIndex, int priorColIndex) {
        int colWidth = 0;
        for (int index = priorColIndex; index <= targetIndex; index++) {
            colWidth += checkTexts.get(index).getWidth();
        }
        return colWidth + getPaddingLeft() + (targetIndex - priorColIndex) * textGapWidth;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (checkTexts == null || checkTexts.size() <= 0)
            return;

        for (int i = 0; i < checkTexts.size(); i++) {
            if (i == mNotSelect) {
                drawBg(canvas, checkTexts.get(i), disableColor);
                drawStroke(canvas, checkTexts.get(i));
                drawText(canvas, checkTexts.get(i));
                drawIcon(canvas, checkTexts.get(i));
                continue;
            }
            drawBg(canvas, checkTexts.get(i));
            drawStroke(canvas, checkTexts.get(i));
            drawText(canvas, checkTexts.get(i));
            drawIcon(canvas, checkTexts.get(i));
        }
    }

    /**
     * 绘制文本的背景
     */
    private void drawBg(Canvas canvas, CheckText text) {

        RectF fillRectF = new RectF();
        final int halfWidth = text.getWidth() / 2;
        final int halfHeight = text.getHeight() / 2;
        fillRectF.left = text.getCenterX() - halfWidth;
        fillRectF.top = text.getCenterY() - halfHeight;
        fillRectF.right = text.getCenterX() + halfWidth;
        fillRectF.bottom = text.getCenterY() + halfHeight;

        if (!text.isChecked()) {
            fillPaint.setColor(unCheckedFillColor);
        } else {
            fillPaint.setColor(checkedFillColor);
        }
        canvas.drawRoundRect(fillRectF, radius, radius, fillPaint);
    }

    /**
     * 绘制文本的背景
     */
    private void drawBg(Canvas canvas, CheckText text, int color) {

        RectF fillRectF = new RectF();
        final int halfWidth = text.getWidth() / 2;
        final int halfHeight = text.getHeight() / 2;
        fillRectF.left = text.getCenterX() - halfWidth;
        fillRectF.top = text.getCenterY() - halfHeight;
        fillRectF.right = text.getCenterX() + halfWidth;
        fillRectF.bottom = text.getCenterY() + halfHeight;

        fillPaint.setColor(color);
        canvas.drawRoundRect(fillRectF, radius, radius, fillPaint);
    }

    /**
     * 绘制文本的边框
     */
    private void drawStroke(Canvas canvas, CheckText text) {

        RectF strokeRectF = new RectF();
        final int halfWidth = text.getWidth() / 2;
        final int halfHeight = text.getHeight() / 2;
        strokeRectF.left = text.getCenterX() - halfWidth;
        strokeRectF.top = text.getCenterY() - halfHeight;
        strokeRectF.right = text.getCenterX() + halfWidth;
        strokeRectF.bottom = text.getCenterY() + halfHeight;

        if (!text.isChecked()) {
            strokePaint.setColor(unCheckedStrokeColor);
        } else {
            strokePaint.setColor(checkedStrokeColor);
        }
        canvas.drawRoundRect(strokeRectF, radius, radius, strokePaint);
    }

    /**
     * 绘制文本
     */
    private void drawText(Canvas canvas, CheckText text) {
        Rect targetRect = new Rect();
        final int halfWidth = text.getWidth() / 2;
        final int halfHeight = text.getHeight() / 2;
        targetRect.left = text.getCenterX() + halfWidth - text.getTextWidth() - textPaddingRight;
        targetRect.top = text.getCenterY() - halfHeight;
        targetRect.right = text.getCenterX() + halfWidth - textPaddingRight;
        targetRect.bottom = text.getCenterY() + halfHeight;

        textPaint.setColor(text.isChecked() ? checkedTextColor : unCheckedTextColor);
        Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
        int baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
        // 实现水平居中，drawText对应改为传入targetRect.centerX(),也可以不设置，默认为left,自己计算
        textPaint.setTextAlign(Paint.Align.CENTER);
        if (groupWidth != 0) {
            canvas.drawText(text.getText(), text.getCenterX() + drawableWidth / 2 + drawTextGapWidth / 2, baseline, textPaint);
        } else {
            canvas.drawText(text.getText(), targetRect.centerX(), baseline, textPaint);
        }
    }

    private void drawIcon(Canvas canvas, CheckText text) {
        Bitmap bitmap;
        if (checkedDrawable != null && unCheckedDrawable != null) {
            Drawable drawable = text.isChecked() ? checkedDrawable : unCheckedDrawable;
            bitmap = drawableToZoomBitmap(drawable, drawableWidth, drawableHeight);
            if (groupWidth != 0) {
                canvas.drawBitmap(bitmap, text.getCenterX() - drawableWidth - drawableWidth / 3 + textPaddingLeft - drawTextGapWidth / 2,
                        text.getCenterY() - drawableHeight / 2, null);
            } else {
                canvas.drawBitmap(bitmap, text.getCenterX() - text.getWidth() / 2 + textPaddingLeft, text.getCenterY() - drawableHeight / 2, null);
            }
        } else {
            if (checkedRes != 0 && unCheckedRes != 0) {
                int res = text.isChecked() ? checkedRes : unCheckedRes;
                Drawable drawable = mContext.getResources().getDrawable(res);
                bitmap = drawableToZoomBitmap(drawable, drawableWidth, drawableHeight);
                if (groupWidth != 0) {
                    canvas.drawBitmap(bitmap, text.getCenterX() - drawableWidth - drawableWidth / 3 + textPaddingLeft - drawTextGapWidth / 2,
                            text.getCenterY() - drawableHeight / 2, null);
                } else {
                    canvas.drawBitmap(bitmap, text.getCenterX() - text.getWidth() / 2 + textPaddingLeft, text.getCenterY() - drawableHeight / 2, null);
                }
            }
        }
    }

    /**
     * dispatchDraw()-->Draw()-->onDraw();
     * 重新绘制
     */
    private void requestInvalidate() {
        if (Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId())
            invalidate();
        else
            postInvalidate();
    }

    private static class CheckText {
        //位置
        private int index;
        //中心X坐标
        private int centerX;
        //中心Y坐标
        private int centerY;
        //视图的宽度
        private int width;
        //文本的宽度
        private int textWidth;
        //视图的高度
        private int height;
        //文本的高度
        private int textHeight;
        //文本信息
        private String text;
        //文本字体大小
        private int textSize;
        //是否被选中
        private boolean isChecked;

        public int getCenterX() {
            return centerX;
        }

        public void setCenterX(int centerX) {
            this.centerX = centerX;
        }

        public int getCenterY() {
            return centerY;
        }

        public void setCenterY(int centerY) {
            this.centerY = centerY;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getTextSize() {
            return textSize;
        }

        public void setTextSize(int textSize) {
            this.textSize = textSize;
        }

        public boolean inRange(int touchX, int touchY) {
            boolean inX = (touchX >= centerX - width / 2) && (touchX <= centerX + width / 2);
            boolean inY = (touchY >= centerY - height / 2) && (touchY <= centerY + height / 2);
            return inX && inY;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getTextWidth() {
            return textWidth;
        }

        public void setTextWidth(int textWidth) {
            this.textWidth = textWidth;
        }

        public int getTextHeight() {
            return textHeight;
        }

        public void setTextHeight(int textHeight) {
            this.textHeight = textHeight;
        }
    }

    /**
     * drawable 缩放
     */
    private static Bitmap drawableToZoomBitmap(Drawable drawable, int w, int h) {
        // 取 drawable 的长宽
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        // drawable转换成bitmap
        Bitmap oldbmp = drawableToBitmap(drawable);
        // 创建操作图片用的Matrix对象
        Matrix matrix = new Matrix();
        // 计算缩放比例
        float sx = ((float) w / width);
        float sy = ((float) h / height);
        // 设置缩放比例
        matrix.postScale(sx, sy);
        // 建立新的bitmap，其内容是对原bitmap的缩放后的图
        return Bitmap.createBitmap(oldbmp, 0, 0, width, height, matrix, true);
    }

    /**
     * Drawable转换成Bitmap
     */
    private static Bitmap drawableToBitmap(Drawable drawable) {
        // 取 drawable 的长宽
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        // 取 drawable 的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    private List<CheckText> getCheckTexts() {
        return checkTexts;
    }

    private void updateCheckTexts(List<CheckText> checkTexts) {
        if (checkTexts != null && checkTexts.size() > 0) {
            this.checkTexts.clear();
            this.checkTexts.addAll(checkTexts);
            for (int index = 0; index < this.checkTexts.size(); index++) {
                CheckText checkText = this.checkTexts.get(index);
                if (checkText.isChecked)
                    checkedTexts.put(index, checkText);
            }
            requestLayout();
        }
    }

    public void setNewData(List<String> data) {
        if (data != null && data.size() > 0) {
            if (checkModel == MULTI) {
                if (maxCheckedNum < 0) {
                    maxCheckedNum = data.size();
                }
            } else {
                maxCheckedNum = 1;
            }
            List<CheckText> tags = new ArrayList<>();
            for (String tagText : data) {
                CheckText tag = new CheckText();
                tag.setText(tagText);
                tags.add(tag);
            }
            updateCheckTexts(tags);
        }
    }

    public List<String> getCheckedData() {
        List<String> data = new ArrayList<>();
        for (int index = 0; index < checkedTexts.size(); index++) {
            CheckText text = checkedTexts.get(checkedTexts.keyAt(index));
            data.add(text.text);
        }
        return data;
    }

    public void setTextEnable(int position) {
        this.mNotSelect = position;
        invalidate();
    }

    public void setSelectIndex(int position) {
        getCheckTexts().get(position).setChecked(true);
        for (int index = 0; index < this.checkTexts.size(); index++) {
            CheckText checkText = this.checkTexts.get(index);
            if (checkText.isChecked)
                checkedTexts.put(index, checkText);
        }
        invalidate();
    }

    public void resetStatus(String data) {
        for (int index = 0; index < this.checkTexts.size(); index++) {
            CheckText checkText = this.checkTexts.get(index);
            if (checkText.text.equals(data)) {
                getCheckTexts().get(index).setChecked(false);
                checkedTexts.remove(index);
            }
        }
        invalidate();
    }

    public void clear() {
        cleanRadioChecked();
        requestInvalidate();
    }

    public void setCheckedListener(CheckedChangeListener listener) {
        this.listener = listener;
    }

    public interface CheckedChangeListener {
        void onCheckedChange(int position, String data);
    }

}
