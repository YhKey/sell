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
                    <form role="form" method="post" action="/sell/seller/product/save">
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label>商品名称</label>
                                <input name="productName" type="text" class="form-control" value="${(productInfo.productName)!""}"/>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label>商品价格</label>
                                <input name="productPrice" type="text" class="form-control" value="${(productInfo.productPrice)!""}"/>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label>商品库存</label>
                                <input name="productStock" type="number" class="form-control" value="${(productInfo.productStock)!""}"/>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label>商品描述</label>
                                <input name="productDescription" type="text" class="form-control" value="${(productInfo.productDescription)!""}"/>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label>一级类目</label>
                                <select id="categoryType" name="categoryType" class="form-control" onchange="changeSecCategory()">
                                    <#list categoryList as category>
                                        <option value="${category.categoryType}"
                                                <#if (productInfo.categoryType)?? && productInfo.categoryType == category.categoryType >
                                                selected
                                                </#if>
                                                >${category.categoryName}
                                        </option>
                                    </#list>
                                </select>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label>二级类目</label>
                                <select id="categorySecType" name="categorySecType" class="form-control">
                                    <#--<#list categoryList as category>
                                        <option value="${category.categoryType}"
                                                <#if (productInfo.categoryType)?? && productInfo.categoryType == category.categoryType >
                                                    selected
                                                </#if>
                                        >${category.categoryName}
                                        </option>
                                    </#list>-->
                                </select>
                            </div>
                        </div>
                        <div class="col-sm-12">
                            <div class="form-group">
                                <label>图片</label><br>
                                <img src="${(productInfo.productIcon)!""}">
                                <input name="productIcon" type="text" class="form-control" value="${(productInfo.productIcon)!""}"/>
                            </div>
                        </div>
                        <#--隐藏字段productId-->
                        <input hidden type="text" name="productId" value="${(productInfo.productId)!""}">
                        <div class="col-sm-4">
                        <button type="submit" class="btn btn-default">提交</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>

<script>
    function changeSecCategory() {
        var target1 = document.getElementById("categoryType");
        var target2 = document.getElementById("categorySecType");
        //得到第一个下拉框的内容
        var selected_initial = target1.options[target1.selectedIndex].value;
        var categoryList=[];
        <#if categoryList??>
        <#list categoryList as category>
        var categoryVO={};
        categoryVO.categoryId= "${category.categoryId}";
        categoryVO.categoryName= "${category.categoryName}";
        categoryVO.categoryType= "${category.categoryType}";
            <#if category.productCategorySecList??>
            var productCategorySecList=[];
            <#list category.productCategorySecList as categorySec>
            var categorySec={};
            categorySec.categorySecId="${categorySec.categorySecId}";
            categorySec.categoryId="${categorySec.categoryId}";
            categorySec.categorySecName="${categorySec.categorySecName}";
            categorySec.categorySecType="${categorySec.categorySecType}";
            productCategorySecList.push(categorySec);
            </#list>
            </#if>
        categoryVO.productCategorySecList= productCategorySecList;
        categoryList.push(categoryVO);
        </#list>
        </#if>
        for (var i in categoryList) {
            if (selected_initial==categoryList[i].categoryType){
                var initial_list = categoryList[i].productCategorySecList;
                if (initial_list.length!=0) {
                    for (var i in initial_list) {
                        var item = new Option(initial_list[i].categorySecName, initial_list[i].categorySecType);
                        <#if (productInfo.categorySecType)??>
                        if ("${productInfo.categorySecType}"==initial_list[i].categorySecType) {
                            item.selected=true;
                        }
                        </#if>
                        //将列表中的内容加入到第二个下拉框
                        target2.options.add(item);
                    }
                }else {
                    while (target2.options.length){
                        target2.options.remove(0);
                    }
                }
            }
        }
    }

    window.onload=function () { changeSecCategory(); };
</script>
</html>
