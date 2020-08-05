package com.bazl.clims.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by hejia on 2019/10/9.
 */
@Component
public class ToConfigure {

    @Value("${limsEdition}")
    private String limsEdition;

    /** 检验人1 */
    @Value("${inspector1}")
    private String inspector1;

    /** 检验人1职务 */
    @Value("${inspector1Post}")
    private String inspector1Post;

    /** 检验人2 */
    @Value("${inspector2}")
    private String inspector2;

    /** 检验人2职务 */
    @Value("${inspector2Post}")
    private String inspector2Post;

    /**检验人3*/
    @Value("${inspector3}")
    private String inspector3;

    /** 检验人3职务 */
    @Value("${inspector3Post}")
    private String inspector3Post;

    public String getLimsEdition() {
        return limsEdition;
    }

    public void setLimsEdition(String limsEdition) {
        this.limsEdition = limsEdition;
    }

    public String getInspector1() {
        return inspector1;
    }

    public void setInspector1(String inspector1) {
        this.inspector1 = inspector1;
    }

    public String getInspector1Post() {
        return inspector1Post;
    }

    public void setInspector1Post(String inspector1Post) {
        this.inspector1Post = inspector1Post;
    }

    public String getInspector2() {
        return inspector2;
    }

    public void setInspector2(String inspector2) {
        this.inspector2 = inspector2;
    }

    public String getInspector2Post() {
        return inspector2Post;
    }

    public void setInspector2Post(String inspector2Post) {
        this.inspector2Post = inspector2Post;
    }

    public String getInspector3() {
        return inspector3;
    }

    public void setInspector3(String inspector3) {
        this.inspector3 = inspector3;
    }

    public String getInspector3Post() {
        return inspector3Post;
    }

    public void setInspector3Post(String inspector3Post) {
        this.inspector3Post = inspector3Post;
    }
}
