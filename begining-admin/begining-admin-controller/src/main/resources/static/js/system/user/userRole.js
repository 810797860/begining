/**
 * Created by wupeng on 2017/9/24.
 */

var rolePid = '';
var rolePidMenu = [];
var roleIdMenu = [];
var $roleTree = $('#role-tree');
var $modifyFinishedBtn = $('#modify-finished-btn');
var $modifyCancelBtn = $('#modify-cancel-btn');
var zNodes = [];
var zTreeObj = {}

// 角色获取子节点的formId
var formId = 39;

$(function () {
    console.log(selectedRoleList);
    showRole(allRoleList, selectedRoleList);
    $modifyFinishedBtn.bind("click", modifyFinished);
    $modifyCancelBtn.bind("click", modifyCancel);
})

function modifyFinished() {
    var checkedNodes = zTreeObj.getCheckedNodes(true);
    var allCheckedNodes = [];
    $.each(checkedNodes, function (j, item) {
        // if(!item.getCheckStatus().half) {
        allCheckedNodes.push(item);
        // }
    });
    var checkedList = [];
    // for (var i = 0; i < zNodes.length; i++) {
    //     if (zNodes[i].checked) {
    //         checkedList.push(zNodes[i].id)
    //     }
    // }
    if (allCheckedNodes.length > 0) {
        for (var i = 0; i < allCheckedNodes.length; i++) {
            checkedList.push(allCheckedNodes[i].id)
        }
    }
    //发送数据
    var postData = checkedList;
    $.ajax({
        type: 'post',
        url: '/admin/userRole/create_update/' + userId,
        contentType: 'application/json;charset=utf-8',
        dataType: 'json',
        data: JSON.stringify(postData),
        success: function (res) {
            console.log(res)
            new PNotify({
                title: '角色配置',
                text: '角色配置成功',
                type: 'success',
                delay: 3000,
                addclass: "stack-bottomright"
            });
            closeFrame();
        }, error: function (XMLHttpRequest, textStatus, errorThrown) {
            new PNotify({
                title: '角色配置',
                text: '角色配置失败',
                type: 'error',
                delay: 3000,
                addclass: "stack-bottomright"
            });
        }
    });
}

function modifyCancel() {
    closeFrame();
}

function showRole(parentMenu, childMenu) {
    for (var i = 0; i < childMenu.length; i++) {
        for (var j = 0; j < parentMenu.length; j++) {
            if (parentMenu[j].id === childMenu[i].id) {
                parentMenu[j].checked = true;
                break;
            }
        }
    }
    showTreeTable(parentMenu)
}

// 显示数据源的树状结构
function showTreeTable(roleList) {
    for (var i = 0, iLen = roleList.length; i < iLen; i++) {
        var openFlag = true;
        if (roleList[i].open) {
            openFlag = roleList[i].open
        }
        var obj = {
            id: roleList[i].id,
            name: roleList[i].roleDesc,
            checked: roleList[i].checked || false,
            open: openFlag
        };
        if (roleList[i].pid) {
            obj.pId = roleList[i].pid
        }
        zNodes.push(obj);
    }
    setTreeViewContent(zNodes);
}

// 树状结构配置
function setTreeViewContent(zNodes) {
    //数据传回来的选中项
    console.log(zNodes)
    // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
    var setting = {
        view: {
            selectedMulti: false
        },
        check: {
            enable: true,
            chkboxType: {"Y": "", "N": "s"}
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            beforeCheck: beforeCheck,
            onCheck: onCheck
        }
    };
    if (parent.window.userObjRole) {
        var userObjRole = parent.window.userObjRole;
        console.log(userObjRole);
        for (var k = 0; k < userObjRole.length; k++) {
            if (parseInt(userObjRole[k].id) === parseInt(roleId) || parseInt(userObjRole[k].pid) === parseInt(rolePid)) {
                setting.check.enable = false;
            }
        }
    }
    zTreeObj = $.fn.zTree.init($roleTree, setting, zNodes);
}

var code, log, className = "dark";

function beforeCheck(treeId, treeNode) {
    className = (className === "dark" ? "" : "dark");
    showLog("[ " + getTime() + " beforeCheck ]&nbsp;&nbsp;&nbsp;&nbsp;" + treeNode.name);
    return (treeNode.doCheck !== false);
}

function onCheck(e, treeId, treeNode) {
    showLog("[ " + getTime() + " onCheck ]&nbsp;&nbsp;&nbsp;&nbsp;" + treeNode.name);
    console.log(treeNode)
    console.log(zNodes)
    for (var i = 0; i < zNodes.length; i++) {
        if (treeNode.id == zNodes[i].id) {
            zNodes[i].checked = treeNode.checked;
            continue;
        }
    }
    console.log(zNodes);
}

function showLog(str) {
    if (!log) log = $("#log");
    log.append("<li class='" + className + "'>" + str + "</li>");
    if (log.children("li").length > 6) {
        log.get(0).removeChild(log.children("li")[0]);
    }
}

function getTime() {
    var now = new Date(),
        h = now.getHours(),
        m = now.getMinutes(),
        s = now.getSeconds(),
        ms = now.getMilliseconds();
    return (h + ":" + m + ":" + s + " " + ms);
}

function checkNode(e) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
        type = e.data.type,
        nodes = zTree.getSelectedNodes();
    if (type.indexOf("All") < 0 && nodes.length == 0) {
        alert("请先选择一个节点");
    }

    if (type == "checkAllTrue") {
        zTree.checkAllNodes(true);
    } else if (type == "checkAllFalse") {
        zTree.checkAllNodes(false);
    } else {
        var callbackFlag = $("#callbackTrigger").attr("checked");
        for (var i = 0, l = nodes.length; i < l; i++) {
            if (type == "checkTrue") {
                zTree.checkNode(nodes[i], true, false, callbackFlag);
            } else if (type == "checkFalse") {
                zTree.checkNode(nodes[i], false, false, callbackFlag);
            } else if (type == "toggle") {
                zTree.checkNode(nodes[i], null, false, callbackFlag);
            } else if (type == "checkTruePS") {
                zTree.checkNode(nodes[i], true, true, callbackFlag);
            } else if (type == "checkFalsePS") {
                zTree.checkNode(nodes[i], false, true, callbackFlag);
            } else if (type == "togglePS") {
                zTree.checkNode(nodes[i], null, true, callbackFlag);
            }
        }
    }
}

function setAutoTrigger(e) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    zTree.setting.check.autoCheckTrigger = $("#autoCallbackTrigger").attr("checked");
    $("#autoCheckTriggerValue").html(zTree.setting.check.autoCheckTrigger ? "true" : "false");
}

// 关闭模态窗
function closeFrame() {
    if (parent.layer) {
        var layerIndex = parent.layer.getFrameIndex(window.name);
        parent.layer.close(layerIndex);
    }
}