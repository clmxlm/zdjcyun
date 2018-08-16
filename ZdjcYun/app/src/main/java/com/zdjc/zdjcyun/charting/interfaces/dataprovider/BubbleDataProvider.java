package com.zdjc.zdjcyun.charting.interfaces.dataprovider;


import com.zdjc.zdjcyun.charting.data.BubbleData;

public interface BubbleDataProvider extends BarLineScatterCandleBubbleDataProvider {

    BubbleData getBubbleData();
}
