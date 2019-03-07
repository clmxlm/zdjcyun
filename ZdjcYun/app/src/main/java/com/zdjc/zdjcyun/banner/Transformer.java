package com.zdjc.zdjcyun.banner;

import android.support.v4.view.ViewPager.PageTransformer;

import com.zdjc.zdjcyun.banner.transformer.AccordionTransformer;
import com.zdjc.zdjcyun.banner.transformer.BackgroundToForegroundTransformer;
import com.zdjc.zdjcyun.banner.transformer.CubeInTransformer;
import com.zdjc.zdjcyun.banner.transformer.CubeOutTransformer;
import com.zdjc.zdjcyun.banner.transformer.DefaultTransformer;
import com.zdjc.zdjcyun.banner.transformer.DepthPageTransformer;
import com.zdjc.zdjcyun.banner.transformer.FlipHorizontalTransformer;
import com.zdjc.zdjcyun.banner.transformer.FlipVerticalTransformer;
import com.zdjc.zdjcyun.banner.transformer.ForegroundToBackgroundTransformer;
import com.zdjc.zdjcyun.banner.transformer.RotateDownTransformer;
import com.zdjc.zdjcyun.banner.transformer.RotateUpTransformer;
import com.zdjc.zdjcyun.banner.transformer.ScaleInOutTransformer;
import com.zdjc.zdjcyun.banner.transformer.StackTransformer;
import com.zdjc.zdjcyun.banner.transformer.TabletTransformer;
import com.zdjc.zdjcyun.banner.transformer.ZoomInTransformer;
import com.zdjc.zdjcyun.banner.transformer.ZoomOutSlideTransformer;
import com.zdjc.zdjcyun.banner.transformer.ZoomOutTranformer;


public class Transformer {
    public static Class<? extends PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;
}
