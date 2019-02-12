package com.example.yanjiang.stockchart.util;

import com.jcraft.jzlib.ZInputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by kinger on 2016/11/21.
 */
public class ZlibUtils {

    public static byte[] unjzlib(byte[] object) {
        byte[] data = null;
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(object);
            ZInputStream zIn = new ZInputStream(in);
            byte[] buf = new byte[102400];
            int num = -1;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((num = zIn.read(buf, 0, buf.length)) != -1) {
                baos.write(buf, 0, num);
            }
            data = baos.toByteArray();
            baos.flush();
            baos.close();
            zIn.close();
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
