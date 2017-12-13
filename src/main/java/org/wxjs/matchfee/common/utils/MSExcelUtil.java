package org.wxjs.matchfee.common.utils;

public class MSExcelUtil {
	 
    public static final short EXCEL_COLUMN_WIDTH_FACTOR = 256;
    public static final int UNIT_OFFSET_LENGTH = 7;
    public static final int[] UNIT_OFFSET_MAP = new int[] { 0, 36, 73, 109, 146, 182, 219 };
 

    public static short pixel2WidthUnits(int pxs) {
        short widthUnits = (short) (EXCEL_COLUMN_WIDTH_FACTOR * (pxs / UNIT_OFFSET_LENGTH));
        widthUnits += UNIT_OFFSET_MAP[(pxs % UNIT_OFFSET_LENGTH)];
        return widthUnits;
    }
 

    public static int widthUnits2Pixel(int widthUnits) {
        int pixels = (widthUnits / EXCEL_COLUMN_WIDTH_FACTOR) * UNIT_OFFSET_LENGTH;
        int offsetWidthUnits = widthUnits % EXCEL_COLUMN_WIDTH_FACTOR;
        pixels += Math.round(offsetWidthUnits
                / ((float) EXCEL_COLUMN_WIDTH_FACTOR / UNIT_OFFSET_LENGTH));
 
        return pixels;
    }
}