package com.easyapp.baseproject.lib.tool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by easyapp_jim on 2016/5/3.
 */
public class CollectionTool {

    // for Java 1.5+
    public static <T> List<T> arrayToList(final T[] array) {
        final List<T> l = new ArrayList<T>(array.length);

        for (final T s : array) {
            l.add(s);
        }
        return (l);
    }

}
