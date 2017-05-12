# 一个类似于radioGroup和checkGroup 和流动标签tagClouds 一体管理的组件
自定义继承View,可直接放置到listview等的子类视图中  

主要特点：
* 可用作显示给用户单选或者多选；  

* 可自定义显示的模式：无边框，有边框，有图标和隐藏图标几种组合模式  

* 流动布局，每一行的显示个数可根据显示的数据和屏幕的宽度自适应换行  

* 设置最大选中个数。在流动标签中很实用 

`class CheckBoxGroupView extends View`  


代码和效果说明：mainActivity  
	CheckBoxGroupView checkTextGroupView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkTextGroupView= (CheckBoxGroupView) findViewById(R.id.checkTextGroupView);
        checkTextGroupView.updateCheckTexts(initList());
    }

    public List<CheckBoxGroupView.CheckText> initList(){

        List<CheckBoxGroupView.CheckText> list=new ArrayList<CheckBoxGroupView.CheckText>();
        for(int index=0;index<10;index++){
            CheckBoxGroupView.CheckText checkText=new CheckBoxGroupView.CheckText();
            checkText.setText("中文"+index);
            list.add(checkText);
        }

        return list;
    }
  
## 这里列出几种常用的模式,更多拼凑的模式你可以慢慢尝试下
#### 无边框单选模式(多选修改*app:checkModel="MULTI"*) 

	<com.checkbox.CheckBoxGroupView
        android:id="@+id/checkTextGroupView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:checkedTextColor="#009EF2"
        app:textPaddingLeft="16dp"
        app:textPaddingRight="16dp"
        app:textPaddingButtom="12dp"
        app:textPaddingTop="12dp"
        app:lineHeight="12dp"
        app:strokeModel="GONE"
        app:checkModel="SIMPLE"
        app:strokeWidth="1px"
        app:textGapWidth="10dp"
        app:textSize="16sp"
        app:unCheckedTextColor="@color/black_overlay" />


![image](https://github.com/igeek-YZ/TextCheckGroupView/blob/master/gifs/simple_gone.gif )  

#### 选中边框单选模式(多选修改*app:checkModel="MULTI"*)  

	<com.igeek.textcheckgroupview.CheckBoxGroupView
        android:id="@+id/checkTextGroupView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:checkedStrokeColor="@color/colorPrimary"
        app:checkedTextColor="#009EF2"
        app:textPaddingLeft="16dp"
        app:textPaddingRight="16dp"
        app:textPaddingButtom="12dp"
        app:textPaddingTop="12dp"
        app:lineHeight="12dp"
        app:strokeModel="GONE_STROKE"
        app:checkModel="SIMPLE"
        app:strokeWidth="1px"
        app:textGapWidth="10dp"
        app:textSize="16sp"
        app:unCheckedStrokeColor="@color/gray_efeff4"
        app:unCheckedTextColor="@color/black_overlay" />

![image](https://github.com/igeek-YZ/TextCheckGroupView/blob/master/gifs/simple_gone_Stroke.gif )  

#### 选中边框多选模式
![image](https://github.com/igeek-YZ/TextCheckGroupView/blob/master/gifs/mulit_gone_Stroke.gif )

#### 选中边框带图标单选模式(多选修改*app:checkModel="MULTI"*) 

	<com.igeek.textcheckgroupview.CheckBoxGroupView
        android:id="@+id/checkTextGroupView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:checkedStrokeColor="@color/colorPrimary"
        app:checkedTextColor="#009EF2"
        app:textPaddingLeft="16dp"
        app:textPaddingRight="16dp"
        app:textPaddingButtom="12dp"
        app:textPaddingTop="12dp"
        app:lineHeight="12dp"
        app:strokeModel="GONE_STROKE"
        app:checkModel="SIMPLE"
        app:strokeWidth="1px"
        app:textGapWidth="10dp"
        app:textSize="16sp"
        app:drawTextGapWidth="8dp"
        app:drawableWidth="20dp"
        app:drawableHeight="20dp"
        app:unCheckedDrawable="@mipmap/ic_radio_btn_unchecked_black_24dp"
        app:checkedDrawable="@mipmap/ic_radio_btn_checked_black_24dp"
        app:unCheckedStrokeColor="@color/gray_efeff4"
        app:unCheckedTextColor="@color/black_overlay" />


![image](https://github.com/igeek-YZ/TextCheckGroupView/blob/master/gifs/simple_icon_gone_Stroke.gif )  

#### 属性说明
	<declare-styleable name="CheckBoxGroupView">
        <!--文本字体大小-->
        <attr name="textSize" format="dimension|reference"/>
        <!-- 文本距离边框的填充间距 -->
        <attr name="textPadding" format="dimension|reference"/>
        <!-- 文本距离边框的左填充间距 -->
        <attr name="textPaddingLeft" format="dimension|reference"/>
        <!-- 文本距离边框的上填充间距 -->
        <attr name="textPaddingTop" format="dimension|reference"/>
        <!-- 文本距离边框的右填充间距 -->
        <attr name="textPaddingRight" format="dimension|reference"/>
        <!-- 文本距离边框的底部填充间距 -->
        <attr name="textPaddingButtom" format="dimension|reference"/>
        <!-- 文本之间的间隔距离 -->
        <attr name="textGapWidth" format="dimension|reference"/>
        <!-- 换行的行高间距 -->
        <attr name="lineHeight" format="dimension|reference"/>
        <!-- 选中状态的颜色 -->
        <attr name="checkedTextColor" format="color|reference"/>
        <!-- 未选中状态的颜色(默认) -->
        <attr name="unCheckedTextColor" format="color|reference"/>
        <!-- 选中边框颜色 -->
        <attr name="checkedStrokeColor" format="color|reference"/>
        <!-- 未选中边框颜色(默认) -->
        <attr name="unCheckedStrokeColor" format="color|reference"/>
        <!-- 边框宽度 -->
        <attr name="strokeWidth" format="dimension|reference"/>
        <!-- 选中的图标 -->
        <attr name="checkedDrawable" format="reference|color"/>
        <!-- 默认的图标 -->
        <attr name="unCheckedDrawable" format="reference|color"/>
        <!-- 图标的宽度 -->
        <attr name="drawTextGapWidth" format="reference|dimension"/>
        <!-- 图标的高度 -->
        <attr name="drawableHeight" format="reference|dimension"/>
        <!-- 图标与文本之间的间距 -->
        <attr name="drawableWidth" format="reference|dimension"/>
        <!-- 用户触发模式 -->
        <attr name="checkModel">
            <!-- 单选 -->
            <enum name="SIMPLE" value="4"/>
            <!-- 多选 -->
            <enum name="MULTI" value="5"/>
        </attr>
        <attr name="strokeModel">
            <!--默认和选中都显示边框-->
            <enum name="STROKE" value="1"/>
            <!--默认隐藏边框,选中显示边框-->
            <enum name="GONE_STROKE" value="2"/>
            <!--默认和选中都隐藏边框-->
            <enum name="GONE" value="3"/>
        </attr>
    </declare-styleable>

### 注意

* 多选时,要设置最大个数,否则无效