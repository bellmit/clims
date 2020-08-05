package com.bazl.clims.datamodel;



import java.util.List;
import java.util.Map;

/**
 * @author wanghaiyang
 * @date 2019/4/29.
 */
public class SampleGeneDataModel {

    private List<String> markerList;

    private List<Map<String,List<String>>> geneDataModelList;

    private List<Map<String,List<String>>> genePIModelList;

    public List<String> getMarkerList() {
        return markerList;
    }

    public void setMarkerList(List<String> markerList) {
        this.markerList = markerList;
    }

    public List<Map<String, List<String>>> getGeneDataModelList() {
        return geneDataModelList;
    }

    public void setGeneDataModelList(List<Map<String, List<String>>> geneDataModelList) {
        this.geneDataModelList = geneDataModelList;
    }

    public List<Map<String, List<String>>> getGenePIModelList() {
        return genePIModelList;
    }

    public void setGenePIModelList(List<Map<String, List<String>>> genePIModelList) {
        this.genePIModelList = genePIModelList;
    }
}
