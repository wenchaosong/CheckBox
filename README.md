# 流动标签组件
在原项目[TextCheckGroupView](https://github.com/igeek-YZ/TextCheckGroupView) 基础上
优化了点击事件和固定宽度

主要特点：
* 可用作显示给用户单选或者多选；  
* 可自定义显示的模式：无边框，有边框，有图标和隐藏图标几种组合模式
* 流动布局，每一行的显示个数可根据显示的数据和屏幕的宽度自适应换行
* 设置最大选中个数。在流动标签中很实用

# 注意

* 设置 drawable 时,要设置宽高

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
	implementation 'com.github.wenchaosong:CheckBox:1.1.4'
}
```

### mainActivity
```
    CheckBoxGroupView checkBoxGroupView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBoxGroupView = (CheckBoxGroupView) findViewById(R.id.checkBoxGroupView);
        checkBoxGroupView.setNewData(initList());
    }

    private List<String> initList() {
        List<String> list = new ArrayList<>();
        for (int index = 0; index < 12; index++) {
            list.add("中" + index);
        }
        return list;
    }
```

#### 属性说明
```
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
        <attr name="cb_textPaddingBottom" format="dimension|reference"/>
        <!-- 文本之间的间隔距离 -->
        <attr name="cb_textGapWidth" format="dimension|reference"/>
        <!-- 总宽度 -->
        <attr name="cb_groupWidth" format="dimension|reference"/>
        <!-- 总高度 -->
        <attr name="cb_groupHeight" format="dimension|reference" />
        <!-- 换行的行高间距 -->
        <attr name="cb_lineHeight" format="dimension|reference"/>
        <!-- 换行的行高间距 -->
        <attr name="cb_disableColor" format="color|reference" />
        <!-- 选中状态的颜色 -->
        <attr name="cb_checkedTextColor" format="color|reference"/>
        <!-- 未选中状态的颜色(默认) -->
        <attr name="cb_unCheckedTextColor" format="color|reference"/>
        <!-- 选中边框颜色 -->
        <attr name="cb_checkedStrokeColor" format="color|reference"/>
        <!-- 未选中边框颜色(默认) -->
        <attr name="cb_unCheckedStrokeColor" format="color|reference"/>
        <!-- 选中填充颜色 -->
        <attr name="cb_checkedFillColor" format="color|reference" />
        <!-- 未选中填充颜色(默认) -->
        <attr name="cb_unCheckedFillColor" format="color|reference" />
        <!-- 边框宽度 -->
        <attr name="cb_strokeWidth" format="dimension|reference"/>
        <!-- 选中的图标 -->
        <attr name="cb_checkedDrawable" format="reference|color"/>
        <!-- 选中的图标 -->
        <attr name="cb_checkedRes" format="reference" />
        <!-- 默认的图标 -->
        <attr name="cb_unCheckedDrawable" format="reference|color"/>
        <!-- 默认的图标 -->
        <attr name="cb_unCheckedRes" format="reference" />
        <!-- 图标的宽度 -->
        <attr name="cb_drawTextGapWidth" format="reference|dimension"/>
        <!-- 图标的高度 -->
        <attr name="cb_drawableHeight" format="reference|dimension"/>
        <!-- 图标与文本之间的间距 -->
        <attr name="cb_drawableWidth" format="reference|dimension"/>
        <!-- 圆角角度 -->
        <attr name="cb_tagRadius" format="reference|dimension" />
        <!-- 是否加粗 -->
        <attr name="cb_textBold" format="boolean" />
        <!-- 是否重复选择 -->
        <attr name="cb_repeatCheck" format="boolean" />
        <!-- 多选最大个数 -->
        <attr name="cb_maxCheckedNum" format="integer" />
        <!-- 选择模式 -->
        <attr name="cb_checkModel">
            <!-- 单选 -->
            <enum name="SIMPLE" value="1" />
            <!-- 多选 -->
            <enum name="MULTI" value="2" />
        </attr>
    </declare-styleable>
```