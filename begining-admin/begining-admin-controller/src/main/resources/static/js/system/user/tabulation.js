/**
 * Created by lqs on 2017/7/24.
 * 表单列表脚本
 */

// 关联展示类型
var SINGLE_CHOICE = 1011,
    MULTIPLE_CHOICE = 1012,
    MULTIPLE_TREE_CHOICE = 1013;

// 存储所选项
var selections = [];
// layer弹出层的下标
var layerIndex = 0;
// 关联选择对象
var relevanceObj = {
    id: null,
    name: null,
    fieldType: null
};

var $table = $('#table'),
    $remove = $('#remove'),
    $searchPart = $("#search-part"),
    $search = $('#search'),
    $toggleSearch = $('#toggle-search');

var formId = $('#formId').val();

var SEARCHING = false

var $addUserModal = $('#add-user-modal');

$(function () {
    // 消息弹出框弹出位置设置
    var stack_bottomright = {"dir1": "up", "dir2": "left", "firstpos1": 25, "firstpos2": 25};

    // 请求url
    // var url = '/report/reportList/' + reportId;
    // var urlParams = '';
    //
    // if(reportId !== 15) {
    //     // 拼接url中的参数
    //     for( var key in urlDatas) {
    //         if(urlParams === '') {
    //             urlParams += '?';
    //         }else {
    //             urlParams += '&';
    //         }
    //         urlParams += key + '=' + urlDatas[key];
    //     }
    //     // 拼接url
    //     url += urlParams;
    // } else {
    //     var tempFormId = $("#formId", window.parent.document).val();
    //     if(!urlDatas['target_id']) {
    //         // pass
    //     } else if(typeof tempFormId === 'undefined' || tempFormId === null) { // 父页面没有form id
    //         url += '?button_type=1517&target_id='+urlDatas['target_id'];
    //     }else {
    //         url += '?button_type=1518&target_id='+urlDatas['target_id'];
    //     }
    // }
    $('#add-user-btn').bind('click', addUser);
    initTable();
});

// 新增用户
function addUser() {
    if (!StringNoEmpty($('#user-name').val()) || !StringNoEmpty($('#user-account').val()) || !StringNoEmpty($('#user-password').val())) {
        layer.msg("请检查是否完整填写用户名、账号、密码", function () {
        });
        return;
    }
    var postData = {
        userName: $('#user-name').val() || null,
        account: $('#user-account').val() || null,
        password: $('#user-password').val() || null,
        phone: $('#user-phone').val() || null,
        email: $('#user-email').val() || null
    };
    $.ajax({
        type: 'post',
        url: '/admin/user/create_update',
        contentType: 'application/json;charset=utf-8',
        dataType: 'json',
        data: JSON.stringify(postData),
        success: function (res) {
            if (res.success === true) {
                refreshTable();
                $addUserModal.modal('hide');
                new PNotify({
                    title: '添加用户',
                    text: '添加用户成功！',
                    type: 'success',
                    delay: 3000,
                    addclass: "stack-bottomright",
                    stack: stack_bottomright
                });
            } else {
                new PNotify({
                    title: '添加用户',
                    text: '添加用户失败！' + res.message,
                    type: 'error',
                    delay: 3000,
                    addclass: "stack-bottomright",
                    stack: stack_bottomright
                });
            }
        }, error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert('添加用户失败，服务器内部错误');
        }
    });
}

// 删除用户
function deleteUser() {
    var selectIds = getSelection('id');
    if (selectIds.length === 0) {
        layer.msg("至少选择一项！", function () {
        });
        return;
    }
    if (confirm('你确定要删除这些吗？')) {
        var postData = [];
        for (var i = 0; i < selectIds.length; i++) {
            postData.push(selectIds[i].id);
        }
        console.log(selectIds);
        $.ajax({
            url: '/systemUser/batch_delete',
            data: JSON.stringify(postData),
            type: 'post',
            contentType: 'application/json;charset=utf-8',
            success: function (res) {
                new PNotify({
                    title: '删除用户',
                    text: '删除用户成功',
                    type: 'success',
                    delay: 3000,
                    addclass: "stack-bottomright",
                    stack: stack_bottomright
                });
                layer.closeAll();
                if (res.success === true) {
                    refreshTable();
                } else {
                }
            },
            error: function (error) {
                console.log("error");
            }
        });
    }
}

// // 密码加密
// function passwordEncrypt(password) {
//     return encrypt(password);
// }

