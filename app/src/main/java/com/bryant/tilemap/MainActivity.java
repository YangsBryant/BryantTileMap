package com.bryant.tilemap;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.TileOverlayOptions;
import com.amap.api.maps.model.UrlTileProvider;
import com.amap.api.maps.model.VisibleRegion;
import java.net.URL;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mapView)
    MapView mapView;

    private AMap aMap;
    private MarkerOptions markerOptions;
    private String tileUrl = "http://mt2.google.cn/vt/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mapView.onCreate(savedInstanceState);// 此方法必须重写
        aMap = mapView.getMap();
        aMap.getUiSettings().setZoomControlsEnabled(false);// 缩放按钮是否显示
        aMap.getUiSettings().setRotateGesturesEnabled(false);// 倾斜手势是否可用
        aMap.moveCamera(CameraUpdateFactory.zoomTo(16));//缩放级别
        aMap.showMapText(false);//不显示位置名称，因为谷歌瓦片图层已经有热点标注
        aMap.getUiSettings().setLogoBottomMargin(50);//设置LOGO底部的距离
        aMap.getUiSettings().setLogoLeftMargin(50);//设置LOGO左边的距离

        //设置自定义瓦片中心点
        double[] central = getCentralLatLng(new LatLng(25.246254745730973, 115.02752709100358),new LatLng(25.2297424864891, 115.00983818939284));
        LatLng latLng = new LatLng(central[0], central[1]);
        aMap.moveCamera(CameraUpdateFactory.changeLatLng(latLng));

        //创建Marker
        markerOptions = new MarkerOptions();
        View markerView = LayoutInflater.from(this).inflate(R.layout.marker_layout, mapView, false);
        markerOptions.icon(BitmapDescriptorFactory.fromView(markerView));//Marker图标

        //添加Marker标注
        markerOptions.position(latLng);
        aMap.addMarker(markerOptions);
        aMap.animateCamera(CameraUpdateFactory.changeLatLng(latLng), 500, null);

        //地图点击事件
        aMap.setOnMapClickListener(latLng12 -> {
            //清除地图Marker点(会把瓦片层也清除)
            aMap.clear();
            //重新设置瓦片层
            aMap.addTileOverlay(tileOverlayOptions);
            aMap.addTileOverlay(tileOverlayOptions2);
            //添加Marker标注
            LatLng latLng1 = new LatLng(latLng12.latitude, latLng12.longitude);
            markerOptions.position(latLng1);
            aMap.addMarker(markerOptions);
            aMap.animateCamera(CameraUpdateFactory.changeLatLng(latLng1), 500, null);
        });

        //添加谷歌瓦片到地图
        aMap.addTileOverlay(tileOverlayOptions);
        //添加自定义瓦片到地图
        aMap.addTileOverlay(tileOverlayOptions2);
    }

    //谷歌瓦片图
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
​                        p：带标签的地形图
                        ​s：卫星图
                        y：带标签的卫星图
​                        h：标签层（路名、地名等）*/
                        String url = tileUrl + "lyrs=y@167000000&hl=zh-CN&gl=cn&x=" + x + "&y=" + y + "&z=" + zoom + "&s=Galil.png";
                        return new URL(url);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            });

    //自定义瓦片图
    TileOverlayOptions tileOverlayOptions2 =
            new TileOverlayOptions().tileProvider(new UrlTileProvider(256, 256) {
                // x横坐标 ，y纵坐标，zoom缩放比
                @Override
                public URL getTileUrl(int x, int y, int zoom) {
                    try {
                        //获取自定义瓦片图中心的经纬度
                        double[] central = getCentralLatLng(new LatLng(25.246254745730973, 115.02752709100358),new LatLng(25.2297424864891, 115.00983818939284));
                        //判断瓦片图中心的经纬度是否是在屏幕内的经纬，从而获取id
                        if(getLat().contains(new LatLng(central[0],central[1]))){
                            //Y轴矫正公式
                            int y2 = (int) (Math.pow(2, zoom) - y - 1);
                            //自定义瓦片图的地址
                            String url = "https://tile-orchard.oss-cn-shenzhen.aliyuncs.com/"
                                    + 1 + "/" + zoom + "/" + x + "/" + y2 + ".png";
                            return new URL(url);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            });

    //由东南角和西北角求中心点
    public double[] getCentralLatLng(LatLng maxLatLng,LatLng minLatLng){
        double latMax = maxLatLng.latitude - minLatLng.latitude;
        double lonMax = maxLatLng.longitude - minLatLng.longitude;
        return new double[]{minLatLng.latitude+latMax/2,minLatLng.longitude+lonMax/2};
    }
    //获取屏幕矩形区域的经纬度范围
    public LatLngBounds getLat(){
        VisibleRegion visibleRegion = aMap.getProjection().getVisibleRegion();
        return visibleRegion.latLngBounds;
    }
}
