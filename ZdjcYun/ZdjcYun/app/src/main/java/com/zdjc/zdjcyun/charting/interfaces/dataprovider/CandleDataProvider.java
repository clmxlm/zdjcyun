package com.zdjc.zdjcyun.charting.interfaces.dataprovider;


import com.zdjc.zdjcyun.charting.data.CandleData;

public interface CandleDataProvider extends BarLineScatterCandleBubbleDataProvider {

    CandleData getCandleData();
}