function initTable() {
    $table.bootstrapTable({
        height: getHeight(),
        toolbar: "#toolbar",
        url: '/admin/user/query',
        method: 'post',
        responseHandler: responseHandler,
        queryParams: queryParams,
        striped: true,
        showRefresh: true,
        showColumns: true,
        showToggle: true,
        detailView: true,
        minimumCountColumns: 2,
        pagination: true,
        pageList: "[15, 20, 25, 50, 100]",
        pageNumber: 1,
        pageSize: 15,
        showFooter: false,
        sidePagination: "server"
    });
    // sometimes footer render error.
    setTimeout(function () {
        $table.bootstrapTable('resetView');
    }, 200);

    $table.on('expand-row.bs.table', function (e, index, row, $detail) {
        var html = [];
        $.each(row, function (key, value) {
            html.push('<p><b>' + key + ':</b> ' + value + '</p>');
        });
        return $detail.html(html);
    });
    $(window).resize(function () {
        $table.bootstrapTable('resetView', {
            height: getHeight()
        });
    });
    $toggleSearch.on('click', function (e) {
        e.stopPropagation();
        if ($searchPart.css('display') === 'none') {
            $toggleSearch.html('<i class="icon-angle-up"></i> 收起搜索');
        } else {
            $toggleSearch.html('<i class="icon-angle-down"></i> 展开搜索');
        }
        $searchPart.slideToggle(function () {
            $table.bootstrapTable('resetView', {
                height: getHeight()
            });
        });
        $search.toggle();
    });
    // checkbox 选中
    $(".checkbox-input").click(function (e) {
        e.stopPropagation();
        if ($(this).hasClass("selected")) {
            $(this).removeClass("selected");
            $(this).children('input[type="checkbox"]').first().prop('checked', false);
        } else {
            $(this).addClass("selected");
            $(this).children('input[type="checkbox"]').first().prop('checked', true);
        }
        searchInfo(true);
    });
    // 搜索
    $search.on('click', function (e) {
        e.stopPropagation();
        searchInfo(true);
    });
    // 搜索表单域中绑定的事件
    $("input.search-control").on('keydown', function (e) {
        e.stopPropagation();
        if (e.keyCode == "13") {
            searchInfo(true);
        }
    });
    $("select.search-control").on("change", function (e) {
        e.stopPropagation();
        searchInfo(true);
    })
}

// 刷新列表
function refreshTable() {
    $table.bootstrapTable('refresh');
}

// 数据返回处理
function responseHandler(res) {
    return {
        "total": res.recordsTotal,//总页数
        "rows": res.data   //数据
    };
}

// 搜索功能
function searchInfo(flag) {
    SEARCHING = !!flag
    if (SEARCHING) $('#table').bootstrapTable('refresh', {pageNumber: 1}); // 重置页码
    else $table.bootstrapTable(('refresh'));
}

//表格数据获取的参数
function queryParams(params) {
    var page = {};
    page.current = params.offset / params.limit + 1;
    page.size = params.limit;
    var sorts = [];
    var defaultSort = {};
    defaultSort.field = 'id';
    defaultSort.isAsc = false;
    sorts.push(defaultSort);
    var postData = {
        page: page,
        sorts: sorts
    };
    return postData;
}

// 获取bootstrap table高度
function getHeight() {
    var searchHeight = 0;
    if ($("#search-part").css('display') !== 'none') {
        searchHeight = $('#search-part').height();
    }
    return $(window).height() - 20 - searchHeight;
}

// 刷新列表并且弹出提示信息
function refreshAndShowMessage(options) {
    new PNotify(options);
    $('#table').bootstrapTable('refresh');
}

// 确定按钮控制
function confirmHandle() {
    var selectionsLen = selections.length;
    if (selectionsLen === 0) {
        layer.msg("未选择任何项！", function () {
        });
        return;
    }
    switch (parseInt(relevanceObj['fieldType'])) { // 转换为数字
        case SINGLE_CHOICE:
            if (selectionsLen > 1) {
                layer.msg("只能选择一项！", function () {
                });
                return;
            }
            break;
        case MULTIPLE_CHOICE:
        case MULTIPLE_TREE_CHOICE:
            break;
    }
    if (parent.window.handleRelevance) {
        parent.window.handleRelevance(selections, relevanceObj.selector);
        layerIndex = parent.layer.getFrameIndex(window.name);
        parent.layer.close(layerIndex);
    }
}

// 撤销控制操作
function repealHandle() {
    if (parent.layer) {
        parent.window.handleRelevance([], relevanceObj.selector);
        layerIndex = parent.layer.getFrameIndex(window.name);
        parent.layer.close(layerIndex);
    }
}

// 关联获取的值
function setRelevanceObj(id, name, fieldType, selector) {
    relevanceObj['id'] = id;
    relevanceObj['name'] = name;
    relevanceObj['fieldType'] = fieldType;
    relevanceObj['selector'] = selector;
}

