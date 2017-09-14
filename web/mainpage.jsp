<%--
  Created by IntelliJ IDEA.
  User: Bounc
  Date: 03.08.2017
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.1/jquery.min.js"></script>
<link rel="stylesheet" href="dist/themes/default/style.min.css"/>
<link rel="stylesheet"
      href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css"/>
<link rel="stylesheet"
      href="/resources/css/mainpage.css"/>
<script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>

<!-- JS Tree scripts-->
<script src="dist/jstree.min.js"></script>


<!-- Main page scripts-->
<script src="resources/js/mainpage.js"></script>



<!--script type="text/javascript">


    $(document).ready(function () {
        $('#jstree').jstree({
            "plugins": ["sort", "themes", "json_data", "dnd", "contextmenu"],

            'core': {
                'check_callback': true,
                "themes" : {
                    "variant" : "large"
                },
                'data': {
                    'url': function (node) {
                        return node.id === '#' ? '/tree/?id=%23' : '/tree/?id=' + node.id;
                    },
                }

            },
            'contextmenu': {
                'items': contextmenu
            }
        });

        function contextmenu(node) {

            var tree = $('#jstree').jstree(true);

            var items = {
                "Create": {
                    "separator_before": false,
                    "separator_after": true,
                    "label": "Create",
                    "action": function (obj) {
                        let name = window.prompt("Input new node name", "");
                        if(name !== null && name !== ""){
                            $.ajax({
                                type: "GET",
                                url: "${pageContext.request.contextPath}/check_name",
                                data: {
                                    "name": name,
                                    "parent": node.id
                                },
                                success : function(resp)
                                {
                                    if(resp === true){
                                        tree.refresh_node(node);
                                    }
                                }
                            });
                        }
                    }
                },
                "Rename": {
                    "separator_before": false,
                    "separator_after": false,
                    "label": "Rename",
                    "action": function (obj) {
                        tree.edit(node, node.text, function (node) {
                            $.ajax({
                                type: "GET",
                                url: "${pageContext.request.contextPath}/update",
                                data: {
                                    "id": node.id,
                                    "name": node.text
                                },
                            });
                            tree.refresh_node(tree.get_parent(node));
                        });

                    }
                },

                "Remove": {
                    "separator_before": true,
                    "separator_after": false,
                    "label": "Remove",
                    "action": function (obj) {
                        $.ajax({
                            type: "GET",
                            url: "${pageContext.request.contextPath}/delete",
                            data: {
                                "id": node.id
                            },
                            success: function (data) {
                                if (data == true) {
                                    tree.refresh_node(tree.get_parent(node));
                                }
                            }
                        });
                    }
                }
            };
            return items;
        }

        $('#jstree').on('after_close.jstree', function (event, data) {
            // Flag it to be reloaded on reopen:
            data.node.state.loaded = false;
        });
    });

    function updateTree() {
        var tree = $('#jstree').jstree(true);
        tree.refresh();
    };


</script-->
<head>
    <title>Tree browser</title>
</head>
<body>
<div class="container">
    <div class="row top-buffer">
        <div id="jstree">
            <!-- Tree -->
            <ul>
                <li id="1">Root node 1</li>
            </ul>
        </div>
    </div>
    <div class="row top-buffer">
        <button type="button" onclick="updateTree()" class="btn btn-primary"> Sync &#8635;</button>
    </div>
    <!--div class="row top-buffer">
        <a href="https://www.jstree.com/docs/json/">jsTree docs</a>
    </div-->
</div>
</body>
</html>
