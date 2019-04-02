# 流动标签组件
在原项目[TextCheckGroupView](https://github.com/igeek-YZ/TextCheckGroupView) 基础上
优化了点击事件和固定宽度

主要特点：
* 可用作显示给用户单选或者多选；  

* 可自定义显示的模式：无边框，有边框，有图标和隐藏图标几种组合模式  

* 流动布局，每一行的显示个数可根据显示的数据和屏幕的宽度自适应换行  

* 设置最大选中个数。在流动标签中很实用 

## 使用

- Step 1. 把 JitPack repository 添加到build.gradle文件中 repositories的末尾:
```
repositories {
    maven { url "https://jitpack.io" }
}
```
- Step 2. 在你的app build.gradle 的 dependencies 中添加依赖
```
dependencies {
	implementation 'com.github.wenchaosong:CheckBox:1.0.7'
}
```
# 注意

* 多选时,要设置最大个数,否则无效
* 设置 drawable 时,要设置宽高

### mainActivity

    CheckBoxGroupView checkBoxGroupView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBoxGroupView= (CheckBoxGroupView) findViewById(R.id.checkBoxGroupView);
        checkBoxGroupView.updateCheckTexts(initList());
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
        android:id="@+id/checkBoxGroupView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:cb_checkedTextColor="#009EF2"
        app:cb_textPaddingLeft="16dp"
        app:cb_textPaddingRight="16dp"
        app:cb_textPaddingButtom="12dp"
        app:cb_textPaddingTop="12dp"
        app:cb_lineHeight="12dp"
        app:cb_strokeModel="GONE"
        app:cb_checkModel="SIMPLE"
        app:cb_strokeWidth="1px"
        app:cb_textGapWidth="10dp"
        app:cb_textSize="16sp"
        app:cb_unCheckedTextColor="@color/black_overlay" />


![image](/gifs/simple_gone.gif )  

#### 选中边框单选模式(多选修改*app:checkModel="MULTI"*)  

	<com.checkbox.CheckBoxGroupView
        android:id="@+id/checkBoxGroupView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:cb_checkedStrokeColor="@color/colorPrimary"
        app:cb_checkedTextColor="#009EF2"
        app:cb_textPaddingLeft="16dp"
        app:cb_textPaddingRight="16dp"
        app:cb_textPaddingButtom="12dp"
        app:cb_textPaddingTop="12dp"
        app:cb_lineHeight="12dp"
        app:cb_strokeModel="GONE_STROKE"
        app:cb_checkModel="SIMPLE"
        app:cb_strokeWidth="1px"
        app:cb_textGapWidth="10dp"
        app:cb_textSize="16sp"
        app:cb_unCheckedStrokeColor="@color/gray_efeff4"
        app:cb_unCheckedTextColor="@color/black_overlay" />

![image](/gifs/simple_gone_Stroke.gif )  

#### 选中边框多选模式
![image](/gifs/mulit_gone_Stroke.gif )

#### 选中边框带图标单选模式(多选修改*app:checkModel="MULTI"*) 

	<com.checkbox.CheckBoxGroupView
        android:id="@+id/checkBoxGroupView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:cb_checkedStrokeColor="@color/colorPrimary"
        app:cb_checkedTextColor="#009EF2"
        app:cb_textPaddingLeft="16dp"
        app:cb_textPaddingRight="16dp"
        app:cb_textPaddingButtom="12dp"
        app:cb_textPaddingTop="12dp"
        app:cb_lineHeight="12dp"
        app:cb_strokeModel="GONE_STROKE"
        app:cb_checkModel="SIMPLE"
        app:cb_strokeWidth="1px"
        app:cb_textGapWidth="10dp"
        app:cb_textSize="16sp"
        app:cb_drawTextGapWidth="8dp"
        app:cb_drawableWidth="20dp"
        app:cb_drawableHeight="20dp"
        app:cb_unCheckedDrawable="@mipmap/ic_radio_btn_unchecked_black_24dp"
        app:cb_checkedDrawable="@mipmap/ic_radio_btn_checked_black_24dp"
        app:cb_unCheckedStrokeColor="@color/gray_efeff4"
        app:cb_unCheckedTextColor="@color/black_overlay" />


![image](/gifs/simple_icon_gone_Stroke.gif )  

#### 属性说明
	<declare-styleable name="CheckBoxGroupView">
        <!--文本字体大小-->
        <attr name="cb_textSize" format="dimension|reference"/>
        <!-- 文本距离边框的填充间距 -->
        <attr name="cb_textPadding" format="dimension|reference"/>
        <!-- 文本距离边框的左填充间距 -->
        <attr name="cb_textPaddingLeft" format="dimension|reference"/>
        <!-- 文本距离边框的上填充间距 -->
        <attr name="cb_textPaddingTop" format="dimension|reference"/>
        <!-- 文本距离边框的右填充间距 -->
        <attr name="cb_textPaddingRight" format="dimension|reference"/>
        <!-- 文本距离边框的底部填充间距 -->
        <attr name="cb_textPaddingButtom" format="dimension|reference"/>
        <!-- 文本之间的间隔距离 -->
        <attr name="cb_textGapWidth" format="dimension|reference"/>
        <!-- 总宽度 -->
        <attr name="cb_groupWidth" format="dimension|reference"/>
        <!-- 换行的行高间距 -->
        <attr name="cb_lineHeight" format="dimension|reference"/>
        <!-- 选中状态的颜色 -->
        <attr name="cb_checkedTextColor" format="color|reference"/>
        <!-- 未选中状态的颜色(默认) -->
        <attr name="cb_unCheckedTextColor" format="color|reference"/>
        <!-- 选中边框颜色 -->
        <attr name="cb_checkedStrokeColor" format="color|reference"/>
        <!-- 未选中边框颜色(默认) -->
        <attr name="cb_unCheckedStrokeColor" format="color|reference"/>
        <!-- 边框宽度 -->
        <attr name="cb_strokeWidth" format="dimension|reference"/>
        <!-- 选中的图标 -->
        <attr name="cb_checkedDrawable" format="reference|color"/>
        <!-- 默认的图标 -->
        <attr name="cb_unCheckedDrawable" format="reference|color"/>
        <!-- 图标的宽度 -->
        <attr name="cb_drawTextGapWidth" format="reference|dimension"/>
        <!-- 图标的高度 -->
        <attr name="cb_drawableHeight" format="reference|dimension"/>
        <!-- 图标与文本之间的间距 -->
        <attr name="cb_drawableWidth" format="reference|dimension"/>
        <!-- 用户触发模式 -->
        <attr name="cb_checkModel">
            <!-- 单选 -->
            <enum name="SIMPLE" value="4"/>
            <!-- 多选 -->
            <enum name="MULTI" value="5"/>
        </attr>
        <attr name="cb_strokeModel">
            <!--默认和选中都显示边框-->
            <enum name="STROKE" value="1"/>
            <!--默认隐藏边框,选中显示边框-->
            <enum name="GONE_STROKE" value="2"/>
            <!--默认和选中都隐藏边框-->
            <enum name="GONE" value="3"/>
        </attr>
    </declare-styleable>
