package com.bazl.clims.dao;

import com.bazl.clims.model.po.DnaNoSeq;
import tk.mybatis.mapper.common.*;

/**
 * @Author: lzn
 * @Date: 2020/3/31 14:13
 * @Version: 1.0
 */
public interface SeqNoGenerateMapper {
    public int selectNextVal(String code);

    public int deleteCode(String code);

    /**
     * 根据code还原currentVal的值
     * @param code
     * @return
     */
    public int returnCurrentValByCode(String code);
    /**
     * 根据code增加currentVal的值
     * @param code
     * @return
     */
    public int updateCurrentValByCode(String code);
    /**
     * 增加
     * @param dnaNoSeq
     * @return
     */
    public void insert(DnaNoSeq dnaNoSeq);
  /*
  * 根据code查no
  * */
    public DnaNoSeq selectByCode(String code);
}