// 关联选择框的打开
function openRelativeDialog(event) {
    var target = $(event.currentTarget);
    var url = target.attr('data-url');
    var bindField = target.attr('data-bind-field');
    var fieldType = target.attr('data-field-type');
    var id = target.attr('data-id');
    var name = target.attr('data-title');
    var selector = target.attr('data-name');
    // 处理url问题
    if (url === 'undefined') {
        var tempVal = $('[name="' + bindField + '"]').val();
        url = formFieldsData[selector]['reference_link_' + tempVal];
    }
    var title = '';
    switch (parseInt(fieldType)) {
        case SINGLE_CHOICE:
            title = '关联单项选择';
            break;
        case MULTIPLE_CHOICE:
            title = '关联多项选择';
            break;
        case MULTIPLE_TREE_CHOICE:
            title = '关联多项（树形）选择'
            break;
        default:
            title = '关联选择'
    }
    var index = layer.open({
        type: 2,
        content: url,
        area: ['1000px', '600px'],
        maxmin: true,
        shadeClose: true,
        title: title,
        success: function (layero, index) {
            //得到iframe页的窗口对象
            var iframeWin = window[layero.find('iframe')[0]['name']];
            // 执行iframe页的方法：
            iframeWin.setRelevanceObj(id, name, fieldType, selector);
        }
    });
    layer.full(index);
}

// 返回关联选择后的值
function handleRelevance(data, selector) {
    var tempName = '';
    var tempId = '';
    for (var i = 0, len = data.length; i < len; i++) {
        if (i > 0) { // 如果不是第一个
            tempName += ',';
            tempId += ',';
        }
        tempName += data[i].name;
        tempId += data[i].id;
    }
    $('input[name=' + selector + ']').val(tempId).next().val(tempName);
    // 调用搜索功能
    searchInfo();
}

// 触发关联选择的打开
function triggerOpenRelativeDialog(event) {
    if (document.all) {
        $(event.currentTarget).next().get(0).click();
    } else {
        var e = document.createEvent("MouseEvents");
        e.initEvent("click", true, true);
        $(event.currentTarget).next().get(0).dispatchEvent(e);
    }
}

// 报表勾选获取信息通用接口  -- 接收参数类型，空值、字符串、字符串数组
function getSelectionMessage(option) {
    if (typeof option === 'undefined') {
        return [];
    } else if (typeof option === 'string') {
        return $.map($table.bootstrapTable('getSelections'), function (row) {
            var obj = {};
            var tempName = row[option] + '';
            if (tempName.indexOf('<a') !== -1) {
                tempName = $(tempName).text();
            }
            obj[option] = tempName;
            return obj;
        });
    } else {
        return $.map($table.bootstrapTable('getSelections'), function (row) {
            var obj = {};
            for (var i = 0, len = option.length; i < len; i++) {
                var tempName = row[option[i]] + '';
                if (tempName.indexOf('<a') !== -1) {
                    tempName = $(tempName).text();
                }
                obj[option[i]] = tempName;
            }
            return obj;
        });
    }
}

// 消息框位置控制
var stack_bottomright = {"dir1": "up", "dir2": "left", "firstpos1": 25, "firstpos2": 25};

/**
 * 获取选中项
 * @param  可以是：空值、字符串、字符串数组
 */
function getSelection(option) {
    if (typeof option === 'undefined') {
        return [];
    } else if (typeof option === 'string') {
        return $.map($table.bootstrapTable('getSelections'), function (row) {
            var obj = {};
            var tempName = row[option] + '';
            if (tempName.indexOf('<a') !== -1) {
                tempName = $(tempName).text();
            }
            obj[option] = tempName;
            return obj;
        });
    } else {
        return $.map($table.bootstrapTable('getSelections'), function (row) {
            var obj = {};
            for (var i = 0, len = option.length; i < len; i++) {
                var tempName = row[option[i]] + '';
                if (tempName.indexOf('<a') !== -1) {
                    tempName = $(tempName).text();
                }
                obj[option[i]] = tempName;
            }
            return obj;
        });
    }
}

//分配角色
function setUserRole() {
    var selectedIds = getSelection('id');
    if (selectedIds.length === 0) {
        layer.msg("请选择一项！", function () {
        });
    } else if (selectedIds.length === 1) {
        var url = '/admin/userRole/' + selectedIds[0].id + '/create.html';
        var title = '分配角色';
        layer.open({
            type: 2,
            content: url,
            area: ['80%', '80%'],
            maxmin: true,
            shadeClose: true,
            title: title
        });
    } else if (selectedIds.length > 1) {
        layer.msg("只能选择一项！", function () {
        });
    }
}

// 关闭模态窗
function closeFrame() {
    if (parent.layer) {
        var layerIndex = parent.layer.getFrameIndex(window.name);
        parent.layer.close(layerIndex);
    }
}

function StringNoEmpty(str) {
    if (str != null && str != "" && str != undefined) {
        return true;
    } else return false;
}