<template>
    <div class="app-container">
        <div class="filter-container">
            <el-select v-model="listQuery.ruleType" placeholder="规则类型" clearable style="width: 90px;" class="filter-item">
                <el-option v-for="item in ruleTypeOptions" :key="item.value" :label="item.label" :value="item.value" />
            </el-select>
            <el-input v-model="listQuery.ruleName" placeholder="规则名称" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
            <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
                搜索
            </el-button>
            <el-button class="filter-item" type="primary" plain style="float: right;" icon="el-icon-plus" @click="handleNewRule">
                新建规则
            </el-button>
        </div>

        <el-table v-loading="listLoading" :data="list" border fit highlight-current-row style="width: 100%;" :row-style="{height:'50px'}">
            <el-table-column label="规则名称" prop="ruleName" align="center" min-width="20%">
                <template slot-scope="scope">
                    <span>{{ scope.row.ruleName }}</span>
                </template>
            </el-table-column>
            <el-table-column label="创建用户" prop="createUser" align="center" min-width="10%">
                <template slot-scope="scope">
                    <span>{{ scope.row.createUser }}</span>
                </template>
            </el-table-column>
            <el-table-column label="创建时间" prop="createTime" align="center" min-width="15%">
                <template slot-scope="scope">
                    <span>{{ scope.row.createTime | formDate }}</span>
                </template>
            </el-table-column>
            <el-table-column label="修改用户" prop="modifyUser" align="center" min-width="10%">
                <template slot-scope="scope">
                    <span>{{ scope.row.modifyUser }}</span>
                </template>
            </el-table-column>
            <el-table-column label="修改时间" prop="modifyTime" align="center" min-width="15%">
                <template slot-scope="scope">
                    <span>{{ scope.row.modifyTime | formDate }}</span>
                </template>
            </el-table-column>
            <el-table-column label="状态" prop="status" align="center" min-width="10%">
                <template slot-scope="scope">
                    <span>{{ scope.row.status | statusFilters }}</span>
                </template>
            </el-table-column>
            <el-table-column label="操作" align="center" min-width="20%" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button type="primary" plain size="mini" @click="handleInfo(scope.row)">
                        查看
                    </el-button>
                    <el-button type="info" plain size="mini" @click="handleUpdate(scope.row)">
                        编辑
                    </el-button>
                    <el-button type="danger" plain size="mini" @click="handleDelete(scope.row)">
                        删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
import sysEnum from "../../common/sysEnum"
import moment from 'moment'

export default {
    name: 'MappingRule',
    filters: {
        formDate(value) {
          if(value == null || value == ''){
            return null;
          }
          return moment(value).format('YYYY-MM-DD hh:mm:ss')
        },
        statusFilters(status) {
            const statusMap = {
                0: 'danger',
                1: 'success'
            }
            return statusMap[status]
        }
    },
    data() {
        return {
            ruleTypeOptions: sysEnum.dataTypes,
            listLoading: true,
            list: [],
            listQuery: {
                ruleType: undefined,
                ruleName: '',
                pageNo: 1,
                pageSize: 20,
                orderName: '',
                orderType: ''
            }
        }
    },
    created() {

    },
    methods: {
        handleFilter() {

        },
        handleNewRule() {

        },
        handleInfo(row) {

        },
        handleUpdate(row) {

        },
        handleDelete(row) {

        }
    }
}
</script>

<style>

</style>
