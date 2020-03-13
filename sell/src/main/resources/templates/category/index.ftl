<html>
<#include "../common/head.ftl">
<body>

<div id="wrapper" class="toggled">
    <#--边栏sidebar-->
    <#include "../common/nav.ftl">
    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-sm-12 column">
                    <#-- 一級类目 -->
                    <table id="category" class="table select-table table-striped">
                        <tbody style="text-align:right;">
                        <tr>
                            <input hidden id="categoryId" type="text" name="categoryId" value="${(productCategory.categoryId)!""}">
                            <td>一级类目名称：</td>
                            <td><input id="categoryName" name="categoryName" type="text" class="form-control"
                                       value="${(productCategory.categoryName)!""}"/></td>
                            <td>type：</td>
                            <td><input id="categoryType" name="categoryType" type="number" class="form-control"
                                       value="${(productCategory.categoryType)!""}"/></td>
                        </tr>
                        </tbody>
                    </table>

                    <#-- 二级类目 -->
                    <div class="table-box" style="margin: 20px;">
                        <div id="toolbar" style="margin-bottom: 20px">
                            <button id="insertRow" type="button" class="btn btn-default">新增</button>
                            <button id="getTableData"type="button"  class="btn btn-default" onclick="">保存</button>
                        </div>
                        <table id="table"></table>
                    </div>

                </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
<script type="text/javascript">
    var $category = $('#category');
    var $table = $('#table');
    var $insertRow = $('#insertRow');
    var $delRow = $('#delRow');
    var $getTableData = $('#getTableData');
    var datas=[];
    //初始化将测试集包含的用例存在数组里面
    <#if productCategorySecList??>
    <#list productCategorySecList as item>
    var item={
        "categorySecId":${item.categorySecId},
        "categorySecName":"${item.categorySecName}",
        "categorySecType":${item.categorySecType},
        "categoryIcon":"${item.categoryIcon}"
    };
    datas.push(item);
    <#--datas.push("${item}");-->
    </#list>
    <#else>
    []
    </#if>
    // console.log(datas);
    $(function() {
        /*新增*/
        $insertRow.click(function() {
            $table.bootstrapTable('insertRow', {
                index: 0,
                row: {
                    categorySecId: '',
                    categorySecName: '',
                    categorySecType: '',
                    categoryIcon: ''
                }
            });
        });
        /*删除*/
        $delRow.click(function() {
            $table.bootstrapTable('insertRow', {
                index: 0,
                row: {
                    categorySecId: '',
                    categorySecName: '',
                    categorySecType: '',
                    categoryIcon: ''
                }
            });
        });

        $table.bootstrapTable('destroy').bootstrapTable({
        // $table.bootstrapTable({
            //数据
            url:'',
            data: datas,
            toolbar: '#toolbar',
            clickEdit: true,
            showLoading: false,
            // showToggle: true,
            // showColumns: true,//展示栏
            pagination: true,//显示分页条
            // showPaginationSwitch: true,//显示切换分页按钮
            // showRefresh: true,      //显示刷新按钮
            // clickToSelect: true,  //点击row选中radio或CheckBox
            columns: [{
                checkbox: true
            }, {
                field: 'categorySecId',
                visible: false
            }, {
                field: 'categorySecName',
                title: '二级类目名称'
            }, {
                field: 'categorySecType',
                title: '二级type'
            }, {
                field: 'categoryIcon',
                title: '二级类目图片链接',
                width: '400px'
            }, ],
            /**
             * @param {点击列的 field 名称} field
             * @param {点击列的 value 值} value
             * @param {点击列的整行数据} row
             * @param {td 元素} $element
             */
            onClickCell: function(field, value, row, $element) {
                $element.attr('contenteditable', true);
                $element.blur(function() {
                    var index = $element.parent().data('index');
                    var tdValue = $element.html();
                    saveData(index, field, tdValue);
                })
            }
        });
        /*隐藏loading*/
        $table.bootstrapTable('hideLoading');
        $getTableData.click(function() {
            var form={
                "categoryId":document.getElementById("categoryId").value,
                "categoryName":document.getElementById("categoryName").value,
                "categoryType":document.getElementById("categoryType").value,
                "productCategorySecList":$table.bootstrapTable('getData')
            }
            // console.log(form);
            // console.log(JSON.stringify($table.bootstrapTable('getData')));
            $.ajax({
            url: '/sell/seller/category/save',
            type: 'POST',
            dataType: "json",
            data:JSON.stringify(form),
            contentType: "application/json",
            dataType:"application/json",
            success: function (result) {
                console.log(result);
                if(result.success){

                }
                else{

                }
                }
            });
        });

        function saveData(index, field, value) {
            $table.bootstrapTable('updateCell', {
                index: index,       //行索引
                field: field,       //列名
                value: value        //cell值
            })
        }

    });
</script>
</html>
