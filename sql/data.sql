/**
  AM_PERSONAL_INFO表添加字段
  委托人单位联系电话org_phone
  songshuai
  2018-12-24
 */
alter table AM_PERSONAL_INFO add (org_phone VARCHAR2(64));
comment on column AM_PERSONAL_INFO.org_phone is '单位联系电话';


/**
  dict_item dict_info表添加数据
  委托人单位联系电话org_phone
  sixiru
  2018-12-28
 */

/*样本状态*/
insert into dict_info(DICT_INFO_ID,  DICT_TYPE_CODE, DICT_TYPE_NAME, CREATE_PERSON) values(sys_guid(), 'SAMPLE_STATUS', '样本状态', 'admin');

insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'SAMPLE_STATUS'), '01', 'SAMPLE_STATUS', '待受理', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'SAMPLE_STATUS'), '02', 'SAMPLE_STATUS', '已受理', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'SAMPLE_STATUS'), '03', 'SAMPLE_STATUS', '拒绝受理', 'admin');

/**
  lims_person_info表修改person_type字段长度大小
  委托人单位联系电话org_phone
  sixiru
  2018-12-28
 */
 alter table lims_person_info modify(PERSON_TYPE VARCHAR2(12));


 /**
  PERSON_DETAIL表修改person_type字段长度大小
  委托人单位联系电话org_phone
  sixiru
  2018-12-28
 */
  alter table PERSON_DETAIL modify(PERSON_TYPE VARCHAR2(12));
/**
  添加职务字典项
  songshuai
  2018-12-27
 */

/*职务*/
insert into dict_info(DICT_INFO_ID,  DICT_TYPE_CODE, DICT_TYPE_NAME, CREATE_PERSON) values(sys_guid(), 'POSITION_LIST', '职务', 'admin');

insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'POSITION_LIST'), '01', 'POSITION_LIST', '警员', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'POSITION_LIST'), '02', 'POSITION_LIST', '警长', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'POSITION_LIST'), '03', 'POSITION_LIST', '副局长', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'POSITION_LIST'), '04', 'POSITION_LIST', '副科长', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'POSITION_LIST'), '05', 'POSITION_LIST', '科长', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'POSITION_LIST'), '06', 'POSITION_LIST', '局长', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'POSITION_LIST'), '07', 'POSITION_LIST', '副队长', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'POSITION_LIST'), '08', 'POSITION_LIST', '科员', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'POSITION_LIST'), '09', 'POSITION_LIST', '队长', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'POSITION_LIST'), '10', 'POSITION_LIST', '所长', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'POSITION_LIST'), '11', 'POSITION_LIST', '探长', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'POSITION_LIST'), '12', 'POSITION_LIST', '副所长', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'POSITION_LIST'), '13', 'POSITION_LIST', '教导员', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'POSITION_LIST'), '14', 'POSITION_LIST', '处长', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'POSITION_LIST'), '15', 'POSITION_LIST', '主任', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'POSITION_LIST'), '16', 'POSITION_LIST', '副主任', 'admin');



insert into DICT_INFO(DICT_INFO_ID,  DICT_TYPE_CODE, DICT_TYPE_NAME, CREATE_PERSON) values(sys_guid(), 'CASE_TYPE', '案件类型', 'admin');
insert into DICT_INFO(DICT_INFO_ID,  DICT_TYPE_CODE, DICT_TYPE_NAME, CREATE_PERSON) values(sys_guid(), 'CASE_PROPERTY', '案件性质', 'admin');




insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'CASE_TYPE'), '01', 'CASE_TYPE', '质控人员登记案件', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'CASE_TYPE'), '02', 'CASE_TYPE', '二版本案件', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'CASE_TYPE'), '03', 'CASE_TYPE', '命案与上级交办案件', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'CASE_TYPE'), '04', 'CASE_TYPE', '涉黑人员登记案件', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'CASE_TYPE'), '05', 'CASE_TYPE', '亲缘鉴定登记案件', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'CASE_TYPE'), '06', 'CASE_TYPE', '违法犯罪登记案件', 'admin');



insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'CASE_PROPERTY'), '01', 'CASE_PROPERTY', '凶杀', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'CASE_PROPERTY'), '02', 'CASE_PROPERTY', '伤害', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'CASE_PROPERTY'), '03', 'CASE_PROPERTY', '其他盗窃', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'CASE_PROPERTY'), '04', 'CASE_PROPERTY', '抢夺抢劫', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'CASE_PROPERTY'), '05', 'CASE_PROPERTY', '强奸', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'CASE_PROPERTY'), '06', 'CASE_PROPERTY', '走失人口', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'CASE_PROPERTY'), '07', 'CASE_PROPERTY', '伤害致死', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'CASE_PROPERTY'), '08', 'CASE_PROPERTY', '治安', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'CASE_PROPERTY'), '09', 'CASE_PROPERTY', '其它', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'CASE_PROPERTY'), '10', 'CASE_PROPERTY', '交通事故', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'CASE_PROPERTY'), '11', 'CASE_PROPERTY', '打拐', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'CASE_PROPERTY'), '12', 'CASE_PROPERTY', '尸源认定', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'CASE_PROPERTY'), '13', 'CASE_PROPERTY', '入室盗窃', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'CASE_PROPERTY'), '14', 'CASE_PROPERTY', '盗窃机动车', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'CASE_PROPERTY'), '15', 'CASE_PROPERTY', '盗窃车内财物', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'CASE_PROPERTY'), '16', 'CASE_PROPERTY', '盗抢工地', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'CASE_PROPERTY'), '17', 'CASE_PROPERTY', '爬楼钻窗', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'CASE_PROPERTY'), '18', 'CASE_PROPERTY', '盗窃保险柜', 'admin');




/*人员类型  2019-01-02*/
insert into dict_info(DICT_INFO_ID,  DICT_TYPE_CODE, DICT_TYPE_NAME, CREATE_PERSON) values(sys_guid(), 'PERSON_TYPE', '人员类型', 'admin');



insert into DICT_ITEM (DICT_ITEM_ID, DICT_INFO_ID, DICT_TYPE_CODE, DICT_CODE, DICT_NAME, DICT_DESC, CREATE_DATETIME, CREATE_PERSON)
values (sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'PERSON_TYPE'), 'PERSON_TYPE', '01', '嫌疑人', null, null, 'admin');

insert into DICT_ITEM (DICT_ITEM_ID, DICT_INFO_ID, DICT_TYPE_CODE, DICT_CODE, DICT_NAME, DICT_DESC, CREATE_DATETIME, CREATE_PERSON)
values (sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'PERSON_TYPE'), 'PERSON_TYPE', '02', '嫌疑人亲属', null, null, 'admin');

insert into DICT_ITEM (DICT_ITEM_ID, DICT_INFO_ID, DICT_TYPE_CODE, DICT_CODE, DICT_NAME, DICT_DESC, CREATE_DATETIME, CREATE_PERSON)
values (sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'PERSON_TYPE'), 'PERSON_TYPE', '03', '受害人', null, null, 'admin');

insert into DICT_ITEM (DICT_ITEM_ID, DICT_INFO_ID, DICT_TYPE_CODE, DICT_CODE, DICT_NAME, DICT_DESC, CREATE_DATETIME, CREATE_PERSON)
values (sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'PERSON_TYPE'), 'PERSON_TYPE', '04', '受害人亲属', null, null, 'admin');

insert into DICT_ITEM (DICT_ITEM_ID, DICT_INFO_ID, DICT_TYPE_CODE, DICT_CODE, DICT_NAME, DICT_DESC, CREATE_DATETIME, CREATE_PERSON)
values (sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'PERSON_TYPE'), 'PERSON_TYPE', '05', '失踪人员', null, null, 'admin');

insert into DICT_ITEM (DICT_ITEM_ID, DICT_INFO_ID, DICT_TYPE_CODE, DICT_CODE, DICT_NAME, DICT_DESC, CREATE_DATETIME, CREATE_PERSON)
values (sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'PERSON_TYPE'), 'PERSON_TYPE', '06', '失踪人员亲属', null, null, 'admin');

insert into DICT_ITEM (DICT_ITEM_ID, DICT_INFO_ID, DICT_TYPE_CODE, DICT_CODE, DICT_NAME, DICT_DESC, CREATE_DATETIME, CREATE_PERSON)
values (sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'PERSON_TYPE'), 'PERSON_TYPE', '99', '其它人员', null, null, 'admin');



/*性别*/
insert into dict_info(DICT_INFO_ID,  DICT_TYPE_CODE, DICT_TYPE_NAME, CREATE_PERSON) values(sys_guid(), 'GENDER', '性别', 'admin');


insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'GENDER'), '01', 'GENDER', '未知', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'GENDER'), '02', 'GENDER', '男', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'GENDER'), '03', 'GENDER', '女', 'admin');



/*检材类型 2019-01-02*/
insert into dict_info(DICT_INFO_ID,  DICT_TYPE_CODE, DICT_TYPE_NAME, CREATE_PERSON) values(sys_guid(), 'SAMPLE_TYPE', '检材类型', 'admin');

insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'SAMPLE_TYPE'), '01', 'SAMPLE_TYPE', '血液', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'SAMPLE_TYPE'), '02', 'SAMPLE_TYPE', '精斑', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'SAMPLE_TYPE'), '03', 'SAMPLE_TYPE', '脱落细胞', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'SAMPLE_TYPE'), '04', 'SAMPLE_TYPE', '唾液斑', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'SAMPLE_TYPE'), '05', 'SAMPLE_TYPE', '指甲', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'SAMPLE_TYPE'), '06', 'SAMPLE_TYPE', '牙齿', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'SAMPLE_TYPE'), '07', 'SAMPLE_TYPE', '骨骼', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'SAMPLE_TYPE'), '08', 'SAMPLE_TYPE', '组织', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'SAMPLE_TYPE'), '09', 'SAMPLE_TYPE', '毛发', 'admin');


