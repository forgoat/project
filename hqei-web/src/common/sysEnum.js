import { Enum } from "./Enum";

export default {
  dataTypes: new Enum()
    .add("ORG", "机构", 0)
    .add("TALENT", "人才", 1)
    .add("TEAM", "团队", 2)
    // .add('INSTITUTION_JOURNAL_SERVE', '学会期刊任职', 3)
    .add("PROJECT", "项目", 4)
    // .add('PLATFORM', '平台', 5)
    // .add('PAPER', '论文', 6)
    .add("CPRS_TRANSFORM", "专利及其转化", 7),
  // .add('STANDARD_AND_GUIDELINES', '标准与指南', 8)
  // .add('AWARD', '奖项', 9)
  // .add('ACADEMIC_MISCONDUCT', '学术不端', 10)
  processTaskStatusList: new Enum()
    .add("NOT_STARTED", "未开始", 0)
    .add("PROCESSING", "加工中", 1)
    .add("PROCESS_FINISH", "加工完成", 2)
    .add("AUDIT_FINISH", "审核完成", 3)
    .add("CLOSE", "关闭", 4),
  processStatusList: new Enum()
    .add("UN_PROCESS", "未加工", 0)
    .add("SEARCHED", "已检索", 1)
    .add("PROCESSED", "已加工", 2)
    .add("AUDITED", "已审核", 3),
  originalDataTypes: new Enum()
    .add("ESI", "ESI高被引作者", 1)
    .add("NATIONAL_SCIENCE_MAJOR_PROJECTS", "国家科技重大专项", 2)
    .add("NSFC", "国自然", 4)
    .add("CPRS_INVENTOR_AUTH", "中文核心期刊论文", 14)
    .add("ACADEMICIAN", "院士", 16)
    .add("MOST_LEADER_TALENT", "科技创新领军人才", 17)
    .add("CHEUNG_KONG_SCHOLARS", "长江学者", 18)
    .add("THOUSAND_TALENTS_PLAN", "千人计划", 19)
    .add("NSFC_EXCELLENT_YOUTH_TEAM", "杰青优青创新群体", 20)
    .add("CPRS_TRANSFORM", "专利转化", 31)
    .add("YOUTH_TALENT", "青年拔尖人才", 32),

  importRules: new Enum()
    .add("rule1", "规则1", 1)
    .add("rule2", "规则2", 2)
    .add("rule3", "规则3", 3)
    .add("rule4", "规则4", 4)
    .add("rule5", "规则5", 5),

  importTaskStatus: new Enum()
    .add("IMPORTING", "导入中", 0)
    .add("IMPORTED", "导入完成", 1)
    .add("STANDARDING", "规范中", 2)
    .add("STANDARDED", "规范完成", 3),

  auditModifyTypes: new Enum()
    .add("UN_MODIFY", "未修正", 0)
    .add("MODIFY", "已修正", 1),

  standardDataTypes: new Enum()
    .add("TALENT", "人才", 1)
    .add("TEAM", "团队", 2)
    .add("INSTITUTION_JOURNAL_SERVE", "学会期刊任职", 3)
    .add("PROJECT", "项目", 4)
    .add("PLATFORM", "平台", 5)
    .add("PAPER", "论文", 6)
    .add("CPRS_TRANSFORM", "专利及其转化", 7)
    .add("STANDARD_AND_GUIDELINES", "标准与指南", 8)
    .add("AWARD", "奖项", 9)
    .add("ACADEMIC_MISCONDUCT", "学术不端", 10),

  logTypes: new Enum()
    .add("创建任务", "创建任务", 1)
    .add("加工", "加工", 2)
    .add("审核", "审核", 3)
    .add("关闭任务", "关闭任务", 4),

  quotaLevels: new Enum()
    .add("FIRST_LEVEL", "一级指标", 1)
    .add("THIRD_LEVEL", "三级指标", 3)
    .add("MINIMUN_LEVEL", "最低指标", 4)
};
