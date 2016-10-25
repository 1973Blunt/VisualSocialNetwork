/**
 * Created by lmc on 2016/10/25.
 */
$(function () {
    $("#button").bind("click",
        function () {
            var name = $("#name").val();
            $.ajax({
                type: 'POST',
                data: {
                    'nickName': name
                },
                url: getWebRootPath() + "/getFollowedUsers",
                success: function (data) {
//                                debugger;
                    var obj = eval("(" + data + ")");
                    if (obj.userInfos == null) {
                        alert("查不到该用户");
                        return;
                    }
                    var graph = new Springy.Graph();
                    var label1 = graph.newNode({
                        label: name,
                        ondoubleclick: function () {
                            findDetail(name)
                        }
                    });
                    $.each(obj.userInfos, function (idx, userInfo) {
//                                    alert(userInfo.nickName+"  "+userInfo.wb_usr_id);
                        if (userInfo.nickName == null || userInfo.nickName == "") {
                            var label2 = graph.newNode({label: userInfo.wb_usr_id});
                        } else {
                            var label2 = graph.newNode({
                                label: userInfo.nickName,
                                ondoubleclick: function () {
                                    findDetail(userInfo.nickName)
                                }
                            });
                        }
                        graph.newEdge(label2, label1, {color: '#00A0B0'});
                    })
                    var springy = window.springy = jQuery('#springydemo').springy({
                        graph: graph,
                        nodeSelected: function (node) {
                            console.log('Node selected: ' + JSON.stringify(node.data));
                        }
                    });
                }

            });

        });
})

function findDetail(name) {
    // alert(name);
    $.ajax({
        type: 'POST',
        data: {
            'nickName': name
        },
        url: getWebRootPath() + "/getUserInfo",
        success: function (data) {
//                                debugger;
            var obj = eval("(" + data + ")");
            var str = "nickName:" + obj.nickName + "\n"
            +"id:" + obj.wb_usr_id + "\n"
            + "follows:" + obj.num_follows + "\n"
            + "province:" + obj.province + "\n"
            + "url:" + obj.url + "\n"
            + "gender:" + obj.gender + "\n"
            + "fans:" + obj.num_fans + "\n"
            + "tweets:" + obj.num_tweets + "\n"
            + "city:" + obj.city + "\n"
            + "birthday:" + obj.birthday + "\n"
            + "signature:" + obj.signature + "\n"
            + "birthday:" + obj.birthday + "\n"
            alert(str);
        }

    });
}

function getWebRootPath() {
    var webroot = document.location.href;
    webroot = webroot.substring(webroot.indexOf('//') + 2, webroot.length);
    webroot = webroot.substring(webroot.indexOf('/') + 1, webroot.length);
    webroot = webroot.substring(0, webroot.indexOf('/'));
    var rootpath = "/" + webroot;
    return rootpath;
}