/*包装方法*/
insert into dict_info(DICT_INFO_ID,  DICT_TYPE_CODE, DICT_TYPE_NAME, CREATE_PERSON) values(sys_guid(), 'PACK_METHOD', '包装方法', 'admin');


insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'PACK_METHOD'), '01', 'PACK_METHOD', '纸袋', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'PACK_METHOD'), '02', 'PACK_METHOD', '塑料袋', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'PACK_METHOD'), '03', 'PACK_METHOD', '塑料盒', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'PACK_METHOD'), '04', 'PACK_METHOD', '其他', 'admin');



/*案件级别*/
insert into dict_info(DICT_INFO_ID,  DICT_TYPE_CODE, DICT_TYPE_NAME, CREATE_PERSON) values(sys_guid(), 'CASE_LEVEL', '案件级别', 'admin');


insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'CASE_LEVEL'), '01', 'CASE_LEVEL', '严重暴力', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'CASE_LEVEL'), '02', 'CASE_LEVEL', '特大', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'CASE_LEVEL'), '03', 'CASE_LEVEL', '重大', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'CASE_LEVEL'), '04', 'CASE_LEVEL', '一般', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'CASE_LEVEL'), '05', 'CASE_LEVEL', '轻微', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'CASE_LEVEL'), '06', 'CASE_LEVEL', '其他', 'admin');




/*案件状态*/
insert into dict_info(DICT_INFO_ID,  DICT_TYPE_CODE, DICT_TYPE_NAME, CREATE_PERSON) values(sys_guid(), 'CASE_STATUS', '案件状态', 'admin');



insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'CASE_STATUS'), '01', 'CASE_STATUS', '未受理', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'CASE_STATUS'), '02', 'CASE_STATUS', '在检验', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'CASE_STATUS'), '03', 'CASE_STATUS', '已完成', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'CASE_STATUS'), '04', 'CASE_STATUS', '已退案', 'admin');




/*鉴定类别*/
insert into dict_info(DICT_INFO_ID,  DICT_TYPE_CODE, DICT_TYPE_NAME, CREATE_PERSON) values(sys_guid(), 'IDENTIFICATION_TYPE', '鉴定类别', 'admin');


insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'IDENTIFICATION_TYPE'), '01', 'IDENTIFICATION_TYPE', 'DNA鉴定', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'IDENTIFICATION_TYPE'), '02', 'IDENTIFICATION_TYPE', '毒化鉴定', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'IDENTIFICATION_TYPE'), '03', 'IDENTIFICATION_TYPE', '病理鉴定', 'admin');
insert into dict_item(DICT_ITEM_ID, DICT_INFO_ID, DICT_CODE, DICT_TYPE_CODE, DICT_NAME, CREATE_PERSON) values(sys_guid(), (select t.dict_info_id from dict_info t where t.dict_type_code = 'IDENTIFICATION_TYPE'), '04', 'IDENTIFICATION_TYPE', '临床鉴定', 'admin');

/**
PERSON_DETAIL 人员正面照片存储路径
 */
alter table PERSON_DETAIL add (PERSON_FRONT_PICTURE_PATH VARCHAR2(512) default '人员正面照片存储路径');

/**
LIMS_SAMPLE_INFO_DNA添加照片字段
 */
alter table LIMS_SAMPLE_INFO_DNA add (SAMPLE_DNA_PICTURE VARCHAR2(256) default '样本照片名称');
alter table LIMS_SAMPLE_INFO_DNA add (SAMPLE_DNA_PICTURE_PATH VARCHAR2(526) default '样本照片路径');



/**
LIMS_PEROSN_RELATION 添加删除相关字段
2019-01-03
 */
 alter table LIMS_PEROSN_RELATION add (DELETE_FLAG VARCHAR2(2) default '删除标记');
 alter table LIMS_PEROSN_RELATION add (DELETE_DATETIME TIMESTAMP(6) default '删除时间');
 alter table LIMS_PEROSN_RELATION add (DELETE_PERSON VARCHAR2(128) default '删除人');
 alter table LIMS_PEROSN_RELATION add (DELETE_REASON VARCHAR2(128) default '删除原因');


 /**
LIMS_CONSIGNMENT_INFO添加所属辖区code
 */
alter table LIMS_CONSIGNMENT_INFO add (AREA_ORG_CODE VARCHAR2(156) default '所属辖区code');


-- Create table
create table SERIAL_NUMBER
(
  id         VARCHAR2(64),
  type_code  VARCHAR2(64),
  type_value VARCHAR2(64),
  year       VARCHAR2(64)
)
tablespace ALIMS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
