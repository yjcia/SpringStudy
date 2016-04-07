package com.spring.util;

import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.AxisType;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Line;
import com.spring.common.EchartsAttribute;
import com.spring.model.Server;

import java.util.List;

/**
 * Created by YanJun on 2016/4/6.
 */
public class EchartsUtil {
    public static String generateMemUseageLine(List<Server> serverList) {

        GsonOption option = new GsonOption();
        option.title().text(EchartsAttribute.MEM_LINE_TITLE);
        option.tooltip().trigger(Trigger.axis);
        option.legend(EchartsAttribute.MEM_USAGE_SERIES,EchartsAttribute.MEM_TOTAL_SERIES);
        option.grid().left("3%");
        option.grid().right("4%");
        option.grid().bottom("3%");
        option.grid().containLabel(true);
        option.toolbox().show(true).feature(Tool.saveAsImage);
        option.xAxis(
                new CategoryAxis().data(getServerMemXAxisArr(serverList)));
        option.yAxis(new ValueAxis());
        Bar usedBar = new Bar(EchartsAttribute.MEM_USAGE_SERIES);
        usedBar.data(getServerMemUsedDataArr(serverList));

        Bar totalBar = new Bar(EchartsAttribute.MEM_TOTAL_SERIES);
        totalBar.data(getServerMemTotalDataArr(serverList));
        option.series(usedBar,totalBar);
        return option.toString();
    }



    public static String generateCpuUseageLine(List<Server> serverList) {
        GsonOption option = new GsonOption();
        option.title().text(EchartsAttribute.CPU_LINE_TITLE);
        option.tooltip().trigger(Trigger.axis);
        option.legend(EchartsAttribute.CPU_USAGE_USER,EchartsAttribute.CPU_USAGE_SYS);
        option.grid().left("3%");
        option.grid().right("4%");
        option.grid().bottom("3%");
        option.grid().containLabel(true);
        option.toolbox().show(true).feature(Tool.saveAsImage);
        option.xAxis(
                new CategoryAxis().data(getServerCpuXAxisArr(serverList)));
        option.yAxis(new ValueAxis());
        Bar cpuUserBar = new Bar(EchartsAttribute.CPU_USAGE_USER);
        cpuUserBar.data(getServerCpuUserDataArr(serverList));

        Bar cpuSysBar = new Bar(EchartsAttribute.CPU_USAGE_SYS);
        cpuSysBar.data(getServerCpuSysDataArr(serverList));
        option.series(cpuUserBar,cpuSysBar);
        return option.toString();
    }

    public static String[] getServerMemXAxisArr(List<Server> serverList) {
        String[] xAxisArr = new String[serverList.size()];
        for (int i = 0; i < serverList.size(); i++) {
            xAxisArr[i] = serverList.get(i).getHostName().substring(10);
        }
        return xAxisArr;
    }

    public static String[] getServerCpuXAxisArr(List<Server> serverList) {
      return getServerMemXAxisArr(serverList);
    }

    public static String[] getServerMemUsedDataArr(List<Server> serverList) {
        String[] dataArr = new String[serverList.size()];
        for (int i = 0; i < serverList.size(); i++) {
            dataArr[i] = String.valueOf(serverList.get(i).getUsedMem());
        }
        return dataArr;
    }
    public static String[] getServerMemTotalDataArr(List<Server> serverList) {
        String[] dataArr = new String[serverList.size()];
        for (int i = 0; i < serverList.size(); i++) {
            dataArr[i] = String.valueOf(serverList.get(i).getTotalMem());
        }
        return dataArr;
    }
    public static String[] getServerCpuUserDataArr(List<Server> serverList) {
        String[] dataArr = new String[serverList.size()];
        for (int i = 0; i < serverList.size(); i++) {
            dataArr[i] = String.valueOf(serverList.get(i).getCpuForUser());
        }
        return dataArr;
    }
    public static String[] getServerCpuSysDataArr(List<Server> serverList) {
        String[] dataArr = new String[serverList.size()];
        for (int i = 0; i < serverList.size(); i++) {
            dataArr[i] = String.valueOf(serverList.get(i).getCpuForSys());
        }
        return dataArr;
    }
}
