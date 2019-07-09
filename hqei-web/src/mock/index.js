const Mock = require('mockjs')

//格式：Mock.mock(url,post/get,返回的数据)
const Random = Mock.Random // Mock.Random 是一个工具类，用于生成各种随机数据

let result=[]

for(let i = 0; i < 10; i ++) { // 可自定义生成的个数
    let template = {
              "id": Random.natural(1, 10),
              "fileName": Random.character(),
              "originalDataType": Random.natural(1, 10),
              "uploadUser": Random.name(),
              "importUser": Random.name(),
              "status": Random.boolean,
          }
    result.push(template)
  }
let data = {
    "code": "0",
    "msg": null,
    "errorMap": null,
    "result": result,
    "totalCount": 30,
  }

let result2=[]
for(let i = 0; i < 3; i ++){
let template = {
              "propName":Random.name(),
              "placeholder": Random.name(),
              "propValue": "",
          }
result2.push(template)
}
let getSearchItems = {
    "code": "0",
    "msg": null,
    "errorMap": null,
    "result": result2,
    "totalCount": 0,
  }

  //标准数据文件
let result3=[]

for(let i = 0; i < 10; i ++) { // 可自定义生成的个数
    let template = {
              "auditedNum": Random.natural(1, 10),
              "dealUser": Random.character(),
              "dataType": Random.natural(1, 10),
              "num": Random.name(),
              "processRate": Random.name(),
              "processTotal": Random.boolean,
              "status": Random.natural(1, 10),
              "taskId": Random.natural(1, 100),
              "trueRate": Random.character(),
              "unAuditNum": Random.natural(1, 100),
              "unProcessNum": Random.natural(1, 100),
          }
    result3.push(template)
  }
let standardData = {
    "code": "0",
    "msg": null,
    "errorMap": null,
    "result": result3,
    "totalCount": 30,
  }  

//data
// Mock.mock('/api/original/query', 'post', require('./json/originalQuery'))
Mock.mock('/api/original/query', 'post', data)
Mock.mock('/api/original/getSearchItems','get',getSearchItems)

Mock.mock('/api/standardData/query','post',require('./json/task/standardTaskDetail/query'))
Mock.mock('/api/standardData/getSearchItems','get',getSearchItems)
Mock.mock('/api/standardData/getProcessStandardData?taskId=123456', 'get', require('./json/task/Task/getProcessStandardData'))
Mock.mock('/api/standardData/historyRecommend', 'post', require('./json/task/processStandardData/historyRecommend'))

Mock.mock('/api/role/listAll','get',require('./json/role/listAll'))

Mock.mock('/api/login/auth', 'post', require('./json/login/auth'))
Mock.mock('/api/login/getInfo', 'post', require('./json/login/getInfo'))
Mock.mock('/api/login/logout', 'post', require('./json/login/logout.json'))

//导入
Mock.mock('/api/importTask/query', 'post', require('./json/importTask/query'))
Mock.mock('/api/importTask/importingTask', 'post', require('./json/importTask/importingTask'))

//task
Mock.mock('/api/task/query', 'post', require('./json/task/Task/query'))

Mock.mock('/api/standardData/getAuditStandardData?taskId=123456', 'get', require('./json/task/AuditStandardData/getAuditStandardData'))

Mock.mock('/api/department/getProcessDept?organizationId=34995', 'get', require('./json/task/orgTask/getProcessDept'))
Mock.mock('/api/department/process', 'post', require('./json/task/deptTask/process'))
Mock.mock('/api/department/101', 'delete', require('./json/org/Department/delete'))
Mock.mock('/api/department/query', 'post', require('./json/org/Department/query'))

Mock.mock('/api/process/doBaiduSiteDept', 'post', require('./json/task/processStandardData/doBaiduSiteDept'))
Mock.mock('/api/process/doBaiduDept', 'post', require('./json/task/processStandardData/doBaiduDept'))
Mock.mock('/api/process/doWangFangDept', 'post', require('./json/task/processStandardData/doWangFangDept'))
Mock.mock('/api/process/doBaiduDeptHomePage', 'post', require('./json/task/deptTask/doBaiduDeptHomePage'))
Mock.mock('/api/process/doBaiduOrgHomePage', 'post', require('./json/task/orgTask/doBaiduOrgHomePage'))
Mock.mock('/api/standardData/secondOrgRecommend', 'post', require('./json/task/processStandardData/secondOrgRecommend'))

//org
Mock.mock('/api/organization/query', 'post', require('./json/org/Org/query'))
Mock.mock('/api/organization/100', 'delete', require('./json/org/Org/delete'))
Mock.mock('/api/organization/100', 'get', require('./json/org/Org/get'))
Mock.mock('/api/organization/process', 'post', require('./json/org/Org/process'))


Mock.mock('/api/operateLog/queryOperateLog', 'post', require('./json/operateLog/queryOperateLog'))

//user
Mock.mock('/api/role/listTable', 'get', require('./json/user/role/listTable'))
Mock.mock('/api/permission/listAllPermission', 'get', require('./json/user/role/listAllPermission'))