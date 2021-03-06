
package com.zdjc.zdjcyun.charting.data;


import com.zdjc.zdjcyun.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

/**
 * Data object that encapsulates all data associated with a LineChart.
 * 
 * @author Philipp Jahoda
 */
public class LineData extends BarLineScatterCandleBubbleData<ILineDataSet> {

    public LineData() {
        super();
    }

    public LineData(ILineDataSet... dataSets) {
        super(dataSets);
    }

    public LineData(ArrayList<ILineDataSet> dataSets) {
        super(dataSets);
    }
}
