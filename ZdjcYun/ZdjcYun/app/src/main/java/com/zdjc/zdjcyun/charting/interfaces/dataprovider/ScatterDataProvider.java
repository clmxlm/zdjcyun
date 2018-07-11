package com.zdjc.zdjcyun.charting.interfaces.dataprovider;


import com.zdjc.zdjcyun.charting.data.ScatterData;

public interface ScatterDataProvider extends BarLineScatterCandleBubbleDataProvider {

    ScatterData getScatterData();
}
