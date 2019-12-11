# BryantTileMap
android使用谷歌地图(Android uses Google Maps)，利用高德地图加载谷歌瓦片图层(Loading Google tile layer with Gaud map)



## 使用方式
对接高德地图，利用核心代码加载谷歌瓦片图层

## BryantTileMap的优点

1.解决android端需要翻墙才能使用谷歌地图

2.可以使用高德的任何API

## BryantTileMap的缺点

1.谷歌地图分辨率稍有模糊

2.谷歌地图的热点标注比高德地图少很多

3.加载谷歌瓦片图层略慢，并且可以看到底层

## 关于网络安全配置
请在AndroidManifest.xml的application节点下添加android:networkSecurityConfig="@xml/network_security_config"
否则无法访问谷歌瓦片图层

## 关于MCodeCheck.apk
可以获取SHA1序列号，很多第三平台会用到 
[软件下载地址](https://raw.githubusercontent.com/YangsBryant/BryantTileMap/master/MCodeCheck.apk)

## 主要代码
```java
public class MainActivity extends AppCompatActivity{

    @BindView(R.id.mapView)
    MapView mapView;

    private AMap aMap;
    private MarkerOptions markerOptions;
    String tileUrl = "http://mt2.google.cn/vt/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mapView.onCreate(savedInstanceState);// 此方法必须重写
        aMap = mapView.getMap();
        aMap.getUiSettings().setZoomControlsEnabled(false);// 缩放按钮是否显示
        aMap.getUiSettings().setRotateGesturesEnabled(false);// 倾斜手势是否可用
        aMap.moveCamera(CameraUpdateFactory.zoomTo(18));//缩放级别
        aMap.showMapText(false);//不显示位置名称，因为谷歌瓦片图层已经有热点标注
        aMap.getUiSettings().setLogoBottomMargin(50);//设置LOGO底部的距离
        aMap.getUiSettings().setLogoLeftMargin(50);//设置LOGO左边的距离
        LatLng latLng = new LatLng(25.8597028368,114.8968382763);
        aMap.moveCamera(CameraUpdateFactory.changeLatLng(latLng));//设置中心点

        //创建Marker
        markerOptions = new MarkerOptions();
        View markerView = LayoutInflater.from(this).inflate(R.layout.marker_layout,mapView,false);
        markerOptions.icon(BitmapDescriptorFactory.fromView(markerView));//Marker图标

        //添加Marker标注
        markerOptions.position(latLng);
        aMap.addMarker(markerOptions);
        aMap.animateCamera(CameraUpdateFactory.changeLatLng(latLng),500,null);

        //地图点击事件
        aMap.setOnMapClickListener(latLng12 -> {
            //清除地图Marker点(会把瓦片层也清除)
            aMap.clear();
            //重新设置瓦片层
            aMap.addTileOverlay(tileOverlayOptions);
            //添加Marker标注
            LatLng latLng1 = new LatLng(latLng12.latitude, latLng12.longitude);
            markerOptions.position(latLng1);
            aMap.addMarker(markerOptions);
            aMap.animateCamera(CameraUpdateFactory.changeLatLng(latLng1),500,null);
        });

        //添加瓦片到地图
        aMap.addTileOverlay(tileOverlayOptions);
    }
    TileOverlayOptions tileOverlayOptions =
            new TileOverlayOptions().tileProvider(new UrlTileProvider(256, 256) {
                // x横坐标 ，y纵坐标，zoom缩放比
                @Override
                public URL getTileUrl(int x, int y, int zoom) {
                    try {
                        /*谷歌瓦片图层地址
                        lyrs参数:
                        m：路线图
                        t：地形图
                        p：带标签的地形图
                        s：卫星图
                        y：带标签的卫星图
                        h：标签层（路名、地名等）*/
                        String url = tileUrl+"lyrs=y@167000000&hl=zh-CN&gl=cn&x="+x+"&y="+y+"&z="+zoom+"&s=Galil.png";
                        return new URL(url);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            });
}
```

## 布局文件
```java
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:id="@+id/map"
    tools:context=".MainActivity"
    android:name="com.google.android.gms.maps.SupportMapFragment" >

    <com.amap.api.maps.MapView
        android:id="@+id/mapView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        />

</androidx.constraintlayout.widget.ConstraintLayout>
```
## 联系QQ：961606042
