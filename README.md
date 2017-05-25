# android-optimize-coderwjq
android性能优化



### 一、内存优化之减少内存占用，图片压缩

#### 1. 边界压缩

使用边界压缩前后，应用占用内存的对比

> 加载原图时，应用所占内存的分析

![01-加载原图后的内存占用](https://raw.githubusercontent.com/coderwjq/MDResource/master/images/图片加载优化/01-加载原图后的内存占用.png)

> 加载压缩后的图片时，应用所占内存的分析

![02-加载压缩后的内存占用](https://raw.githubusercontent.com/coderwjq/MDResource/master/images/图片加载优化/02-加载压缩后的内存占用.png)

#### 2. 色彩压缩

> 使用RGB_565色彩压缩，其他色彩模式也已测试，不再贴图

![03-加载原图使用RGB_565色彩压缩](https://github.com/coderwjq/MDResource/blob/master/images/图片加载优化/03-加载原图使用RGB_565色彩压缩.png)