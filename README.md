# VolleyImage
Gif Image  use volley
#基于Volley实现网络加载图片，包括加载GIF动态图片，圆形图片，普通的图片。配合LruCache实现内存缓存

Gif图片加载采用koral--实现的[android-gif-drawable][1]   ，此库底层使用C库进行Gif的编解码，效率和显示效果方面都非常棒。
  [1]: https://github.com/koral--/android-gif-drawable
  
#这是具体展示的效果图
![image](https://github.com/BaymaxTong/VolleyImage/blob/master/loading.gif)

另外，还可以自定义修给正在加载，和加载失败的显示默认图片
