package com.bazl.clims.compare.relative;

import com.bazl.clims.compare.relative.po.Marker;
import com.bazl.clims.compare.relative.po.ParentageMatchOptions;
import com.bazl.clims.compare.relative.po.ParentageMatchResult;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Sun on 2019/4/2.
 */
public interface ParentageCalculator {

    ParentageMatchResult calculate(Map<String, Marker> var1, Map<String, Marker> var2, Map<String, Marker> var3, ParentageMatchOptions var4);

}
